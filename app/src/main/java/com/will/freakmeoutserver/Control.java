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
import com.will.freakmeoutserver.Model.Grab;

public class Control extends AppCompatActivity implements View.OnTouchListener,OnMessage {

    private Button lbtn;
    private Button dbtn;
    private Button ubtn;
    private Button rbtn;
    private Button grab;

    public float posx=250,posy=250;



    Boolean Upress = false;
    Boolean Dpress = false;
    Boolean Lpress= false;
    Boolean Rpress = false;
    Boolean Gpress=false;



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


                    case R.id.grab:
                        Log.e("grab", "graaaaaaaaaaaaab");
                        Gpress=true;
                        new Thread(
                        ()-> {
                            while(Gpress){
                                int g=0;
                                Grab grab = new Grab(g);
                                Gson gson = new Gson();
                                String json= gson.toJson(grab);
                                tcp.sendMessage(json);
                            }
                        }
                        ).start();
                        break;



                    case R.id.ubtn:
                        Log.e("up", "presiooooooooon");
                        Upress = true;
                        new Thread(
                                () -> {
                                    while (Upress) {
                                        if (posy >= 10) {
                                            posy -= 0.05;
                                            Coord coor = new Coord(posx, posy);
                                            Gson gson = new Gson();
                                            String json = gson.toJson(coor);
                                            tcp.sendMessage(json);
                                        }
                                    }
                                }
                        ).start();
                        break;


                    case R.id.dbtn:
                        Log.e("down", "presiooooooooon");
                        Dpress = true;
                        new Thread(
                                () -> {
                                    while (Dpress) {
                                        if (posy <= 650) {
                                            posy += 0.05;

                                            Coord coor = new Coord(posx, posy);
                                            Gson gson = new Gson();
                                            String json = gson.toJson(coor);
                                            tcp.sendMessage(json);
                                        }
                                    }
                                }
                        ).start();
                        break;


                    case R.id.lbtn:
                        Log.e("left", "presiooooooooon");
                        Lpress = true;
                        new Thread(
                                () -> {
                                    while (Lpress) {
                                        if (posx >= 50) {
                                            posx -= 0.05;

                                            Coord coor = new Coord(posx, posy);
                                            Gson gson = new Gson();
                                            String json = gson.toJson(coor);
                                            tcp.sendMessage(json);
                                        }
                                    }
                                }
                        ).start();
                        break;


                    case R.id.rbtn:
                        Log.e("right", "presiooooooooon");
                        Rpress = true;
                        new Thread(
                                () -> {
                                    while (Rpress) {
                                        if (posx <= 1100) {
                                            posx += 0.05;

                                            Coord coor = new Coord(posx, posy);
                                            Gson gson = new Gson();
                                            String json = gson.toJson(coor);
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

                    case R.id.lbtn:
                        Lpress=false;
                        Log.e("down","Suaveeee");
                        break;

                    case R.id.rbtn:
                        Rpress=false;
                        Log.e("down","Suaveeee");
                        break;


                    case R.id.grab:
                        Gpress= false;
                        Log.e("grab","suaveeee");

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
