package com.example.rezeptebuch;

import android.graphics.Picture;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;
    private ArrayList<Ingredient> Ingredients = new ArrayList<>();
    private Picture picture;
    public Recipe(int id, String name){
        this.id = id;
        this.name = name;
        this.Ingredients = new ArrayList<>();
    }
    public void addIngredient(Ingredient ingredient)
    {
        this.Ingredients.add(ingredient);
    }
}
