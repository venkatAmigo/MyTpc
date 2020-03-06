package com.vvitmdc.chats.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvitmdc.chats.R;

import java.util.ArrayList;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatViewHolder> {
    private final Context context;
    private final ArrayList<String> cname;
    private final ArrayList<String> packs;

    public StatAdapter(Context context, ArrayList<String> cname, ArrayList<String> packs, ArrayList<String> nosels) {
        this.context = context;
        this.cname = cname;
        this.packs = packs;
        this.nosels = nosels;
    }

    private final ArrayList<String> nosels;
    @NonNull
    @Override
    public StatAdapter.StatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate( R.layout.stat_item,parent,false);
        return new StatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatAdapter.StatViewHolder holder, int position) {
        holder.cnameT.setText(cname.get(position));
        holder.packT.setText(packs.get(position));
        holder.noT.setText(nosels.get(position));
    }

    @Override
    public int getItemCount() {
        if(cname!=null)
            return cname.size();
        else
            return 0;
    }

    public class StatViewHolder extends RecyclerView.ViewHolder {
        TextView cnameT,packT,noT;
        public StatViewHolder(@NonNull View itemView) {
            super(itemView);
            cnameT=itemView.findViewById(R.id.stat_cname);
            packT=itemView.findViewById(R.id.stat_pack_tv);
            noT=itemView.findViewById(R.id.stat_sel_tv);
        }
    }
}
