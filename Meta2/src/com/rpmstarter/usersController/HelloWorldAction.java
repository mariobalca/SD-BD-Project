package com.rpmstarter.usersController;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by mariobalca on 12/3/15.
 */
public class HelloWorldAction implements SessionAware{
    private String error;
    private Map<String,Object> session;

    public String execute() throws Exception {
        return "success";
    }

    public String getName() {
        return (String) session.get("username");
    }
    public String getError(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
