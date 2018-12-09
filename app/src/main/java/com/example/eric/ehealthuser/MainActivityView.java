package com.example.eric.ehealthuser;

import com.example.eric.ehealthuser.model.UhnPatient;

import java.util.List;


public interface MainActivityView {
    void displayDataOnRecyclerView(List<UhnPatient> list);
    void displayNoData();

}
