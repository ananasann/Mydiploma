package com.hfad.mydiploma.ui.tests;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.hfad.mydiploma.ApiClient;
import com.hfad.mydiploma.ApiInterface;

import com.hfad.mydiploma.GradeBody;

import com.hfad.mydiploma.R;

import com.hfad.mydiploma.dataTests.TestsCard;
import com.hfad.mydiploma.dataTests.onetest.OneTestAdapter;
import com.hfad.mydiploma.dataTests.onetest.OneTestData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestThemeFragment extends Fragment {

    private List<TestsCard> testsCardList;
    private TextView itemNameTestH;
    private String itemNameTest;
    private Integer position;
    private OneTestAdapter oneTestAdapter;
    private RecyclerView oneTestRec;
    private Button buttonSubmit;
    List<OneTestData> listOfOneTestData;
    private Integer grade;
    public long finalGrade;
    GoogleSignInAccount buffAccTest;

    private Integer[] editTextDataHolder = new Integer[500];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.test_theme, container, false);
        itemNameTestH = root.findViewById(R.id.item_name_test);
        oneTestRec = root.findViewById(R.id.one_test_rc);
        buttonSubmit = root.findViewById(R.id.buttonSubmit);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle arg = this.getArguments();
        if (arg != null) {
            itemNameTest = arg.getString("keyForNameTest", "");
            position = arg.getInt("keyForPositionTest", 0);
        }
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);*/
    }

    @Override
    public void onResume() {
        super.onResume();
        itemNameTestH.setText(itemNameTest);
        ApiInterface testApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TestsCard>> oneTest = testApi.getTest();

       /* List<OneTestData> listOfOneTestData = new ArrayList<OneTestData>();
        listOfOneTestData.add(new OneTestData("Что есть что?", "a)b)c)", 1));
        listOfOneTestData.add(new OneTestData("Вопрос?", "a)b)c)d)", 2));
        listOfOneTestData.add(new OneTestData("Что есть что?", "a)b)c)", 1));
        listOfOneTestData.add(new OneTestData("Вопрос?", "a)b)c)d)", 2));
        listOfOneTestData.add(new OneTestData("Что есть что?", "a)b)c)", 1));
        listOfOneTestData.add(new OneTestData("Вопрос?", "a)b)c)d)", 2));*/

        oneTestAdapter = new OneTestAdapter(getContext(), new OneTestAdapter.MyEditTextListener() {
            @Override
            public void onTextChanged(Integer position, Integer answer) {
                editTextDataHolder[position] = answer;
            }
        });
        oneTestRec.setLayoutManager(new LinearLayoutManager(getContext()));
        oneTestRec.setAdapter(oneTestAdapter);

        buttonSubmit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 0;
                for (int i = 0; i < listOfOneTestData.size(); i++) {
                    boolean isCorrect = listOfOneTestData.get(i).getCorrAnsTest().equals(editTextDataHolder[i]);
                    if (isCorrect) {
                        grade++;
                    }
                }
                finalGrade = Math.round(Double.valueOf(grade) / Double.valueOf(listOfOneTestData.size()) * 10);
            }
        }));

        oneTest.enqueue(new Callback<List<TestsCard>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TestsCard>> call, Response<List<TestsCard>> response) {
                testsCardList = response.body();
                List<OneTestData> oneTestData = testsCardList.get(position).getTestDataListData();//.get(0);
                oneTestAdapter.setList(oneTestData);
                listOfOneTestData = oneTestData;
            }
            @Override
            public void onFailure(Call<List<TestsCard>> call, Throwable t) {
            }
        });

        buffAccTest = GoogleSignIn.getLastSignedInAccount(getContext());
        String accTokenTest = buffAccTest.getIdToken();


        GradeBody body = new GradeBody(
                String.valueOf(finalGrade), accTokenTest
        );
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> postGradeCall = apiInterface.postGrade(body);

        postGradeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
            }

        });

    }
}
