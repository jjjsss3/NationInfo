package com.example.exnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

public class Country_Detail_Activity extends AppCompatActivity {
    String pattern="###,###.###";
    DecimalFormat decimalFormat =new DecimalFormat(pattern);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        Bundle extras=getIntent().getExtras();
        ((TextView) findViewById(R.id.tv_cd_code)).setText(extras.getString("countryCode"));
        ((TextView) findViewById(R.id.tv_cd_name)).setText(extras.getString("countryName"));
        ((TextView) findViewById(R.id.tv_cd_pop)).setText(decimalFormat.format(Double.parseDouble(extras.getString("countryPop"))));
        ((TextView) findViewById(R.id.tv_cd_area)).setText(decimalFormat.format(Double.parseDouble(extras.getString("countryArea"))));
        ((TextView)findViewById(R.id.cd_km2)).setText(Html.fromHtml("km<sup><small> 2 </small></sup>"));

        Glide.with(this).load(extras.getString("url_flag")).placeholder(R.drawable.progress_animation).into((ImageView) findViewById(R.id.iv_cd_flag));
        Glide.with(this).load(extras.getString("url_map")).placeholder(R.drawable.progress_animation).into((ImageView) findViewById(R.id.iv_cd_map));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}