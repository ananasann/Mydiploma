package com.hfad.mydiploma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    private GoogleSignInClient mSignInClient;
    private static final int RC_SIGN_IN = 9001;
    SignInButton signInButton;
    @Nullable
    public GoogleSignInAccount currAcc;
    String authCode;
    String idTokenAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_google);
        getSupportActionBar().hide();
        String serverClientId = getString(R.string.server_client_id);
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(serverClientId)
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
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                currAcc = task.getResult();
                idTokenAcc = currAcc.getIdToken();

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Не удалось войти", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        AuthAcc item = new AuthAcc(idTokenAcc);
        List<AuthAcc> body = new ArrayList<AuthAcc>();
        body.add(item);
        ApiInterface authApi = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AuthAcc>> authAcc = authApi.postAuthAcc(body);

        authAcc.enqueue(new Callback<List<AuthAcc>>() {
            @Override
            public void onResponse(Call<List<AuthAcc>> call, Response<List<AuthAcc>> response) {
            }

            @Override
            public void onFailure(Call<List<AuthAcc>> call, Throwable t) {
            }

        });
    }
}