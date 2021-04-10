package com.hfad.mydiploma.ui.theory;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.mydiploma.ApiInterface;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTheory.ApiClient;
import com.hfad.mydiploma.dataTheory.Theory;
import com.hfad.mydiploma.dataTheory.TheoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheoryFragment extends Fragment {

    List<Theory> listOfDataTheory;
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
        Call<List<Theory>> theor = theoryApi.getTheor();

        //listOfDataTheory = new ArrayList<Theory>();
        /*listOfDataTheory.add(new Theory("Тема 1","Фильтрация"));
        listOfDataTheory.add(new Theory("Тема 2", "Спектр2"));
        listOfDataTheory.add(new Theory("Тема 3", "Спектр3"));;*/

        TheoryAdapter.MyClickListener listener = new TheoryAdapter.MyClickListener() {
            @Override
            public void onItemClick(Theory item) {
                Toast.makeText(getContext(),"TAP " + item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        };

        theoryAdapter = new TheoryAdapter(getContext(), /*listOfDataTheory,*/ listener);
        theoryRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        theoryRecycler.setAdapter(theoryAdapter);
        //theoryAdapter.setList(listOfDataTheory);


        theor.enqueue(new Callback<List<Theory>>(){ //метод.в очереди
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Theory>> call, Response<List<Theory>> response) {
                listOfDataTheory  = response.body();
                Log.d("henlo", "how are you" + listOfDataTheory);
                listOfDataTheory.forEach(item -> Log.d("tag",  " look title " + item.getTitle()));
                theoryAdapter.setList(listOfDataTheory);
            }

            @Override
            public void onFailure(Call<List<Theory>> call, Throwable t) {
                Log.d("chmo", t.getLocalizedMessage());
            }
        });


    }
}