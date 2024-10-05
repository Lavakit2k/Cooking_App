package com.example.rezeptebuch;


import java.util.ArrayList;

public class Ingredient {
    public final int id;
    public final String name;
    public int amount;
    public float weight;
    public final static ArrayList<Ingredient> AllIngredients = new ArrayList<>();
    private int picture;

    public Ingredient(int id, String name, int pic){
        this.id = id;
        this.name = name;
        this.picture = pic;
    }

    public Ingredient(int id, String name, int amount, float weight){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.weight = weight;
    }

    public static void setAllIngredients() {
        AllIngredients.add(new Ingredient(0, "water", R.drawable.water));
        AllIngredients.add(new Ingredient(1, "milk", R.drawable.milk));
        AllIngredients.add(new Ingredient(2, "honey", R.drawable.honey));
        AllIngredients.add(new Ingredient(3, "flour", R.drawable.flour));
        AllIngredients.add(new Ingredient(4, "potato", R.drawable.potato));
    }

    public Ingredient getIngredientById(int id) {
        for (Ingredient ingredient : AllIngredients) {

            if (ingredient.id == id) {

                return ingredient;
            }
        }

        return null;
    }

    public Ingredient Clone(){
        return new Ingredient(this.id, this.name, this.amount, this.weight);
    }
    public Ingredient CloneWithWeight(float weight){
        return new Ingredient(this.id, this.name, this.amount, weight);
    }
    public Ingredient CloneWithAmount(int amount){
        return new Ingredient(this.id, this.name, amount, this.weight);
    }
    public Ingredient CloneWithWeightAndAmount(float weight, int amount){
        return new Ingredient(this.id, this.name, amount, weight);
    }
}
