package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.AdEntity;
import com.celtra.challange.data.pr3mar.models.reports.AdSummary;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@RequestScoped
public class AdDAO extends GenericDAO<AdEntity, Long> {

    public AdDAO() {
        super(AdEntity.class);
    }

    @Override
    public List<AdEntity> findAll() {
        return em.createNamedQuery("Ad.findAll", AdEntity.class)
                .getResultList();
    }

    public long countAll() {
        return em.createNamedQuery("Ad.countAll", Long.class)
                .getSingleResult();
    }

    public Long getLatestId() {
        try {
            return em.createNamedQuery("Ad.getLastId", Long.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return -1L;
        }
    }

    public Long getLastNIds(int max) {
        return em.createNamedQuery("Ad.getIds", Long.class)
                .setMaxResults(max)
                .getSingleResult();
    }

    public List<AdSummary> getAdSummary(Date from, Date to) {
        Query query = em.createNativeQuery(
                "SELECT res1.AdId, " +
                        "       AdName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT AdId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      WHERE DateOccurred BETWEEN :dateFrom AND :dateTo " +
                        "      GROUP BY AdId) res1 " +
                        "       INNER JOIN Ad ON res1.AdId = ID" +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT AdId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.AdId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.AdId = res1.AdId " +
                        "ORDER BY impressions DESC",
                "AdSummary"
        )
                .setParameter("dateFrom", from)
                .setParameter("dateTo", to);
        return query.getResultList();
    }

    public List<AdSummary> getAdSummaryByCampaignId(Long campaignId) {
        Query query = em.createNativeQuery(
                "SELECT res1.AdId," +
                        "       AdName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT AdId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      WHERE " +
                        "           CampaignId = :campaignId " +
                        "      GROUP BY AdId) res1 " +
                        "       INNER JOIN Ad ON res1.AdId = Ad.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT AdId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.AdId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.AdId = res1.AdId " +
                        "ORDER BY impressions DESC",
                "AdSummary"
        )
                .setParameter("campaignId", campaignId);
        return query.getResultList();
    }

    public List<AdSummary> getAdSummaryByIdList(List<Long> ids) {
        Query query = em.createNativeQuery(
                "SELECT res1.AdId," +
                        "       AdName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT AdId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      WHERE " +
                        "           AdId IN (:ids) " +
                        "      GROUP BY AdId) res1 " +
                        "       INNER JOIN Ad ON res1.AdId = Ad.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT AdId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.AdId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.AdId = res1.AdId " +
                        "ORDER BY impressions DESC",
                "AdSummary"
        )
                .setParameter("ids", ids);
        return query.getResultList();
    }

    public List<AdSummary> getAdSummaryByNameList(List<String> name) {
        Query query = em.createNativeQuery(
                "SELECT res1.AdId," +
                        "       AdName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT AdId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      GROUP BY AdId) res1 " +
                        "       INNER JOIN (" +
                        "                   SELECT * FROM Ad WHERE AdName IN (:adName) " +
                        "               )res3 ON res1.AdId = res3.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT AdId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.AdId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.AdId = res1.AdId " +
                        "ORDER BY impressions DESC",
                "AdSummary"
        )
                .setParameter("adName", name);
        return query.getResultList();
    }

    public List<AdSummary> getReportByDay(Date dateFrom, Date dateTo) {
        Query query = em.createNativeQuery(
                "SELECT res1.AdId, " +
                        "       AdName, " +
                        "       CONCAT(year, '-', month, '-', day) AS 'weekDay', " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT AdId, " +
                        "             COUNT(*)                      AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)        AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId)      AS 'interactions', " +
                        "             DATEPART(YEAR, DateOccurred)  AS 'year', " +
                        "             DATEPART(MONTH, DateOccurred) AS 'month', " +
                        "             DATEPART(DAY, DateOccurred)   AS 'day' " +
                        "      FROM Impression " +
                        "      WHERE DateOccurred BETWEEN :dateFrom AND :dateTo " +
                        "      GROUP BY DATEPART(DAY, DateOccurred), " +
                        "               DATEPART(MONTH, DateOccurred), " +
                        "               DATEPART(YEAR, DateOccurred), " +
                        "               AdId) res1 " +
                        "       INNER JOIN Ad ON res1.AdId = Ad.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT AdId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.AdId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.AdId = res1.AdId " +
                        " ORDER BY 'WeekDay' DESC, " +
                        "         impressions DESC",
                "AdSummaryDay"
        )
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo);
        return query.getResultList();
    }
}
