package com.example.eric.ehealthuser;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eric.ehealthuser.model.UhnPatient;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static List<UhnPatient> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mIdTextView;
        public TextView mBirthdayTextView;
        public TextView mGenderTextView;
        public CardView mCardView;

        public MyViewHolder(final View v) {
            super(v);

            mNameTextView = v.findViewById(R.id.tv_name);
            mIdTextView = v.findViewById(R.id.tv_id);
            mGenderTextView = v.findViewById(R.id.tv_gender);
            mBirthdayTextView = v.findViewById(R.id.tv_birthday);
            mCardView = v.findViewById(R.id.card_view);
        }
    }

    public MyAdapter(List<UhnPatient> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mNameTextView.setText(mDataset.get(position).name);
        holder.mIdTextView.setText("ID: "+mDataset.get(position).id);
        holder.mGenderTextView.setText("Gender: "+mDataset.get(position).gender);
        holder.mBirthdayTextView.setText(mDataset.get(position).birthday);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),DetailActivity.class);

                        intent.putExtra("patient", mDataset.get(position));
                        view.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}