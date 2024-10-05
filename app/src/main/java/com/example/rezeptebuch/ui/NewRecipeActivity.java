package com.example.rezeptebuch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rezeptebuch.R;

public class NewRecipeActivity extends AppCompatActivity {

    private EditText ingredientName;
    private EditText ingredientAmount;
    private EditText ingredientWeight;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        // Verknüpfe UI-Elemente mit den Views
        ingredientName = findViewById(R.id.ingredient_name);
        ingredientAmount = findViewById(R.id.ingredient_amount);
        ingredientWeight = findViewById(R.id.ingredient_weight);
        addButton = findViewById(R.id.button_add);

        // Beim Klicken auf "Hinzufügen" den Namen, die Menge und das Gewicht erfassen
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ingredientName.getText().toString();
                int amount = Integer.parseInt(ingredientAmount.getText().toString());
                float weight = Float.parseFloat(ingredientWeight.getText().toString());

                // Die Daten an die MainActivity zurückgeben
                Intent resultIntent = new Intent();
                resultIntent.putExtra("ingredient_name", name);
                resultIntent.putExtra("ingredient_amount", amount);
                resultIntent.putExtra("ingredient_weight", weight);
                setResult(RESULT_OK, resultIntent);
                finish(); // Schließt die Activity und gibt die Daten zurück
            }
        });
    }
}

