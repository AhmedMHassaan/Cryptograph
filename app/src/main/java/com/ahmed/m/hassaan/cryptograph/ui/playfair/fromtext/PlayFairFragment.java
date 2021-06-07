package com.ahmed.m.hassaan.cryptograph.ui.playfair.fromtext;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ahmed.m.hassaan.cryptograph.databinding.FragmentHillBinding;
import com.ahmed.m.hassaan.cryptograph.databinding.FragmentPlayFiarBinding;
import com.ahmed.m.hassaan.cryptograph.ui.hill.HillViewModel;
import com.ahmed.m.hassaan.cryptograph.ui.playfair.PlayFairViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class PlayFairFragment extends Fragment implements View.OnClickListener {

    private PlayFairViewModel playFairViewModel;
    private FragmentPlayFiarBinding binding;


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        playFairViewModel = new ViewModelProvider(this).get(PlayFairViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playFairViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.lblResult.setText(s));

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPlayFiarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        events();

        return root;
    }

    private void events() {
        binding.btnEncrypt.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnEncrypt) {
            String key = binding.txtKey.getText().toString();
            String plain = binding.txtPlain.getText().toString();
            if (plain.isEmpty()) {
                Snackbar.make(v, "Enter Plain Text", BaseTransientBottomBar.LENGTH_SHORT).show();
            } else if (key.isEmpty()) {

                Snackbar.make(v, "Enter Key", BaseTransientBottomBar.LENGTH_SHORT).show();

            } else {
                try {

//                    int keyInt = Integer.parseInt(key);
                    playFairViewModel.encrypt(key, plain);
                } catch (Exception e) {
                    Snackbar.make(v, "Error " + e.getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                    Log.e("TAG", "onClick: ",e );
                    e.printStackTrace();
                }
            }

        }

    }
}