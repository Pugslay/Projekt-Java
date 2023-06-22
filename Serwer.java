package com.example.karty;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serwer extends Karty_obiekt {

    private static int nextClientId = 1;


    static InsertKart insertKart;
    static CustomPanel CP;
    Klient klient1;
    Klient2 klient2;
    private static boolean gracze=true;
    private static int gracz1=20,gracz2=20;
    private static List<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static int[][] pozycja = new int[4][2];
    private  static int[][] hp= new int[4][2];
    static String HP_G_2;
    static String HP_G_1;

    public Serwer(int id, String nazwa, int ATA, int HP, int koszt, int pancerz, String ef1, String ef2, String plik) {
        super(id, nazwa, ATA, HP, koszt, pancerz, ef1, ef2, plik);
    }

    public static void main(String[] args) {
        start(2222);
    }

    public static void start(int port) {
        try {
            InsertKart insertKart = new InsertKart();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serwer nasłuchuje na porcie " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączenie przyjęte od klienta: " + clientSocket.getInetAddress().getHostAddress());

                // Tworzenie obsługi klienta i dodanie go do listy
                ClientHandler clientHandler = new ClientHandler(clientSocket, nextClientId);
                clients.add(clientHandler);
                nextClientId++;

                // Uruchomienie wątku dla obsługi klienta
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final int clientId;
        private Socket clientSocket;
        private BufferedReader input;
        private PrintWriter output;

        public ClientHandler(Socket clientSocket, int clientId) {
            this.clientSocket = clientSocket;
            this.clientId = clientId;
            try {
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            try {
                String message;
                CP = new CustomPanel();
                insertKart = new InsertKart();
                InsertKart.Pobierz(true);
                while ((message = input.readLine()) != null) {
                    String[] parts = message.split(";");
                    System.out.println("Wiadomość do klienta " + clientId + ": " + message);



                    System.out.println("0/0: "+pozycja[0][0]);
                    System.out.println("0/1: "+pozycja[0][1]);

                    message_encryption(message);
                    /*String wiad = insertKart.szukajIDS(pozycja[0][0]) +";usun;panel"+1+";"+hp[0][0];
                    String wiadE = insertKart.szukajIDS(pozycja[0][0]) +";usun;panele"+1+";"+hp[0][0];
                    wiad ="0;usun;panel"+1+"; ";
                    wiadE ="0;usun;panele"+1+"; ";
                    forwardMessageToThisClients(wiad);
                    forwardMessageToOtherClients(wiadE);*/



                    message=message+";"+gracz1+";"+gracz2;
                    forwardMessageToOtherClients(message);



                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        }

        private void forwardMessageToOtherClients(String message) {
            for (ClientHandler client : clients) {
                if (client != this) {
                    client.output.println(message);
                }
            }
        }

        private void forwardMessageToThisClients(String message) {
            for (ClientHandler client : clients) {
                if (client == this) {
                    client.output.println(message);
                }
            }
        }

        private void message_encryption(String message){
            String[] parts = message.split(";"); // Separator to średnik (;)
            String karta, K_pozycja;


            if(gracze){
                karta = parts[0]; //pobiera id karty
                K_pozycja = parts[1]; //pobiera pozycje karty
                switch (K_pozycja) {
                    case "koniectury" -> {
                        gracze = !gracze;
                        logika();
                    }
                    case "panele1" -> {pozycja[0][0] = Integer.parseInt(karta);
                        hp[0][0]=insertKart.szukajHPS(pozycja[0][0]);}
                    case "panele2" -> {pozycja[1][0] = Integer.parseInt(karta);
                        hp[1][0]=insertKart.szukajHPS(pozycja[1][0]);}
                    case "panele3" -> {pozycja[2][0] = Integer.parseInt(karta);
                        hp[2][0]=insertKart.szukajHPS(pozycja[2][0]);}
                    case "panele4" -> {pozycja[3][0] = Integer.parseInt(karta);
                        hp[3][0]=insertKart.szukajHPS(pozycja[3][0]);}
                }
            }
            else {
                karta = parts[0]; //pobiera id karty
                K_pozycja = parts[1]; //pobiera pozycje karty
                switch (K_pozycja) {
                    case "koniectury" -> {
                        gracze = !gracze;
                        logika();
                    }
                    case "panele1" -> {pozycja[0][1] = Integer.parseInt(karta);
                        hp[0][1]=insertKart.szukajHPS(pozycja[0][1]);}
                    case "panele2" -> {pozycja[1][1] = Integer.parseInt(karta);
                        hp[1][1]=insertKart.szukajHPS(pozycja[1][1]);}
                    case "panele3" -> {pozycja[2][1] = Integer.parseInt(karta);
                        hp[2][1]=insertKart.szukajHPS(pozycja[2][1]);}
                    case "panele4" -> {pozycja[3][1] = Integer.parseInt(karta);
                        hp[3][1]=insertKart.szukajHPS(pozycja[3][1]);}
                }
            }
        }
        public void logika(){
            for (int j = 0; j < 4; j++) {
                int n=j+1;
                String wiad, wiadE;

                if(Objects.equals(insertKart.szukajNazwaS(pozycja[j][0]), "NIC") && !Objects.equals(insertKart.szukajNazwaS(pozycja[j][1]), "NIC")){
                    gracz1=gracz1-insertKart.szukajATA(pozycja[j][1]);
                    continue;}
                else{
                    if(Objects.equals(insertKart.szukajNazwaS(pozycja[j][1]), "NIC") && !Objects.equals(insertKart.szukajNazwaS(pozycja[j][0]), "NIC")) {
                        gracz2 = gracz2 - insertKart.szukajATA(pozycja[j][0]);
                        continue;
                    }
                }




                //Dystansowe
                if(Objects.equals(insertKart.szukajEfektyS_1(pozycja[j][0]), "DYS")){//T
                    if(insertKart.szukajEfektyS_1(pozycja[j][0]).equals("DYS"))//TT
                        przeb(j);
                    else{//TN
                        //Gracz 1 ma atak dystansowy
                        if(insertKart.szukajEfektyS_1(pozycja[j][0]).equals("P1") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("P1")){
                            if(insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][0])-1)>0){
                                hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-1));
                                wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                                wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                                forwardMessageToThisClients(wiad);
                                forwardMessageToOtherClients(wiadE);}
                        }
                        else{
                            if(insertKart.szukajEfektyS_1(pozycja[j][0]).equals("P2") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("P2")){
                                if(insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-2)>0){
                                    hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-2));
                                    wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                                    wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                                    forwardMessageToThisClients(wiad);
                                    forwardMessageToOtherClients(wiadE);}
                            }
                        }

                        //Obrażenia bez redukcji
                        if(insertKart.szukajATAS(pozycja[j][0]) - insertKart.szukajPANS(pozycja[j][1])>0) {
                            hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - insertKart.szukajPANS(pozycja[j][1]));
                            wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                            wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                            forwardMessageToThisClients(wiad);
                            forwardMessageToOtherClients(wiadE);
                        }

                        if (hp[j][1] <= 0 && insertKart.szukajNazwaS(pozycja[j][0]) != "NIC") {
                            wiad ="0;usun;panel"+n+"; ";
                            wiadE ="0;usun;panele"+n+"; ";
                            forwardMessageToThisClients(wiad);
                            forwardMessageToOtherClients(wiadE);
                            System.out.println("DUPA1");
                            pozycja[j][1] = 0;
                        }
                        else{ //atak karty gracza 2 po dystansowym
                            if(insertKart.szukajEfektyS_1(pozycja[j][1]).equals("P1") || insertKart.szukajEfektyS_2(pozycja[j][1]).equals("P1")){
                                if(insertKart.szukajATAS(pozycja[j][1]) - (insertKart.szukajPANS(pozycja[j][0])-1)>0){
                                    hp[j][0] = hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][0]-1));
                                    wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                                    wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                                    forwardMessageToThisClients(wiad);
                                    forwardMessageToOtherClients(wiadE);}}
                            else{
                                if(insertKart.szukajEfektyS_1(pozycja[j][1]).equals("P2") || insertKart.szukajEfektyS_2(pozycja[j][1]).equals("P2")){
                                    if(insertKart.szukajATAS(pozycja[j][1]) - (insertKart.szukajPANS(pozycja[j][0])-2)>0){
                                        hp[j][0] = hp[j][0] - (insertKart.szukajATA(pozycja[j][1]) - (insertKart.szukajPANS(pozycja[j][0])-2));
                                        wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                                        wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                                        forwardMessageToThisClients(wiad);
                                        forwardMessageToOtherClients(wiadE);}}
                            }

                            if(insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][0])>0){
                                hp[j][0] = hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][0]));
                                wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                                wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                                forwardMessageToThisClients(wiad);
                                forwardMessageToOtherClients(wiadE);}

                            if (hp[j][0] <= 0 && insertKart.szukajNazwaS(pozycja[j][1]) != "NIC") {
                                wiad ="0;usun;panel"+n+"; ";
                                wiadE ="0;usun;panele"+n+"; ";
                                forwardMessageToThisClients(wiad);
                                forwardMessageToOtherClients(wiadE);
                                System.out.println("DUPA0");
                                pozycja[j][0] = 0;
                            }
                        }
                    }
                }
                else{
                    if(insertKart.szukajEfektyS(pozycja[j][1]) != "DYS"&& insertKart.szukajEfektyS(pozycja[j][1]) != "NIC")
                        przeb(j);
                    else{ //atak karty gracza 2 po dystansowym
                        if((insertKart.szukajEfektyS_1(pozycja[j][0]).equals("P1") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("P1")) && (insertKart.szukajEfektyS_2(pozycja[j][0])!=null ||insertKart.szukajEfektyS_1(pozycja[j][0])!=null)){
                            if(insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajATAS(pozycja[j][1])-1)>0){
                                hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-1));
                                wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                                wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                                forwardMessageToThisClients(wiad);
                                forwardMessageToOtherClients(wiadE);
                            }
                        }
                        else{
                            if(insertKart.szukajEfektyS_1(pozycja[j][0]).equals("P2") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("P2")){
                                if(insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-2)>0){
                                    hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[0][j]) - (insertKart.szukajPANS(pozycja[1][j])-2));
                                    wiad = insertKart.szukajIDS(pozycja[1][j]) +";usun;panel"+n+";"+hp[1][j];
                                    wiadE = insertKart.szukajIDS(pozycja[1][j]) +";usun;panele"+n+";"+hp[1][j];
                                    forwardMessageToThisClients(wiad);
                                    forwardMessageToOtherClients(wiadE);
                                }
                            }
                        }

                        if(insertKart.szukajATAS(pozycja[j][0]) - insertKart.szukajPANS(pozycja[j][1])>0)
                            hp[j][1] = hp[j][1] - (insertKart.szukajATA(pozycja[j][0]) - insertKart.szukajPANS(pozycja[j][1]));

                        if (hp[j][1] <= 0 && insertKart.szukajNazwaS(pozycja[j][0]) != "NIC") {
                            wiad ="0;usun;panel"+n+"; ";
                            wiadE ="0;usun;panele"+n+"; ";
                            forwardMessageToThisClients(wiad);
                            forwardMessageToOtherClients(wiadE);
                            pozycja[j][1] = 0;
                        }

                    }
                }
            }

        }

        private void przeb(int j) {
            int n = j+1;
            String wiad, wiadE;
            if (insertKart.szukajEfektyS_1(pozycja[j][0]).equals("p1") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("p1"))
                if((hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][0])-1)))>0) {
                    hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) + ((insertKart.szukajPANS(pozycja[j][1]) - 1)));
                    wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                    wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                    forwardMessageToThisClients(wiad);
                    forwardMessageToOtherClients(wiadE);}
                else {
                    if (insertKart.szukajEfektyS_1(pozycja[j][0]).equals("p2") || insertKart.szukajEfektyS_2(pozycja[j][0]).equals("p2")) {
                        if((hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - (insertKart.szukajPANS(pozycja[j][1])-2)))>0){
                            hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - ((insertKart.szukajPANS(pozycja[j][1] - 2))));
                            wiadE = insertKart.szukajIDS(pozycja[j][1]) +";usun;panel"+n+";"+hp[j][1];
                            wiad = insertKart.szukajIDS(pozycja[j][1]) +";usun;panele"+n+";"+hp[j][1];
                            forwardMessageToThisClients(wiad);
                            forwardMessageToOtherClients(wiadE);}

                    }
                    else
                    if((hp[j][1] - (insertKart.szukajATAS(pozycja[j][0]) - insertKart.szukajPANS(pozycja[j][1])))>0){
                        hp[j][1] = hp[j][1] - (insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][1]));
                        wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][1];
                        wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][1];
                        forwardMessageToThisClients(wiad);
                        forwardMessageToOtherClients(wiadE);}
                }


            if (insertKart.szukajEfektyS_1(pozycja[j][1]).equals("p1") || insertKart.szukajEfektyS_2(pozycja[j][1]).equals("p1")){
                if((hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - (insertKart.szukajPANS(pozycja[j][0])-1)))>0){
                    hp[j][0] = hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) + ((insertKart.szukajPANS(pozycja[j][0]) - 1)));
                    wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                    wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                    forwardMessageToThisClients(wiad);
                    forwardMessageToOtherClients(wiadE);}}
            else {
                if (insertKart.szukajEfektyS_1(pozycja[j][1]).equals("p2") || insertKart.szukajEfektyS_1(pozycja[j][1]).equals("p2")) {
                    if((hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - (insertKart.szukajPANS(pozycja[j][0])-2)))>0){
                        hp[j][0] = hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) + ((insertKart.szukajPANS(pozycja[j][0]) - 2)));
                        wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                        wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                        forwardMessageToThisClients(wiad);
                        forwardMessageToOtherClients(wiadE);}

                }
                else{
                    if((hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][1])))>0)
                        hp[j][0] = hp[j][0] - (insertKart.szukajATAS(pozycja[j][1]) - insertKart.szukajPANS(pozycja[j][0]));}
            }

            if (hp[j][0] <= 0 && insertKart.szukajNazwaS(pozycja[j][0]) != "NIC") {
                wiad ="0;usun;panel"+n+"; ";
                wiadE ="0;usun;panele"+n+"; ";
                forwardMessageToThisClients(wiad);
                forwardMessageToOtherClients(wiadE);
                System.out.println("DUPA0PRZEB");
                pozycja[j][0] = 0;
            }

            hp[j][1] = hp[j][1] - insertKart.szukajATAS(pozycja[j][0]);

            if (hp[j][1] <= 0 && insertKart.szukajNazwaS(pozycja[j][1]) != "NIC") {
                wiadE ="0;usun;panel"+n+"; ";
                wiad ="0;usun;panele"+n+"; ";
                forwardMessageToThisClients(wiad);
                forwardMessageToOtherClients(wiadE);
                System.out.println("DUPA1PRZEB");
                pozycja[j][1] = 0;
            }

        }


    }



}