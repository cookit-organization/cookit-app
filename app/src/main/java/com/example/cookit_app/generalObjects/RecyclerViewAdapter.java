package com.example.cookit_app.generalObjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<RecipeCard> list;
    private LayoutInflater inflater;
    ItemClickListener clickListener;

    public RecyclerViewAdapter(Context context, List<RecipeCard> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeCard recipeCard = list.get(position);
        //set actions for each recipe card here !
        holder.recipe_name.setText(recipeCard.getRecipe_name());
        holder.author_name.setText(recipeCard.getAuthor_name());
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

