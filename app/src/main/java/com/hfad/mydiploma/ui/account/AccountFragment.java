package com.hfad.mydiploma.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.hfad.mydiploma.AuthActivity;
import com.hfad.mydiploma.R;

public class AccountFragment extends Fragment {

    TextView aboutDev;
    TextView logOut;
    TextView fmo;
    TextView accEmail;
    ImageView accImage;
    TextView grades;
    private GoogleSignInClient mSignInClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        fmo = root.findViewById(R.id.FMO);
        accEmail = root.findViewById(R.id.accEmail);
        accImage = root.findViewById(R.id.accImage);
        logOut = root.findViewById(R.id.logOut);
        aboutDev = root.findViewById(R.id.aboutDev);
        grades = root.findViewById(R.id.grades);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        aboutDev.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevInfFragment devInfFragment = new DevInfFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.accFrag, devInfFragment, "fragTag")
                        .addToBackStack(null)
                        .commit();
            }
        }));

        grades.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradesFragment gradFragment = new GradesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.accFrag, gradFragment, "fragTag")
                        .addToBackStack(null)
                        .commit();
            }
        }));


    }

    private void bind(@Nullable GoogleSignInAccount acc) {
        if (acc == null) {
        } else {
            fmo.setText(acc.getDisplayName());
            accEmail.setText(acc.getEmail());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new RoundedCorners(180));
            Glide
                    .with(this)
                    .load(acc.getPhotoUrl())
                    .apply(requestOptions)
                    .error(R.drawable.ic_account_black_24dp)
                    .into(accImage);
        }
    }
}