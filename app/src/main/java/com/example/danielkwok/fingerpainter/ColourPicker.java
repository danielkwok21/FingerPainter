package com.example.danielkwok.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
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
import java.util.Map;
import java.util.Set;

public class ColourPicker extends AppCompatActivity {
    private static final String TAG = "ColourPicker";
    private ColorPickerView colorPickerView;
    private int currentColour = Color.BLACK;

    ArrayList<Integer> pickedColours = new ArrayList<>();
    ArrayList<Integer> tileIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);



        tileIds.add(R.id.colour_picker_color1_iv);
        tileIds.add(R.id.colour_picker_color2_iv);
        tileIds.add(R.id.colour_picker_color3_iv);
        tileIds.add(R.id.colour_picker_color4_iv);
        tileIds.add(R.id.colour_picker_color5_iv);
        tileIds.add(R.id.colour_picker_color6_iv);
        tileIds.add(R.id.colour_picker_color7_iv);
        tileIds.add(R.id.colour_picker_color8_iv);
        tileIds.add(R.id.colour_picker_color9_iv);
        tileIds.add(R.id.colour_picker_color10_iv);
        tileIds.add(R.id.colour_picker_color11_iv);
        tileIds.add(R.id.colour_picker_color12_iv);

        setTileColours();

        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                currentColour = color;
                updateTileColours();
                if(fromUser){
                    Log.d(TAG, "currentColour: "+currentColour);
                    Intent intent = new Intent();
                    intent.putExtra("pickedColour", currentColour);
                    setResult(1, intent);
                    finish();
                }
            }
        });
    }

    private void updateTileColours(){
        pickedColours.add(0, currentColour);
        pickedColours.remove(11);
        setTileColours();
    }

    private void setTileColours(){
        for(int i=0; i<pickedColours.size(); i++){
            ImageView tile = findViewById(tileIds.get(i));
            tile.setColorFilter(pickedColours.get(i));
        }
    }


    public void pickColourFromTile(View v){
        try{
            int index = tileIds.indexOf(v.getId());
            currentColour = pickedColours.get(index);
            Log.d(TAG, "curentColour: "+currentColour);
            Intent intent = new Intent();
            intent.putExtra("pickedColour", currentColour);
            setResult(1, intent);
            finish();
        }catch(Exception e){
            Log.d(TAG, "error: "+e);
        }
    }
}
