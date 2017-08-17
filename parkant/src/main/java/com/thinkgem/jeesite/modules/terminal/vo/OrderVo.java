package com.thinkgem.jeesite.modules.terminal.vo;

/**
 * Created by Administrator on 2017/8/16 0016.
 */
public class OrderVo {

    private String id;
    private String haverName;
    private String haverPhone;
    private String parkingName;
    private String carSeatNum;
    private String shareStartDate;
    private String shareEndDate;
    private String startDate;
    private String endDate;
    private String status;

    public String getHaverName() {
        return haverName;
    }

    public void setHaverName(String haverName) {
        this.haverName = haverName;
    }

    public String getHaverPhone() {
        return haverPhone;
    }

    public void setHaverPhone(String haverPhone) {
        this.haverPhone = haverPhone;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getCarSeatNum() {
        return carSeatNum;
    }

    public void setCarSeatNum(String carSeatNum) {
        this.carSeatNum = carSeatNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareStartDate() {
        return shareStartDate;
    }

    public void setShareStartDate(String shareStartDate) {
        this.shareStartDate = shareStartDate;
    }

    public String getShareEndDate() {
        return shareEndDate;
    }

    public void setShareEndDate(String shareEndDate) {
        this.shareEndDate = shareEndDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
