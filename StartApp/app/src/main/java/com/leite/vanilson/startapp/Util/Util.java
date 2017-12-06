package com.leite.vanilson.startapp.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.leite.vanilson.startapp.R;

/**
 * Created by Vanilson on 06/04/17.
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

    public static void showMsgAlertOK(final Activity activity, String title, String message, TypeMsg typeMsg) {
        AlertDialog alertDialog = null;
        int theme = 0;
        int icon = 0;
        switch (typeMsg){
            case INFO:
                icon = R.mipmap.info;
                theme = R.style.AppTheme_Dark_Dialog_Info;
                break;
            case SUCCESS:
                icon = R.mipmap.success;
                theme = R.style.AppTheme_Dark_Dialog_Success;
                break;
            case ALERT:
                icon = R.mipmap.alert;
                theme = R.style.AppTheme_Dark_Dialog_Alert;
                break;
            case ERROR:
                icon = R.mipmap.error;
                theme = R.style.AppTheme_Dark_Dialog_Error;
                break;

        }
        alertDialog = new AlertDialog.Builder(activity, theme).create();
        alertDialog.setIcon(icon);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        final AlertDialog finalAlertDialog = alertDialog;
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Util.showMsgToast(activity, "Util do Toast");
                finalAlertDialog.dismiss();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);
    }
}
