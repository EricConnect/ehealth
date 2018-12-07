package com.example.eric.ehealthuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.eric.ehealthuser.api.ApiService;
import com.example.eric.ehealthuser.api.UserService;
import com.example.eric.ehealthuser.entity.PatientEntry;
import com.example.eric.ehealthuser.model.UhnPatient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private UserService userService = ApiService.getInstance().getPatientService();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<UhnPatient> dataList = new ArrayList<UhnPatient>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPatientList();

        mRecyclerView = findViewById(R.id.rv_view);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getPatientList() {
        Observable<com.example.eric.ehealthuser.entity.Bundle> observable = userService.getUserList();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.example.eric.ehealthuser.entity.Bundle>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   Log.d(TAG, "onSubscribe");

                               }

                               @Override
                               public void onNext(com.example.eric.ehealthuser.entity.Bundle patientList) {

                                   Log.d(TAG, "onNext");

                                   PatientEntry p;

                                   for (int i = 0; i < patientList.patientList.size(); i++) {
                                       p = patientList.patientList.get(i);
                                       dataList.add(new UhnPatient(p.resource.id, p.getName(), p.resource.gender, p.resource.birthDate));

                                   }
                                   mAdapter.notifyDataSetChanged();


                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.d(TAG, "onError");
                                   Log.d(TAG, e.toString());

                               }

                               @Override
                               public void onComplete() {
                                   Log.d(TAG, "onComplete");

                               }
                           }

                );
    }

    private void getPatient(String id) {
        Observable<PatientEntry> observable = userService.getUserEntity(id);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PatientEntry>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   Log.d(TAG, "onSubscribe");

                               }

                               @Override
                               public void onNext(PatientEntry patient) {

                                   Log.d(TAG, "onNext");


                                   Log.d(TAG, patient.resource.birthDate);

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.d(TAG, "onError");
                                   Log.d(TAG, e.toString());

                               }

                               @Override
                               public void onComplete() {
                                   Log.d(TAG, "onComplete");

                               }
                           }

                );
    }

}
