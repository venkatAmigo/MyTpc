package com.vvitmdc.chats.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.vvitmdc.chats.ChatActivity;
import com.vvitmdc.chats.R;
import com.vvitmdc.chats.UserData;

import java.util.ArrayList;

public class ExpertsAdapter extends RecyclerView.Adapter<ExpertsAdapter.ExpertHolder> {
    private ArrayList<String> expertsList;
    private Context context;
    static int location=0;

    public ExpertsAdapter(ArrayList<String> expertsList, Context context) {
        this.expertsList = expertsList;
        this.context = context;
    }
    @NonNull
    @Override
    public ExpertsAdapter.ExpertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.experts_item,parent,false);
        return new ExpertHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertsAdapter.ExpertHolder holder, final int position) {
        holder.item.setText("TPO");
        location=position;
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ChatActivity.class);
                UserData.chatWith=expertsList.get(position);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (expertsList!=null)
        return expertsList.size();
        else
            return 0;
    }

    public class ExpertHolder extends ViewHolder {
        TextView item;
        public ExpertHolder(@NonNull final View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.expert_text_view);
        }
    }
}
