package com.hfad.mydiploma.ui.theory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTheory.Theory;
import com.hfad.mydiploma.dataTheory.TheoryAdapter;

import java.util.ArrayList;

public class TheoryFragment extends Fragment {

    private TheoryViewModel theoryViewModel;
/*
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        theoryViewModel =
                new ViewModelProvider(this).get(TheoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_theory, container, false);
        final TextView textView = root.findViewById(R.id.text_theory);
        theoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_theory, container, false);


        TextView tv1;

        theoryRecycler = (RecyclerView) root.findViewById(R.id.theory_recycler);


        return root;
    }

    private ArrayList<Theory> listOfDataTheory;
    private RecyclerView theoryRecycler;
    private TheoryAdapter theoryAdapter;

    @Override
    public void onResume() {
        super.onResume();

        listOfDataTheory = new ArrayList< Theory >();

        listOfDataTheory.add(new Theory("Theme 1","Фильтрация"));
        listOfDataTheory.add(new Theory("Theme 2", "Спектр2"));
        listOfDataTheory.add(new Theory("Theme 3", "Спектр3"));
        listOfDataTheory.add(new Theory("Theme 4", "Спектр4"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));
        listOfDataTheory.add(new Theory("Theme 5", "Спектр5"));

        theoryAdapter = new TheoryAdapter(getContext(), listOfDataTheory);

        theoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        theoryRecycler.setAdapter(theoryAdapter);

    }
}