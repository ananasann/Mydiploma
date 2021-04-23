package com.hfad.mydiploma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AuthActivity extends AppCompatActivity {

    private GoogleSignInClient mSignInClient;
    private static final int RC_SIGN_IN = 9001;
    SignInButton signInButton;
    @Nullable
    public GoogleSignInAccount currAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_google);
        getSupportActionBar().hide();

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
                Log.d("blya", "");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            Log.d("tag", "");
            if (task.isSuccessful()) {
                // Sign in succeeded, proceed with account
                currAcc = task.getResult();
                //AccountManager.get(getApplicationContext()).addAccount()
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
            // Sign in failed, handle failure and update UI
            // ...
            Toast.makeText(this, "Не удалось войти", Toast.LENGTH_SHORT).show();
            }

        }
    }
}