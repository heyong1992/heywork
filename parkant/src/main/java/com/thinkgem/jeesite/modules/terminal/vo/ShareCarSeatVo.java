package com.thinkgem.jeesite.modules.terminal.vo;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class ShareCarSeatVo {

    private String id;

    private String carSeatName;

    private String startTime;

    private String endTime;

    private String parkingName;

    private String haverId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarSeatName() {
        return carSeatName;
    }

    public void setCarSeatName(String carSeatName) {
        this.carSeatName = carSeatName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getHaverId() {
        return haverId;
    }

    public void setHaverId(String haverId) {
        this.haverId = haverId;
    }
}
