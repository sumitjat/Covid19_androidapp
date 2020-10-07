package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private String urlJsonObj = "https://api.covid19india.org/state_district_wise.json";
    TextView t1,t2,t3;
    String Active,Closed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);


        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONObject Statecode = response.getJSONObject("Madhya Pradesh");
                    JSONObject dis = Statecode.getJSONObject("districtData");
                    JSONObject Mydis = dis.getJSONObject("Narsinghpur");
                    Active= Mydis.getString("active");
                    Closed =Mydis.getString("confirmed");

                    t1.setText(Active);
                    t2.setText(Closed);


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

    public void next(View view)
    {
        Intent i =  new Intent(this, AllData.class);
        startActivity(i);
    }
}