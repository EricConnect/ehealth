package com.example.eric.ehealthuser.entity;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Bundle {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("entry")
    @Expose
    public List<PatientEntry> patientList;

}