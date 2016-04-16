package com.example.m.calstdweight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculate standard weight
     */
    public void caculateStdWeight(View v) {
        RadioButton rbFemal = (RadioButton) findViewById(R.id.femal);
        RadioButton rbMale = (RadioButton) findViewById(R.id.male);
        EditText ft = (EditText) findViewById(R.id.height_ft);
        EditText in = (EditText) findViewById(R.id.height_in);
        boolean isMale = rbMale.isChecked();
        boolean isFemal = rbFemal.isChecked();
        String ftStr = ft.getText().toString();
        String inStr = in.getText().toString();
        String alert = null;
        String alertMsg = null;
        if (!isMale && !isFemal) {
            alert = "Alert - No Gender";
            alertMsg = "Please select Gender";
        }
        if (ftStr.length() == 0 && inStr.length() == 0) {
            if (alert != null) {
                alert = alert + " And No Height";
                alertMsg = "Please select Gender and enter Height.";
            } else {
                alert = "Alert - No Height";
                alertMsg = "Please enter Height.";
            }
        }
        if (alert != null) {
            alert = alert + "!";
            openAlertDialog(alert, alertMsg);
            return;
        }

        double feet = 0;
        double inch = 0;
        if (ftStr.length() > 0) feet = Double.parseDouble(ftStr);
        if (inStr.length() > 0) inch = Double.parseDouble(inStr);
        double heightCm = feet * 30.48 + inch * 2.54;

        int weightKg = getWeight(isMale, heightCm);
        int weightLbs = (int)(weightKg * 2.20462);

        String resultStr = "You Standard Weight is: " + weightLbs + "Lbs" +" or " + weightKg + "Kg";

        Intent intent = new Intent();
        intent.setClass(this, NextActivity.class);
        Bundle bun = new Bundle();
        bun.putString("ResultStr", resultStr);
        intent.putExtras(bun);
        startActivity(intent);

        this.finish();

    }

    /**
     * Get standard weight (kg) based on gender and height
     */
    public int getWeight(boolean male, double height) {
        if (male)
            return (int)((height-80)*0.7);
        else
            return (int)((height-70)*0.6);
    }
    /**
     * Open Alert Dialog
     */
    public void openAlertDialog(String alert, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(alert)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever..."
                            }
                        }
                ).create().show();
    }
}
