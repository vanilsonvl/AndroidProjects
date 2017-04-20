package com.leite.vanilson.startapp.Util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leite.vanilson.startapp.R;

/**
 * Created by vanilsonvl on 06/04/17.
 */

public class Util {

    public static void showMsgToast(Activity activity, String message){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layoutToast = inflater.inflate(R.layout.my_toast, (ViewGroup) activity.findViewById(R.id.lytToast));

        TextView txtToast = (TextView) layoutToast.findViewById(R.id.txtToast);
        txtToast.setText(message);


//        Toast toast = Toast.makeText(this, "Primeiro Toast", Toast.LENGTH_LONG);
        Toast toast = new Toast(activity);
        toast.setView(layoutToast);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showMsgAlertOK(Activity activity, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon(R.mipmap.info);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}
