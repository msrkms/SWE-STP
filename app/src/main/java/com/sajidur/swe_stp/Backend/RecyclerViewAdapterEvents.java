package com.sajidur.swe_stp.Backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.swe_stp.R;

import java.util.ArrayList;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.ViewHolder> {

    private Context mContext;
    private ArrayList<Events>eventsArrayList;

    public RecyclerViewAdapterEvents(Context context,ArrayList<Events>eventsArrayList){
        mContext=context;
        this.eventsArrayList=eventsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlistitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(eventsArrayList!=null){
            holder.textViewTitle.setText(eventsArrayList.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {

        if(eventsArrayList==null){
            return 0;
        }
        else{
            return eventsArrayList.size();
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView imageViewImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewImage=(ImageView)itemView.findViewById(R.id.eventlistImage);
            textViewTitle=(TextView)itemView.findViewById(R.id.eventlistTitle);
        }
    }
}
