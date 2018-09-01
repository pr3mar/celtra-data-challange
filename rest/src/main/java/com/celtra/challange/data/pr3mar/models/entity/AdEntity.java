package com.celtra.challange.data.pr3mar.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Ad")
@Table(name = "Ad")
@NamedQueries({
        @NamedQuery(
                name = "Ad.findAll",
                query = "SELECT c FROM Ad c"
        ),
        @NamedQuery(
                name = "Ad.countAll",
                query = "SELECT count(c) FROM Ad c"
        ),
        @NamedQuery(
                name = "Ad.getIds",
                query = "SELECT DISTINCT c.id FROM Ad c ORDER BY c.dateStored DESC "
        ),
        @NamedQuery(
                name = "Ad.getLastId",
                query = "SELECT c.id FROM Ad c ORDER BY c.dateStored DESC "
        )
})
public class AdEntity implements Serializable {

    private long id;
    private String adName;
    private long campaignId;
    private Date dateStored = new Date();

    public AdEntity() {
    }

    public AdEntity(String adName, long campaignId) {
        this.adName = adName;
        this.campaignId = campaignId;
    }

    public AdEntity(long id, String adName, long campaignId) {
        this.id = id;
        this.adName = adName;
        this.campaignId = campaignId;
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

    @Column(name = "AdName", columnDefinition = "nvarchar(50)")
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    @Column(name = "CampaignId", columnDefinition = "bigint", nullable = false)
    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "DateStored", columnDefinition = "datetime", updatable = false, nullable = false)
    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }
}