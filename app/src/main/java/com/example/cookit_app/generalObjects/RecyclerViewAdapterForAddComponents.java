package com.example.cookit_app.generalObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import java.util.List;

public class RecyclerViewAdapterForAddComponents extends RecyclerView.Adapter<RecyclerViewAdapterForAddComponents.ViewHolder>{

    private final List<Component> list;
    private final LayoutInflater inflater;
    RecyclerViewAdapterForAddComponents.ItemClickListener clickListener;

    public RecyclerViewAdapterForAddComponents(Context context, List<Component> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public List<Component> getList(){
        return list;
    }

    @NonNull @Override
    public RecyclerViewAdapterForAddComponents.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.add_component_card, parent, false);
        return new RecyclerViewAdapterForAddComponents.ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged") @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Component component = list.get(position);

        holder.component_et.setText(component.getComponent());
        holder.amount_et.setText(component.getAmount());
        holder.remove.setOnClickListener(view -> {
            if(list.size() == 1) return;
            list.remove(position);
            notifyDataSetChanged();
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

        TextView component_et, amount_et;
        ImageButton remove;

        public ViewHolder(View itemView) {
            super(itemView);
             component_et = itemView.findViewById(R.id.component_et);
             amount_et = itemView.findViewById(R.id.amount_et);
             remove = itemView.findViewById(R.id.remove_ib);

             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
