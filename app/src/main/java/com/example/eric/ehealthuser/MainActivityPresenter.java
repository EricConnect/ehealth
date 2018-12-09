package com.example.eric.ehealthuser;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.eric.ehealthuser.api.ApiService;
import com.example.eric.ehealthuser.api.UserService;
import com.example.eric.ehealthuser.entity.Bundle;
import com.example.eric.ehealthuser.entity.PatientEntry;
import com.example.eric.ehealthuser.model.UhnPatient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter {

    private final String TAG = "MainActivityPresenter";

    private MainActivityView mView;
    private UserService service = ApiService.getInstance().getPatientService();


    @SuppressLint("CheckResult")
    public void displayDataOn(MainActivityView view) {

        this.mView = view;

        service.getUserList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Bundle, List<UhnPatient>>() {
                    @Override
                    public List<UhnPatient> apply(com.example.eric.ehealthuser.entity.Bundle bundle) {
                        List<UhnPatient> patientList = new ArrayList<>();
                        for (int i = 0; i < bundle.patientList.size(); i++) {
                            PatientEntry entry = bundle.patientList.get(i);
                            patientList.add(
                                    new UhnPatient(
                                            entry.resource.id,
                                            entry.getName(),
                                            entry.resource.gender,
                                            entry.resource.birthDate
                                    )
                            );
                        }
                        Log.d(TAG, "map funtion run up. size:" + patientList.size());
                        return patientList;
                    }
                })
                .subscribe(new Consumer<List<UhnPatient>>() {
                    @Override
                    public void accept(List<UhnPatient> uhnPatients) {

                        mView.displayDataOnRecyclerView(uhnPatients);
                    }
                });

    }

}
