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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements IPresenter {

    private final String TAG = "MainActivityPresenter";

    private MainActivityView mView;
    private UserService service = ApiService.getInstance().getPatientService();


    @SuppressLint("CheckResult")
    public void displayDataOn(MainActivityView view) {

        this.mView = view;

        service.getUserList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Bundle>() {
                    @Override
                    public Bundle apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "error at get data.");
                        Log.d(TAG, throwable.getMessage());
                        return null;
                    }
                })
                .map(new TransformBundleToList())
                .subscribe(new ObservePatientList());

    }

    ///
    // transform bundle to list
    //
    public class TransformBundleToList implements Function<Bundle, List<UhnPatient>>{
        @Override
        public List<UhnPatient> apply(Bundle bundle) {
            List<UhnPatient> patientList = new ArrayList<>();
            try {
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
            }catch (Exception e){
                Log.d(TAG, "Error on get data from server." + e.getMessage());
            }
            Log.d(TAG, "map funtion run up. size:" + patientList.size());
            return patientList;
        }
    }

    public class ObservePatientList implements Observer<List<UhnPatient>>{
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<UhnPatient> uhnPatients) {
            if (uhnPatients.size() >0){
                mView.displayDataOnRecyclerView(uhnPatients);
            }else{
                Log.d(TAG, "patient list size less then 1");
                mView.displayNoData();
            }

        }

        @Override
        public void onError(Throwable e) {
            mView.displayNoData();
            Log.d(TAG, "Some error on get data.");

        }

        @Override
        public void onComplete() {

        }

    }

}
