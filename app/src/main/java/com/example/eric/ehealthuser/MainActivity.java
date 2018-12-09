package com.example.eric.ehealthuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.eric.ehealthuser.model.UhnPatient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<UhnPatient> dataList = new ArrayList<>();
    private MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    // initialize find view and bind
    private void init() {
        mRecyclerView = findViewById(R.id.rv_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

        Log.d(TAG, "initial and bind finish");

        presenter = new MainActivityPresenter();
        presenter.displayDataOn(this);

    }

    @Override
    public void displayDataOnRecyclerView(List<UhnPatient> list) {
        dataList.clear();
        dataList.addAll(list);
        mAdapter.notifyDataSetChanged();

    }
}
