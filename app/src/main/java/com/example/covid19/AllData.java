package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;

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

import static java.nio.file.attribute.AclEntryType.ALLOW;

public class AllData extends AppCompatActivity {

    String Active,Recover,Total,State;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private ArrayList<String> statename=new ArrayList<String>();;
    private ArrayList<String> activecase=new ArrayList<String>();
    private ArrayList<String> confirmedcase=new ArrayList<String>();
    private ArrayList<String> recoveredcase=new ArrayList<String>();
    Parcelable parce;
    private final static String KEY = "Some";




    String urlJsonObj ="https://api.covid19india.org/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {


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

                    mAdapter = new MyAdapter(activecase,recoveredcase,confirmedcase,statename,getApplicationContext(),layoutManager);

                    recyclerView.setAdapter(mAdapter);




                    recyclerView.restoreDefaultFocus();






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



//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//       mAdapter.onSaveInstanceState(outState);
// }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mAdapter.onRestoreInstanceState(savedInstanceState);
//    }

}