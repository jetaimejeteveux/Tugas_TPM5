package com.belajar.tugastpm5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;


public class CallFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnDial = getActivity().findViewById(R.id.btnDial);
        TextInputLayout numberPhone = getActivity().findViewById(R.id.number_phone);

        btnDial.setOnClickListener(v ->  {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + Uri.encode(numberPhone.getEditText().getText().toString())));
            startActivity(intent);
        });
    }
}