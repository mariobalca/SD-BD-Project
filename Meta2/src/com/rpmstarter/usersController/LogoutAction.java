package com.rpmstarter.usersController;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by pedro on 12/10/15.
 */
public class LogoutAction extends ActionSupport implements SessionAware{
    private Map<String,Object> session;

    public String execute(){
        session.remove("username");
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }
}
