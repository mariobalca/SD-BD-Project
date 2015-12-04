package com.struts2;

/**
 * Created by mariobalca on 12/3/15.
 */
public class HelloWorldAction{
    private String name;
    private String error;

    public String execute() throws Exception {
        return "success";
    }

    public String getName() {
        return name;
    }
    public String getError(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setName(String name) {
        this.name = name;
    }
}
