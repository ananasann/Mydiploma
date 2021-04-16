package com.hfad.mydiploma.dataTheory.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hfad.mydiploma.R;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private LayoutInflater mInflater;


    public PagerAdapter(Context context, List<Object> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolderImDes extends RecyclerView.ViewHolder {
        ImageView imageUrl;
        TextView textTheor;

        ViewHolderImDes(View ImDes) {
            super(ImDes);
            imageUrl = ImDes.findViewById(R.id.imageView);
            textTheor = ImDes.findViewById(R.id.text_theor_descr);
        }

        public void bind() {
            itemView.setBackgroundColor(Color.GRAY);
        }
    }

    public class ViewHolderQuesA extends RecyclerView.ViewHolder {
        TextView textQuiz;
        TextView ansOptions;
        TextView corrAnsNum;

        ViewHolderQuesA(View qA) {
            super(qA);
            textQuiz = qA.findViewById(R.id.text_quiz);
            ansOptions = qA.findViewById(R.id.ans_op);
            corrAnsNum = qA.findViewById(R.id.corr_ans_num);
        }

        public void bind() {
            itemView.setBackgroundColor(Color.DKGRAY);
        }

    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    @Override
    public int getItemCount() {
        if(mData != null){
            return mData.size();
        }
        return 0;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                View ImDes = mInflater.inflate(R.layout.img_and_descr, parent, false);
                viewHolder =  new ViewHolderImDes(ImDes);
            break;
            case 2:
                View qA = mInflater.inflate(R.layout.quest_and_ans_options, parent, false);
                viewHolder =  new ViewHolderQuesA(qA);
                break;
            default:
                View view = mInflater.inflate(R.layout.fragment_tests, parent, false);
                viewHolder =  new ViewHolderQuesA(view);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object obgectOnPosition = mData.get(position);

        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImDes viewHolderImDes = (ViewHolderImDes)holder;

                viewHolderImDes.bind();

                Log.d("TAG","1");
                break;

            case 2:
                ViewHolderQuesA viewHolderQuesA = (ViewHolderQuesA)holder;
                Log.d("TAG","2");
                viewHolderQuesA.bind();

                break;
        }
    }

}



