package com.example.danielkwok.fingerpainter.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.danielkwok.fingerpainter.R;

public class BrushPicker extends AppCompatActivity {

    private static final String TAG = "BrushPicker";

    private static final String ROUND = "ROUND";
    private static final String SQUARE = "SQUARE";
    private int defaultSize = 10;
    private String defaultBrush = ROUND;
    private final int MAX_SIZE = 200;
    private int currentSize;
    private String currentBrush;

    ImageView roundBrush;
    ImageView roundHighlight;
    ImageView squareBrush;
    ImageView squareHighlight;
    ImageView previewBrush;
    SeekBar brushSizeSeekBar;
    TextView brushSizeText;
    ImageView increaseBrushSize;
    ImageView decreaseBrushSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_picker);

        Intent intent = getIntent();

        //setting default values from mainActivity
        //depends on value from FingerPainterView. ie if default value from there changes, so does it here
        if (intent != null) {
            defaultSize = intent.getIntExtra("defaultSize", 1);
            defaultBrush = intent.getStringExtra("defaultBrush");
        }

        currentSize = defaultSize;
        currentBrush = defaultBrush;

        roundBrush = findViewById(R.id.brush_picker_roundBrush_iv);
        roundHighlight = findViewById(R.id.brush_picker_round_highlight_iv);
        squareBrush = findViewById(R.id.brush_picker_squareBrush_iv);
        squareHighlight = findViewById(R.id.brush_picker_square_highlight_iv);
        previewBrush = findViewById(R.id.brush_picker_size_iv);
        brushSizeSeekBar = findViewById(R.id.brush_picker_sizeBar_iv);
        brushSizeText = findViewById(R.id.brush_picker_size_tv);
        increaseBrushSize = findViewById(R.id.brush_picker_size_dec_iv);
        decreaseBrushSize = findViewById(R.id.brush_picker_size_inc_iv);

        roundBrush.setOnClickListener((v)->{
            setBrush(ROUND);
        });

        squareBrush.setOnClickListener((v)->{
            setBrush(SQUARE);
        });

        setBrush(currentBrush);
        setBrushSize(currentSize);

        //seekbar
        brushSizeSeekBar.setMax(MAX_SIZE);
        brushSizeSeekBar.setProgress(currentSize);
        brushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrushSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        brushSizeText.setText(Integer.toString(defaultSize));

        decreaseBrushSize.setOnClickListener((v)->{
            increaseBrushSize();
        });

        increaseBrushSize.setOnClickListener((v)->{
            decreaseBrushSize();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentSize", currentSize);
        outState.putString("currentBrush", currentBrush);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentSize = savedInstanceState.getInt("currentSize");
        currentBrush = savedInstanceState.getString("currentBrush");
        //set default brush
        setBrush(currentBrush);

        //preview brush size image
        setBrushSize(currentSize);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("pickedBrush", currentBrush);
        intent.putExtra("pickedSize", currentSize);
        setResult(1, intent);
        finish();
        return true;
    }

    //sets brush based on user selection
    private void setBrush(String brush){
        currentBrush = brush;
        if(currentBrush.equals(ROUND)){
            previewBrush.setImageResource(R.drawable.brush_size_round);
            roundHighlight.setVisibility(View.VISIBLE);
            squareHighlight.setVisibility(View.INVISIBLE);

        }else if(currentBrush.equals(SQUARE)){
            previewBrush.setImageResource(R.drawable.brush_size_square);
            roundHighlight.setVisibility(View.INVISIBLE);
            squareHighlight.setVisibility(View.VISIBLE);
        }
    }

    //sets brush size based on user selection
    //+1 is added because an image size of 0 means its default size.
    //hence for previewBrush, the smallest size is actually 1, instead of the UI displayed 0
    private void setBrushSize(int size){
        currentSize = size;
        previewBrush.getLayoutParams().height = currentSize+1;
        previewBrush.getLayoutParams().width = currentSize+1;
        previewBrush.requestLayout();

        brushSizeText.setText(Integer.toString(currentSize));
        brushSizeSeekBar.setProgress(currentSize);
    }

    private void increaseBrushSize(){
        if(currentSize<MAX_SIZE){
            currentSize++;
            setBrushSize(currentSize);
        }
    }

    private void decreaseBrushSize(){
        if(currentSize>0){
            currentSize--;
            setBrushSize(currentSize);
        }
    }
}
