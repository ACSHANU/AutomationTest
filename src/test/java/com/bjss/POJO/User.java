package com.bjss.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class User {

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "name = '" + name + '\'' +
                        ",job = '" + job + '\'' +
                        "}";
    }
}