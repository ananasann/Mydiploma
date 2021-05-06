package com.hfad.mydiploma.dataTheory.pager;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hfad.mydiploma.R;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private LayoutInflater mInflater;

    public PagerAdapter(Context context/*, List<Object> data*/) {
        this.mInflater = LayoutInflater.from(context);
        /*this.mData = data;*/
    }

    public void setList(List<Object> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolderImDes extends RecyclerView.ViewHolder {
        ImageView imageUrlH;
        TextView textTheorH;

        ViewHolderImDes(View ImDes) {
            super(ImDes);
            imageUrlH = ImDes.findViewById(R.id.imageView);
            textTheorH = ImDes.findViewById(R.id.text_theor_descr);
        }

        public void bind(ImageAndDescription imageAndDescription) {
            String textTeor = imageAndDescription.textTheor;
            String imageUrl = imageAndDescription.imageUrl;

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new RoundedCorners(20));
            Glide
                    .with(itemView.getContext())
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(imageUrlH);
            Log.d("vse", "loxi");
            textTheorH.setText(textTeor);
            itemView.setBackgroundColor(Color.rgb(178, 178, 178));
        }
    }

    public class ViewHolderQuesA extends RecyclerView.ViewHolder {
        TextView textQuizH;
        RadioButton rB1;
        RadioButton rB2;
        RadioButton rB3;
        Button qaCheck;
        RadioGroup qaGroup;

        ViewHolderQuesA(View qA) {
            super(qA);
            textQuizH = qA.findViewById(R.id.text_quiz);
            rB1 = qA.findViewById(R.id.radioButton);
            rB2 = qA.findViewById(R.id.radioButton2);
            rB3 = qA.findViewById(R.id.radioButton3);
            qaCheck = qA.findViewById(R.id.qaCheck);
            qaGroup = qA.findViewById(R.id.ans_op);
        }

        public void bind(QuestAndAnsOptions questAndAnsOptions) {

            //itemView.findViewById()

            String textQuiz = questAndAnsOptions.textQuiz;
            List<String> ansOptions = questAndAnsOptions.ansOptions;
            Integer corrAnsNum = questAndAnsOptions.corrAnsNum;


            textQuizH.setText(textQuiz);
            rB1.setText(ansOptions.get(0));
            rB2.setText(ansOptions.get(1));
            rB3.setText(ansOptions.get(2));
            qaCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("bla","blabla" + qaGroup.getCheckedRadioButtonId());
                    if(qaGroup.getCheckedRadioButtonId() != -1) {
                        View view = itemView.findViewById(qaGroup.getCheckedRadioButtonId());
                        int index = qaGroup.indexOfChild(view);
                        int trueIndex = index / 2;

                        if (trueIndex == questAndAnsOptions.corrAnsNum - 1) {
                            Toast.makeText(itemView.getContext(), "Correct", Toast.LENGTH_SHORT).show();
                            view.setBackgroundColor(Color.rgb(	114, 202, 124));

                        } else {
                            Toast.makeText(itemView.getContext(), "Not correct", Toast.LENGTH_SHORT).show();
                            view.setBackgroundColor(Color.rgb(	202, 124, 114));
                        }

                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rB1.setBackgroundColor(Color.TRANSPARENT);
                                rB2.setBackgroundColor(Color.TRANSPARENT);
                                rB3.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }, 1000);
                    } else  {
                        Toast.makeText(itemView.getContext(), "Not checked", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            itemView.setBackgroundColor(Color.rgb(220, 220, 220));
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
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                View ImDes = mInflater.inflate(R.layout.img_and_descr, parent, false);
                viewHolder = new ViewHolderImDes(ImDes);
                break;
            default:
                View view = mInflater.inflate(R.layout.quest_and_ans_options, parent, false);
                viewHolder = new ViewHolderQuesA(view);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object obgectOnPosition = mData.get(position);

        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImDes viewHolderImDes = (ViewHolderImDes) holder;

                viewHolderImDes.bind((ImageAndDescription) obgectOnPosition);
                Log.d("TAG", "1");
                break;

            case 2:
                ViewHolderQuesA viewHolderQuesA = (ViewHolderQuesA) holder;

                Log.d("TAG", "2");
                viewHolderQuesA.bind((QuestAndAnsOptions) obgectOnPosition);

                break;
        }
    }


}



