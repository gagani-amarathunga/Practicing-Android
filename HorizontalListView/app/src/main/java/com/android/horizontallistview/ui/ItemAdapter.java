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
import java.util.Currency;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private RecyclerView recyclerView;
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

        /* Setting item view holder to take 40 percent of the screen width of the device */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int) (parent.getWidth() * 0.4);
        view.setLayoutParams(params);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String currencySymbol = Currency.getInstance(Locale.US).getSymbol();
        String itemPrice = currencySymbol + String.valueOf(itemsList.get(position).itemPrice);

        holder.itemName.setText(itemsList.get(position).itemName);

        /* Setting the image view to take 40 percent of the screen width of the device to fill the item holder */
        holder.itemImage.getLayoutParams().width = (int) (this.recyclerView.getWidth() * 0.4);
        holder.itemImage.setImageResource(itemsList.get(position).imageResourceId);

        holder.itemPrice.setText(itemPrice);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView itemName;
        private ImageView itemImage;
        private TextView itemPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Passing item details to detail activity
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("ITEM_NAME", itemsList.get(getAdapterPosition()).itemName);
            intent.putExtra("ITEM_IMAGE", itemsList.get(getAdapterPosition()).imageResourceId);
            intent.putExtra("ITEM_PRICE", itemsList.get(getAdapterPosition()).itemPrice);
            view.getContext().startActivity(intent);
        }
    }
}
