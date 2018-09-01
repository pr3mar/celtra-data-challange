package com.celtra.challange.data.pr3mar.models.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImpressionDTO {

    private long id;
    private long campaignId;
    private long adId;
    private long userId;
    private Integer interactionTypeId;
    private Date dateOccurred;
    private Date dateStored;

    public ImpressionDTO() {
    }

    public ImpressionDTO(
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

    public ImpressionDTO(
            long id, long campaignId, long adId, long userId,
            Integer interactionTypeId, Date dateOccurred, Date dateStored
    ) {
        this.id = id;
        this.campaignId = campaignId;
        this.adId = adId;
        this.userId = userId;
        this.interactionTypeId = interactionTypeId;
        this.dateOccurred = dateOccurred;
        this.dateStored = dateStored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Integer getInteractionTypeId() {
        return interactionTypeId;
    }

    public void setInteractionTypeId(Integer interactionTypeId) {
        this.interactionTypeId = interactionTypeId;
    }

    public Date getDateOccurred() {
        return dateOccurred;
    }

    public void setDateOccurred(Date dateOccurred) {
        this.dateOccurred = dateOccurred;
    }

    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "ImpressionDTO{" +
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
