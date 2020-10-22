package com.will.freakmeoutserver;

import androidx.appcompat.app.AppCompatActivity;

import android.location.OnNmeaMessageListener;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.will.freakmeoutserver.Model.Coord;

public class Control extends AppCompatActivity implements View.OnTouchListener,OnMessage {

    private Button lbtn;
    private Button dbtn;
    private Button ubtn;
    private Button rbtn;
    private Button grab;

    float posx=250;
    float posy=250;

    Boolean Upress = false;
    Boolean Dpress = false;
    Boolean Lpress= false;
    Boolean Rpress = false;



    private TCPSingleton tcp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        lbtn = findViewById(R.id.lbtn);
        dbtn = findViewById(R.id.dbtn);
        ubtn = findViewById(R.id.ubtn);
        rbtn = findViewById(R.id.rbtn);
        grab = findViewById(R.id.grab);



        tcp = TCPSingleton.getInstance();
        tcp.setObserver(this);

        lbtn.setOnTouchListener(this);
        dbtn.setOnTouchListener(this);
        ubtn.setOnTouchListener(this);
        rbtn.setOnTouchListener(this);
        grab.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                switch(v.getId()) {

                    case R.id.ubtn:
                        Log.e("up", "presiooooooooon");
                        Upress = true;
                        Gson Gup = new Gson();
                        new Thread(
                                () -> {
                                    while (Upress) {
                                        if (posy >= 10) {
                                            posy -= 0.2;

                                            Coord move = new Coord(posx, posy);
                                            String json = Gup.toJson(move);
                                            tcp.sendMessage(json);
                                        }
                                    }
                                }
                        ).start();
                        break;


                    case R.id.dbtn:
                        Log.e("down", "presiooooooooon");
                        Dpress = true;
                        Gson Gdown = new Gson();
                        new Thread(
                                () -> {
                                    while (Dpress) {
                                        if (posy <= 650) {
                                            posy += 0.2;

                                            Coord move = new Coord(posx, posy);
                                            String json = Gdown.toJson(move);
                                            tcp.sendMessage(json);
                                        }
                                    }
                                }
                        ).start();
                        break;
                }
                break;




            case MotionEvent.ACTION_UP:
                switch(v.getId()){
                    case R.id.ubtn:
                        Upress=false;
                        Log.e("up","Suaveeee");
                        break;

                    case R.id.dbtn:
                        Dpress=false;
                        Log.e("down","Suaveeee");
                        break;
                }
        break;

        }

        return true;
    }


    @Override
    public void messageback(String msg) {
        Log.e("MSG LLEGOOO",""+msg);
    }
}
