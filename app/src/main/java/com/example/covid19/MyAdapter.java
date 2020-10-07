package com.example.covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {




    private ArrayList<String> statename=new ArrayList<String>();;
    private ArrayList<String> activecase=new ArrayList<String>();
    private ArrayList<String> confirmedcase=new ArrayList<String>();
    private ArrayList<String> recoveredcase=new ArrayList<String>();

    public MyAdapter(ArrayList<String> activecase, ArrayList<String> recoveredcase, ArrayList<String> confirmedcase, ArrayList<String> statename)
    {
        this.activecase=activecase;
        this.recoveredcase=recoveredcase;
        this.confirmedcase=confirmedcase;
        this.statename=statename;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }


    public void onBindViewHolder(MyViewHolder viewHolder, int position)
    {
        viewHolder.t1.setText(statename.get(1));
        viewHolder.t2.setText(activecase.get(1));
        viewHolder.t3.setText(confirmedcase.get(1));
        viewHolder.t4.setText(recoveredcase.get(1));

    }

    @Override
    public int getItemCount() {
        return activecase.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3,t4;
        public MyViewHolder (View itemView) {
            super(itemView);



            t1=itemView.findViewById(R.id.statenamec);
            t2=itemView.findViewById(R.id.activec);
            t3= itemView.findViewById(R.id.confirmedc);
            t4 = itemView.findViewById(R.id.recoveredc);
        }
    }
}
