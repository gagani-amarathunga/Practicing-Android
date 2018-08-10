package com.android.horizontallistview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.horizontallistview.R;

import java.util.Currency;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private String itemName;
    private int imageResourceId;
    private String itemPrice;

    private TextView item_desc_text_view;
    private ImageView item_image_image_view;
    private TextView item_price_text_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        item_desc_text_view = (TextView) findViewById(R.id.item_desc_title_text_view);
        item_image_image_view = (ImageView) findViewById(R.id.item_image_view);
        item_price_text_view = (TextView) findViewById(R.id.item_price_text_view);

        // Retrieve the content passed through the intent
        itemName = getIntent().getStringExtra("ITEM_NAME");
        imageResourceId = getIntent().getIntExtra("ITEM_IMAGE", R.drawable.apples);
        itemPrice = Currency.getInstance(Locale.US).getSymbol() + getIntent().getIntExtra("ITEM_PRICE", 1) + "";

        item_desc_text_view.setText(itemName);
        item_image_image_view.setImageResource(imageResourceId);
        item_price_text_view.setText(itemPrice);
    }
}
