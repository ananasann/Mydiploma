package com.hfad.mydiploma.dataTheory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;

import java.util.ArrayList;
import java.util.List;

public class TheoryAdapter  extends RecyclerView.Adapter<TheoryAdapter.ViewHolder> {


    public interface MyClickListener {
        void onItemClick(Theory item);
    }

    private List<Theory> mData;
    private LayoutInflater mInflater;
    private MyClickListener mClickListener;

    // data is passed into the constructor
    public TheoryAdapter(Context context,/*List<Theory> data,*/ MyClickListener clickListener) {
        this.mInflater = LayoutInflater.from(context);
       // this.mData = data;
        this.mClickListener = clickListener;
    }

    public void setList(List<Theory> data) {
        this.mData = data;
        notifyDataSetChanged();
        //Уведомить адаптер, что изменился набор данных
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);


    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Theory theoryOnPosition = mData.get(position);

        String title = theoryOnPosition.title;
        String itemName = theoryOnPosition.itemName;

        holder.itemTitle.setText(title);
        holder.itemName.setText(itemName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(theoryOnPosition);
            }
        });
    }


    // total number of rows
    @Override
    public int getItemCount() {
        //return mData.size();
        if(mData != null){
            return mData.size();
        }
        return 0;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {// implements View.OnClickListener {
        TextView itemTitle;
        TextView itemName;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemName = itemView.findViewById(R.id.item_name);

            //itemView.setOnClickListener(this);
        }

        //public void bind

        /*@Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }*/
    }

    // convenience method for getting data at click position
    /*String getItem(int id) {
        return mData.get(id);
    }*/


}