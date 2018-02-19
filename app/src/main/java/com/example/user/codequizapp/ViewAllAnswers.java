package com.example.user.codequizapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Created by USER on 12/11/2017.
 */

public class ViewAllAnswers extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    InputStream is = null;
    ArrayAdapter<String> adapter;
    String parse_stock_json = "https://oasyspointofsale.000webhostapp.com/pointofsale/view_result.php";
    String line = null;
    String result = null;
    String[] data;
    View v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_answers);

        listView = (ListView)findViewById(R.id.listViewAnswers);

        listView.setOnItemClickListener(this);

        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    if(isNetworkAvailable()){


        getData();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);



    }else{

        Snackbar snackbar = Snackbar.make(view,"Network unavailable check your connection settings",Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .setAction("DISMISS",new View.OnClickListener(){


                    @Override
                    public void onClick(View v) {


                    }
                });

        snackbar.show();
    }
    }

    private void getData() {

        try {

            URL url = new URL(parse_stock_json);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);

            is = new BufferedInputStream(con.getInputStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //read the contents
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {

                    sb.append(line + "\n");

                }

                is.close();
                result = sb.toString();

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            //parse the json data

            try {

                JSONArray ja = new JSONArray(result);
                JSONObject jo = null;

                data = new String[ja.length()];

                for (int i = 0; i < ja.length(); i++) {

                    jo = ja.getJSONObject(i);
                    data[i] = jo.getString("item_name");

                }

            } catch (Exception ef) {

                ef.printStackTrace();
            }

        }
    }

    public boolean isNetworkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();


    }
}
