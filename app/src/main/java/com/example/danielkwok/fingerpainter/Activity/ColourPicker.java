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
    private ArrayList<Integer> pickedColours = new ArrayList<>();
    private ArrayList<Integer> tileIds = new ArrayList<>();

    ColorPickerView colourWheel;
    ImageView pickedColour;
    TextView pickedColourHex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_picker);

        Intent intent = getIntent();

        //setting default values
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

    private void setTiles(){
        pickedColours.add(getResources().getColor(R.color.black));
        pickedColours.add(getResources().getColor(R.color.grey));
        pickedColours.add(getResources().getColor(R.color.white));
        pickedColours.add(getResources().getColor(R.color.red));
        pickedColours.add(getResources().getColor(R.color.orange));
        pickedColours.add(getResources().getColor(R.color.yellow));
        pickedColours.add(getResources().getColor(R.color.green));
        pickedColours.add(getResources().getColor(R.color.blue));
        pickedColours.add(getResources().getColor(R.color.darkblue));
        pickedColours.add(getResources().getColor(R.color.purple));
        pickedColours.add(getResources().getColor(R.color.pink));
        pickedColours.add(getResources().getColor(R.color.brown));

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

        for(int i=0; i<pickedColours.size(); i++){
            ImageView tile = findViewById(tileIds.get(i));
            tile.setColorFilter(pickedColours.get(i));
        }
    }

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

    public void selectColourFromTile(View v){
        try{
            int index = tileIds.indexOf(v.getId());
            currentColour = pickedColours.get(index);
            setColourPreview();
        }catch(Exception e){
            Log.d(TAG, "error: "+e);
        }
    }


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
