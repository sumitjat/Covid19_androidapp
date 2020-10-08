package com.example.covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class MyAdapte extends RecyclerView.Adapter {




    private ArrayList<String> statename=new ArrayList<String>();;
    private ArrayList<String> activecase=new ArrayList<>();
    private ArrayList<String> confirmedcase=new ArrayList<String>();
    private ArrayList<String> recoveredcase=new ArrayList<String>();

    public MyAdapte(ArrayList<String> activecase, ArrayList<String> recoveredcase, ArrayList<String> confirmedcase, ArrayList<String> statename)
    {
        this.activecase=activecase;
        this.recoveredcase=recoveredcase;
        this.confirmedcase=confirmedcase;
        this.statename=statename;
    }

    @NonNull
    @Override
    public MyAdapte.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    public void onBindViewHolder(MyViewHolder viewHolder, int position)
    {
       viewHolder.t1.setText(statename.get(position));
       viewHolder.t2.setText(activecase.get(position));
       viewHolder.t3.setText(confirmedcase.get(position));
       viewHolder.t4.setText(recoveredcase.get(position));
    }

    //https://www.tutorialspoint.com/android-working-with-recycler-view




//    public void onBindViewHolder(MyViewHolder viewHolder, int position)
//    {
//        viewHolder.t1.setText(statename.get(1));
//        viewHolder.t2.setText(activecase.get(1));
//        viewHolder.t3.setText(confirmedcase.get(1));
//        viewHolder.t4.setText(recoveredcase.get(1));
//
//    }

    @Override
    public int getItemCount() {
        return activecase.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

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
