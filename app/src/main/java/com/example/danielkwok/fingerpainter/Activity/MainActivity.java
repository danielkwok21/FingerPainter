package com.example.danielkwok.fingerpainter.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.danielkwok.fingerpainter.R;
import com.example.danielkwok.fingerpainter.Utils.Utils;
import com.example.danielkwok.fingerpainter.Views.FingerPainterView;

public class MainActivity extends AppCompatActivity {

    FingerPainterView myFingerPainterView;
    ImageView main_color_iv;
    ImageView main_brush_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            myFingerPainterView.setColour(extras.getInt("colour"));
        }

        myFingerPainterView = findViewById(R.id.myFingerPainterViewId);

        main_color_iv = findViewById(R.id.main_color_iv);
        main_color_iv.setOnClickListener((v)->
            selectColor()
        );

        main_brush_iv = findViewById(R.id.main_brush_iv);
        main_brush_iv.setOnClickListener((v)->
            selectBrush()
        );
    }

    private void selectColor(){
        Intent intent = new Intent(this, ColourPicker.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null && requestCode == 1){
            int pickedColour = data.getIntExtra("pickedColour", 1);
            myFingerPainterView.setColour(pickedColour);
        }
    }

    private void selectBrush(){
        Utils.printToast(getApplicationContext(),"Brush clicked");
    }
}
