package com.example.samandar_demo;

import static com.google.android.material.resources.MaterialResources.getDrawable;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import de.hdodenhof.circleimageview.CircleImageView;


public class Team_Fragment extends Fragment {


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_, container, false);


        // Fragment ichidagi tugma bosilganda dialogni ochish
        TextView project_man = view.findViewById(R.id.user_1);
        TextView myself = view.findViewById(R.id.user_2);
        TextView motion_design = view.findViewById(R.id.user_3);
        TextView intelligence = view.findViewById(R.id.user_4);

        project_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog1();
            }
        });



        myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog2();
            }
        });


        motion_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog3();
            }
        });



        intelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog4();
            }
        });

        return view;
    }











    private void showCustomDialog1() {
        // Dialogni yaratish va joylash
        final Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.custom_dialog_layout);


        // Dialog elementlarini topib olish
        CircleImageView imageView = dialog.findViewById(R.id.imageView);
        TextView textView = dialog.findViewById(R.id.textView);
        MaterialButton okButton = dialog.findViewById(R.id.btn_okay);

        // Elementlarga ma'lumotlarini joylash
        textView.setText("Dilbar Shukurullayevna\n \nDoctor of Philosophy, Specialist of Social media marketing!\n \nBog`lanish: +998901234567\n Telegram: @Anonym_logoped");
        imageView.setImageResource(R.drawable.img_9);

        // OK tugmasini bosganda dialogni yopish
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Dialogni namoyish etish
        dialog.show();

    }




    private void showCustomDialog2() {
        // Dialogni yaratish va joylash
        final Dialog dialog2 = new Dialog(requireContext());
        dialog2.setContentView(R.layout.custom_dialog_layout);


        // Dialog elementlarini topib olish
        CircleImageView imageView2 = dialog2.findViewById(R.id.imageView);
        TextView textView2 = dialog2.findViewById(R.id.textView);
        MaterialButton okButton2 = dialog2.findViewById(R.id.btn_okay);

        // Elementlarga ma'lumotlarini joylash
        textView2.setText("Samandar Khasanov\n \nAndroid Dasturchi\n \nBog`lanish: +998901234567\n Telegram: @Anonym_android");
        imageView2.setImageResource(R.drawable.img_10);

        // OK tugmasini bosganda dialogni yopish
        okButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        // Dialogni namoyish etish
        dialog2.show();


    }

    private void showCustomDialog3() {
        // Dialogni yaratish va joylash
        final Dialog dialog3 = new Dialog(requireContext());
        dialog3.setContentView(R.layout.custom_dialog_layout);


        // Dialog elementlarini topib olish
        CircleImageView imageView3 = dialog3.findViewById(R.id.imageView);
        TextView textView3 = dialog3.findViewById(R.id.textView);
        MaterialButton okButton3 = dialog3.findViewById(R.id.btn_okay);

        // Elementlarga ma'lumotlarini joylash
        textView3.setText("Sagdiana Azimova\n \nMotion dizayner\n \nBog`lanish: +998901234567\n Telegram: @Anonym_motion");
        imageView3.setImageResource(R.drawable.img_12);

        // OK tugmasini bosganda dialogni yopish
        okButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.dismiss();
            }
        });

        // Dialogni namoyish etish
        dialog3.show();
    }


    private void showCustomDialog4() {
        // Dialogni yaratish va joylash
        final Dialog dialog4 = new Dialog(requireContext());
        dialog4.setContentView(R.layout.custom_dialog_layout);


        // Dialog elementlarini topib olish
        CircleImageView imageView4 = dialog4.findViewById(R.id.imageView);
        TextView textView4 = dialog4.findViewById(R.id.textView);
        MaterialButton okButton4 = dialog4.findViewById(R.id.btn_okay);

        // Elementlarga ma'lumotlarini joylash
        textView4.setText("Bobur Yo`ldoshev\n \nSun`iy Intellekt dasturchisi\n \nBog`lanish: +998901234567\n Telegram: @Anonym_intelligence");
        imageView4.setImageResource(R.drawable.img_11);

        // OK tugmasini bosganda dialogni yopish
        okButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4.dismiss();
            }
        });

        // Dialogni namoyish etish
        dialog4.show();

    }



}

