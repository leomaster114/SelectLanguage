package com.example.selectlanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferencesManager mInstance;
    TextView tv_ptit,tv_name;
    Button btn_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = SharedPreferencesManager.getInstance(this);
        if (mInstance.isSelected()){
            String languageToLoad; // your language
            String lang = mInstance.getData();
            if(lang.equals("English")) languageToLoad  = "en";
            else languageToLoad  = "vi";;
            setLocale(languageToLoad);
        }
        else{
            showDialog();
        }
        setContentView(R.layout.activity_main);
        tv_ptit = findViewById(R.id.tv_ptit);
        tv_name = findViewById(R.id.tv_name);

        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInstance.clear();
                showDialog();
            }
        });
    }
    public void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        CharSequence[] items = {"Vietnamese", "English"};
        dialog.setTitle(R.string.select);
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    Toast.makeText(MainActivity.this,"Vietnamese",Toast.LENGTH_SHORT).show();
                    mInstance.saveData("Vietnamese");
                }else{
                    Toast.makeText(MainActivity.this,"English",Toast.LENGTH_SHORT).show();
                    mInstance.saveData("English");
                }
            }
        });
        dialog.show();
    }
    public void setLocale(String langCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        configuration.locale = new Locale(langCode);
        res.updateConfiguration(configuration,dm);
    }
}
