package com.erdemakgul.speakeasy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.erdemakgul.speakeasy.databinding.ActivityEkleBinding;

import java.util.ArrayList;
import java.util.List;

public class Ekle extends AppCompatActivity {

    private DataManager dataManager;
    private EditText firstWordEditText;
    private EditText secondWordEditText;
    public static List<Integer> numbers= new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekle);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        dataManager = new DataManager(this);
        firstWordEditText = findViewById(R.id.birinciistek);
        secondWordEditText = findViewById(R.id.ikinciistek);














    }
    public void deneme(View view) {
        System.out.println("deneme");
        System.out.println("deneme");
        String firstWord = firstWordEditText.getText().toString().trim();
        String secondWord = secondWordEditText.getText().toString().trim();

        // Boşluk kontrolü
        if (firstWord.isEmpty() || secondWord.isEmpty()) {
            Toast.makeText(Ekle.this, "Her iki alan da doldurulmalıdır!", Toast.LENGTH_LONG).show();
        } else {
            dataManager.addEntry(firstWord, secondWord);


            int idvalue=dataManager.iddondur(firstWord,1);

            System.out.println(numbers);
            dataManager.printEntries(idvalue);
            System.out.println("kelimenin id si :"+idvalue);




        }







    }




}