package com.peterkimeli.adalabs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.peterkimeli.adalabs.R;
import com.peterkimeli.adalabs.adapters.MyAdapter;
import com.peterkimeli.adalabs.model.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA="http://elacomms.com/kimeli/adalab.json";

    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();
        loadRecyclerViewData();
//        for (int i=0; i<10;i++){
//            ListItem listItem =new ListItem(
//                    "Heading " + (i+1),
//                    "Descriptions come here"
//            );
//
//            listItems.add(listItem);

//        }
//        adapter=new MyAdapter(listItems,this);
//        recyclerView.setAdapter(adapter);
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONArray array=jsonObject.getJSONArray("heroes");

                            for (int i=0; i<array.length();i++){
                                JSONObject jsonObject1=array.getJSONObject(i);
                                ListItem item=new ListItem(
                                        jsonObject1.getString("name"),
                                        jsonObject1.getString("about"),
                                        jsonObject1.getString("image")
                                );
                                listItems.add(item);

                            }

                            adapter=new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyErrorr) {

            progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),volleyErrorr.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        // data request from the server
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}