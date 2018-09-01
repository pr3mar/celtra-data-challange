package com.celtra.challange.data.pr3mar.services;

import com.celtra.challange.data.pr3mar.dao.*;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.ImpressionDTO;
import com.celtra.challange.data.pr3mar.models.entity.AdEntity;
import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;
import com.celtra.challange.data.pr3mar.models.entity.ImpressionEntity;
import com.celtra.challange.data.pr3mar.models.entity.UserEntity;
import com.celtra.challange.data.pr3mar.transformers.ImpressionTransformer;
import com.celtra.challange.data.pr3mar.utils.Pair;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.enums.LogLevel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RequestScoped
@Transactional
public class GenerateDataService {

    private static final Logger log = LogManager.getLogger(GenerateDataService.class.getName());

    private static final long MAX_CAMPAIGNS = 1000;
    private static final long MAX_ADS = 1000;
    private static final long MAX_USERS = 10000000;
    private static final long MAX_IMPRESSIONS_PER_AD = 10;

    @Inject
    private AdDAO adDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private CampaignDAO campaignDAO;

    @Inject
    private ImpressionDAO impressionDAO;

    @Inject
    private InteractionTypeDAO interactionTypeDAO;

    @Inject
    private ImpressionTransformer impressionTransformer;

    @Transactional
    public List<ImpressionDTO> generateNewData(
            Date inputFrom,
            Date inputTo,
            Long numCampaigns,
            Long numAds,
            Long numImpressions,
            Long numUsers
    ) throws InvalidEntityException, InvalidParameterException,
             EntityNotFoundException, InternalServerExecption
    {
        long startTime = System.nanoTime();
        // validate the dates
        Pair<Date, Date> dates = getDates(inputFrom, inputTo);
        Date dateFrom = dates.getLeft();
        Date dateTo = dates.getRight();

        // validate the numbers
        // this generates at least 10 new objects of each type (campaign, ad, user, impression)
        long numberOfCampaigns = getValue(numCampaigns);
        numberOfCampaigns = numberOfCampaigns > MAX_CAMPAIGNS ? MAX_CAMPAIGNS : numCampaigns;

        long numberOfAds = getValue(numAds);
        numberOfAds = numberOfAds > MAX_ADS ? MAX_ADS : numberOfAds;

        long numberOfUsers = getValue(numUsers);
        numberOfUsers = numberOfUsers > MAX_USERS ? MAX_USERS : numberOfUsers;

        long numberOfImpressions = getValue(numImpressions);
        numberOfImpressions = numberOfImpressions > 10 * numberOfUsers * numberOfAds ? numberOfUsers * numberOfAds : numberOfImpressions;

        // Check if there is sufficient amount of users, if not - create new users to match the requirement
        long userCount = userDAO.countAll();
        long toCreate = numberOfUsers - userCount;

        if(toCreate > 0) {
            log.info("Database does not have sufficient amount of users. Creating...");
            createNewUsers(toCreate);
        } else {
            log.info("Database has sufficient amount of users. Continuing...");
        }

        List<Long> userIds = userDAO.getIds();
        log.log(LogLevel.DEBUG, "Ids of users in system: " + userIds);

        // Create ${numberOfCampaigns} new campaigns
        List<CampaignEntity> campaignIds = createNewCampaigns(numberOfCampaigns, dateFrom, dateTo);

        // Create ${numberOfAds} new ads
        List<AdEntity> ads = createNewAds(numberOfAds, campaignIds);

        List<Integer> interactionTypes = interactionTypeDAO.getIds();

        // Create ${numberOfImpressions} new impressions for random campaign, ad, and user
        // This part also determines whether there will be an interaction or not.
        List<ImpressionEntity> impressions = createNewImpressions(
                numberOfImpressions,
                campaignIds,
                ads,
                userIds,
                interactionTypes,
                dateFrom,
                dateTo
        );
        long endTime = System.nanoTime();
        log.info("Data generation completed in: " + (endTime - startTime)/(1000000.0 * 1000) + "s");
        return impressionTransformer.transformToDTO(impressions);
    }

    private Long getValue(Long val) {
        if(val == null || val <= 0) {
            return 10L;
        }
        return val;
    }

    private Pair<Date, Date> getDates(Date from, Date to) {
        Date dateFrom;
        Date dateTo;
        if(to == null) {
            dateTo = new Date(); // now
        } else {
            dateTo = new Date(to.getTime());
        }
        if(from == null) {
            dateFrom = new Date(
                    dateTo.getTime() - (7 * 24 * 60 * 60 * 1000) // one (1) week prior to now
            );
        } else {
            dateFrom = new Date(from.getTime());
        }
        if(dateTo.before(dateFrom)) {
            // if the `to` date is before the from date, swap them
            Date buffer = new Date(dateTo.getTime());
            dateTo = new Date(dateFrom.getTime());
            dateFrom = new Date(buffer.getTime());
        }
        long timeDiff = dateTo.getTime() - dateFrom.getTime();
        if (timeDiff < 24 * 60 * 60 * 1000) {
            // if the time frame is shorter than one (1) day,
            // then expand the from date for one (1) day before the current value
            dateFrom = new Date(dateFrom.getTime() - 24 * 60 * 60 * 1000);
        }
        return new Pair<>(dateFrom, dateTo);
    }

    private void createNewUsers(long amount)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        log.info("Creating " + amount + " users.");
        long id = userDAO.getLatestId() + 1;
        for(int i = 0; i < amount; i++, id++) {
            UserEntity entity = new UserEntity(
                    "u" + id + "@mail.com"
            );
            entity = userDAO.createNew(entity);
            log.log(LogLevel.DEBUG, entity.toString());
        }
    }

    private List<CampaignEntity> createNewCampaigns(
            long amount,
            Date from,
            Date to
    )
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        log.info("Creating " + amount + " campaigns.");
        long id = campaignDAO.getLatestId() + 1;
        long timeDiff = to.getTime() - from.getTime();
        List<CampaignEntity> entities = new ArrayList<>();
        for(int i = 0; i < amount; i++, id++) {
            CampaignEntity entity;
            float prob = ThreadLocalRandom.current().nextFloat();
            if(prob < 0.30) {
                 entity = new CampaignEntity(
                        "campaign" + id,
                         new Date(from.getTime() + ThreadLocalRandom.current().nextLong(timeDiff / 4)),
                         new Date(to.getTime() - ThreadLocalRandom.current().nextLong(timeDiff / 4))
                );
            } else if (prob < 0.6) {
                entity = new CampaignEntity(
                        "campaign" + id,
                        new Date(from.getTime() + ThreadLocalRandom.current().nextLong(timeDiff / 4)),
                        null
                );
            } else {
                entity = new CampaignEntity(
                        "campaign" + id
                );
            }
            entity = campaignDAO.createNew(entity);
            entities.add(entity);
            log.log(LogLevel.DEBUG, entity.toString());
        }
        return entities;
    }

    private List<AdEntity> createNewAds(
            long amount,
            List<CampaignEntity> campaigns
    )
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        log.info("Creating " + amount + " ads.");
        long id = adDAO.getLatestId() + 1;
        List<AdEntity> entities = new ArrayList<>();
        for(int i = 0; i < amount; i++, id++) {
            AdEntity entity = new AdEntity(
                    "ad" + id,
                    campaigns.get(ThreadLocalRandom.current().nextInt(campaigns.size())).getId() // select a campaign ID at random
            );
            entity = adDAO.createNew(entity);
            entities.add(entity);
            log.log(LogLevel.DEBUG, entity.toString());
        }
        return entities;
    }

    @Transactional
    private List<ImpressionEntity> createNewImpressions(
            long amount,
            List<CampaignEntity> campaigns,
            List<AdEntity> ads,
            List<Long> userIds,
            List<Integer> interactionTypeIds,
            Date from,
            Date to
    ) throws  InternalServerExecption, InvalidEntityException,
                InvalidParameterException, EntityNotFoundException
    {
        log.info("Creating " + amount + " impressions.");
        Random rand = new Random();
        List<ImpressionEntity> entities = new ArrayList<>();
        Map<AdEntity, CampaignEntity> adCampaign = new HashMap<>();
        Map<Long, Integer> userImpression = new HashMap<>();
        Map<String, Integer> userAd = new HashMap<>();
        for(int i = 0; i < amount; i++) {
            AdEntity ad = ads.get(ThreadLocalRandom.current().nextInt(ads.size()));
            CampaignEntity campaign;
            if(adCampaign.containsKey(ad)) {
                campaign = adCampaign.get(ad);
            } else {
                adCampaign.put(ad, findCampaign(campaigns, ad.getCampaignId()));
                campaign = adCampaign.get(ad);
            }
//            Long userId = userIds.get(rand.nextInt(userIds.size()));
//            Long userId = chooseUserImpression(userIds, userAd);
            Long userId = chooseUserAd(ad, userIds, userAd);
            if(userId == null) {
                return entities;
            }
            if(campaign == null) {
                log.info("WTF?");
            }
            Integer interaction = chooseInteraction(interactionTypeIds);
            ImpressionEntity entity = new ImpressionEntity(
                    campaign.getId(),
                    ad.getId(),
                    userId, // TODO: handle max 10 impressions per user!
                    interaction,
                    chooseImpressionDate(campaign, from, to)
            );
            entity = impressionDAO.createNew(entity);
            entities.add(entity);
            log.log(LogLevel.DEBUG, entity.toString());
        }

        return entities;
    }

    private CampaignEntity findCampaign(
            List<CampaignEntity> campaigns,
            long id
    ) throws InternalServerExecption {
        for(CampaignEntity campaign : campaigns) {
            if(campaign.getId() == id) {
                return campaign;
            }
        }
        throw new InternalServerExecption("Bad list of campaigns/ads");
    }

    private Date chooseImpressionDate(
            CampaignEntity campaign,
            Date from,
            Date to
    ) {
        Date dateFrom = campaign.getDateStarted() == null ? from : campaign.getDateStarted();
        Date dateTo = campaign.getDateEnded() == null ? to : campaign.getDateEnded();
        if(dateFrom == null || dateTo == null) {
            log.info("WTF???");
        }
        return new Date(
                ThreadLocalRandom.current().nextLong(dateFrom.getTime(), dateTo.getTime())
        );
    }

    private Integer chooseInteraction(
            List<Integer> ids
    ){
        float prob = ThreadLocalRandom.current().nextFloat();
        if(prob < 0.85) {
            return null;
        }
        return ids.get(ThreadLocalRandom.current().nextInt((int) ids.size()));
    }

    private Long chooseUserAd(
            AdEntity ad,
            List<Long> ids,
            Map<String, Integer> userAd
    ) {
        long id;
        String key;
        int count = 0;
        do {
            count++;
            id = ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
            key = ad.getId() + "," + id;
            if(userAd.containsKey(key)) {
                int value = userAd.get(key);
                if(value < MAX_IMPRESSIONS_PER_AD) {
                    userAd.put(key, value + 1);
                    return id;
                }
            } else {
                userAd.put(key, 1);
                return id;
            }
            if(count % 1000 == 0) {
                boolean all = true;
                for(String currentKey : userAd.keySet()) {
                    if(userAd.get(currentKey) < MAX_IMPRESSIONS_PER_AD) {
                        all = false;
                    }
                }
                if(all) {
                    return null;
                }
            }
        } while (true);
    }

    private Long chooseUserImpression(
            List<Long> ids,
            Map<Long, Integer> userImpression
    ) {
        long id;
        int count = 0;
        do {
            count++;
            id = ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
            if(userImpression.containsKey(id)) {
                int value = userImpression.get(id);
                if(value < MAX_IMPRESSIONS_PER_AD) {
                    userImpression.put(id, value + 1);
                    return id;
                }
            } else {
                userImpression.put(id, 1);
                return id;
            }
            if(count % 1000 == 0) {
                boolean all = true;
                for(Long key : userImpression.keySet()) {
                    if(userImpression.get(key) < MAX_IMPRESSIONS_PER_AD) {
                        all = false;
                    }
                }
                if(all) {
                    return null;
                }
            }
        } while (true);
    }
}
