package com.thinkgem.jeesite.modules.terminal.vo;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class CarSeatVo {

    /**
     * 车位id
     */
    private String id;
    /**
     * 车位名称
     */
    private String name;

    /**
     * 停车场名称
     */
    private String parkName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
