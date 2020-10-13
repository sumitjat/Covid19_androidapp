package com.example.covid19;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.smallholder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    private ArrayList<String> statename = new ArrayList<String>();

    private ArrayList<String> activecase = new ArrayList<>();
    private ArrayList<String> confirmedcase = new ArrayList<String>();
    private ArrayList<String> recoveredcase = new ArrayList<String>();


    public SubItemAdapter(ArrayList<String> activecas, ArrayList<String> recoveredcas, ArrayList<String> confirmedcas, ArrayList<String> statenam) {
        this.activecase = activecas;
        this.recoveredcase = recoveredcas;
        this.confirmedcase = confirmedcas;
        this.statename = statenam;
    }

    @NonNull
    @Override
    public smallholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_data, parent, false);
        return new SubItemAdapter.smallholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull SubItemAdapter.smallholder viewHolder, int position)
    {
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


    public class smallholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4;

        public smallholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.statenameca);

            t2 = itemView.findViewById(R.id.activeca);
            t3 = itemView.findViewById(R.id.confirmedca);
            t4 = itemView.findViewById(R.id.recoveredca);
        }
    }
}
