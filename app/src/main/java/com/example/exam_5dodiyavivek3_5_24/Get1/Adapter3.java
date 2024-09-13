package com.example.exam_5dodiyavivek3_5_24.Get1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam_5dodiyavivek3_5_24.Get3.Model2;
import com.example.exam_5dodiyavivek3_5_24.R;

import java.util.List;

public class Adapter3 extends RecyclerView.Adapter<Adapter3.MyViewHolder>{

    private final List<Model2> products;
    public Context context;


    public Adapter3(List<Model2> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.getapi2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter3.MyViewHolder holder, int position) {

        holder.id.setText(products.get(position).getId());
        holder.name.setText(products.get(position).getName());
        holder.year.setText(products.get(position).getYear());
        holder.color.setText(products.get(position).getColor());
        holder.pantone_value.setText(products.get(position).getPantone_value());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,year,color,pantone_value;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            year = itemView.findViewById(R.id.year);
            color = itemView.findViewById(R.id.color);
            pantone_value = itemView.findViewById(R.id.pantone_value);

        }
    }
}
