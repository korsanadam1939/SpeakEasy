package com.erdemakgul.speakeasy;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private TextView textView;
    private DataManager dataManager;
    private Random RANDOM = new Random();
    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        textView = findViewById(R.id.kelimeyazi);
        dataManager = new DataManager(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        System.out.println(Ekle.numbers);
        dataManager.listeyeekle();
        List<Integer> numbers = Ekle.numbers;
        myImageView = findViewById(R.id.imageView3);


        kelimeayarla();

    }

    public void ekle(View view) {
        Intent intent = new Intent(Quiz.this, Ekle.class);
        startActivity(intent);
    }

    public void yenile(View view) {
        myImageView.setImageResource(R.drawable.tablo);
        dataManager.listeyeekle();
        kelimeayarla();
        myImageView.setClickable(true);


    }
    public void card(View view){
        myImageView.setImageResource(R.drawable.quizz);
        String metin = textView.getText().toString();
        String ikinci=dataManager.ikinciyibul(metin);
        textView.setText(ikinci);
        myImageView.setClickable(false);





    }
    public void bildim(View view){
        // ImageView nesnesini al


// Görselin kaynak id'sini kontrol etmek için
        int expectedDrawableId = R.drawable.quizz; // Kontrol etmek istediğiniz görselin id'si
        Drawable currentDrawable = myImageView.getDrawable();

        if (currentDrawable != null && currentDrawable.getConstantState() != null) {
            Drawable expectedDrawable = ContextCompat.getDrawable(this, expectedDrawableId);

            if (expectedDrawable != null && expectedDrawable.getConstantState() != null &&
                    currentDrawable.getConstantState().equals(expectedDrawable.getConstantState())) {
                System.out.println("sarı");
                String metin = textView.getText().toString();
                int id=dataManager.iddondur(metin,2);
                dataManager.deleteEntry(id);
                Ekle.numbers.removeAll(Collections.singleton(id));
                kelimeayarla();
                myImageView.setClickable(true);
                renkyesil();


            } else {

                String metin = textView.getText().toString();
                int id=dataManager.iddondur(metin,1);
                dataManager.deleteEntry(id);
                Ekle.numbers.removeAll(Collections.singleton(id));
                kelimeayarla();
                myImageView.setClickable(true);
                renkyesil();            }
        } else {
            System.out.println("görsel yok");
        }



    }
    public void bilemedim(View view){
        kelimeayarla();
        myImageView.setClickable(true);
        renkyesil();

    }


    public void kelimeayarla(){
        List<Integer> numbers = Ekle.numbers;
        if (numbers != null && !numbers.isEmpty()) {

            int randomIndex = RANDOM.nextInt(numbers.size());
            int randomItem = numbers.get(randomIndex);
            String kelime=dataManager.printEntries(randomItem);
            textView.setText(kelime);


        } else {
            textView.setText("KELİME YOK");
        }
        renkyesil();

    }

    public void renkyesil(){
        myImageView.setImageResource(R.drawable.tablo);
    }


}
