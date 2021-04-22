package com.hfad.mydiploma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.Driver;
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
    private GoogleSignInClient mSignInClient;
    private static final int RC_SIGN_IN = 9001;
    SignInButton signInButton;
    @Nullable public GoogleSignInAccount currAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_google);
        getSupportActionBar().hide();

        ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);

        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .setHostedDomain("miem.hse.ru")
                        .build();

        mSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = mSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
                Log.d("blya","");
            }
        });


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            Log.d("tag","");
            if (task.isSuccessful()) {
                // Sign in succeeded, proceed with account
                currAcc = task.getResult();

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
            } else {
                // Sign in failed, handle failure and update UI
                // ...
                Toast.makeText(this, "Неудалось войти", Toast.LENGTH_SHORT).show();
            }
        }
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