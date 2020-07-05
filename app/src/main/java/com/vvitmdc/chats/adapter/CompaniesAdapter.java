package com.vvitmdc.chats.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vvitmdc.chats.R;
import com.vvitmdc.chats.Result;

import java.util.List;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.MyViewHolder> {

    private Context context;
    private List<Result> list;
    public CompaniesAdapter(Context context, List<Result> list) {
        this.context =context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Result item = list.get(position);
        holder.name.setText(item.getCompanyt());
        holder.pack.setText(item.getPackageInLakhs());
        holder.price.setText(item.getTotal());

    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,pack,price;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.com);
            price = itemView.findViewById(R.id.tot);
            pack=itemView.findViewById(R.id.pac);

        }
    }
}
