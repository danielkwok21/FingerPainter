package com.example.danielkwok.fingerpainter.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.example.danielkwok.fingerpainter.R;
import com.example.danielkwok.fingerpainter.Views.FingerPainterView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int SELECT_COLOUR = 1;
    private static final int SELECT_BRUSH = 2;

    private FingerPainterView myFingerPainterView;
    ImageView main_color_iv;
    ImageView main_brush_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFingerPainterView = findViewById(R.id.myFingerPainterViewId);
        main_color_iv = findViewById(R.id.main_color_iv);
        main_brush_iv = findViewById(R.id.main_brush_iv);

        //load image onto background if open from external app
        Intent intent = getIntent();
        if(intent!=null){
            try{
                myFingerPainterView.load(getIntent().getData());
            }catch (Exception e){
                Log.d(TAG, "error: "+e);
            }
        }

        main_color_iv.setOnClickListener((v)->
            selectColor()
        );

        main_brush_iv.setOnClickListener((v)->
            selectBrush()
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColour", myFingerPainterView.getColour());
        outState.putString("currentBrush", myFingerPainterView.getBrush().toString());
        outState.putInt("currentSize", myFingerPainterView.getBrushWidth());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myFingerPainterView.setColour(savedInstanceState.getInt("currentColour"));
        myFingerPainterView.setBrush(Paint.Cap.valueOf(savedInstanceState.getString("currentBrush")));
        myFingerPainterView.setBrushWidth(savedInstanceState.getInt("currentSize"));
    }

    private void selectColor(){
        Intent intent = new Intent(this, ColourPicker.class);
        intent.putExtra("defaultColour", myFingerPainterView.getColour());
        startActivityForResult(intent, SELECT_COLOUR);
    }

    private void selectBrush(){
        Intent intent = new Intent(this, BrushPicker.class);
        intent.putExtra("defaultSize", myFingerPainterView.getBrushWidth());
        intent.putExtra("defaultBrush", myFingerPainterView.getBrush().toString());
        startActivityForResult(intent, SELECT_BRUSH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch(requestCode){
                case SELECT_COLOUR:
                    int pickedColour = data.getIntExtra("pickedColour", R.color.black);
                    myFingerPainterView.setColour(pickedColour);
                    break;

                case SELECT_BRUSH:
                    Paint.Cap brush = Paint.Cap.valueOf(data.getStringExtra("pickedBrush"));
                    int size = data.getIntExtra("pickedSize", 20);
                    myFingerPainterView.setBrush(brush);
                    myFingerPainterView.setBrushWidth(size);
            }
        }
    }
}
