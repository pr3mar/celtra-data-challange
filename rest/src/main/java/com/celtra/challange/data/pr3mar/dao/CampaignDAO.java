package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;
import com.celtra.challange.data.pr3mar.models.reports.CampaignSummary;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@RequestScoped
public class CampaignDAO extends GenericDAO<CampaignEntity, Long> {

    public CampaignDAO() {
        super(CampaignEntity.class);
    }

    @Override
    public List<CampaignEntity> findAll() {
        return em.createNamedQuery("Campaign.findAll", CampaignEntity.class)
                .getResultList();
    }

    public Long getLatestId() {
        return em.createNamedQuery("Campaign.getLastId", Long.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Long getLastNIds(int max) {
        return em.createNamedQuery("Campaign.getIds", Long.class)
                .setMaxResults(max)
                .getSingleResult();
    }

    public List<CampaignSummary> getReportByIdList(List<Long> ids) {
        Query query = em.createNativeQuery(
                "SELECT res1.CampaignId, " +
                        "       CampaignName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT CampaignId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      GROUP BY CampaignId) res1 " +
                        "       INNER JOIN ( " +
                        "                   SELECT * FROM Campaign WHERE ID IN (:ids) " +
                        "                       ) res3 ON res1.CampaignId = res3.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT CampaignId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.CampaignId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.CampaignId = res1.CampaignId " +
                        "ORDER BY impressions DESC",
                "CampaignSummary"
        )
                .setParameter("ids", ids);
        return query.getResultList();
    }

    public List<CampaignSummary> getReportByNameList(List<String> names) {
        Query query = em.createNativeQuery(
                "SELECT res1.CampaignId, " +
                        "       CampaignName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "   FROM (SELECT CampaignId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      GROUP BY CampaignId) res1 " +
                        "       INNER JOIN ( " +
                        "                   SELECT * FROM Campaign WHERE CampaignName IN (:names) " +
                        "                       ) res3 ON res1.CampaignId = res3.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT CampaignId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.CampaignId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.CampaignId = res1.CampaignId " +
                        "ORDER BY impressions DESC",
                "CampaignSummary"
        )
                .setParameter("names", names);
        return query.getResultList();
    }

    public List<CampaignSummary> getReportsByDate(Date from, Date to) {
        Query query = em.createNativeQuery(
                "SELECT res1.CampaignId, " +
                        "       CampaignName, " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "       FROM (SELECT CampaignId, " +
                        "             COUNT(*)                 AS 'impressions', " +
                        "             COUNT(DISTINCT UserId)   AS 'uniqueUsers', " +
                        "             COUNT(InteractionTypeId) AS 'interactions' " +
                        "      FROM Impression " +
                        "      WHERE DateOccurred BETWEEN :dateFrom AND :dateTo " +
                        "      GROUP BY CampaignId) res1 " +
                        "       INNER JOIN ( " +
                        "                   SELECT * FROM Campaign " +
                        "                       ) res3 ON res1.CampaignId = res3.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT CampaignId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.CampaignId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.CampaignId = res1.CampaignId " +
                        "ORDER BY impressions DESC",
                "CampaignSummary"
        )
                .setParameter("dateFrom", from)
                .setParameter("dateTo", to);
        return query.getResultList();
    }

    public List<CampaignSummary> getReportByDay(Date dateFrom, Date dateTo) {
        Query query = em.createNativeQuery(
                "SELECT res1.CampaignId, " +
                        "       CampaignName, " +
                        "       CONCAT(year, '-', month, '-', day) AS 'weekDay', " +
                        "       impressions, " +
                        "       uniqueUsers, " +
                        "       interactions, " +
                        "       swipe, " +
                        "       touch, " +
                        "       click, " +
                        "       pinch " +
                        "FROM (SELECT CampaignId, " +
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
                        "               CampaignId) res1 " +
                        "       INNER JOIN Campaign ON res1.CampaignId = Campaign.ID " +
                        "       INNER JOIN (SELECT * " +
                        "                   FROM (SELECT CampaignId, I.Type, COUNT(I.ID) AS 'count' " +
                        "                         FROM Impression Im " +
                        "                                JOIN InteractionType I on Im.InteractionTypeId = I.ID " +
                        "                         GROUP BY I.Type, Im.CampaignId) src " +
                        "                            pivot ( " +
                        "                              sum(src.count) " +
                        "                            for src.Type in ([swipe], [touch], [click], [pinch]) " +
                        "                            ) piv) res2 ON res2.CampaignId = res1.CampaignId " +
                        " ORDER BY 'WeekDay' DESC, " +
                        "         impressions DESC",
                "CampaignSummaryDay"
        )
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo);
        return query.getResultList();
    }
}
