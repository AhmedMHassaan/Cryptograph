package com.ahmed.m.hassaan.cryptograph.ui.caesar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ahmed.m.hassaan.cryptograph.databinding.FragmentCaesarBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class CaesarFragment extends Fragment implements View.OnClickListener {

    private CaesarViewModel caesarViewModel;
    private FragmentCaesarBinding binding;


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        caesarViewModel = new ViewModelProvider(this).get(CaesarViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        caesarViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.lblResult.setText(s));

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCaesarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.btnEncrypt.setOnClickListener(this);

        return root;
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
                    int keyInt = Integer.parseInt(key);
                    caesarViewModel.encrypt(keyInt, plain);
                } catch (Exception e) {
                    Snackbar.make(v, "Error "+e.getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

        }

    }
}