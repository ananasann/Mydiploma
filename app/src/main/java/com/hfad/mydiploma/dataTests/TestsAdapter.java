package com.hfad.mydiploma.dataTests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTheory.TheoryAdapter;
import com.hfad.mydiploma.dataTheory.TheoryCard;

import java.util.List;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.ViewHolder> {


    public interface MyClickListener {
        void onItemClick(TestsCard item);
    }

    private List<TestsCard> mData;
    private LayoutInflater mInflater;
    private TestsAdapter.MyClickListener mClickListener;

    // data is passed into the constructor
    public TestsAdapter(Context context,/*List<Theory> data,*/ TestsAdapter.MyClickListener clickListener) {
        this.mInflater = LayoutInflater.from(context);
        // this.mData = data;
        this.mClickListener = clickListener;
    }

    public void setList(List<TestsCard> data) {
        this.mData = data;
        notifyDataSetChanged();
        //Уведомить адаптер, что изменился набор данных
    }

    // inflates the row layout from xml when needed
    @Override
    public TestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout_tests, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestsCard testsOnPosition = mData.get(position);

        String titleTest = testsOnPosition.titleTest;
        String itemNameTest = testsOnPosition.itemNameTest;

        holder.itemTitleTest.setText(titleTest);
        holder.itemNameTest.setText(itemNameTest);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(testsOnPosition);
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
        TextView itemTitleTest;
        TextView itemNameTest;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitleTest = itemView.findViewById(R.id.item_title_tests);
            itemNameTest = itemView.findViewById(R.id.item_name_tests);
        }
    }
}