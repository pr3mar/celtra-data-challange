package com.celtra.challange.data.pr3mar.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Impression")
@Table(name = "Impression")
@NamedQueries({
        @NamedQuery(
                name = "Impression.findAll",
                query = "SELECT c FROM Impression c"
        ),
        @NamedQuery(
                name = "Impression.getIds",
                query = "SELECT DISTINCT c.id FROM Impression c ORDER BY c.dateStored DESC "
        ),
        @NamedQuery(
                name = "Impression.getLastId",
                query = "SELECT c.id FROM Impression c ORDER BY c.dateStored DESC "
        )
})
public class ImpressionEntity implements Serializable {

    private long id;
    private long campaignId;
    private long adId;
    private long userId;
    private Integer interactionTypeId;
    private Date dateOccurred;
    private Date dateStored = new Date();

    public ImpressionEntity() {
    }

    public ImpressionEntity(
            long campaignId, long adId, long userId,
            Integer interactionTypeId, Date dateOccurred
    ) {
        this.campaignId = campaignId;
        this.adId = adId;
        this.userId = userId;
        this.interactionTypeId = interactionTypeId;
        this.dateOccurred = dateOccurred;
    }

    public ImpressionEntity(
            long id, long campaignId, long adId, long userId,
            Integer interactionTypeId, Date dateOccurred
    ) {
        this.id = id;
        this.campaignId = campaignId;
        this.adId = adId;
        this.userId = userId;
        this.interactionTypeId = interactionTypeId;
        this.dateOccurred = dateOccurred;
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

    @Column(name = "CampaignId", columnDefinition = "bigint", updatable = false, nullable = false)
    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "AdId", columnDefinition = "bigint", updatable = false, nullable = false)
    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    @Column(name = "UserId", columnDefinition = "bigint", updatable = false, nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "InteractionTypeId", columnDefinition = "int", updatable = false)
    public Integer getInteractionTypeId() {
        return interactionTypeId;
    }

    public void setInteractionTypeId(Integer interactionTypeId) {
        this.interactionTypeId = interactionTypeId;
    }

    @Column(name = "DateOccurred", columnDefinition = "datetime", updatable = false, nullable = false)
    public Date getDateOccurred() {
        return dateOccurred;
    }

    public void setDateOccurred(Date dateOccured) {
        this.dateOccurred = dateOccured;
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
        return "ImpressionEntity{" +
                "id=" + id +
                ", campaignId=" + campaignId +
                ", adId=" + adId +
                ", userId=" + userId +
                ", interactionTypeId=" + interactionTypeId +
                ", dateOccurred=" + dateOccurred +
                ", dateStored=" + dateStored +
                '}';
    }
}
