package com.example.cookit_app.generalObjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit_app.R;
import com.example.cookit_app.server.responseObjects.Recipe;

import java.util.List;

public class RecyclerViewAdapterForAddComponents extends RecyclerView.Adapter<RecyclerViewAdapterForAddComponents.ViewHolder>{

    private final List<Recipe> list;
    private final LayoutInflater inflater;
    RecyclerViewAdapter.ItemClickListener clickListener;

    public RecyclerViewAdapterForAddComponents(Context context, List<Recipe> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForAddComponents.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecyclerViewAdapterForAddComponents.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView component_et, amount_et;
        ImageButton add, remove;

        public ViewHolder(View itemView) {
            super(itemView);
            //add the views
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
