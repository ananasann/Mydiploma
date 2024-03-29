package com.hfad.mydiploma.dataTests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;

import java.util.List;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.ViewHolder> {


    public interface MyClickListener {
        void onItemClick(TestsCard item, Integer position);
    }

    private List<TestsCard> mData;
    private LayoutInflater mInflater;
    private TestsAdapter.MyClickListener mClickListener;

    public TestsAdapter(Context context, TestsAdapter.MyClickListener clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mClickListener = clickListener;
    }

    public void setList(List<TestsCard> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public TestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout_tests, parent, false);
        return new ViewHolder(view);
    }

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
                mClickListener.onItemClick(testsOnPosition, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        //return mData.size();
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitleTest;
        TextView itemNameTest;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitleTest = itemView.findViewById(R.id.item_title_tests);
            itemNameTest = itemView.findViewById(R.id.item_name_tests);
        }
    }
}