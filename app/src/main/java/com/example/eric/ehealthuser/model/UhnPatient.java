package com.example.eric.ehealthuser.model;

import java.io.Serializable;

public class UhnPatient implements Serializable {
    public String id;
    public String name;
    public String gender;
    public String birthday;

    public UhnPatient(){}
    public UhnPatient(String id, String name, String gender, String birthday){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("{id:%s, name:%s, gender:%s, birthday:%s}", id, name, gender, birthday);
    }
}
