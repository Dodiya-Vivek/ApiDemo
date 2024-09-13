package com.example.exam_5dodiyavivek3_5_24.Get2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam_5dodiyavivek3_5_24.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final List<Model> products;
    public Context context;


    public Adapter(List<Model> allProduct, Context context) {
        this.products = allProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.getapi2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,year,color,pantone_value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            year = itemView.findViewById(R.id.year);
            color = itemView.findViewById(R.id.color);
            pantone_value = itemView.findViewById(R.id.pantone_value);

        }
    }
}
