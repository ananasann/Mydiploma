package com.hfad.mydiploma.ui.theory;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hfad.mydiploma.ApiClient;
import com.hfad.mydiploma.ApiInterface;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTheory.TheoryCard;
import com.hfad.mydiploma.dataTheory.pager.CardData;
import com.hfad.mydiploma.dataTheory.pager.PagerAdapter;
import com.hfad.mydiploma.dataTheory.pager.ImageAndDescription;
import com.hfad.mydiploma.dataTheory.pager.QuestAndAnsOptions;
import com.hfad.mydiploma.ui.account.DevInfFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheorQuizFragment extends Fragment {

    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private TabLayoutMediator tabLayoutMediator;
    private ViewPager2 pager;
    private List<QuestAndAnsOptions> listOfQuestAndAnsOp;
    private List<ImageAndDescription> listOfImagAndDiscr;
    private List<TheoryCard> listofTheoryCard;
    private String itemName;
    private TextView itemNameF;
    private Integer position;
    RadioGroup rG;
    Button qaCheck;
    Integer corrAnsNum;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.theor_plus_quiz, container, false);
        pager = (ViewPager2) root.findViewById(R.id.pager);
        itemNameF = root.findViewById(R.id.item_nameF);
        tabLayout = (TabLayout) root.findViewById(R.id.tab);
        rG = root.findViewById(R.id.ans_op);
        qaCheck = root.findViewById(R.id.qaCheck);
        /*Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });*/
        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = this.getArguments();
        if (arg != null) {
            itemName = arg.getString("keyForName", "");
            position = arg.getInt("keyForPosition", 0);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        itemNameF.setText(itemName);

        ApiInterface cardApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TheoryCard>> card = cardApi.getTheor();

        pagerAdapter = new PagerAdapter(getContext());
        pager.setAdapter(pagerAdapter);

/*        qaCheck.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton checkedRadioButton = (RadioButton) rG.findViewById(checkedId);
                        int checkedIndex = rG.indexOfChild(checkedRadioButton);
                        Log.d("rrr", "brbrb" + checkedIndex);
                    }
                });*//*
            }
        }));*/

        card.enqueue(new Callback<List<TheoryCard>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TheoryCard>> call, Response<List<TheoryCard>> response) {
                listofTheoryCard = response.body();
                CardData cardData = listofTheoryCard.get(position).getCardData().get(0);
                Log.d("tagggg", "123" + position);

                ArrayList<Object> list = new ArrayList<Object>();
                for (int i = 0; i < cardData.getImageAndDescriptionList().size(); i = i + 1) {
                    list.add(cardData.getImageAndDescriptionList().get(i));
                    list.add(cardData.getQuestAndAnsOptionsList().get(i));
                }
                pagerAdapter.setList(list);
                Log.d("poluchilos", "how are you");
            }

            @Override
            public void onFailure(Call<List<TheoryCard>> call, Throwable t) {
                Log.d("chmo", "1234");
            }
        });

       /* List<String> ansOptions = new ArrayList();
        ansOptions.add("Fuck");

        List<QuestAndAnsOptions> listOfQuestAndAnsOp = new ArrayList<QuestAndAnsOptions>();
        listOfQuestAndAnsOp.add(new QuestAndAnsOptions("Вопрос", ansOptions, 1));
        listOfQuestAndAnsOp.add(new QuestAndAnsOptions("Другой вопрос", ansOptions, 2));

        List<ImageAndDescription> listOfImagAndDiscr = new ArrayList<ImageAndDescription>();
        listOfImagAndDiscr.add(new ImageAndDescription("чуть-чуть теории", "https://cdn25.img.ria.ru/images/156087/28/1560872802_0:778:1536:1642_600x0_80_0_0_606c2d47b6d37951adc9eaf750de22f0.jpg"));

        listOfImagAndDiscr.add(new ImageAndDescription("и тут теория", "https://images11.esquire.ru/upload/img_cache/acf/acfbe9979332a4bab9cec3485f678f61_ce_1080x673x0x0_cropped_960x600.jpg"));

        List<Object> listOfCardData = new ArrayList<Object>();
        listOfCardData.add(listOfImagAndDiscr.get(0));
        listOfCardData.add(listOfQuestAndAnsOp.get(0));
        listOfCardData.add(listOfImagAndDiscr.get(1));
        listOfCardData.add(listOfQuestAndAnsOp.get(1));
        //listOfCardData.add(listOfImagAndDiscr.get(2));
        // listOfCardData.add(listOfQuestAndAnsOp.get(2));*/


        tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position % 2 == 0) {
                    tab.setIcon(R.drawable.ic_img_and_descr);
                } else {
                    tab.setIcon(R.drawable.ic_quest_and_ans_op);
                }
            }
        });
        tabLayoutMediator.attach();

    }


    //ССЫЛКА НИЖЕ ПРЕДНАЗНАЧЕНА ДЛЯ КАСТОМИЗАЦИИ КНОПКИ НАЗАД, МОЖНО БУДЕТ ВСТАВИТЬ В ТЕСТЫ И ТОГДА ИЗ НИХ НЕЛЬЗЯ БУДЕТ ВЫЙТИ ДО ПОЛНОГО ВЫПОЛНЕНИЯ
    //https://developer.android.com/guide/navigation/navigation-custom-back
}


