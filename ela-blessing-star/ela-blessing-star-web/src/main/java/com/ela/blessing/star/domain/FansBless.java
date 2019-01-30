package com.ela.blessing.star.domain;

import java.util.Date;

public class FansBless {
    private Integer id;

    private String userIdentity;

    private String userNick;

    private Integer starId;

    private String starName;

    private Integer emoticonId;

    private String blessingContent;

    private String txid;

    private Date cTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public Integer getEmoticonId() {
        return emoticonId;
    }

    public void setEmoticonId(Integer emoticonId) {
        this.emoticonId = emoticonId;
    }

    public String getBlessingContent() {
        return blessingContent;
    }

    public void setBlessingContent(String blessingContent) {
        this.blessingContent = blessingContent;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}