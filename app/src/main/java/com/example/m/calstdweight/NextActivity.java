package com.example.m.calstdweight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by M on 4/15/2016.
 */
public class NextActivity extends AppCompatActivity {
    private String resultStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Bundle bun = this.getIntent().getExtras();
        resultStr = bun.getString("ResultStr");
        TextView textView = (TextView)findViewById(R.id.resultStr);
        if (textView != null) textView.setText(resultStr);
    }

    public void sendResult(View view){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, resultStr);
        sendIntent.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

        /**
        Intent chooser = Intent.createChooser(sendIntent, "Your Standard Weight");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }**/

    }

    public void closeApp(View view){
        this.finish();
    }

    public void goBack(View view){
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
