package com.example.listviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener listener;

    public MyAdapter(List<String> names,int layout,OnItemClickListener listener){
        this.names=names;
        this.layout=layout;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(names.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;

        public  ViewHolder(View v){
            super(v);
            this.textViewName = v.findViewById(R.id.textView);
        }

        public void bind(final String name, final OnItemClickListener listener){
            this.textViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name,getAdapterPosition());
                }
            });
        }
    }



    public interface OnItemClickListener{
        void onItemClick(String name, int position);
    }
}
