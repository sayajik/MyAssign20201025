package com.sayajik.myassign20201025.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sayajik.myassign20201025.R;
import com.sayajik.myassign20201025.model.ShapeDataModel;

import java.util.List;

public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ShapeViewHolders>{

    Context mContext;
    List<ShapeDataModel> shapeDataModelList;
    OnItemClickListener onItemClickListener = null;

    public ShapeAdapter(Context mContext, List<ShapeDataModel> shapeDataModelList, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.shapeDataModelList = shapeDataModelList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ShapeViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shape_item, parent, false);
        return new ShapeViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeViewHolders holder, int position) {
        holder.shapeName.setText(shapeDataModelList.get(position).getTitle());
        Log.d("Error", "Image - "+shapeDataModelList.get(position).getLink());
        Glide.with(mContext)
                .load(shapeDataModelList.get(position).getLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .into(holder.shapeImage);
    }

    @Override
    public int getItemCount() {
        return shapeDataModelList.size();
    }

    class ShapeViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView shapeName = null;
        ImageView shapeImage = null;

        public ShapeViewHolders(@NonNull View itemView) {
            super(itemView);
            shapeName = itemView.findViewById(R.id.shape_name);
            shapeImage = itemView.findViewById(R.id.shape_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
