package com.celtra.challange.data.pr3mar.models.reports;

public class AdSummary {

    private Long AdId;
    private String AdName;
    private String weekDay;
    private Long impressions;
    private Long uniqueUsers;
    private Long interactions;
    private Long swipe;
    private Long touch;
    private Long click;
    private Long pinch;

    public AdSummary(
            Long AdId, String AdName, Long impressions, Long uniqueUsers, Long interactions,
            Long swipe, Long touch, Long click, Long pinch
    ) {
        this.AdId = AdId;
        this.AdName = AdName;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public AdSummary(
            Long adId, String adName, String weekDay, Long impressions,
            Long uniqueUsers, Long interactions, Long swipe,
            Long touch, Long click, Long pinch
    ) {
        AdId = adId;
        AdName = adName;
        this.weekDay = weekDay;
        this.impressions = impressions;
        this.uniqueUsers = uniqueUsers;
        this.interactions = interactions;
        this.swipe = swipe;
        this.touch = touch;
        this.click = click;
        this.pinch = pinch;
    }

    public Long getAdId() {
        return AdId;
    }

    public void setAdId(Long adId) {
        this.AdId = adId;
    }

    public String getAdName() {
        return AdName;
    }

    public void setAdName(String adName) {
        AdName = adName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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
}
