package com.ahmed.m.hassaan.cryptograph.ui.hill;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ahmed.m.hassaan.cryptograph.databinding.FragmentCaesarBinding;
import com.ahmed.m.hassaan.cryptograph.databinding.FragmentHillBinding;
import com.ahmed.m.hassaan.cryptograph.ui.caesar.CaesarViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class HillFragment extends Fragment implements View.OnClickListener {

    private HillViewModel hillViewModel;
    private FragmentHillBinding binding;
    int[][] key;
    int n; // the size of array : key[n][n]


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        hillViewModel = new ViewModelProvider(this).get(HillViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hillViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.lblResult.setText(s));

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHillBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        events();

        return root;
    }

    private void events() {
        binding.btnEncrypt.setOnClickListener(this);

        binding.txtKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TAG_HILL", "afterTextChanged: Text is " + s);
                if (s.length() % 2 == 0 && s.length() > 0) {
                    // text : 123456
                    binding.btnEncrypt.setEnabled(true);
                    n = s.length() / 2; // n = 3;
                    Log.d("TAG_HILL", "afterTextChanged: N is " + n);
                    String raw = s.subSequence(0, n).toString(); // raw = 123

                    Log.d("TAG_HILL", "afterTextChanged: raw is " + raw + " And The Length is" + raw.length());
                    key = new int[n][raw.length()];  // key = [n][2]
                    for (int i = 0; i < raw.length(); i++) {
                        for (int j = 0; j < n; j++) {
                            key[i][j] = Integer.parseInt(String.valueOf(s.charAt(i)));
                            //  1       2       3
                            //  4       5       6
                        }


                        if (n == raw.length()) {
                            Log.d("TAG_HILL", "afterTextChanged: it is ok");
                        } else {
                            Log.d("TAG_HILL", "afterTextChanged: it is Not Ok eeee");
                        }

                    }


                } else {
                    binding.btnEncrypt.setEnabled(false);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnEncrypt) {
            String keyStr = binding.txtKey.getText().toString();
            String plain = binding.txtPlain.getText().toString();
            if (plain.isEmpty()) {
                Snackbar.make(v, "Enter Plain Text", BaseTransientBottomBar.LENGTH_SHORT).show();
            } else if (keyStr.isEmpty()) {

                Snackbar.make(v, "Enter Key", BaseTransientBottomBar.LENGTH_SHORT).show();

            } else {
                try {

//                    int keyInt = Integer.parseInt(key);
                    hillViewModel.encrypt(key, n, plain);
                } catch (Exception e) {
                    Snackbar.make(v, "Error " + e.getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

        }

    }
}