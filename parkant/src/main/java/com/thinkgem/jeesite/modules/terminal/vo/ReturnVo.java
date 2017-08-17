package com.thinkgem.jeesite.modules.terminal.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class ReturnVo {

    private String success;

    private String reason;

    private List<?> list=new ArrayList<Object>();

    private Object object;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
