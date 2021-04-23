package com.hfad.mydiploma.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hfad.mydiploma.AuthActivity;
import com.hfad.mydiploma.MainActivity;
import com.hfad.mydiploma.R;

import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {

    TextView logOut;
    TextView fmo;
    TextView group;
    ImageView accImage;
    private GoogleSignInClient mSignInClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        fmo = root.findViewById(R.id.FMO);
        group = root.findViewById(R.id.group);
        accImage = root.findViewById(R.id.accImage);
        logOut = root.findViewById(R.id.logOut);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();


        GoogleSignInAccount buffAcc = GoogleSignIn.getLastSignedInAccount(getContext());//((MainActivity) getActivity()).currAcc;
        bind(buffAcc);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(getContext(), gso);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignInClient.signOut();
                Intent intent = new Intent(getContext(), AuthActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bind(@Nullable GoogleSignInAccount acc) {
        if (acc == null) {
            Log.d("check", "null");
        } else {
            fmo.setText(acc.getDisplayName());
            group.setText(acc.getEmail());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new RoundedCorners(180));
            Glide
                    .with(this)
                    .load(acc.getPhotoUrl())
                    .apply(requestOptions)
                    .error(R.drawable.ic_account_black_24dp)
                    .into(accImage);


            Log.d("check", "nenull");
        }
    }


}