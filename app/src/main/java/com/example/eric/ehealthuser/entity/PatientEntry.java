package com.example.eric.ehealthuser.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientEntry {

    @SerializedName("fullUrl")
    @Expose
    public String fullUrl;

    @SerializedName("resource")
    @Expose
    public Resource resource;


    public class Resource{
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("active")
        @Expose
        public Boolean active;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("birthDate")
        @Expose
        public String birthDate;

        @SerializedName("name")
        @Expose
        public List<Name> name;

        public class Name{
            @SerializedName("family")
            @Expose
            public String family;
            @SerializedName("given")
            @Expose
            public String[] given;
        }

    }

    @Override
    public String toString() {
        return String.format("url: %s, Id: %s, gender:%s, birthday:%s, name: %s",
                fullUrl,
                resource.id,
                resource.gender,
                resource.birthDate
                ,resource.name.get(0).family +resource.name.get(0).given[0]
                );
    }
    public String getName(){
        return String.format("%s %s", resource.name.get(0).family, String.join(" ",resource.name.get(0).given));
    }
}