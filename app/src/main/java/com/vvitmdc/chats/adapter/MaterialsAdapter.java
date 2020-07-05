package com.vvitmdc.chats.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vvitmdc.chats.PdfView;
import com.vvitmdc.chats.R;
import com.vvitmdc.chats.Upload;

import java.util.List;

public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.MyViewHolder1>  {
    private Context context;
    private List<Upload> list;
    public MaterialsAdapter(Context context, List<Upload> list) {
        this.context =context;
        this.list = list;
    }

    @Override
    public MaterialsAdapter.MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.material_item,parent,false);
        return new MyViewHolder1(itemView);
    }

    @Override
    public void onBindViewHolder(MaterialsAdapter.MyViewHolder1 holder, int position) {

        Upload item = list.get(position);
        holder.name.setText(item.getName());


    }


    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }



    public class MyViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name,pack,price;


        public MyViewHolder1(View itemView) {
                    super(itemView);
                    name = itemView.findViewById(R.id.mat);
                    itemView.setOnClickListener((View.OnClickListener) this);

        }


        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, PdfView.class);
            intent.putExtra("url",list.get(getAdapterPosition()).getUrl());
            intent.putExtra("name",list.get(getAdapterPosition()).getName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
