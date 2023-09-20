package com.example.samandar_demo;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;
import static androidx.camera.core.impl.utils.ContextUtil.getApplicationContext;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.yeyint.customalertdialog.CustomAlertDialog;


public class Gift_Fragment extends Fragment {



    LottieAnimationView animationView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_camera_open, container, false);

        animationView = view.findViewById(R.id.gift_1);

        animationView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                CustomAlertDialog dialog = new CustomAlertDialog(getActivity(), CustomAlertDialog.DialogStyle.FILL_STYLE);

                dialog.setDialogImage(getResources().getDrawable(R.drawable.img_3), getResources().getColor(com.yeyint.customalertdialog.R.color.colorTransparent));
                dialog.setAlertMessage("8 coinga ega bo`ldingiz olishni xohlaysizmi?");
                dialog.setDialogType(CustomAlertDialog.DialogType.SUCCESS);
                dialog.setImageSize(200,200);
                dialog.create();
                dialog.show();


                dialog.setPositiveButton("Ha", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast oynani chiqaring
                        Toast.makeText(getActivity(), "Coinlar hisobingizga qo`shildi", Toast.LENGTH_SHORT).show();
                        // Dialogni yoping
                        dialog.dismiss();
                    }
                });

                // "Cancel" tugmasi bosilganda
                dialog.setNegativeButton("hozir emas", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast oynani chiqaring
                        Toast.makeText(getActivity(), "Siz coinlarni olmadingiz", Toast.LENGTH_SHORT).show();
                        // Dialogni bekor qiling
                        dialog.cancel();





                    }
                });

            }
        });




        return view;
    }
}