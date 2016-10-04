package com.appteamnith.hillffair;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Akatsuki on 10/4/2016.
 */
public class ThemeSelectionDialog implements View.OnClickListener {
    Context context;

    public ThemeSelectionDialog(Context context) {
        this.context = context;
    }

    void show() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_theme_selection, null);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        ImageButton batman, flash, superman, captain, hulk, wonderwoman;
        batman = (ImageButton)view.findViewById(R.id.batman);
        flash = (ImageButton)view.findViewById(R.id.flash);
        superman = (ImageButton)view.findViewById(R.id.superman);
        captain =(ImageButton) view.findViewById(R.id.captain);
        wonderwoman =(ImageButton) view.findViewById(R.id.wonderwoman);
        hulk = (ImageButton)view.findViewById(R.id.hulk);

        batman.setOnClickListener(this);
        flash.setOnClickListener(this);
        superman.setOnClickListener(this);
        captain.setOnClickListener(this);
        wonderwoman.setOnClickListener(this);
        hulk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.batman:break;
            case R.id.flash:break;
            case R.id.superman:break;
            case R.id.captain:break;
            case R.id.wonderwoman:break;
            case R.id.hulk:break;


        }
    }
}
