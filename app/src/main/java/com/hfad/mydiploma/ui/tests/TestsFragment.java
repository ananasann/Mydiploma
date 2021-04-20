package com.hfad.mydiploma.ui.tests;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.ApiClient;
import com.hfad.mydiploma.ApiInterface;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTests.TestsAdapter;
import com.hfad.mydiploma.dataTests.TestsCard;
import com.hfad.mydiploma.dataTheory.TheoryAdapter;
import com.hfad.mydiploma.dataTheory.TheoryCard;
import com.hfad.mydiploma.ui.theory.TheorQuizFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsFragment extends Fragment {

    private RecyclerView testsRecycler;
    private TestsAdapter testsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tests, container, false);
        testsRecycler = (RecyclerView) root.findViewById(R.id.tests_recycler);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //ApiInterface testApi = ApiClient.getClient().create(ApiInterface.class);
        //Call<List<TestsCard>> test = testApi.getTest();

        List<TestsCard> listOfTestCard = new ArrayList<TestsCard>();
        listOfTestCard.add(new TestsCard("Тема: Прореживание сигнала","Дата выполнения: 22.04.2022"));
        listOfTestCard.add(new TestsCard("Тема: Дискретизация","Дата выполнения: 29.04.2022"));

        TestsAdapter.MyClickListener listener = new TestsAdapter.MyClickListener() {
            @Override
            public void onItemClick(TestsCard item) {
                Toast.makeText(getContext(),"TAP " + item.getTitleTest(),Toast.LENGTH_SHORT).show();
                /*TheorQuizFragment theorQuizFrag = new TheorQuizFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.theorFragment, theorQuizFrag, "fiaentTag")
                        .addToBackStack(null)
                        .commit();*/
            }
        };

        testsAdapter = new TestsAdapter(getContext(), /*listOfDataTheory,*/ listener);
        testsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //testsRecycler.setLayoutManager(new GridLayoutManager(getActivity(),1));
        testsRecycler.setAdapter(testsAdapter);
        testsAdapter.setList(listOfTestCard);


       /* theor.enqueue(new Callback<List<TheoryCard>>(){ //метод.в очереди
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
        });*/


    }
}