package com.ela.blessing.star.domain;

import java.util.Date;

public class StarInfo {
    private int starId;
    private String starName;
    private int blessingCount;
    private Date utime;


    public int getBlessingCount() {
        return blessingCount;
    }

    public void setBlessingCount(int blessingCount) {
        this.blessingCount = blessingCount;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public int getStarId() {
        return starId;
    }

    public void setStarId(int starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }
}
