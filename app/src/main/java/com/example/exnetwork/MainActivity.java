package com.example.exnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RecyclerView rcvCountries;
    ServiceHandler serviceHandler;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvCountries = (RecyclerView)findViewById(R.id.rcvCountries);
        btn = (Button)findViewById(R.id.btnRefresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllCountries();
            }
        });

        getAllCountries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((CountryAdapter) rcvCountries.getAdapter()).getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((CountryAdapter) rcvCountries.getAdapter()).getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }

    private void getAllCountries() {
        ContentValues callParams = new ContentValues();
        callParams.put("username", "tdnghia");
        serviceHandler = new ServiceHandler(MainActivity.this, rcvCountries, callParams);
        serviceHandler.execute(ServiceHandler.DISPLAY);
    }
}
