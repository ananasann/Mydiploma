package com.hfad.mydiploma.dataTests.onetest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OneTestAdapter extends RecyclerView.Adapter<OneTestAdapter.ViewHolder> {

    private List<OneTestData> mData;
    private LayoutInflater mInflater;
    private MyEditTextListener mTextListener;

    public interface MyEditTextListener {
        public void onTextChanged(Integer position, Integer answer);
    }

    ;


    public OneTestAdapter(Context context, MyEditTextListener listener ) {
        mInflater = LayoutInflater.from(context);
        mTextListener = listener;
    }

    public void setList(List<OneTestData> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_for_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OneTestData oneTestDataOnPosition = mData.get(position);

        String questTest = oneTestDataOnPosition.questTest;
        String ansOpTest = oneTestDataOnPosition.ansOpTest;
        Integer corrAnsTest = oneTestDataOnPosition.corrAnsTest;

        holder.questTest.setText(questTest);
        holder.ansOpTest.setText(ansOpTest);
        holder.editTextTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int value;

                if (s.length() < 1)
                    value = -1;
                else
                    value = Integer.parseInt(s.toString());
                mTextListener.onTextChanged(position, value);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        TextView questTest;
        TextView ansOpTest;
        EditText editTextTest;
        Integer corrAnsTest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questTest = itemView.findViewById(R.id.quesTest);
            ansOpTest = itemView.findViewById(R.id.ansOpTest);
            editTextTest = itemView.findViewById(R.id.editTextTest);
        }
    }
}
