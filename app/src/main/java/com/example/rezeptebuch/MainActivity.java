package com.example.rezeptebuch;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rezeptebuch.ui.NewRecipeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.content.Intent;
import android.content.res.Configuration;

import com.example.rezeptebuch.databinding.ActivityMainBinding;

import java.util.Locale; // Hier wird die Locale-Klasse importiert


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Ingredient.setAllIngredients();

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding Recipe", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.AddButton).show();
                Intent intent = new Intent(MainActivity.this, NewRecipeActivity.class);
                startActivityForResult(intent, 1); // 1 ist der Request-Code
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        if (settingsItem != null) {
            SpannableString s = new SpannableString(settingsItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
            settingsItem.setTitle(s);
        }
        MenuItem languageItem = menu.findItem(R.id.action_language);
        if (languageItem != null) {
            SpannableString s = new SpannableString(languageItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
            languageItem.setTitle(s);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_language) {
            showLanguageSelectionDialog(); // Zeige den Dialog an
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLanguageSelectionDialog() {
        String[] languages = {
                getString(R.string.action_language_de), // Deutsch
                getString(R.string.action_language_en), // Englisch
                getString(R.string.action_language_sk)  // Slowakisch
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_language) // Titel des Dialogs aus strings.xml
                .setItems(languages, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            setLocale("de"); // Deutsch
                            break;
                        case 1:
                            setLocale("en"); // Englisch
                            break;
                        case 2:
                            setLocale("sk"); // Slowakisch
                            break;
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Um die Aktivität neu zu starten und die Änderungen anzuzeigen
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Die Daten von der recipeActivity holen
            String name = data.getStringExtra("ingredient_name");
            int amount = data.getIntExtra("ingredient_amount", 0);
            float weight = data.getFloatExtra("ingredient_weight", 0f);

            // Füge die Zutat der Liste hinzu oder aktualisiere die UI
            Ingredient newIngredient = new Ingredient(Ingredient.AllIngredients.size(), name, amount, weight);
            Ingredient.AllIngredients.add(newIngredient);

            Snackbar.make(binding.getRoot(), "Ingredient added: " + name, Snackbar.LENGTH_LONG).show();
        }
    }
}
