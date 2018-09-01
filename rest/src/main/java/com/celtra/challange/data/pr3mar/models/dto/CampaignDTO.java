package com.celtra.challange.data.pr3mar.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignDTO implements Serializable {

    private long id;
    private String campaignName;
    private Date dateStarted;
    private Date dateEnded;
    private Date dateStored;

    public CampaignDTO() {
    }

    public CampaignDTO(long id, String campaignName) {
        this.id = id;
        this.campaignName = campaignName;
    }

    public CampaignDTO(long id, String campaignName, Date dateStored) {
        this.id = id;
        this.campaignName = campaignName;
        this.dateStored = dateStored;
    }

    public CampaignDTO(long id, String campaignName, Date dateStarted, Date dateEnded) {
        this.id = id;
        this.campaignName = campaignName;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
    }

    public CampaignDTO(long id, String campaignName, Date dateStarted, Date dateEnded, Date dateStored) {
        this.id = id;
        this.campaignName = campaignName;
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
        this.dateStored = dateStored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
                "id=" + id +
                ", campaignName='" + campaignName + '\'' +
                ", dateStarted=" + dateStarted +
                ", dateEnded=" + dateEnded +
                ", dateStored=" + dateStored +
                '}';
    }
}
