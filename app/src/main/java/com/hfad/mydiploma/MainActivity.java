package com.hfad.mydiploma;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    EditText ETemail;
    EditText ETpassword;
    private static final int RC_SIGN_IN = 9001;
    @Nullable public GoogleSignInAccount currAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().show();

       /* ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);*/

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_theory, R.id.navigation_tests, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                currAcc = task.getResult();
                setContentView(R.layout.activity_main);
                getSupportActionBar().show();
                /*//ТО, ЧТО НИЖЕ И КАСАЕТСЯ БОТНАВ - ТУТ ТОЖЕ БЫТЬ НЕ ДОЛЖНО, ПОТОМУ ЧТО ВЫЗЫВАЕТСЯ ПРИ КЛИКЕ, А НАМ НЕ НУЖНО ЗАХОДИТЬ КАЖДЫЙ РАЗ
                BottomNavigationView navView = findViewById(R.id.nav_view);
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.navigation_theory, R.id.navigation_tests, R.id.navigation_account)
                        .build();
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                NavigationUI.setupWithNavController(navView, navController);*/
            } else {
                Toast.makeText(this, "Не удалось войти", Toast.LENGTH_SHORT).show();
            }
        }
    }
}