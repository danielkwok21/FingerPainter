package com.example.danielkwok.fingerpainter.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.example.danielkwok.fingerpainter.R;
import com.example.danielkwok.fingerpainter.Utils.Utils;
import com.example.danielkwok.fingerpainter.Views.FingerPainterView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int SELECT_PIC = 1;
    private static final int SELECT_COLOUR = 2;
    private static final int SELECT_BRUSH = 3;
    private FingerPainterView myFingerPainterView;

    private ImageView main_gallery_iv;
    private ImageView main_color_iv;
    private ImageView main_brush_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFingerPainterView = findViewById(R.id.myFingerPainterViewId);
        main_gallery_iv = findViewById(R.id.main_gallery_iv);
        main_color_iv = findViewById(R.id.main_color_iv);
        main_brush_iv = findViewById(R.id.main_brush_iv);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myFingerPainterView.setColour(extras.getInt("colour"));
        }

        myFingerPainterView.load(getIntent().getData());

        main_gallery_iv.setOnClickListener((v)->{
            chooseImage();
        });

        main_color_iv.setOnClickListener((v)->
            selectColor()
        );

        main_brush_iv.setOnClickListener((v)->
            selectBrush()
        );
    }

    private void chooseImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PIC);
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
                case SELECT_PIC:
                    Uri uri = data.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        myFingerPainterView.setImageBackground(bitmap);
                    }catch(Exception e){
                        Log.d(TAG, "URI not available");
                    }

                    break;
                case SELECT_COLOUR:
                    int pickedColour = data.getIntExtra("pickedColour", 1);
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
