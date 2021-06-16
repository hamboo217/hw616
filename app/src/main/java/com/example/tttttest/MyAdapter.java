package com.example.tttttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<roo> allWords = new ArrayList<>();

    public void setAllWords(List<roo> allWords) {
        this.allWords = allWords;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell,parent,false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {
        roo roo = allWords.get(position);
        holder.textViewnum.setText(roo.getPartySize());
        holder.textViewname.setText(roo.getGuestName());
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewnum,textViewname;
        int texttime;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textViewnum = itemView.findViewById(R.id.textViewnum);
            textViewname = itemView.findViewById(R.id.textViewname);


        }
    }
}
