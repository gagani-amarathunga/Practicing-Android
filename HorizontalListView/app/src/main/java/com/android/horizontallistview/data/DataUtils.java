package com.android.horizontallistview.data;

import com.android.horizontallistview.R;

public class DataUtils {

    // Item names
    private static String fruitItemNames[] = {
            "Apples",
            "Mandarins",
            "Grapes",
            "Cherries",
            "Plums",
            "Persimmons",
            "Currants"};

    private static String vegetableItemNames[] = {
            "Carrot",
            "Green Beans",
            "Tomatoes",
            "Broccoli",
            "Pumpkin",
            "Potatoes",
            "Asparagus"};

    // Image IDs of the items
    private static int fruitImages[] = {
            R.drawable.apples,
            R.drawable.mandarins,
            R.drawable.grapes,
            R.drawable.cherries,
            R.drawable.plums,
            R.drawable.persimmons,
            R.drawable.currants};

    private static int vegetableImages[] = {
            R.drawable.carrot,
            R.drawable.green_beans,
            R.drawable.tomatoes,
            R.drawable.broccoli,
            R.drawable.pumpkin,
            R.drawable.potatos,
            R.drawable.asparagus};

    // Prices of the items
    private static int fruitPrices[] = {2, 5, 7, 8, 3, 2, 4};
    private static int vegetablePrices[] = {3, 2, 4, 7, 5, 2, 4};

    public String[] getFruitItemNames() {
        return fruitItemNames;
    }

    public void setFruitItemNames(String[] fruitItemNames) {
        DataUtils.fruitItemNames = fruitItemNames;
    }

    public String[] getVegetableItemNames() {
        return vegetableItemNames;
    }

    public void setVegetableItemNames(String[] vegetableItemNames) {
        DataUtils.vegetableItemNames = vegetableItemNames;
    }

    public int[] getFruitImages() {
        return fruitImages;
    }

    public void setFruitImages(int[] fruitImages) {
        DataUtils.fruitImages = fruitImages;
    }

    public int[] getVegetableImages() {
        return vegetableImages;
    }

    public void setVegetableImages(int[] vegetableImasge) {
        vegetableImages = vegetableImasge;
    }

    public int[] getFruitPrices() {
        return fruitPrices;
    }

    public void setFruitPrices(int[] fruitPrices) {
        DataUtils.fruitPrices = fruitPrices;
    }

    public int[] getVegetablePrices() {
        return vegetablePrices;
    }

    public void setVegetablePrices(int[] vegetablePrices) {
        DataUtils.vegetablePrices = vegetablePrices;
    }
}
