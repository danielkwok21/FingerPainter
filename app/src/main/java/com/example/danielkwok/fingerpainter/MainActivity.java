package com.example.danielkwok.fingerpainter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.danielkwok.fingerpainter.Utils.Utils;
import com.example.danielkwok.fingerpainter.Views.FingerPainterView;

public class MainActivity extends AppCompatActivity {

    FingerPainterView myFingerPainterView;
    ImageView main_color_iv;
    ImageView main_brush_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFingerPainterView = findViewById(R.id.myFingerPainterViewId);
        main_color_iv = findViewById(R.id.main_color_iv);
        main_color_iv.setOnClickListener((v)-> Utils.printToast(getApplicationContext(),"Colour wheel clicked"));

        main_brush_iv = findViewById(R.id.main_brush_iv);
        main_brush_iv.setOnClickListener((v)->Utils.printToast(getApplicationContext(),"Brush clicked"));

    }
}
