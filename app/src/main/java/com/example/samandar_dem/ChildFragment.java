package com.example.samandar_dem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_dem.Articulation.Artikulatsiya;
import com.example.samandar_dem.Barmoqlar.BarmoqActvity;
import com.example.samandar_dem.Activities.MimikaActivity;
import com.example.samandar_dem.Activities.TanaHarakatActivity;


import com.example.samandar_dem.Puzzle_Games.OptionActivity;
import com.example.samandar_dem.Tovushlar.ImagetoVoice;
import com.example.samandar_dem.Tovushlar.TovushActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import me.ibrahimsn.lib.SmoothBottomBar;


public class ChildFragment extends Fragment {

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;

    ShimmerFrameLayout bola1, bola2, bola3, bola4, bola5, bola6, bola7, bola8, bola9, bola10;

    MaterialCardView bolim_1, bolim_2, bolim_3, bolim_4, bolim_5, bolim_6, bolim_7, bolim_8, bolim_9, bolim_10;

    SmoothBottomBar bottomBar;
    MediaPlayer imagevoice;
    LinearLayout layout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_child, container, false);


        img1 = view.findViewById(R.id.child1);
        img2 = view.findViewById(R.id.child2);
        img3 = view.findViewById(R.id.child3);
        img4 = view.findViewById(R.id.child4);
        img5 = view.findViewById(R.id.child5);
        img6 = view.findViewById(R.id.child6);
        img7 = view.findViewById(R.id.child7);
        bottomBar = view.findViewById(R.id.bottomBar);
        layout = view.findViewById(R.id.layout_top);



        bola1 = view.findViewById(R.id.shim1);
        bola2 = view.findViewById(R.id.shim2);
        bola3 = view.findViewById(R.id.shim3);
        bola4 = view.findViewById(R.id.shim4);
        bola5 = view.findViewById(R.id.shim5);
        bola6 = view.findViewById(R.id.shim6);
        bola7 = view.findViewById(R.id.shim7);
        bola8 = view.findViewById(R.id.shim8);
        bola9 = view.findViewById(R.id.shim9);
        bola10 = view.findViewById(R.id.shim10);


        bolim_1 = view.findViewById(R.id.articulation);
        bolim_2 = view.findViewById(R.id.nafas);
        bolim_3 = view.findViewById(R.id.tovush);
        bolim_4 = view.findViewById(R.id.barmoq);
        bolim_5 = view.findViewById(R.id.mimika);
        bolim_6 = view.findViewById(R.id.gift);
        bolim_7 = view.findViewById(R.id.daktil);
        bolim_8 = view.findViewById(R.id.fonematik);
        bolim_9 = view.findViewById(R.id.oylash_rivoj);
        bolim_10 = view.findViewById(R.id.diqqat_rivoj);


        bolim_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_2.setStrokeColor(Color.parseColor("#D48BE1"));


                Sigh_Fragment fragment = new Sigh_Fragment();

                getActivity().getSupportFragmentManager().beginTransaction()

                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)


                        .replace(R.id.framelayout_container, fragment).addToBackStack(null).commit();

                Animatoo.INSTANCE.animateDiagonal(getContext());

            }
        });

        bolim_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_7.setStrokeColor(Color.parseColor("#D48BE1"));
                Lesson_teachers fragment = new Lesson_teachers();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.windmill_enter, R.anim.windmill_exit)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)


                        .replace(R.id.framelayout_container, fragment).addToBackStack(null).commit();

            }
        });


//        gift.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Gift_Fragment fragment = new Gift_Fragment();
//
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.windmill_enter, R.anim.windmill_exit)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//
//
//                        .replace(R.id.framelayout_container, fragment).addToBackStack(null).commit();
//
//            }
//        });


        img1 = view.findViewById(R.id.child1);
        img2 = view.findViewById(R.id.child2);
        img3 = view.findViewById(R.id.child3);
        img4 = view.findViewById(R.id.child4);
        img5 = view.findViewById(R.id.child5);
        img6 = view.findViewById(R.id.child6);
        img7 = view.findViewById(R.id.child7);
        img8 = view.findViewById(R.id.child8);
        img9 = view.findViewById(R.id.child9);
        img10 = view.findViewById(R.id.child10);


        bolim_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_1.setStrokeColor(Color.parseColor("#D48BE1"));
                articulation();
            }
        });


        bolim_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_3.setStrokeColor(Color.parseColor("#D48BE1"));
                tovushlar();
            }
        });


        bolim_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_4.setStrokeColor(Color.parseColor("#D48BE1"));
                barmoqlar();
            }
        });


        bolim_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_5.setStrokeColor(Color.parseColor("#D48BE1"));
                mimika();
            }
        });


        bolim_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_6.setStrokeColor(Color.parseColor("#D48BE1"));
                badantarbiya();
            }
        });

        bolim_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_9.setStrokeColor(Color.parseColor("#D48BE1"));
                puzzleGame();

            }
        });
        bolim_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_10.setStrokeColor(Color.parseColor("#D48BE1"));

               attention();


            }
        });
        bolim_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolim_8.setStrokeColor(Color.parseColor("#D48BE1"));
                imageToVoice();

            }
        });

//        img7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                courses();
//            }
//        });


        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img.png?alt=media&token=b7670961-e62f-4468-a6b8-08c00158e963")
                .placeholder(R.drawable.loading)
                .into(img1, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola1.stopShimmer();
                        bola1.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_1.png?alt=media&token=e783b64b-f47d-4e0c-b869-a8585f3fb3b9")
                .placeholder(R.drawable.loading)
                .into(img2, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola2.stopShimmer();
                        bola2.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_2.png?alt=media&token=e68f8587-1d82-4349-9e89-07f3610d13b1").placeholder(R.drawable.loading)
                .into(img3, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola3.stopShimmer();
                        bola3.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_3.png?alt=media&token=03ac5c48-3292-4829-abb9-29d8c936ec29").placeholder(R.drawable.loading)
                .into(img4, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola4.stopShimmer();
                        bola4.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });
        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_5.png?alt=media&token=400262a9-eea0-4d28-a1b2-69cc1358d9eb").placeholder(R.drawable.loading)
                .into(img5, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola5.stopShimmer();
                        bola5.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_6.png?alt=media&token=4b4809fd-a61b-4cf6-a09e-88c5fb05b0db").placeholder(R.drawable.loading)
                .into(img6, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola6.stopShimmer();
                        bola6.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/education.png?alt=media&token=769f3849-0992-4480-b16d-00a1ca490525").placeholder(R.drawable.loading)
                .into(img7, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola7.stopShimmer();
                        bola7.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/parent.png?alt=media&token=63c0d2c6-3dc8-41f5-9a5c-19cc08c7bc75").placeholder(R.drawable.loading)
                .into(img8, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola8.stopShimmer();
                        bola8.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/international-childrens-day.png?alt=media&token=fecd13ca-69b1-4585-ab6c-c4a9f116da18").placeholder(R.drawable.loading)
                .into(img9, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola9.stopShimmer();
                        bola9.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });


        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_6.png?alt=media&token=4b4809fd-a61b-4cf6-a09e-88c5fb05b0db").placeholder(R.drawable.loading)
                .into(img10, new Callback() {
                    @Override
                    public void onSuccess() {
                        bola10.stopShimmer();
                        bola10.setShimmer(null);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), R.string.xato_yuklash_rasm, Toast.LENGTH_SHORT).show();
                    }
                });
//
//
//
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bola1.startShimmer();
    }

    @Override
    public void onPause() {
        bola1.stopShimmer();
        super.onPause();


    }


    public void articulation() {

        Intent intent = new Intent(getActivity(), Artikulatsiya.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateCard(getContext());


    }


    public void tovushlar() {

        Intent intent = new Intent(getActivity(), TovushActivity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateSlideDown(getContext());

    }


    public void barmoqlar() {

        Intent intent = new Intent(getActivity(), BarmoqActvity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateInAndOut(getContext());


    }

    public void mimika() {

        Intent intent = new Intent(getActivity(), MimikaActivity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateSlideUp(getContext());


    }

    public void badantarbiya() {

        Intent intent = new Intent(getActivity(), TanaHarakatActivity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateSplit(getContext());

    }

    public void puzzleGame() {

        Intent intent = new Intent(getActivity(), OptionActivity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateShrink(getContext());


    }

    public void imageToVoice() {

        Intent intent = new Intent(getActivity(), ImagetoVoice.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateShrink(getContext());
        imagevoice = MediaPlayer.create(getActivity(), R.raw.imagetovoice); // O'zgarish kiritilsin
        imagevoice.start();


    }



    private void attention() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.diqqatni_jamlash);
        builder.setMessage(R.string.Sizga_2ta_birxil_rasm);
        builder.setPositiveButton(R.string.boshladik, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DifferenceFragment fragment = new DifferenceFragment();

                getActivity().getSupportFragmentManager().beginTransaction()

                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.framelayout_container, fragment).addToBackStack(null).commit();
                Animatoo.INSTANCE.animateCard(getContext());


            }
        });
        builder.setNegativeButton(R.string.keyin, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),R.string.asosiy_bolimdasiz, Toast.LENGTH_SHORT).show();


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


//    public void courses(){
//
//        Intent intent = new Intent(getActivity(), Lessons.class);
//        startActivity(intent);
//
//
//
//
//
//    }


}






