package com.example.covid19;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.programgolder>  {


    private ArrayList<String> statename=new ArrayList<String>();;
    private ArrayList<String> activecase=new ArrayList<>();
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
    public programgolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout, parent, false);
        return new MyAdapter.programgolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull programgolder viewHolder, int position) {
        viewHolder.t1.setTextColor(Color.BLACK);
        viewHolder.t1.setText(statename.get(position));
        viewHolder.t2.setTextColor(Color.BLACK);
        viewHolder.t2.setText("Active Cases: " +activecase.get(position));
        viewHolder.t3.setTextColor(Color.RED);
        viewHolder.t3.setText("Confirmed: "+confirmedcase.get(position));
        viewHolder.t4.setTextColor(Color.parseColor("#006400"));
        viewHolder.t4.setText("Recovered: " +recoveredcase.get(position));

    }

    @Override
    public int getItemCount() {
        return activecase.size();
    }

    public class programgolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4;

        public programgolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.statenamec);

            t2=itemView.findViewById(R.id.activec);
            t3= itemView.findViewById(R.id.confirmedc);
            t4 = itemView.findViewById(R.id.recoveredc);
        }
    }

}
