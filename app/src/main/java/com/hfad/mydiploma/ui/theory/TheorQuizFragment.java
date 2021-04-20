package com.hfad.mydiploma.ui.theory;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hfad.mydiploma.R;
import com.hfad.mydiploma.dataTheory.pager.CardData;
import com.hfad.mydiploma.dataTheory.pager.PagerAdapter;
import com.hfad.mydiploma.dataTheory.pager.ImageAndDescription;
import com.hfad.mydiploma.dataTheory.pager.QuestAndAnsOptions;

import java.util.ArrayList;
import java.util.List;

public class TheorQuizFragment extends Fragment {

    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private TabLayoutMediator tabLayoutMediator;
    private ViewPager2 pager;
    List<QuestAndAnsOptions> listOfQuestAndAnsOp;
    List<ImageAndDescription> listOfImagAndDiscr;
    List<CardData> listOfCardData;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.theor_plus_quiz, container, false);
        pager = (ViewPager2) root.findViewById(R.id.pager);

        tabLayout = (TabLayout) root.findViewById(R.id.tab);

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

    }

    @Override
    public void onResume() {
        super.onResume();
        /*ApiInterface blockApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BlockPager>> block = blockApi.getBlocks();*/

        //fragmentPagerAdapter = new FragmentPagerAdapter(getContext());
        //pager.setAdapter(fragmentPagerAdapter);


        /*block.enqueue(new Callback<List<BlockPager>>(){ //метод.в очереди
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<BlockPager>> call, Response<List<BlockPager>> response) {
                Log.d("poluchilos", "how are you" );
            }
            @Override
            public void onFailure(Call<List<BlockPager>> call, Throwable t) {
                Log.d("chmo", t.getLocalizedMessage());
            }
        });*/

        List ansOptions = new ArrayList();

        List <QuestAndAnsOptions> listOfQuestAndAnsOp = new ArrayList<QuestAndAnsOptions>();
        listOfQuestAndAnsOp.add(new QuestAndAnsOptions("Вопрос",ansOptions,1));
        listOfQuestAndAnsOp.add(new QuestAndAnsOptions("Другой вопрос",ansOptions,2));

        List<ImageAndDescription> listOfImagAndDiscr = new ArrayList<ImageAndDescription>();
        listOfImagAndDiscr.add( new ImageAndDescription("https://cdn25.img.ria.ru/images/156087/28/1560872802_0:778:1536:1642_600x0_80_0_0_606c2d47b6d37951adc9eaf750de22f0.jpg","тут чуть теории"));

        listOfImagAndDiscr.add( new ImageAndDescription("https://images11.esquire.ru/upload/img_cache/acf/acfbe9979332a4bab9cec3485f678f61_ce_1080x673x0x0_cropped_960x600.jpg","и тут теория"));

        List<Object> listOfCardData = new ArrayList<Object>();
        listOfCardData.add(listOfImagAndDiscr);
        listOfCardData.add(listOfQuestAndAnsOp);
        listOfCardData.add(listOfImagAndDiscr);
        listOfCardData.add(listOfQuestAndAnsOp);
        listOfCardData.add(listOfImagAndDiscr);
        listOfCardData.add(listOfQuestAndAnsOp);


        pagerAdapter = new PagerAdapter(getContext(), listOfCardData);
        pager.setAdapter(pagerAdapter);
        tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position % 2 == 0){
                    tab.setIcon(R.drawable.ic_img_and_descr);}
                else{
                    tab.setIcon(R.drawable.ic_quest_and_ans_op);
                }
            }
        });
        tabLayoutMediator.attach();


    }






    //ССЫЛКА НИЖЕ ПРЕДНАЗНАЧЕНА ДЛЯ КАСТОМИЗАЦИИ КНОПКИ НАЗАД, МОЖНО БУДЕТ ВСТАВИТЬ В ТЕСТЫ И ТОГДА ИЗ НИХ НЕЛЬЗЯ БУДЕТ ВЫЙТИ ДО ПОЛНОГО ВЫПОЛНЕНИЯ
    //https://developer.android.com/guide/navigation/navigation-custom-back
}


