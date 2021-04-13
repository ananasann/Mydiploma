package com.hfad.mydiploma.ui.theory;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.hfad.mydiploma.R;

public class TheorQuizFragmant extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.theor_plus_quiz, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        return root;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    //ССЫЛКА НИЖЕ ПРЕДНАЗНАЧЕНА ДЛЯ КАСТОМИЗАЦИИ КНОПКИ НАЗАД, МОЖНО БУДЕТ ВСТАВИТЬ В ТЕСТЫ И ТОГДА ИЗ НИХ НЕЛЬЗЯ БУДЕТ ВЫЙТИ ДО ПОЛНОГО ВЫПОЛНЕНИЯ
    //https://developer.android.com/guide/navigation/navigation-custom-back
}


