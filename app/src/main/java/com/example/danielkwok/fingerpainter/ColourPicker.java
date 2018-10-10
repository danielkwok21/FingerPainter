package com.example.danielkwok.fingerpainter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.danielkwok.fingerpainter.Utils.Utils;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class ColourPicker extends AppCompatActivity {
    private ColorPickerView colorPickerView;
    private int currentColour = Color.BLACK;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);

        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                currentColour = color;
                Utils.printToast(getApplicationContext(), Integer.toString(currentColour));
            }
        });

        back = findViewById(R.id.colour_picker_back_btn);
        back.setOnClickListener((v)->{
            Intent intent = new Intent(ColourPicker.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void pickColour(View v){
        //currentColour = ImageViewCompat.getImageTintList((ImageView)v).getDefaultColor();
        Utils.printToast(getApplicationContext(), Integer.toString(currentColour));
    }
}
