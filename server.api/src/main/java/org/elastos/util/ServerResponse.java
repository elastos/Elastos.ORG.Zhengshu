package org.elastos.util;

import com.alibaba.fastjson.JSON;

public class ServerResponse {
    private int state;
    private String msg;
    private Object data;

    public int getState() {
        return state;
    }

    public ServerResponse setState(int state) {
        this.state = state;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ServerResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ServerResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String toJsonString(){
        return JSON.toJSONString(this);
    }
}
