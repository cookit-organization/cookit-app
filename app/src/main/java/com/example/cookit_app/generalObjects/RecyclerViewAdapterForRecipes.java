package com.example.cookit_app.generalObjects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import com.example.cookit_app.server.responseObjects.Recipe;

import java.util.List;

public class RecyclerViewAdapterForRecipes extends RecyclerView.Adapter<RecyclerViewAdapterForRecipes.ViewHolder> {

    private final List<Recipe> list;
    private final LayoutInflater inflater;
    ItemClickListener clickListener;
    private final Context context;

    public RecyclerViewAdapterForRecipes(Context context, List<Recipe> list) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = list.get(position);

        //set actions for each recipe card here !
        holder.recipe_name.setText(recipe.recipe.getName());
        holder.author_name.setText(recipe.getAuthor_name());

        holder.recipe_image.setOnClickListener(view -> {
            //take him to recipe
            Intent i = new Intent(context, Recipe.class);
            //pass recipe
        });
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recipe_image;
        ImageButton save_button;
        TextView recipe_name, author_name;

        public ViewHolder(View itemView) {
            super(itemView);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            save_button = itemView.findViewById(R.id.save_button);
            recipe_name = itemView.findViewById(R.id.recipe_name_tv);
            author_name = itemView.findViewById(R.id.author_name_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}

