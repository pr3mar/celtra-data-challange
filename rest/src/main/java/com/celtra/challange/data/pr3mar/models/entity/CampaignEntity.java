package com.celtra.challange.data.pr3mar.models.entity;

import com.celtra.challange.data.pr3mar.models.reports.CampaignSummary;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Campaign")
@Table(name = "Campaign")
@NamedQueries({
        @NamedQuery(
                name = "Campaign.findAll",
                query = "SELECT c FROM Campaign c"
        ),
        @NamedQuery(
                name = "Campaign.countAll",
                query = "SELECT count(c) FROM Campaign c"
        ),
        @NamedQuery(
                name = "Campaign.getIds",
                query = "SELECT DISTINCT c.id FROM Campaign c ORDER BY c.dateStored DESC "
        ),
        @NamedQuery(
                name = "Campaign.getLastId",
                query = "SELECT c.id FROM Campaign c ORDER BY c.dateStored DESC "
        )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "CampaignSummary",
            classes = {
                    @ConstructorResult(
                            targetClass = CampaignSummary.class,
                            columns = {
                                    @ColumnResult(name = "CampaignId", type = Long.class),
                                    @ColumnResult(name = "CampaignName", type = String.class),
                                    @ColumnResult(name = "impressions", type = Long.class),
                                    @ColumnResult(name = "uniqueUsers", type = Long.class),
                                    @ColumnResult(name = "interactions", type = Long.class),
                                    @ColumnResult(name = "swipe", type = Long.class),
                                    @ColumnResult(name = "touch", type = Long.class),
                                    @ColumnResult(name = "click", type = Long.class),
                                    @ColumnResult(name = "pinch", type = Long.class)
                            }
                    )
            }
    ),
    @SqlResultSetMapping(
            name = "CampaignSummaryDay",
            classes = {
                    @ConstructorResult(
                            targetClass = CampaignSummary.class,
                            columns = {
                                    @ColumnResult(name = "CampaignId", type = Long.class),
                                    @ColumnResult(name = "CampaignName", type = String.class),
                                    @ColumnResult(name = "weekDay", type = String.class),
                                    @ColumnResult(name = "impressions", type = Long.class),
                                    @ColumnResult(name = "uniqueUsers", type = Long.class),
                                    @ColumnResult(name = "interactions", type = Long.class),
                                    @ColumnResult(name = "swipe", type = Long.class),
                                    @ColumnResult(name = "touch", type = Long.class),
                                    @ColumnResult(name = "click", type = Long.class),
                                    @ColumnResult(name = "pinch", type = Long.class)
                            }
                    )
            }
    )
})
public class CampaignEntity implements Serializable {

    private long id;
    private String campaignName;
    private Date dateStarted;
    private Date dateEnded;
    private Date dateStored = new Date();

    public CampaignEntity() {
    }

    public CampaignEntity(String campaignName) {
        this.campaignName = campaignName;
    }

    public CampaignEntity(long id, String campaignName) {
        this.id = id;
        this.campaignName = campaignName;
    }

    public CampaignEntity(String campaignName, Date dateStarted, Date dateEnded) {
        this.campaignName = campaignName;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
    }

    public CampaignEntity(long id, String campaignName, Date dateStarted, Date dateEnded) {
        this.id = id;
        this.campaignName = campaignName;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint", unique = true, updatable = false, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "CampaignName", columnDefinition = "nvarchar(260)", nullable = false)
    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Column(name = "DateStarted", columnDefinition = "datetime")
    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    @Column(name = "DateEnded", columnDefinition = "datetime")
    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    @Column(name = "DateStored", columnDefinition = "datetime", updatable = false, nullable = false)
    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "CampaignEntity{" +
                "id=" + id +
                ", campaignName='" + campaignName + '\'' +
                ", dateStarted=" + dateStarted +
                ", dateEnded=" + dateEnded +
                ", dateStored=" + dateStored +
                '}';
    }
}
