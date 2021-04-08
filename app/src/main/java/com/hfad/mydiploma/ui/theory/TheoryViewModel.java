package com.hfad.mydiploma.ui.theory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TheoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TheoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Фамилия Имя Отчество");
    }

    public LiveData<String> getText() {
        return mText;
    }
}