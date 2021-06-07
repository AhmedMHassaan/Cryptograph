package com.ahmed.m.hassaan.cryptograph.ui.playfair.fromfile.encrypt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.m.hassaan.cryptograph.R;
import com.ahmed.m.hassaan.cryptograph.databinding.FragmentPlayFairFileEncryptionBinding;
import com.ahmed.m.hassaan.cryptograph.ui.caesar.CaesarViewModel;
import com.ahmed.m.hassaan.cryptograph.ui.playfair.PlayFairViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.app.Activity.RESULT_OK;

public class FragmentPlayFairFileEncryption extends Fragment implements View.OnClickListener {


    FragmentPlayFairFileEncryptionBinding binding;
    ActivityResultLauncher<Intent> fileChooseLauncher;
    ActivityResultLauncher<String> writeStoragePermission;


    CaesarViewModel caesarViewModel;
    private boolean isTreReadFile = false;
    String fileContent = "";

    public FragmentPlayFairFileEncryption() {
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        caesarViewModel = new ViewModelProvider(this).get(CaesarViewModel.class);
        fileChooseLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

            if (result.getData() != null && result.getData().getData() != null && result.getResultCode() == RESULT_OK) {
                Log.d("TAG", "onAttach: File Choosed " + result.getData().getData().getPath());
                readFile(result.getData().getData());
            } else {
                Toast.makeText(binding.getRoot().getContext(), "No File is Chosen", Toast.LENGTH_SHORT).show();
            }
        });

        writeStoragePermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    Log.d("TAG", "onActivityResult: Accepted And Write " + fileContent);
                    writeToFile(fileContent);
                } else {
                    Toast.makeText(context, "Accept Permission To Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlayFairFileEncryptionBinding.inflate(inflater, container, false);
        events();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModels();
    }

    private void observeViewModels() {
        caesarViewModel.getText().observe(getViewLifecycleOwner(),
                s -> {
                    fileContent = s;
                    writeStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                });
    }

    private void events() {
        binding.btnChooseFile.setOnClickListener(this);
        binding.btnEncrypt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnChooseFile) {
            chooseFile();
        } else if (v == binding.btnEncrypt) {
            if (isTreReadFile) {
                encryptFile(binding.lblFileResult.getText().toString());
            } else {
                Toast.makeText(v.getContext(), "Error in Reading File", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        fileChooseLauncher.launch(intent);
    }

    private void readFile(Uri data) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getActivity().getContentResolver().openInputStream(data)));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                Log.d("TAG", "readFile: line is " + line);
            }

            bufferedReader.close();
            binding.lblFileResult.setText(builder.toString());
            isTreReadFile = true;
            binding.btnEncrypt.setVisibility(View.VISIBLE);
            binding.btnChooseFile.setVisibility(View.GONE);

        } catch (Exception e) {

            binding.lblFileResult.setVisibility(View.VISIBLE);
            binding.lblFileResult.setText(e.getMessage());
            binding.btnEncrypt.setVisibility(View.GONE);
            binding.btnChooseFile.setVisibility(View.VISIBLE);
            isTreReadFile = false;
        }
    }

    private void encryptFile(String fileContent) {

        caesarViewModel.encrypt(5, fileContent);
    }

    private void writeToFile(String text) {
        try {

            // save File
//            File file = new File(getContext().getFilesDir(), "enc");
//            if (!file.exists()) {
//                file.mkdir();
//            }

            File mainDir = new File(Environment.getExternalStorageDirectory() + "/Cryptography");
            if (!mainDir.exists()) {
                mainDir.mkdir();
                Log.d("TAG", "writeToFile: Dir Created" + mainDir.mkdir());
            }
            File outPut = new File(mainDir.getPath() + "/encFile.txt");

//            File outPut = new File(file.getAbsolutePath() + "/encFile.txt");
//            File outPut = new File(file1.getAbsolutePath() + "/encFile.txt");
            Log.d("TAG", "writeToFile:  output file is " + outPut.getPath());
            FileWriter fileWriter = new FileWriter(outPut);
            fileWriter.append(text).flush();
            fileWriter.close();
            Toast.makeText(binding.getRoot().getContext(), "File Saved in Path \n" + outPut.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            binding.lblFileResult.setText("Error in writing :\n" + e.getMessage());
        }
    }
}