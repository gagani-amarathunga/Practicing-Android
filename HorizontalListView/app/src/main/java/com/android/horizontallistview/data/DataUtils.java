package com.android.horizontallistview.data;

import com.android.horizontallistview.R;

public class DataUtils {

    // Item names
    private static String fruitItemNames[] = {"Apples", "Mandarins", "Grapes", "Cherries", "Plums", "Persimmons", "Currants"};
    private static String vegetableItemNames[] = {"Carrot", "Green Beans", "Tomatoes", "Broccoli", "Pumpkin", "Potatoes", "Asparagus"};

    // Image IDs of the items
    private static int fruitImages[] = {R.drawable.apples, R.drawable.mandarins, R.drawable.grapes, R.drawable.cherries, R.drawable.plums, R.drawable.persimmons, R.drawable.currants};
    private static int vegetableImages[] = {R.drawable.carrot, R.drawable.green_beans, R.drawable.tomatoes, R.drawable.broccoli, R.drawable.pumpkin, R.drawable.potatos, R.drawable.asparagus};

    public String[] getFruitItemNames() {
        return fruitItemNames;
    }

    public void setFruitItemNames(String[] fruitItemNames) {
        this.fruitItemNames = fruitItemNames;
    }

    public String[] getVegetableItemNames() {
        return vegetableItemNames;
    }

    public void setVegetableItemNames(String[] vegetableItemNames) {
        this.vegetableItemNames = vegetableItemNames;
    }

    public int[] getFruitImages() {
        return fruitImages;
    }

    public void setFruitImages(int[] fruitImages) {
        this.fruitImages = fruitImages;
    }

    public int[] getVegetableImages() {
        return vegetableImages;
    }

    public void setVegetableImages(int[] vegetableImasge) {
        this.vegetableImages = vegetableImasge;
    }

}
