package com.example.samandar_demo.Sqlite_KinderGarden.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.R;
import com.example.samandar_demo.Sqlite_KinderGarden.model.FoodModel;


import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Holder> {

    private Context context;
    private List<FoodModel> foodList;

    // create object for Onitemclick interface
    private OnItemClickListener mListerner;



    public FoodAdapter(Context context, List<FoodModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_custom_layout,parent,false);
        return new Holder(view, mListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.bogcha_name.setText(foodList.get(position).getBogcha_name());
        holder.viloyat.setText(foodList.get(position).getViloyat());
        holder.xizmatlar.setText(foodList.get(position).getXizmatlar());
        holder.kuntartib.setText(foodList.get(position).getKuntartib());
        holder.tili.setText(foodList.get(position).getTili());
        holder.ratsion.setText(foodList.get(position).getRatsion());
        holder.bola_soni.setText(foodList.get(position).getBola_soni());
        holder.tolov_oy.setText(foodList.get(position).getTolov_oy());
        holder.ustunlik_jihat.setText(foodList.get(position).getUstunlik_jihat());
        holder.bonus.setText(foodList.get(position).getBonus());
        holder.xavfsizlik.setText(foodList.get(position).getXavfsizlik());
        holder.raqam.setText(foodList.get(position).getRaqam());
        holder.qoshimca_malumot.setText(foodList.get(position).getQoshimca_malumot());

        // now process image get from database & set it in imageview throw model class
        byte[] foodImageArray = foodList.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImageArray,0,foodImageArray.length);
        holder.foodImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {

        if (foodList == null) {
            return 0;
        }
        return foodList.size();

    }

    class Holder extends RecyclerView.ViewHolder{

        TextView bogcha_name , viloyat , xizmatlar , kuntartib , tili , ratsion , bola_soni , tolov_oy , ustunlik_jihat , bonus , xavfsizlik  , raqam , qoshimca_malumot;
        ImageView foodImage;

        Holder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.frasmi);
            bogcha_name = itemView.findViewById(R.id.fbogcha_name);
            viloyat = itemView.findViewById(R.id.fviloyat);
            xizmatlar = itemView.findViewById(R.id.fxizmatlar);
            kuntartib = itemView.findViewById(R.id.fkuntartib);
            tili = itemView.findViewById(R.id.ftili);
            ratsion = itemView.findViewById(R.id.fratsion);
            bola_soni = itemView.findViewById(R.id.fbola_soni);
            tolov_oy = itemView.findViewById(R.id.ftolov_oy);
            ustunlik_jihat = itemView.findViewById(R.id.fustunlik_jihat);
            bonus = itemView.findViewById(R.id.fbonus);
            xavfsizlik = itemView.findViewById(R.id.fxavfsizlik);
            raqam = itemView.findViewById(R.id.fraqam);
            qoshimca_malumot = itemView.findViewById(R.id.fqoshimca_malumot);

            // add click on itemview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener!= null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    //interface
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    // this will call in activity
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListerner = listener;
    }

}
