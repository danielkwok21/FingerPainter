package com.example.danielkwok.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.danielkwok.fingerpainter.Utils.Utils;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

import java.util.ArrayList;

public class ColourPicker extends AppCompatActivity {
    private static final String TAG = "ColourPicker";
    private ColorPickerView colorPickerView;
    private int currentColour = Color.BLACK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int[] pickedColours ={
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.orange),
                getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.darkblue),
                getResources().getColor(R.color.purple),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white)
        };

        int[] pickedColourTiles = {
                R.id.colour_picker_color1_iv,
                R.id.colour_picker_color2_iv,
                R.id.colour_picker_color3_iv,
                R.id.colour_picker_color4_iv,
                R.id.colour_picker_color5_iv,
                R.id.colour_picker_color6_iv,
                R.id.colour_picker_color7_iv,
                R.id.colour_picker_color8_iv,
                R.id.colour_picker_color9_iv,
                R.id.colour_picker_color10_iv,
                R.id.colour_picker_color11_iv,
                R.id.colour_picker_color12_iv,
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);

        //set tile colours
        for(int i=0; i<pickedColourTiles.length; i++){
            ImageView tile = findViewById(pickedColourTiles[i]);
            tile.setColorFilter(pickedColours[i]);
        }

        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                currentColour = color;

            }
        });

    }

    public void pickColourFromWheel(){

    }

    public void pickColourFromTile(View v){
        currentColour = ImageViewCompat.getImageTintList((ImageView)v).getDefaultColor();
        Log.d(TAG, "curentColour: "+currentColour);
        Intent intent = new Intent();
        intent.putExtra("pickedColour", currentColour);
        setResult(1, intent);
        finish();
    }
}
