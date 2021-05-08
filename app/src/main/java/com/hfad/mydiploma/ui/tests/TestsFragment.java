package com.hfad.mydiploma.ui.tests;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.ApiClient;
import com.hfad.mydiploma.ApiInterface;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTests.TestsAdapter;
import com.hfad.mydiploma.dataTests.TestsCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsFragment extends Fragment {

    private RecyclerView testsRecycler;
    private TestsAdapter testsAdapter;
    List<TestsCard> testsCardList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tests, container, false);
        testsRecycler = (RecyclerView) root.findViewById(R.id.tests_recycler);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiInterface testApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TestsCard>> tests = testApi.getTest();

       /* List<TestsCard> listOfTestCard = new ArrayList<TestsCard>();
        listOfTestCard.add(new TestsCard("Тема: Прореживание сигнала","Дата выполнения: 22.04.2022", testDataList));
        listOfTestCard.add(new TestsCard("Тема: Дискретизация","Дата выполнения: 29.04.2022", testDataList));*/

        TestsAdapter.MyClickListener listener = new TestsAdapter.MyClickListener() {
            @Override
            public void onItemClick(TestsCard item, Integer position) {
                Bundle args = new Bundle();
                args.putString("keyForNameTest", item.getTitleTest());
                args.putInt("keyForPositionTest", position);
                TestThemeFragment testThemeFrag = new TestThemeFragment();
                testThemeFrag.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.testsFrag, testThemeFrag, "fiaentTag")
                        .addToBackStack(null)
                        .commit();
            }
        };

        testsAdapter = new TestsAdapter(getContext(), listener);
        testsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        testsRecycler.setAdapter(testsAdapter);
        testsAdapter.setList(testsCardList);


        tests.enqueue(new Callback<List<TestsCard>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TestsCard>> call, Response<List<TestsCard>> response) {
                testsCardList = response.body();
                testsAdapter.setList(testsCardList);
            }

            @Override
            public void onFailure(Call<List<TestsCard>> call, Throwable t) {
            }
        });


    }
}