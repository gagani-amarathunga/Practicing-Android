package com.android.horizontallistview.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.horizontallistview.vo.Item;
import com.android.horizontallistview.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Item> itemsList;
    private Context context;

    public ItemAdapter(Context context, ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemName.setText(itemsList.get(position).itemName);
        holder.itemImage.setImageResource(itemsList.get(position).imageResourceId);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemName;
        ImageView itemImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Passing item details to detail activity
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("ITEM_NAME", itemsList.get(getAdapterPosition()).itemName);
            intent.putExtra("ITEM_IMAGE", itemsList.get(getAdapterPosition()).imageResourceId);
            view.getContext().startActivity(intent);
        }
    }
}
