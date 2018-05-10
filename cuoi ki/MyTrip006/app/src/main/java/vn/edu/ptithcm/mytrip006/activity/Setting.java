package vn.edu.ptithcm.mytrip006.activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import vn.edu.ptithcm.mytrip006.R;


public class Setting extends AppCompatActivity {

    public static void settings(String title, String color, ActionBar actionBar, Context context){
        /*final Drawable upArrow = context.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        final Drawable search = context.getResources().getDrawable(R.drawable.na_searchicon);
        final Drawable home = context.getResources().getDrawable(R.drawable.na_ic_home_white);
        final Drawable cart = context.getResources().getDrawable(R.drawable.na_cart);*/
        actionBar.setTitle(title);
        String setColor = color;
        int[] colors = new int[5];
        colors[0] = context.getResources().getColor(R.color.colorDefault);
        colors[1] = context.getResources().getColor(R.color.colorGreen);
        colors[2] = context.getResources().getColor(R.color.colorPink);
        colors[3] = context.getResources().getColor(R.color.colorOrange);
        colors[4] = context.getResources().getColor(R.color.colorBlue);
        /*if(setColor.equals("Green"))
        {
            actionBar.setTitle(Html.fromHtml("<font color='" + Color.BLACK + "'>" + title + "</font>"));
            upArrow.setColorFilter(colors[1], PorterDuff.Mode.SRC_ATOP);
            search.setColorFilter(colors[1], PorterDuff.Mode.SRC_ATOP);
            home.setColorFilter(colors[1], PorterDuff.Mode.SRC_ATOP);
            cart.setColorFilter(colors[1], PorterDuff.Mode.SRC_ATOP);
        } else {
            actionBar.setTitle(Html.fromHtml("<font color='" + Color.WHITE + "'>" + title + "</font>"));
            upArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            search.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            home.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            cart.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
        actionBar.setHomeAsUpIndicator(upArrow);*/
        switch (setColor){
            case "Default":
                actionBar.setBackgroundDrawable(new ColorDrawable(colors[0]));
                break;
            case "Green":
                actionBar.setBackgroundDrawable(new ColorDrawable(colors[1]));
                break;
            case "Pink":
                actionBar.setBackgroundDrawable(new ColorDrawable(colors[2]));
                break;
            case "Orange":
                actionBar.setBackgroundDrawable(new ColorDrawable(colors[3]));
                break;
            case "Blue":
                actionBar.setBackgroundDrawable(new ColorDrawable(colors[4]));
                break;
            default: break;
        }
    }
}