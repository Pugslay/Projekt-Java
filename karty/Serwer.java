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

//Klasa, która służy jako serwer. Na niej jest rozpatrywana logika i komunikacja między klientami, które są synchronizowane
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
    private static Tester t;

    public Serwer(int id, String nazwa, int ATA, int HP, int koszt, int pancerz, String ef1, String ef2, String plik) {
        super(id, nazwa, ATA, HP, koszt, pancerz, ef1, ef2, plik);
        t=new Tester();
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
        private int clientId;
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
                    System.out.println("Wiadomość od klienta " + clientId + ": " + message);





                    //System.out.println("0/0: "+pozycja[0][0]);
                    //System.out.println("0/1: "+pozycja[0][1]);

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

        private void disconnectClient() {
            // Zamknij strumienie wejściowe/wyjściowe oraz gniazdo klienta
            try {
                input.close();
                output.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Usuń klienta z listy klientów
            clients.remove(this);

            System.out.println("Klient " + clientId + " został rozłączony.");}


        private void forwardMessageToClients(String message, int ID) {
            for (ClientHandler client : clients) {
                if (client.clientId != ID) {
                    client.output.println(message);
                }
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
            t.start("logika");
            for (int j = 0; j < 4; j++) {
                int n=j+1;
                String wiad, wiadE;

                if(insertKart.szukajNazwaS(pozycja[j][0]).equals("Nic") && !(insertKart.szukajNazwaS(pozycja[j][1]).equals("Nic"))){
                    t.IF();
                    System.out.println("Atak1:" +insertKart.szukajATA(pozycja[j][1]));
                    gracz1=gracz1-insertKart.szukajATA(pozycja[j][1]);
                    continue;}
                else{
                    t.ELSE();
                    if(insertKart.szukajNazwaS(pozycja[j][1]).equals("Nic") && !insertKart.szukajNazwaS(pozycja[j][0]).equals("Nic")) {
                        t.IF();
                        System.out.println("Atak2:" +insertKart.szukajATA(pozycja[j][0]));
                        gracz2 = gracz2 - insertKart.szukajATA(pozycja[j][0]);
                        continue;
                    }
                }

                if((insertKart.szukajEfektyS_1(pozycja[j][0]).equals("DYS")&&insertKart.szukajEfektyS_1(pozycja[j][1]).equals("DYS")) || (insertKart.szukajEfektyS_1(pozycja[j][0]).equals("nic")&&insertKart.szukajEfektyS_1(pozycja[j][1]).equals("nic"))) {
                    System.out.println("Wej1");
                    przeb(j,1,true);
                }else {
                    if(insertKart.szukajEfektyS_1(pozycja[j][0]).equals("DYS")) {
                        System.out.println("Wej2");
                        przeb(j, 0,false);
                    }else {
                        System.out.println("Wej3");
                        przeb(j, 1,false);
                    }
                }
            }
        }
        private void przeb(int j,int pierwszy,boolean wejscie) {
            //wejscie decyduje czy jest brana pod uwagę kolejność czy nie true bierze
            System.out.println("Wykonanie iteracji po raz: "+j);
            String wiad, wiadE;
            int drugi;
            int gracz=1;

            System.out.println("Przed");
            System.out.println("0/0 HP: "+hp[j][0]+"|"+pozycja[j][0]);
            System.out.println("0/1 HP: "+hp[j][1]+"|"+pozycja[j][1]);

            if(pierwszy==1) {
                drugi=0;
            }else {
                drugi=1;
                gracz=0;
            }
            System.out.println("TEST1: "+ insertKart.szukajEfektyS_2(pozycja[j][pierwszy]));
            if(insertKart.szukajEfektyS_2(pozycja[j][pierwszy]).equals("P1")||insertKart.szukajEfektyS_1(pozycja[j][pierwszy]).equals("P1")) {
                System.out.println("if 1");
                if(hp[j][drugi]>hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-1) {
                    hp[j][drugi]=hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-1;
                }
            }
            if(insertKart.szukajEfektyS_2(pozycja[j][drugi]).equals("P1")||insertKart.szukajEfektyS_1(pozycja[j][drugi]).equals("P1")){
                System.out.println("if 2");
                if(hp[j][drugi]>hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-1&&(hp[j][pierwszy] > 0||wejscie)) {
                    hp[j][drugi]=hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-1;
                }
            }
            if(insertKart.szukajEfektyS_2(pozycja[j][pierwszy]).equals("P2")||insertKart.szukajEfektyS_1(pozycja[j][pierwszy]).equals("P2")) {
                System.out.println("if 3");
                if(hp[j][drugi]>hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-2) {
                    hp[j][drugi]=hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-2;
                }
            }
            if(insertKart.szukajEfektyS_2(pozycja[j][drugi]).equals("P2")||insertKart.szukajEfektyS_1(pozycja[j][drugi]).equals("P2")) {
                System.out.println("if 4");
                if(hp[j][drugi]>hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-2&&(hp[j][pierwszy] > 0||wejscie)) {
                    hp[j][drugi]=hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])-2;
                }
            }
            System.out.println("EFEKT1: "+ (insertKart.szukajEfektyS_1(pozycja[j][pierwszy])) + (insertKart.szukajEfektyS_2(pozycja[j][pierwszy])));
            System.out.println("EFEKT2: "+ (insertKart.szukajEfektyS_1(pozycja[j][drugi])) + (insertKart.szukajEfektyS_2(pozycja[j][drugi])));
            if(insertKart.szukajEfektyS_2(pozycja[j][pierwszy]).equals("nic")&&insertKart.szukajEfektyS_1(pozycja[j][pierwszy]).equals("nic")||insertKart.szukajEfektyS_1(pozycja[j][drugi]).equals("CHLP")) {
                System.out.println("if 5");
                //if(hp[j][drugi]>hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy])) {
                    hp[j][drugi]=hp[j][drugi]+insertKart.szukajPANS(pozycja[j][drugi])-insertKart.szukajATAS(pozycja[j][pierwszy]);
                //}
            }
            //drugi atakuje pierwszego jesli rowne zasady i nie bylo innego ataku
            if(insertKart.szukajEfektyS_2(pozycja[j][drugi]).equals("nic")&&insertKart.szukajEfektyS_1(pozycja[j][drugi]).equals("nic")&&(hp[j][drugi]>0||wejscie)||insertKart.szukajEfektyS_1(pozycja[j][drugi]).equals("CHLP")) {
                System.out.println("if 6");
                //if(hp[j][pierwszy]>hp[j][pierwszy]+insertKart.szukajPANS(pozycja[j][pierwszy])-insertKart.szukajATAS(pozycja[j][drugi])) {
                    hp[j][pierwszy]=hp[j][pierwszy]+insertKart.szukajPANS(pozycja[j][pierwszy])-insertKart.szukajATAS(pozycja[j][drugi]);
                //}
            }
            System.out.println("Po");
            System.out.println("0/0 HP: "+hp[j][0]+"|"+pozycja[j][0]);
            System.out.println("0/1 HP: "+hp[j][1]+"|"+pozycja[j][1]);

            int n=j+1;
            if(hp[j][pierwszy]<=0) {
                wiad ="0;usun;panel"+n+"; ";
                wiadE ="0;usun;panele"+n+"; ";
                forwardMessageToClients(wiadE,pierwszy+1);
                forwardMessageToClients(wiad,drugi+1);
                System.out.println("1. postac 1 nie zyje:"+pierwszy);
                pozycja[j][pierwszy] = 0;
                hp[j][pierwszy]=0;
            }
            if(hp[j][drugi]<=0) {
                wiad ="0;usun;panel"+n+"; ";
                wiadE ="0;usun;panele"+n+"; ";
                forwardMessageToClients(wiad,pierwszy+1);
                forwardMessageToClients(wiadE,drugi+1);
                System.out.println("1. postac 2 nie zyje:"+drugi);
                pozycja[j][drugi] = 0;
                hp[j][drugi]=0;
            }
            if(hp[j][drugi]>0) {
                wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                forwardMessageToClients(wiad,pierwszy+1);
                forwardMessageToClients(wiadE,drugi+1);
                System.out.println("2. postac 2 zyje:"+drugi);
            }
            if(hp[j][pierwszy]>0) {
                wiadE = insertKart.szukajIDS(pozycja[j][0]) +";usun;panel"+n+";"+hp[j][0];
                wiad = insertKart.szukajIDS(pozycja[j][0]) +";usun;panele"+n+";"+hp[j][0];
                forwardMessageToClients(wiad,pierwszy+1);
                forwardMessageToClients(wiadE,drugi+1);
                System.out.println("2. postac 1 zyje:"+pierwszy);
            }
        }
    }
}
