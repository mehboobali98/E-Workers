package com.example.eworkers.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eworkers.AboutApp;
import com.example.eworkers.EmailUs;
import com.example.eworkers.Profile;
import com.example.eworkers.R;
import com.example.eworkers.SplashScreen;

import API.IslahManzil;
import API.Strings;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.textView9);

        final TextView shareText = root.findViewById(R.id.shareApp);

        final TextView profileText = root.findViewById(R.id.profileText);

        final TextView emailus = root.findViewById(R.id.emailus);

        final TextView about = root.findViewById(R.id.about);

        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(),
                        Profile.class);
                startActivity(myIntent);
            }
        });

        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = Strings.shareMessage;
                String shareSubject = Strings.shareSubject;
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share Using ..."));
            }
        });
       emailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(),
                        EmailUs.class);
                startActivity(myIntent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(),
                        AboutApp.class);
                startActivity(myIntent);
            }
        });


        final Button logoutButton = (Button) root.findViewById(R.id.logout_btn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IslahManzil.getIslah().logout();
                Intent myIntent = new Intent(getContext(),
                        SplashScreen.class);
                getParentFragment().getActivity().finish();
                startActivity(myIntent);
            }
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Settings");

            }
        });
        return root;
    }
}
