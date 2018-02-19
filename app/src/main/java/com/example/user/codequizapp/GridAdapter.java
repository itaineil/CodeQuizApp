package com.example.user.codequizapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

/**
 * Created by USER on 10/28/2017.
 */

public class GridAdapter extends BaseAdapter {

    public Context mContext;
    public String [] mCodeNames;
    public int [] mCodeImages;
    LayoutInflater mLayoutInflater;

    public GridAdapter(Context context,String [] names,int [] images ){

        this.mContext = context;
        this.mCodeNames = names;
        this.mCodeImages = images;
        mLayoutInflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return mCodeNames.length;
    }

    @Override
    public Object getItem(int position) {
        return mCodeNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        convertView = mLayoutInflater.inflate(R.layout.single_grid_item,null);
        viewHolder.bootstrapCircleThumbnail = (BootstrapCircleThumbnail) convertView.findViewById(R.id.singleImageView);
        viewHolder.awesomeTextView = (AwesomeTextView) convertView.findViewById(R.id.singleTextView);

        viewHolder.bootstrapCircleThumbnail.setImageResource(mCodeImages[position]);
        viewHolder.awesomeTextView.setText(mCodeNames[position]);

        return convertView;
    }

    public static class ViewHolder{

        public BootstrapCircleThumbnail bootstrapCircleThumbnail;
        public AwesomeTextView awesomeTextView;
    }
}
