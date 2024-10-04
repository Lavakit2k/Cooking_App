package com.example.rezeptebuch;

import android.content.Context;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileManager {

    private static final String dataFile = "data.json";
    private static JSONObject Data = new JSONObject();

    public FileManager() {
    }

    public static String GetGameFileString() {
        return dataFile;
    }

    public static JSONObject GetGameFile() {
        return Data;
    }

    public String loadJSONFromFile(Context context) {
        String json = null;
        try {
            FileInputStream fis = context.openFileInput(dataFile);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            Data = new JSONObject(json);  // Daten aus JSON laden
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public void Generate(Context context) {
        try {
            // Sprache in die JSON-Datei speichern (languageCode: 0 = Englisch, 1 = Deutsch, 2 = Slowakisch)
            Data.put("language", 0);

            // Datei im internen Speicher der App speichern
            FileOutputStream fos = context.openFileOutput(dataFile, Context.MODE_PRIVATE);
            fos.write(Data.toString().getBytes());
            fos.close();
            Toast.makeText(context, "Language saved: " + 0, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLanguageCode(Context context) {
        loadJSONFromFile(context);
        try {
            // Lade den Sprachcode aus der Datei (standardmäßig 0 = Englisch)
            return Data.optInt("language", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

