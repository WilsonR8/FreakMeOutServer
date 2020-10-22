package com.will.freakmeoutserver;

import android.location.OnNmeaMessageListener;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSingleton extends Thread{


    // Objeto unico que se va a usar en todas las demás sesiones
    protected static TCPSingleton instanciaUnica;

    // Constructor inaccesible por medios normales
    private TCPSingleton() {}

    protected static TCPSingleton getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new TCPSingleton();
        }
        return instanciaUnica;
    }


private OnMessage observer;
public void setObserver(OnMessage observer){
this.observer = observer;
}



    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    public void run() {
        try {
            // 1.esperando conexion, saludar

            socket = new Socket("192.168.1.33",5000);
            // 3.cliente y servidor conectados
            System.out.println("Cliente conectado");

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writer = new BufferedWriter(osw);

            while (true) {
                System.out.println("Esperando...");
                String line = reader.readLine();
                System.out.println("Recibido: " + line);
            }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    public void sendMessage(String msg) {
        new Thread(
                () -> {
            try {
                writer.write(msg + "\n");
                writer.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        ).start();

    }
}










