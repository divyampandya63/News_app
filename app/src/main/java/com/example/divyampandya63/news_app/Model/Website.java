package com.example.divyampandya63.news_app.Model;

import java.util.List;

import com.example.divyampandya63.news_app.Model.Source;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public class Website {
    private String status;
    private List<Source> sources;

    public Website(){}

    public Website(String status, List<Source> sources){
        this.status=status;
        this.sources=sources;


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }





}
