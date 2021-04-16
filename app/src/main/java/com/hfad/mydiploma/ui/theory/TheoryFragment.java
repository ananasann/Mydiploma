package com.hfad.mydiploma.ui.theory;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.hfad.mydiploma.dataTheory.pager.CardData;
import com.hfad.mydiploma.dataTheory.pager.ImageAndDescription;
import com.hfad.mydiploma.dataTheory.pager.QuestAndAnsOptions;

import java.util.ArrayList;
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
            public void onItemClick(TheoryCard item) {
                //Toast.makeText(getContext(),"TAP " + item.getTitle(),Toast.LENGTH_SHORT).show();
                TheorQuizFragment theorQuizFrag = new TheorQuizFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.theorFragment, theorQuizFrag, "fiaentTag")
                        .addToBackStack(null)
                        .commit();
            }
        };

        theoryAdapter = new TheoryAdapter(getContext(), /*listOfDataTheory,*/ listener);
        theoryRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        theoryRecycler.setAdapter(theoryAdapter);
        //theoryAdapter.setList(listOfDataTheory);


        theor.enqueue(new Callback<List<TheoryCard>>(){ //метод.в очереди
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TheoryCard>> call, Response<List<TheoryCard>> response) {
                listOfDataTheory  = response.body();
                Log.d("henlo", "how are you" + listOfDataTheory);
                listOfDataTheory.forEach(item -> Log.d("tag",  " look title " + item.getTitle()));
                theoryAdapter.setList(listOfDataTheory);
            }

            @Override
            public void onFailure(Call<List<TheoryCard>> call, Throwable t) {
                Log.d("chmo", t.getLocalizedMessage());
            }
        });


    }
}