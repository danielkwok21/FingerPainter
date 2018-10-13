package com.example.danielkwok.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView colour_picker_displayColour_iv;
    private TextView colour_picker_hexCode_tv;

    private ArrayList<Integer> pickedColours;
    private ArrayList<Integer> tileIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null){
            pickedColours = savedInstanceState.getIntegerArrayList("pickedColours");
            tileIds = savedInstanceState.getIntegerArrayList("tileIds");
        }else{
            pickedColours = new ArrayList<>();
            tileIds = new ArrayList<>();

            pickedColours.add(getResources().getColor(R.color.black));
            pickedColours.add(getResources().getColor(R.color.white));
            pickedColours.add(getResources().getColor(R.color.red));
            pickedColours.add(getResources().getColor(R.color.orange));
            pickedColours.add(getResources().getColor(R.color.yellow));
            pickedColours.add(getResources().getColor(R.color.green));
            pickedColours.add(getResources().getColor(R.color.blue));
            pickedColours.add(getResources().getColor(R.color.darkblue));
            pickedColours.add(getResources().getColor(R.color.purple));
            pickedColours.add(getResources().getColor(R.color.white));
            pickedColours.add(getResources().getColor(R.color.white));
            pickedColours.add(getResources().getColor(R.color.white));

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

        }

        setTileColours();

        //displayColour
        colour_picker_displayColour_iv = findViewById(R.id.colour_picker_displayColour_iv);

        //hexCode
        colour_picker_hexCode_tv = findViewById(R.id.colour_picker_hexCode_tv);

        //colour wheel
        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                if(fromUser){
                    currentColour = color;
                    colour_picker_displayColour_iv.setColorFilter(currentColour);
                    String colourHex = getString(R.string.hex_colour, Integer.toHexString(currentColour));
                    colour_picker_hexCode_tv.setText(colourHex);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("pickedColour", currentColour);
        setResult(1, intent);
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateTileColours();
    }

    private void updateTileColours(){
        pickedColours.add(0, currentColour);
        pickedColours.subList(10, 11).clear();
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
        }catch(Exception e){
            Log.d(TAG, "error: "+e);
        }
    }
}
