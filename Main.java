package com.example.karty;

import com.example.Menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        int Port = 2222;
        Thread serwerThread = new Thread(() -> {
            Serwer.start(Port);
        });
        serwerThread.start();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread client1Thread = new Thread(() -> {
            try {
                Klient.start("127.0.0.1", Port, "Klient_1");
            } catch (SQLException | IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        });
        client1Thread.start();

        Thread client2Thread = new Thread(() -> {
            try {
                Klient2.start("127.0.0.2", Port,"Klient_2");
            } catch (SQLException | IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        });
        client2Thread.start();
    }


}
