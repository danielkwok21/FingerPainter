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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ColourPicker extends AppCompatActivity {
    private static final String TAG = "ColourPicker";
    private ColorPickerView colorPickerView;
    private int currentColour = Color.BLACK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        HashMap<Integer, Integer> colourTiles = new HashMap<>();
        colourTiles.put(getResources().getColor(R.color.black), R.id.colour_picker_color1_iv);
        colourTiles.put(getResources().getColor(R.color.white), R.id.colour_picker_color2_iv);
        colourTiles.put(getResources().getColor(R.color.red), R.id.colour_picker_color3_iv);
        colourTiles.put(getResources().getColor(R.color.orange), R.id.colour_picker_color4_iv);
        colourTiles.put(getResources().getColor(R.color.yellow), R.id.colour_picker_color5_iv);
        colourTiles.put(getResources().getColor(R.color.green), R.id.colour_picker_color6_iv);
        colourTiles.put(getResources().getColor(R.color.blue), R.id.colour_picker_color7_iv);
        colourTiles.put(getResources().getColor(R.color.darkblue), R.id.colour_picker_color8_iv);
        colourTiles.put(getResources().getColor(R.color.purple), R.id.colour_picker_color9_iv);
        colourTiles.put(getResources().getColor(R.color.white), R.id.colour_picker_color10_iv);
        colourTiles.put(getResources().getColor(R.color.white), R.id.colour_picker_color11_iv);
        colourTiles.put(getResources().getColor(R.color.white), R.id.colour_picker_color12_iv);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);

        //set tile colours
        Set set = colourTiles.entrySet();
        Iterator itr = set.iterator();
        while(itr.hasNext()){
            //https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
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

    public void pickColourFromTile(ImageView v){
        //currentColour = ImageViewCompat.getImageTintList((ImageView)v).getDefaultColor();
        currentColour = (int)v.getColorFilter();
        Log.d(TAG, "curentColour: "+currentColour);
        Intent intent = new Intent();
        intent.putExtra("pickedColour", currentColour);
        setResult(1, intent);
        finish();
    }
}
