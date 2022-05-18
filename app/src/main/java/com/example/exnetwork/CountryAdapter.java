package com.example.exnetwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {
    private ArrayList<Country> listCountries;
    private ArrayList<Country> listCountriesOld;

    private Map<String,Bitmap> mBitmaps=new HashMap<>();
    // Lưu Context để dễ dàng truy cập
    private Context context;
    Random random=new Random();

    public CountryAdapter(ArrayList<Country> listCountries, Context context) {
        this.listCountries = listCountries;
        this.context = context;
        this.listCountriesOld=listCountries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View view =
                inflater.inflate(R.layout.country_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country =  listCountries.get(position);
        Glide.with(context).load(country.get_URL_FLAG_GIF()).placeholder(R.drawable.progress_animation).into(holder.im_item);
        holder.tv_name.setText(country.getCountryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Country_Detail_Activity.class);
                intent.putExtra("countryCode", country.countryCode);
                intent.putExtra("countryName", country.countryName);
                intent.putExtra("countryPop", country.countryPopulation);
                intent.putExtra("countryArea", country.areaInSqKm);
                intent.putExtra("url_flag", country.get_URL_FLAG_GIF());
                intent.putExtra("url_map", country.get_UR_MAP_IMAGE());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCountries.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch=constraint.toString();
                if(strSearch.isEmpty()){
                    listCountries=listCountriesOld;
                }else {
                     ArrayList<Country> list=new ArrayList<>();
                    for (Country c: listCountriesOld
                         ) {
                            if(c.getCountryName().toLowerCase().contains(strSearch.toLowerCase()))
                                list.add(c);
                    }
                    listCountries=list;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=listCountries;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listCountries= (ArrayList<Country>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView im_item;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            im_item = itemView.findViewById(R.id.countryFlag);
            tv_name = itemView.findViewById(R.id.countryName);
        }
    }
}