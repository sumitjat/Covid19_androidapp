package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class AllData extends AppCompatActivity {

    String Active,Recover,Total,State;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private ArrayList<String> statename=new ArrayList<String>();;
    private ArrayList<String> activecase=new ArrayList<String>();
    private ArrayList<String> confirmedcase=new ArrayList<String>();
    private ArrayList<String> recoveredcase=new ArrayList<String>();



    String urlJsonObj ="https://api.covid19india.org/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);

         recyclerView=findViewById(R.id.alldata_rec);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)



        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray Statecode = response.getJSONArray("statewise");

                    for (int i = 1; i < Statecode.length(); i++)
                    {
                        JSONObject userdetail = Statecode.getJSONObject(i);

                        Log.d("Sumit",userdetail.getString("active"));
                        Log.d("sumit",userdetail.getString("state"));

                       activecase.add(userdetail.getString("active"));
                       recoveredcase.add(userdetail.getString("recovered"));
                       confirmedcase.add(userdetail.getString("confirmed"));
                       statename.add(userdetail.getString("state"));
                       Log.d("test",activecase.get(i-1));

                    }

                    mAdapter = new MyAdapter(activecase,recoveredcase,confirmedcase,statename);
                    recyclerView.setAdapter(mAdapter);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("api", "something is wrong  :-p "+error);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}