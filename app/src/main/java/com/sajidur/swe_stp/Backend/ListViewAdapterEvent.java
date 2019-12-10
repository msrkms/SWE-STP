package com.sajidur.swe_stp.Backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sajidur.swe_stp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewAdapterEvent extends BaseAdapter {

    private Context context;
    private ArrayList<Events>dataModelEventsArrayList;

  public  ListViewAdapterEvent(Context context,ArrayList<Events>dataModelEventsArrayList){
        this.context=context;
        this.dataModelEventsArrayList=dataModelEventsArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }


    @Override
    public int getCount() {
        return dataModelEventsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataModelEventsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewAdapterEvent.ViewHolder holder;

        if(view==null){
            holder= new ListViewAdapterEvent.ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.eventlistitem,null,true);
            holder.imageViewImage=(ImageView)view.findViewById(R.id.eventlistImage);
            holder.textViewTitle=(TextView)view.findViewById(R.id.eventlistTitle);
            view.setTag(holder);
        }else{
            holder=(ListViewAdapterEvent.ViewHolder)view.getTag();
        }

        String imglink = dataModelEventsArrayList.get(i).getImageUrl();
        Picasso.get().load(imglink).into(holder.imageViewImage);
        holder.textViewTitle.setText(dataModelEventsArrayList.get(i).getTitle());
        System.out.println(dataModelEventsArrayList.get(i).getTitle());
        System.out.println(dataModelEventsArrayList.get(i).getImageUrl());

        return view;
    }



    private class ViewHolder {

        protected TextView textViewTitle;
        protected ImageView imageViewImage;

    }

}
