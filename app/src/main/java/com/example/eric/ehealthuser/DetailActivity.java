package com.example.eric.ehealthuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.eric.ehealthuser.model.UhnPatient;

public class DetailActivity extends AppCompatActivity {


    TextView mNameTextView;
    TextView mIdTextView;
    TextView mGenderTextView;
    TextView mBirthdayTextView;

    private UhnPatient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        patient = (UhnPatient) getIntent().getSerializableExtra("patient");
        init();


    }

    private void init() {
        mNameTextView = findViewById(R.id.tv_name);
        mIdTextView = findViewById(R.id.tv_id);
        mGenderTextView = findViewById(R.id.tv_gender);
        mBirthdayTextView = findViewById(R.id.tv_birthday);

        if (patient != null) {
            mNameTextView.setText(patient.name);
            mIdTextView.setText(patient.id);
            mGenderTextView.setText(patient.gender);
            mBirthdayTextView.setText(patient.birthday);

        }

    }
}
