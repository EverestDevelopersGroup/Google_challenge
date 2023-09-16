package com.example.mashinalioqitish.Amaliyot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.mashinalioqitish.R;

import java.util.ArrayList;
import java.util.List;

public class Main_amal extends AppCompatActivity {



    List<Product_amal> productList;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_amal);




        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RelativeLayout relativeLayout = findViewById(R.id.rl_amal);
        relativeLayout.startAnimation(animation);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_amal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        productList = new ArrayList<>();



        productList.add(
                new Product_amal(
                        1,
                        "1-amaliy mashg`ulot\n",
                        60000,
                        R.drawable.img_5,
        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F1-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(514-533).pdf?alt=media&token=b9167d92-5981-4123-8607-af22edb4a49d"
                        ));
        productList.add(
                new Product_amal(
                        1,
                        "2-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F2-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82-(534-585).pdf?alt=media&token=0e7daecd-4bda-4185-ba83-88469aba69da"
                ));



        productList.add(
                new Product_amal(
                        1,
                        "3-amaliy mashg`ulot\n",
                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F3-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82%20(586-628).pdf?alt=media&token=2745e4ca-539c-4f37-8df6-ebc04ad231de"

                ));

        productList.add(
                new Product_amal(
                        1,
                        "4-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F4-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(629-655).pdf?alt=media&token=af9039f5-7a97-4b60-8479-899aafa57a55"
                ));



        productList.add(
                new Product_amal(
                        1,
                        "5-amaliy mashg`ulot\n",
                        60000,
                        R.drawable.img_5,
        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F5-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(656-667).pdf?alt=media&token=a3401a4b-002a-4746-b757-cf64e14cbb1c"
                ));
        productList.add(
                new Product_amal(
                        1,
                        "6-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F6-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(668-689).pdf?alt=media&token=7b3fb5b5-2d6b-4cf8-8766-8b3c31280b8e"
                ));



        productList.add(
                new Product_amal(
                        1,
                        "7-amaliy mashg`ulot\n",
                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F7-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(MAX%2C%20Min)%20(690-739).pdf?alt=media&token=3772e04f-e63f-465f-a10a-d13357790704"

                ));

        productList.add(
                new Product_amal(
                        1,
                        "8-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F8-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(SNT)%20740-787.pdf?alt=media&token=e20e8df8-c233-4b33-acbc-c68b4b9fb1c7"
                ));

        productList.add(
                new Product_amal(
                        1,
                        "9-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F9-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(Sodda%20NT)%20788-812.pdf?alt=media&token=52a735ac-88f9-4d86-807c-6eea816b96f2"
                ));

        productList.add(
                new Product_amal(
                        1,
                        "10-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F10-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(Chuqur%20o'qitish)%20(813-827).pdf?alt=media&token=abff2adf-2e0a-4d73-bc80-231e1574c581"
                ));

        productList.add(
                new Product_amal(
                        1,
                        "11-amaliy mashg`ulot\n",

                        60000,
                        R.drawable.img_5,
                        "https://firebasestorage.googleapis.com/v0/b/mashinali-oqitish.appspot.com/o/Amaliyot%2F11-%D0%B0%D0%BC%D0%B0%D0%BB%D0%B8%D0%B5%CC%88%D1%82(828-847).pdf?alt=media&token=30e565ec-d104-4ea1-85b3-48780093915e"
                ));





        ProductAdapter_adab adapter = new ProductAdapter_adab(this, productList);


        recyclerView.setAdapter(adapter);











    }
}