package com.example.exnetwork;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceHandler extends AsyncTask<String,Void,ArrayList> {
    // URL
    String URL_GETCOUNTRIES = "http://api.geonames.org/countryInfoJSON";
    ContentValues callParams;

    ProgressDialog pDialog;
    RecyclerView rcv;

    static final String DISPLAY = "display";
    static final String CREATE = "create";
    static final String UPDATE = "update";
    static final String DELETE = "delete";

    ServiceCaller serviceCaller = new ServiceCaller();

    Context context;
    ArrayList<Country> countries;

    public ServiceHandler(Context context, RecyclerView rcv, ContentValues params){
        this.context = context;
        this.rcv = rcv;
        callParams = params;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        switch (params[0]) {
            case DISPLAY:
                countries = new ArrayList<>();
                String json = serviceCaller.call(URL_GETCOUNTRIES, ServiceCaller.GET, callParams);
                if (json != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(json);
                        if (jsonObj != null) {
                            JSONArray accounts = jsonObj.getJSONArray("geonames");
                            for (int i = 0; i < accounts.length(); i++) {
                                JSONObject obj = (JSONObject) accounts.get(i);
                                Country country = new Country(obj.getString("countryName"),
                                        obj.getString("countryCode"),
                                        obj.getString("population"),
                                        obj.getString("areaInSqKm"));
                                countries.add(country);
                            }
                        }
                        else {
                            Log.d("JSON Data", "JSON data's format is incorrect!");
                            Country country = new Country("JSON data's format is incorrect!",
                                    "JSON Data", "0");
                            countries.add(country);
                        }
                    } catch (JSONException e) { e.printStackTrace(); }
                } else {
                    Log.d("JSON Data", "Didn't receive any data from server!");
                    Country country = new Country("Didn't receive any data from server!",
                            "JSON Data", "0");
                    countries.add(country);
                }
                break;
            case CREATE: break;
            case UPDATE: break;
            case DELETE: break;
        }
        return countries;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Proccesing..");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList ret) {
        super.onPostExecute(ret);
        if (pDialog.isShowing())
            pDialog.dismiss();
        if (ret == null)
            Toast.makeText(context, "Lỗi - Refresh lại", Toast.LENGTH_SHORT).show();
        loadData();
    }

    private void loadData() {
         CountryAdapter countryAdapter;
        if (countries == null) {
            return;
        }

        countryAdapter=new CountryAdapter(countries, context);
        // Tạo adapter cho listivew
        // Gắn adapter cho listview

        rcv.setAdapter(countryAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(context));
    }
    public ArrayList<Country> getData() {
        return countries;
    }
}

