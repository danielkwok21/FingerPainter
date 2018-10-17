package com.example.danielkwok.fingerpainter.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danielkwok.fingerpainter.R;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

import java.util.ArrayList;

public class ColourPicker extends AppCompatActivity {
    private static final String TAG = "ColourPicker";

    private int defaultColour = Color.BLACK;
    private int currentColour;
    private ArrayList<Integer> tileColours = new ArrayList<>();
    private ArrayList<Integer> tileIds = new ArrayList<>();

    //ColorPickerView is a library from https://github.com/skydoves/ColorPickerPreference by skydoves
    //allows for implementation of colour wheel
    ColorPickerView colourWheel;
    ImageView pickedColour;
    TextView pickedColourHex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);

        Intent intent = getIntent();

        //setting default values
        //depends on value from FingerPainterView. ie if default value from there changes, so does it here
        if (intent != null) {
            defaultColour = intent.getIntExtra("defaultColour", R.color.black);
            currentColour = defaultColour;
        }

        pickedColour = findViewById(R.id.colour_picker_displayColour_iv);
        pickedColourHex = findViewById(R.id.colour_picker_hexCode_tv);
        colourWheel = findViewById(R.id.colorPickerView);

        setTiles();
        setColourPreview();

        colourWheel.setColorListener(selectColourFromWheel());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColour", currentColour);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentColour = savedInstanceState.getInt("currentColour");

        setColourPreview();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("pickedColour", currentColour);
        setResult(1, intent);
        finish();
        return true;
    }

    //prepare array of tileColours & tileIds
    //iterates through array to assign each tile a colour
    private void setTiles(){
        tileColours.add(getResources().getColor(R.color.black));
        tileColours.add(getResources().getColor(R.color.grey));
        tileColours.add(getResources().getColor(R.color.white));
        tileColours.add(getResources().getColor(R.color.red));
        tileColours.add(getResources().getColor(R.color.orange));
        tileColours.add(getResources().getColor(R.color.yellow));
        tileColours.add(getResources().getColor(R.color.green));
        tileColours.add(getResources().getColor(R.color.blue));
        tileColours.add(getResources().getColor(R.color.darkblue));
        tileColours.add(getResources().getColor(R.color.purple));
        tileColours.add(getResources().getColor(R.color.pink));
        tileColours.add(getResources().getColor(R.color.brown));

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

        for(int i = 0; i< tileColours.size(); i++){
            ImageView tile = findViewById(tileIds.get(i));
            tile.setColorFilter(tileColours.get(i));
        }
    }

    //if user selects a colour from colourWheel
    private ColorListener selectColourFromWheel(){
        return new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                if(fromUser){
                    currentColour = color;
                    setColourPreview();
                }
            }
        };
    }

    //if user selects a colour from tile
    public void selectColourFromTile(View v){
        try{
            int index = tileIds.indexOf(v.getId());
            currentColour = tileColours.get(index);
            setColourPreview();
        }catch(Exception e){
            Log.d(TAG, "error: "+e);
        }
    }

    //updates the preview based on colour selected
    private void setColourPreview(){
        pickedColour.setColorFilter(currentColour);
        String colourHex = getString(R.string.hex_colour, Integer.toHexString(currentColour));
        pickedColourHex.setText(colourHex);
        if(currentColour==Color.BLACK){
            pickedColourHex.setTextColor(Color.WHITE);
        }else{
            pickedColourHex.setTextColor(Color.BLACK);
        }
    }
}
