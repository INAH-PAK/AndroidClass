package com.mrhi2022.ex053xmlpullparsermovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MovieAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item= items.get(position);

        holder.tvRank.setText(item.rank);
        holder.tvName.setText(item.name);
        holder.tvOpenDt.setText(item.openDt);
        holder.tvAudiAcc.setText(item.audiAcc);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //inner class //////////////////////////////////////
    class VH extends RecyclerView.ViewHolder{

        TextView tvRank;
        TextView tvName;
        TextView tvOpenDt;
        TextView tvAudiAcc;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvRank= itemView.findViewById(R.id.tv_rank);
            tvName= itemView.findViewById(R.id.tv_name);
            tvOpenDt= itemView.findViewById(R.id.tv_open);
            tvAudiAcc= itemView.findViewById(R.id.tv_audi);
        }
    }//////////////////////////////////////////////////
}
