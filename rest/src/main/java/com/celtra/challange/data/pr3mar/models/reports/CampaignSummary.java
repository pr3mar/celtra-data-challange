package com.celtra.challange.data.pr3mar.models.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignSummary {

    private Long CampaignId;
    private String CampaignName;
    private String weekDay;
    private Date activeFrom;
    private Date activeTo;
    private Long impressions;
    private Long uniqueUsers;
    private Long interactions;
    private Long swipe;
    private Long touch;
    private Long click;
    private Long pinch;
    private List<AdSummary> adSummaries;

    public CampaignSummary(
            Long CampaignId, String CampaignName, Long impressions, Long uniqueUsers, Long interactions,
            Long swipe, Long touch, Long click, Long pinch
    ) {
        this.CampaignId = CampaignId;
        this.CampaignName = CampaignName;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public CampaignSummary(
            Long campaignId, String campaignName, String weekDay,
            Long impressions, Long uniqueUsers, Long interactions,
            Long swipe, Long touch, Long click, Long pinch
    ) {
        CampaignId = campaignId;
        CampaignName = campaignName;
        this.weekDay = weekDay;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public CampaignSummary(
            Long campaignId, String campaignName, Long impressions,
            Long uniqueUsers, Long interactions, Long swipe, Long touch,
            Long click, Long pinch, List<AdSummary> adSummaries
    ) {
        CampaignId = campaignId;
        CampaignName = campaignName;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
        this.adSummaries = adSummaries;
    }

    public CampaignSummary(
            Long campaignId, String campaignName, String weekDay,
            Date activeFrom, Date activeTo, Long impressions, Long uniqueUsers,
            Long interactions, Long swipe, Long touch, Long click, Long pinch
    ) {
        CampaignId = campaignId;
        CampaignName = campaignName;
        this.weekDay = weekDay;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public CampaignSummary(
            Long campaignId, String campaignName, Date activeFrom,
            Date activeTo, Long impressions, Long uniqueUsers,
            Long interactions, Long swipe, Long touch, Long click, Long pinch
    ) {
        CampaignId = campaignId;
        CampaignName = campaignName;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public Long getCampaignId() {
        return CampaignId;
    }

    public void setCampaignId(Long campaignId) {
        CampaignId = campaignId;
    }

    public String getCampaignName() {
        return CampaignName;
    }

    public void setCampaignName(String campaignName) {
        CampaignName = campaignName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    public Long getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(Long uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    public Long getInteractions() {
        return interactions;
    }

    public void setInteractions(Long interactions) {
        this.interactions = interactions;
    }

    public Long getSwipe() {
        return swipe;
    }

    public void setSwipe(Long swipe) {
        this.swipe = swipe;
    }

    public Long getTouch() {
        return touch;
    }

    public void setTouch(Long touch) {
        this.touch = touch;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Long getPinch() {
        return pinch;
    }

    public void setPinch(Long pinch) {
        this.pinch = pinch;
    }

    public List<AdSummary> getAdSummaries() {
        return adSummaries;
    }

    public void setAdSummaries(List<AdSummary> adSummaries) {
        this.adSummaries = adSummaries;
    }
}
