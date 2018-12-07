package com.example.eric.ehealthuser.api;

import com.example.eric.ehealthuser.entity.Bundle;
import com.example.eric.ehealthuser.entity.PatientEntry;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;


public interface UserService {

    //String endPoint = "http://fhirtest.uhn.ca/baseDstu3/Patient/";

    @GET("/baseDstu3/Patient/{id}?_format=json")
    Observable<PatientEntry> getUserEntity(@Path("id") String id);



    @GET("/baseDstu3/Patient?_include=*&_include=Patient%3Ageneral-practitioner&_include=Patient%3Alink&_include=Patient%3Aorganization&_sort=-_id&_count=10")
    // @GET("/baseDstu3/Patient?_include=*&_count=10&_sort=-birthdate")
    Observable<Bundle> getUserList();


}
