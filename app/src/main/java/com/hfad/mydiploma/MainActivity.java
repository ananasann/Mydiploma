package com.hfad.mydiploma;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText ETemail;
    EditText ETpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        getSupportActionBar().hide();

        ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_theory, R.id.navigation_tests, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/  //БЫЛО ТУТ ИЗНАЧАЛЬНО, НО ТАК РАБОТАТЬ НЕ БУДЕТ, ПОТОМУ ЧТО МЫ СНАЧАЛА ВЫЗЫВАЕМ МАКЕТ АВТОРИЗАЦИИ
    }


    public void logIn(View view){
        String OutETemail = ETemail.getText().toString();
        String OutETpassword = ETpassword.getText().toString();

        if (OutETemail.equals("") && OutETpassword.equals("")){
            setContentView(R.layout.activity_main);
            getSupportActionBar().show();

            //ТО, ЧТО НИЖЕ И КАСАЕТСЯ БОТНАВ - ТУТ ТОЖЕ БЫТЬ НЕ ДОЛЖНО, ПОТОМУ ЧТО ВЫЗЫВАЕТСЯ ПРИ КЛИКЕ, А НАМ НЕ НУЖНО ЗАХОДИТЬ КАЖДЫЙ РАЗ
            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_theory, R.id.navigation_tests, R.id.navigation_account)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);

            // Toast.makeText(this, "Заходите", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Неправильно введен логин или пароль", Toast.LENGTH_SHORT).show();
        }
    }

}