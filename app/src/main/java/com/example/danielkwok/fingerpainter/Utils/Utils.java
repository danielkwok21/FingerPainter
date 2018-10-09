package com.example.danielkwok.fingerpainter.Utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void printToast(Context c,String s){
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}
