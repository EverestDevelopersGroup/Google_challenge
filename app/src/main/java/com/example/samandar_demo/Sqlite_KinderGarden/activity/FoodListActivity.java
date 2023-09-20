package com.example.samandar_demo.Sqlite_KinderGarden.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.R;
import com.example.samandar_demo.Sqlite_KinderGarden.SqlActivity;
import com.example.samandar_demo.Sqlite_KinderGarden.adapter.FoodAdapter;
import com.example.samandar_demo.Sqlite_KinderGarden.model.FoodModel;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FoodModel> foodList;
    private LinearLayoutManager layoutManager;
    private FoodAdapter adapter;
    private TextView nodatatext;

    // dialog widget
    private ImageView rasmi;
    private EditText bogcha_nameF , viloyatF , xizmatlarF , kuntartibF , tiliF , ratsionF , bola_soniF , tolov_oyF , ustunlik_jihatF , bonusF , xavfsizlikF  , raqamF , qoshimca_malumotF;

    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        nodatatext = findViewById(R.id.nodatatext);
        recyclerView = findViewById(R.id.foodrecyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        foodList = new ArrayList<>();
        adapter = new FoodAdapter(this, foodList);
        recyclerView.setAdapter(adapter);

        // check data exist or not in list
        if (foodList.size() == 0)
        {
            nodatatext.setVisibility(View.VISIBLE);
        }

        //get foodlist data from sqlite database
        Cursor cursor = SqlActivity.sQliteHelper.getdata("SELECT * FROM FOOD");
        foodList.clear();
        while (cursor.moveToNext()) {
            // get data from column like index 0 = column1, 1 = column2
            int id = cursor.getInt(0);
            String bogcha_name  = cursor.getString(1);
            String viloyat = cursor.getString(2);
            String xizmatlar = cursor.getString(3);
            String kuntartib = cursor.getString(4);
            String tili = cursor.getString(5);
            String ratsion = cursor.getString(6);
            String bola_soni = cursor.getString(7);
            String tolov_oy = cursor.getString(8);
            String ustunlik_jihat = cursor.getString(9);
            String bonus = cursor.getString(10);
            String xavfsizlik = cursor.getString(11);
            String raqam = cursor.getString(12);
            String qoshimcha_malumot = cursor.getString(13);
            byte[] rasmi = cursor.getBlob(14);

            // set received data to list throw model class
            foodList.add(new FoodModel(id, bogcha_name, viloyat , xizmatlar , kuntartib , tili , ratsion , bola_soni  , tolov_oy , ustunlik_jihat , bonus , xavfsizlik  , raqam , qoshimcha_malumot ,rasmi ));
            nodatatext.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {

                final CharSequence[] items = {"Ma`lumotlarni tahrirlash", "Ma`lumotlarni o`chirish"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(FoodListActivity.this);
                dialog.setTitle("Kerakli bo`limni tanlang:");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            //GET POSITION OF ROW
                            Cursor c = SqlActivity.sQliteHelper.getdata("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // Show dialog updata here
                            showDialogUpdate(FoodListActivity.this, arrID.get(position));
                        } else {

                            //GET POSITION OF ROW
                            Cursor c = SqlActivity.sQliteHelper.getdata("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // delete operation dialog method call
                            showDialogDelete(arrID.get(position));

                        }

                    }
                });
                dialog.show();
            }
        });

    }

    // show dialog for delete data
    private void showDialogDelete(final int id) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FoodListActivity.this);
        alertDialogBuilder.setTitle("Eslatma").setMessage("Ushbu ma`lumotlarni o`chirishni xohlaysizmi?").setPositiveButton("Ha", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // delete operation
                try
                {
                    SqlActivity.sQliteHelper.deleteData(id);
                    Toast.makeText(getApplicationContext(), "Ma'lumotlar o`chirildi", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                // call fro refresh food list
                refreshFoodList();

            }
        }).setNegativeButton("hozir emas", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        }).show();

    }


    // show dialog for update
    private void showDialogUpdate(Activity activity, final int position) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_food_dialog);
        dialog.setTitle("Update Food");
        bogcha_nameF = dialog.findViewById(R.id.nomi);
        viloyatF = dialog.findViewById(R.id.viloyat);
        xizmatlarF = dialog.findViewById(R.id.xizmatlar);
        kuntartibF = dialog.findViewById(R.id.kun_tartib);
        tiliF = dialog.findViewById(R.id.tili);
        ratsionF = dialog.findViewById(R.id.ratsion);
        bola_soniF = dialog.findViewById(R.id.bola_soni);
        tolov_oyF = dialog.findViewById(R.id.tolov_oy);
        ustunlik_jihatF = dialog.findViewById(R.id.ustunlik_jihat);
        bonusF = dialog.findViewById(R.id.bonus);
        xavfsizlikF = dialog.findViewById(R.id.xavfsizlik);
        raqamF = dialog.findViewById(R.id.raqam);
        qoshimca_malumotF = dialog.findViewById(R.id.qoshimcha_malumot);
        rasmi = dialog.findViewById(R.id.dialogimagefood);

        updateButton = dialog.findViewById(R.id.dialogupdatebutton);

        // set dialog width
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 1.0);
        // set dialog width
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 1.0);
        // set height & width to dialog for apply
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        // dialog image click operation
        rasmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Request Permission
                ActivityCompat.requestPermissions(FoodListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 888);

            }
        });

        // update button click oepration in dialog
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SqlActivity.sQliteHelper.updateData(bogcha_nameF.getText().toString().trim(),
                            viloyatF.getText().toString().trim(), xizmatlarF.getText().toString().trim(),
                            kuntartibF.getText().toString().trim(), tiliF.getText().toString().trim(),
                            ratsionF.getText().toString().trim(), bola_soniF.getText().toString().trim(),
                            tolov_oyF.getText().toString().trim(), ustunlik_jihatF.getText().toString().trim(),
                            bonusF.getText().toString().trim(), xavfsizlikF.getText().toString().trim(),
                            raqamF.getText().toString().trim(), qoshimca_malumotF.getText().toString().trim(),
                           SqlActivity.imageViewToByte(rasmi), position);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ma'lumotlar yangilandi", Toast.LENGTH_SHORT).show();
                // call fro refresh food list
                refreshFoodList();

            }
        });

    }

    // this method is fro when update data & dialog close it will refresh recyclerview
    private void refreshFoodList() {
        //get foodlist data from sqlite database
        Cursor cursor = SqlActivity.sQliteHelper.getdata("SELECT * FROM FOOD");
        foodList.clear();
        while (cursor.moveToNext()) {
            // get data from column like index 0 = column1, 1 = column2

//            int id, String bogcha_name ,
//                    String viloyat ,
//                    String xizmatlar ,
//                    String kuntartib ,
//                    String tili ,
//                    String ratsion ,
//                    String bola_soni ,
//                    String tolov_oy ,
//                    String ustunlik_jihat ,
//                    String bonus , String xavfsizlik
//                    , String raqam ,
//                    String qoshimca_malumot ,
//            byte[] image


            int id = cursor.getInt(0);
            String bogcha_name = cursor.getString(1);
            String viloyat = cursor.getString(2);
            String xizmatlar = cursor.getString(3);
            String kuntartib = cursor.getString(4);
            String tili = cursor.getString(5);
            String ratsion = cursor.getString(6);
            String bola_soni = cursor.getString(7);
            String tolov_oy = cursor.getString(8);
            String ustunlik_jihat = cursor.getString(9);
            String bonus = cursor.getString(10);
            String xavfsizlik = cursor.getString(11);
            String raqam = cursor.getString(12);
            String qoshimca_malumot = cursor.getString(13);
            byte[] foodimage = cursor.getBlob(14);

            // set received data to list throw model class
            foodList.add(new FoodModel(id, bogcha_name, viloyat, xizmatlar, kuntartib, tili, ratsion, bola_soni, tolov_oy, ustunlik_jihat, bonus, xavfsizlik, raqam, qoshimca_malumot, foodimage));

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            } else {
                Toast.makeText(FoodListActivity.this, "Sizda fayllarga kirish uchun ruxsat yo`q", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // on Activity Result override method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // set our ui image view from storage choose image
                rasmi.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}