package com.hfad.mydiploma.ui.theory;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.ApiInterface;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.ApiClient;
import com.hfad.mydiploma.dataTheory.TheoryCard;
import com.hfad.mydiploma.dataTheory.TheoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheoryFragment extends Fragment {

    List<TheoryCard> listOfDataTheory;
    private RecyclerView theoryRecycler;
    private TheoryAdapter theoryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_theory, container, false);
        TextView tv1;
        theoryRecycler = (RecyclerView) root.findViewById(R.id.theory_recycler);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiInterface theoryApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TheoryCard>> theor = theoryApi.getTheor();

        //List<TheoryCard> listOfTheorCard = new ArrayList<TheoryCard>();
        //listOfTheorCard.add(new TheoryCard("Тема 1","Фильтрация", listOfCardData));
        //listOfTheorCard.add(new TheoryCard("Тема 2", "Спектр2", listOfCardData));

        TheoryAdapter.MyClickListener listener = new TheoryAdapter.MyClickListener() {
            @Override
            public void onItemClick(TheoryCard item, Integer position) {
                Bundle args = new Bundle();
                args.putString("keyForName", item.getItemName());
                args.putInt("keyForPosition", position);
                TheorQuizFragment theorQuizFrag = new TheorQuizFragment();
                theorQuizFrag.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.theorFragment, theorQuizFrag, "fiaentTag")
                        .addToBackStack(null)
                        .commit();
            }
        };

        theoryAdapter = new TheoryAdapter(getContext(), listener);
        theoryRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));

        theor.enqueue(new Callback<List<TheoryCard>>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TheoryCard>> call, Response<List<TheoryCard>> response) {
                listOfDataTheory  = response.body();
                theoryAdapter.setList(listOfDataTheory);
                theoryRecycler.setAdapter(theoryAdapter);
            }

            @Override
            public void onFailure(Call<List<TheoryCard>> call, Throwable t) {
            }
        });
    }
}