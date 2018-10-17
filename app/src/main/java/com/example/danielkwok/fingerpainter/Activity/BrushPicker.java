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

    private static final String BUTT = "BUTT";
    private static final String ROUND = "ROUND";
    private static final String SQUARE = "SQUARE";
    private int defaultSize = 10;
    private String defaultBrush = ROUND;
    private final int MAX_SIZE = 200;
    private int currentSize;
    private String currentBrush;

    ImageView buttBrush;
    ImageView buttHighlight;
    ImageView roundBrush;
    ImageView roundHighlight;
    ImageView squareBrush;
    ImageView squareHighlight;
    ImageView previewBrushSize;
    SeekBar brushSizeSeekBar;
    TextView brushSizeText;
    ImageView increaseBrushSize;
    ImageView decreaseBrushSize;
    TextView brushType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush_picker);

        Intent intent = getIntent();

        //setting default values from mainActivity
        if (intent != null) {
            defaultSize = intent.getIntExtra("defaultSize", 1);
            defaultBrush = intent.getStringExtra("defaultBrush");
        }

        currentSize = defaultSize;
        currentBrush = defaultBrush;

        buttBrush = findViewById(R.id.brush_picker_buttBrush_iv);
        buttHighlight = findViewById(R.id.brush_picker_butt_highlight_iv);
        roundBrush = findViewById(R.id.brush_picker_roundBrush_iv);
        roundHighlight = findViewById(R.id.brush_picker_round_highlight_iv);
        squareBrush = findViewById(R.id.brush_picker_squareBrush_iv);
        squareHighlight = findViewById(R.id.brush_picker_square_highlight_iv);
        previewBrushSize = findViewById(R.id.brush_picker_size_iv);
        brushSizeSeekBar = findViewById(R.id.brush_picker_sizeBar_iv);
        brushSizeText = findViewById(R.id.brush_picker_size_tv);
        increaseBrushSize = findViewById(R.id.brush_picker_size_dec_iv);
        decreaseBrushSize = findViewById(R.id.brush_picker_size_inc_iv);
        brushType = findViewById(R.id.brush_picker_brush_tv);

        buttBrush.setOnClickListener((v)->{
            setBrush(BUTT);
        });

        roundBrush.setOnClickListener((v)->{
            setBrush(ROUND);
        });

        squareBrush.setOnClickListener((v)->{
            setBrush(SQUARE);
        });

        brushType.setText(defaultBrush);

        //set default brush
        setBrush(defaultBrush);

        //preview brush size image
        previewBrushSize.getLayoutParams().height = currentSize;
        previewBrushSize.getLayoutParams().width = currentSize;

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

    private void setBrush(String brush){
        currentBrush = brush;
        brushType.setText(currentBrush);
        if(currentBrush.equals(BUTT)){
            buttHighlight.setVisibility(View.VISIBLE);
            roundHighlight.setVisibility(View.INVISIBLE);
            squareHighlight.setVisibility(View.INVISIBLE);
        }else if(currentBrush.equals(ROUND)){
            buttHighlight.setVisibility(View.INVISIBLE);
            roundHighlight.setVisibility(View.VISIBLE);
            squareHighlight.setVisibility(View.INVISIBLE);
        }else if(currentBrush.equals(SQUARE)){
            buttHighlight.setVisibility(View.INVISIBLE);
            roundHighlight.setVisibility(View.INVISIBLE);
            squareHighlight.setVisibility(View.VISIBLE);
        }
    }

    private void setBrushSize(int size){
        currentSize = size;
        previewBrushSize.getLayoutParams().height = currentSize;
        previewBrushSize.getLayoutParams().width = currentSize;
        previewBrushSize.requestLayout();

        brushSizeText.setText(Integer.toString(currentSize));
    }

    private void increaseBrushSize(){
        currentSize++;
        setBrushSize(currentSize);
    }

    private void decreaseBrushSize(){
        currentSize--;
        setBrushSize(currentSize);
    }
}
