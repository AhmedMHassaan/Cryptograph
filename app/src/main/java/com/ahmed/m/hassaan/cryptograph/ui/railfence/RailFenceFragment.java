package com.ahmed.m.hassaan.cryptograph.ui.railfence;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.m.hassaan.cryptograph.R;
import com.ahmed.m.hassaan.cryptograph.databinding.FragmentViegenreBinding;
import com.ahmed.m.hassaan.cryptograph.ui.viegenre.ViegenreViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class RailFenceFragment extends Fragment {


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        viegenreViewModel = new ViewModelProvider(this).get(ViegenreViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viegenreViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.lblResult.setText(s));

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentViegenreBinding.inflate(inflater, container, false);
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
                    viegenreViewModel.encrypt(key, plain);
                } catch (Exception e) {
                    Snackbar.make(v, "Error " + e.getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                    Log.e("TAG", "onClick: ",e );
                    e.printStackTrace();
                }
            }

        }

    }
}