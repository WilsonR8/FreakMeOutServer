package com.will.freakmeoutserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;
import com.will.freakmeoutserver.Model.Coord;

public class MainActivity extends AppCompatActivity {

    private Button nextbtn;
    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nextbtn=findViewById(R.id.nextbtn);
        tcp = TCPSingleton.getInstance();
        tcp.start();



        nextbtn.setOnClickListener(
                (v) -> {

                    Intent i = new Intent(this,Control.class);
                    startActivity(i);




                }
        );












    }
}