package com.android.horizontallistview.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.horizontallistview.vo.Item;
import com.android.horizontallistview.R;
import com.android.horizontallistview.data.DataUtils;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    private ArrayList<Item> listOfItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private int fragmentId;
    // Name of the items
    private String itemNames[];
    // Image IDs of the item images
    private int itemImages[];

    public ItemsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataUtils dataUtils = new DataUtils();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            fragmentId = bundle.getInt("ID", 0);
        }

        switch (fragmentId) {
            case 1: // Getting vegetable item details
                itemNames = dataUtils.getVegetableItemNames();
                itemImages = dataUtils.getVegetableImages();
                break;
            default: // Getting fruit items details
                itemNames = dataUtils.getFruitItemNames();
                itemImages = dataUtils.getFruitImages();
                break;
        }

        // Addding Item objects to the list view
        for (int i = 0; i < itemNames.length; i++) {
            Item item = new Item();
            item.itemName = itemNames[i];
            item.imageResourceId = itemImages[i];
            listOfItems.add(item);
        }

        getActivity().setTitle("Organic Cart");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.items_card_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        if (listOfItems.size() > 0 && recyclerView != null) {
            recyclerView.setAdapter(new ItemAdapter(getActivity(), listOfItems));
        }
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
