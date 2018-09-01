package com.celtra.challange.data.pr3mar.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdDTO implements Serializable {

    private long id;
    private String adName;
    private long campaignId;
    private Date dateStored;

    public AdDTO() {
    }

    public AdDTO(long id, String adName, long campaignId) {
        this.id = id;
        this.adName = adName;
        this.campaignId = campaignId;
    }

    public AdDTO(long id, String adName, long campaignId, Date dateStored) {
        this.id = id;
        this.adName = adName;
        this.campaignId = campaignId;
        this.dateStored = dateStored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "AdDTO{" +
                "id=" + id +
                ", adName='" + adName + '\'' +
                ", campaignId=" + campaignId +
                ", dateStored=" + dateStored +
                '}';
    }
}
