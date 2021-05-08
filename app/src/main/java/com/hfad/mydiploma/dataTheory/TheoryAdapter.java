package com.hfad.mydiploma.dataTheory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;

import java.util.List;

public class TheoryAdapter extends RecyclerView.Adapter<TheoryAdapter.ViewHolder> {


    public interface MyClickListener {
        void onItemClick(TheoryCard item, Integer position);
    }

    private List<TheoryCard> mData;
    private LayoutInflater mInflater;
    private MyClickListener mClickListener;

    public TheoryAdapter(Context context, MyClickListener clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mClickListener = clickListener;
    }

    public void setList(List<TheoryCard> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TheoryCard theoryOnPosition = mData.get(position);

        String title = theoryOnPosition.title;
        String itemName = theoryOnPosition.itemName;

        holder.itemTitle.setText(title);
        holder.itemName.setText(itemName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(theoryOnPosition, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemName;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }
}