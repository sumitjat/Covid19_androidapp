package com.example.covid19;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.programgolder> {


    private final Context context;
    Context cont;


    public Context getContext() {
        return context;
    }

    private ArrayList<String> statename = new ArrayList<String>();
    ;
    private ArrayList<String> activecase = new ArrayList<>();
    private ArrayList<String> confirmedcase = new ArrayList<String>();
    private ArrayList<String> recoveredcase = new ArrayList<String>();

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public MyAdapter(ArrayList<String> activecase, ArrayList<String> recoveredcase, ArrayList<String> confirmedcase, ArrayList<String> statename,Context context) {
        this.activecase = activecase;
        this.recoveredcase = recoveredcase;
        this.confirmedcase = confirmedcase;
        this.statename = statename;
        this.context=context;
        }

    @NonNull
    @Override
    public programgolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout, parent, false);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v, MyAdapter.programgolder.getPosition());
//            }
//        });
        return new MyAdapter.programgolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final programgolder viewHolder, final int position) {
        viewHolder.t1.setTextColor(Color.BLACK);
        viewHolder.t1.setText(statename.get(position));
        viewHolder.t2.setTextColor(Color.BLACK);
        viewHolder.t2.setText("Active Cases: " + activecase.get(position));
        viewHolder.t3.setTextColor(Color.RED);
        viewHolder.t3.setText("Confirmed: " + confirmedcase.get(position));
        viewHolder.t4.setTextColor(Color.parseColor("#006400"));
        viewHolder.t4.setText("Recovered: " + recoveredcase.get(position));


        // Create layout manager with initial prefetch item count



        // Create sub item view adapter

        // For now simplicity I am doing all JSON parsing here if I have time I will do all this thing in the Item class which is for this purpose only
        // It's gonna mess here from now on

        String urlJsonObj = "https://api.covid19india.org/data.json";

        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(viewHolder.rvSubItem.getContext());

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                statename.get(position)
                try {
                    JSONArray Statecode = response.getJSONArray("statewise");

//                    private ArrayList<String> statename=new ArrayList<String>();;
                     ArrayList<String> activecas=new ArrayList<String>();
                     ArrayList<String> confirmedcas=new ArrayList<String>();
                    ArrayList<String> recoveredcas=new ArrayList<String>();
                    ArrayList<String> statenam=new ArrayList<String>();


                    for (int i = 1; i < Statecode.length(); i++)
                    {
                        JSONObject userdetail = Statecode.getJSONObject(i);

                        Log.d("Sumit",userdetail.getString("active"));
                        Log.d("sumit",userdetail.getString("state"));

                        activecas.add(userdetail.getString("active"));
                        recoveredcas.add(userdetail.getString("recovered"));
                        confirmedcas.add(userdetail.getString("confirmed"));
                        statenam.add(userdetail.getString("state"));
                        Log.d("test",activecase.get(i-1));

                    }

                    LinearLayoutManager layoutManager = new LinearLayoutManager(
                            viewHolder.rvSubItem.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    layoutManager.setInitialPrefetchItemCount(activecas.size());

                    viewHolder.rvSubItem.setLayoutManager(layoutManager);

                    SubItemAdapter subItemAdapter = new SubItemAdapter(activecas,recoveredcas,confirmedcas,statenam);
                    viewHolder.rvSubItem.setAdapter(subItemAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("api", "something is wrong  :-p " + error);
            }
        });
        requestQueue.add(jsonArrayRequest);



        viewHolder.rvSubItem.setRecycledViewPool(viewPool);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.rvSubItem.setVisibility(View.VISIBLE);
            }
        });
    }








    @Override
    public int getItemCount() {
        return activecase.size();
    }

    public static class programgolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4;
        Button button;
        RecyclerView rvSubItem;

        public programgolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.statenamec);

            t2=itemView.findViewById(R.id.activec);
            t3= itemView.findViewById(R.id.confirmedc);
            t4 = itemView.findViewById(R.id.recoveredc);
            rvSubItem=itemView.findViewById(R.id.district_rec);

            button=itemView.findViewById(R.id.button2);

        }
    }

}
