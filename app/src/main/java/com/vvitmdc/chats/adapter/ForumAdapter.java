package com.vvitmdc.chats.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvitmdc.chats.CommentsActivity;
import com.vvitmdc.chats.R;

import java.util.ArrayList;
public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumHolder> {
    private final Context context;
    private final ArrayList<String> cname;
    private final ArrayList<String> roles;
    private final ArrayList<String> dates;
    private final ArrayList<String> quals;
    private final ArrayList<String> ids;
    public ForumAdapter(Context context,ArrayList<String> ids, ArrayList<String> cname, ArrayList<String> roles,
                        ArrayList<String> dates, ArrayList<String> quals, ArrayList<String> packs, ArrayList<String> venues) {
        this.context = context;
        this.cname = cname;
        this.ids=ids;
        this.roles = roles;
        this.dates = dates;
        this.quals = quals;
        this.packs = packs;
        this.venues = venues;
    }

    private final ArrayList<String> packs;
    private final ArrayList<String> venues;


    @NonNull
    @Override
    public ForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.forum_item,parent,false);
        return new ForumHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ForumHolder holder, final int position) {
       /* long tim=Long.parseLong(times.get(position));
        long curtime=new Time(System.currentTimeMillis()).getTime();
        long diff=curtime-tim;
        long min=diff/(1000L*60L)%60L;
        long hours=diff/(60L*60L*1000L)%24;
        long days=diff/(24L*60L*60L*1000L)%30L;
        long mon=diff/(30L*24L*60L*60L*1000L)%12L;
        long years=diff/(12L*30L*24L*60L*60L*1000L);
     *//*   int days=(int)(diff/(1000*60*60*24));
        int hours=(int)((diff-(1000*60*60*24*days))/(1000*60*60));
        int min=(int)(diff-(1000*60*60*24*days)-(1000*60*60*hours))/(1000*60);*//*
        String dif=names.get(position)+"\n";
        if(years>0)
            dif+=years+" Years ago ";
        else if(mon>0)
            dif+=mon+" Months ago";
        else if(days>0)
            dif+=days+" Days ago";
        else if(hours>0)
            dif+=hours+" Hours ago";
        else  if(min>0)
            dif+=min+" Mins ago ";
        else
        {
            dif+=" Just Now";
        }*/
        holder.cnameT.setText(cname.get(position));
        holder.jobT.setText(roles.get(position));
        holder.dateT.setText(dates.get(position));
        holder.qualT.setText(quals.get(position));
        holder.packT.setText(packs.get(position));
        holder.venT.setText(venues.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, CommentsActivity.class);
                i.putExtra("id",ids.get(position));
                i.putExtra("question",cname.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(ids!=null)
            return ids.size();
        else
            return 0;
    }

    public class ForumHolder extends RecyclerView.ViewHolder {
        TextView cnameT,jobT,dateT,qualT,packT,venT;
        final LinearLayout linearLayout;
        ForumHolder(@NonNull View itemView) {
            super(itemView);
            cnameT=itemView.findViewById(R.id.cname);
            jobT=itemView.findViewById(R.id.role_tv);
            dateT=itemView.findViewById(R.id.date_tv);
            qualT=itemView.findViewById(R.id.qualification_tv);
            packT=itemView.findViewById(R.id.pack_tv);
            venT=itemView.findViewById(R.id.venue_tv);
            linearLayout=itemView.findViewById(R.id.forum_id);

        }
    }

}
