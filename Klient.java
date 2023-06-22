package com.example.karty;

import javafx.geometry.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import static com.example.karty.Serwer.insertKart;

public class Klient {
    static CustomPanel CP;
    static boolean tura = true;
    public static JFrame frame;
    static ArrayList<Integer> lista = new ArrayList<>();
    public static boolean click = false;
    public static int opcja = 0;

    public static boolean zaj1 = false;
    public static boolean zaj2 = false;
    public static boolean zaj3 = false;
    public static boolean zaj4 = false;
    static ImageIcon[] icon = new ImageIcon[31];
    static JLabel[] iconlabel = new JLabel[31];
    static JLabel[] iconlabel2 = new JLabel[31];
    static String[] pliki = new String[30];

    static CustomPanel panel1, panel2, panel3, panel4, panelg1, panelg2, panelg3, panelg4, panelg5, panele1, panele2, panele3, panele4;

    private static MouseAdapter panel1MouseListener, panel2MouseListener, panel3MouseListener, panel4MouseListener;
    private static MouseAdapter panelg1MouseListener, panelg2MouseListener, panelg3MouseListener, panelg4MouseListener, panelg5MouseListener, koniecMouseListener;
    static JButton koniec;


    static Random rand = new Random();

    static String message;

    static PrintWriter output;

    static int[][] reka = new int[5][2];

    static int HP = 20;
    static int HP_E = 20;
    static int mana = 10;
    static int manaG = mana;
    static JLabel Zdrowie, E_Zdrowie, JLmana, koszt;
    static JProgressBar progressBar;


    public static void main(String[] args) throws SQLException, IOException, FontFormatException {
        start("127.0.0.1", 2222, args[0]);
    }

    public static void start(String serverIP, int serverPort, String nazwa) throws SQLException, IOException, FontFormatException {
        try {
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Połączono z serwerem: " + serverIP + ":" + serverPort);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            tablica();
            losuj();
            InsertKart insertKart = new InsertKart();
            CP = new CustomPanel();
            InsertKart.Pobierz(false);
            InsertKart.ladowanie(icon, iconlabel, iconlabel2);
            pliki(pliki);
            inicjalizacja(nazwa);
            if (tura) event_init(insertKart, output);


            koszt.setText("Twoja tura!");

            // Odczytuj wiadomości z serwera w oddzielnym wątku
            Thread serverThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverInput.readLine()) != null) {

                        //napisac logike ktora umiesli karte przeciwnika
                        System.out.println("[Klient_1]:Wiadomość od serwera: " + serverMessage);
                        processMessage(serverMessage);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();


            frame_init(frame); //inicjalizacja frame

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void ramka(CustomPanel panelg1, CustomPanel panelg2, CustomPanel panelg3, CustomPanel panelg4, CustomPanel panelg5) {
        panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
        panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
        panelg3.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
        panelg4.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
        panelg5.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
        click = false;
        opcja = 0;
    }


    private static void tablica() {
        for (int i = 0; i < 30; i++) {
            lista.add(i + 1);
        }
    }

    private static void losuj() {
        for (int i = 0; i < 5; i++) {
            int liczba = rand.nextInt(1, lista.size());
            reka[i][0] = lista.get(liczba);
            lista.remove(liczba);
            System.out.println(reka[i][0]);

        }
    }

    private static void inicjalizacja(String nazwa) throws IOException, FontFormatException {
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        Font customFont3 = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        Font customFont4 = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        customFont = customFont.deriveFont(100f);
        customFont3 = customFont3.deriveFont(50f);
        customFont2 = customFont2.deriveFont(50f);
        customFont4 = customFont4.deriveFont(20f);

        frame = new JFrame(nazwa);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        koniec = new JButton("Zakoncz ture");
        koniec.setBounds(150, 465, 200, 100);
        koniec.setLayout(null);
        koniec.setFont(customFont4);

        Zdrowie = new JLabel(HP + "/20");
        Zdrowie.setBounds(1250, 550, 400, 400);
        Zdrowie.setLayout(null);
        Zdrowie.setFont(customFont);
        Zdrowie.setForeground(Color.RED);

        koszt = new JLabel();
        koszt.setBounds(500, 0, 800, 100);
        koszt.setLayout(null);
        koszt.setFont(customFont3);
        koszt.setForeground(Color.RED);

        E_Zdrowie = new JLabel(HP_E + "/20");
        E_Zdrowie.setBounds(100, 100, 400, 400);
        E_Zdrowie.setLayout(null);
        E_Zdrowie.setFont(customFont);
        E_Zdrowie.setForeground(Color.RED);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(10);
        progressBar.setValue(manaG);
        progressBar.setBackground(Color.gray);
        progressBar.setForeground(Color.blue);
        progressBar.setBounds(1525, 640, 100, 350);
        progressBar.setOrientation(1);
        progressBar.setBorder(BorderFactory.createLineBorder(Color.blue, 3, true));

        JLmana = new JLabel(String.valueOf(manaG));
        JLmana.setFont(customFont2);
        JLmana.setBounds(1560, 515, 200, 200);
        JLmana.setLayout(null);
        JLmana.setForeground(Color.BLUE);

        panelg1 = new CustomPanel();
        panelg1.setBounds(250, 785, 200, 225);
        panelg1.setLayout(null);
        panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));

        panelg2 = new CustomPanel();
        panelg2.setBounds(475, 785, 200, 225);
        panelg2.setLayout(null);
        panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));

        panelg3 = new CustomPanel();
        panelg3.setBounds(700, 785, 200, 225);
        panelg3.setLayout(null);
        panelg3.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));

        panelg4 = new CustomPanel();
        panelg4.setBounds(925, 785, 200, 225);
        panelg4.setLayout(null);
        panelg4.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));

        panelg5 = new CustomPanel();
        panelg5.setBounds(1150, 785, 200, 225);
        panelg5.setLayout(null);
        panelg5.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));

//---------------------------------------------------------------------------------------------------------
        panel1 = new CustomPanel();
        panel1.setBounds(370, 550, 200, 225);
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panel3 = new CustomPanel();
        panel3.setBounds(810, 550, 200, 225);
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panel2 = new CustomPanel();
        panel2.setBounds(590, 550, 200, 225);
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panel4 = new CustomPanel();
        panel4.setBounds(1030, 550, 200, 225);
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

//---------------------------------------------------------------------------------------------------------

        panele1 = new CustomPanel();
        panele1.setBounds(370, 250, 200, 225);
        panele1.setBackground(Color.WHITE);
        panele1.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panele3 = new CustomPanel();
        panele3.setBounds(810, 250, 200, 225);
        panele3.setBackground(Color.WHITE);
        panele3.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panele2 = new CustomPanel();
        panele2.setBounds(590, 250, 200, 225);
        panele2.setBackground(Color.WHITE);
        panele2.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));

        panele4 = new CustomPanel();
        panele4.setBounds(1030, 250, 200, 225);
        panele4.setBackground(Color.WHITE);
        panele4.setBorder(BorderFactory.createLineBorder(Color.RED, 3, true));


        CP.InsertGraph(iconlabel[reka[0][0]], panelg1, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])), Integer.toString(insertKart.szukajPAN(reka[0][0])), Integer.toString(insertKart.szukajKOSZT(reka[0][0])), InsertKart.szukajNazwa(reka[0][0]), insertKart.szukajEfekty(reka[0][0]));

        CP.InsertGraph(iconlabel[reka[1][0]], panelg2, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])), Integer.toString(insertKart.szukajPAN(reka[1][0])), Integer.toString(insertKart.szukajKOSZT(reka[1][0])), InsertKart.szukajNazwa(reka[1][0]), insertKart.szukajEfekty(reka[1][0]));

        CP.InsertGraph(iconlabel[reka[2][0]], panelg3, Integer.toString(insertKart.szukajATA(reka[2][0])), Integer.toString(insertKart.szukajHP(reka[2][0])), Integer.toString(insertKart.szukajPAN(reka[2][0])), Integer.toString(insertKart.szukajKOSZT(reka[2][0])), InsertKart.szukajNazwa(reka[2][0]), insertKart.szukajEfekty(reka[2][0]));

        CP.InsertGraph(iconlabel[reka[3][0]], panelg4, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])), Integer.toString(insertKart.szukajPAN(reka[3][0])), Integer.toString(insertKart.szukajKOSZT(reka[3][0])), InsertKart.szukajNazwa(reka[3][0]), insertKart.szukajEfekty(reka[3][0]));

        CP.InsertGraph(iconlabel[reka[4][0]], panelg5, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])), Integer.toString(insertKart.szukajPAN(reka[4][0])), Integer.toString(insertKart.szukajKOSZT(reka[4][0])), InsertKart.szukajNazwa(reka[4][0]), insertKart.szukajEfekty(reka[4][0]));


        panelg1.setFocusable(true);
        panelg2.setFocusable(true);
        panelg3.setFocusable(true);
        panelg4.setFocusable(true);
        panelg5.setFocusable(true);
        panel1.setFocusable(true);
        panel2.setFocusable(true);
        panel3.setFocusable(true);
        panel4.setFocusable(true);
        panele1.setFocusable(true);
        panele2.setFocusable(true);
        panele3.setFocusable(true);
        panele4.setFocusable(true);
    }

    private static void frame_init(JFrame frame) {
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        frame.add(panelg1);
        frame.add(panelg2);
        frame.add(panelg3);
        frame.add(panelg4);
        frame.add(panelg5);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panele1);
        frame.add(panele2);
        frame.add(panele3);
        frame.add(panele4);
        frame.add(koniec);
        frame.add(Zdrowie);
        frame.add(E_Zdrowie);
        frame.add(progressBar);
        frame.add(JLmana);
        frame.add(koszt);

        frame.setSize(1680, 1050);
        frame.setVisible(true);
    }

    private static void processMessage(String message) {
        InsertKart insertKart = new InsertKart();
        String[] parts = message.split(";"); // Separator to średnik (;)

        // Odczytanie poszczególnych części wiadomości

        String karta = parts[0];
        String kod = parts[1];// Pierwsza część to komenda




        int ID = Integer.parseInt(karta);


        //System.out.println("Dane: "+ parts[0]+" "+ID);

        switch (kod) {
            case "panele1" -> {
                CP.InsertGraph(iconlabel2[ID], panele1, Integer.toString(insertKart.szukajATA(ID)), Integer.toString(insertKart.szukajHP(ID)), Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                panele1.repaint();
            }
            case "panele2" -> {
                CP.InsertGraph(iconlabel2[ID], panele2, Integer.toString(insertKart.szukajATA(ID)), Integer.toString(insertKart.szukajHP(ID)), Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                panele2.repaint();
            }
            case "panele3" -> {
                CP.InsertGraph(iconlabel2[ID], panele3, Integer.toString(insertKart.szukajATA(ID)), Integer.toString(insertKart.szukajHP(ID)), Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                panele3.repaint();
            }
            case "panele4" -> {
                CP.InsertGraph(iconlabel2[ID], panele4, Integer.toString(insertKart.szukajATA(ID)), Integer.toString(insertKart.szukajHP(ID)), Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                panele4.repaint();
            }
            case "koniectury" -> {
                HP = Integer.parseInt(parts[2]);
                HP_E = Integer.parseInt(parts[3]);
                tura = true;
                event_init(insertKart, output);
                set_staty();
                koszt.setText("Twoja tura!");
            }
            case "usun" ->{
                String pozycja = parts[2]; // Druga część to dane
                String HP_J = parts[3];
                switch (pozycja){
                    case "panele1" -> {
                        panele1.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel2[ID], panele1, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel2[ID], panele1, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panele1.repaint();
                    }
                    case "panele2" -> {
                        panele2.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel2[ID], panele2, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel2[ID], panele2, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panele2.repaint();
                    }
                    case "panele3" -> {
                        panele3.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel2[ID], panele3, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel2[ID], panele3, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panele3.repaint();
                    }
                    case "panele4" -> {
                        panele4.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel2[ID], panele4, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel2[ID], panele4, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panele4.repaint();
                    }

                    case "panel1" -> {
                        zaj1=false;
                        panel1.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel[ID], panel1, "  ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel[ID], panel1, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));

                        panel1.repaint();
                    }
                    case "panel2" -> {
                        zaj2=false;
                        panel2.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel[ID], panel2, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel[ID], panel2, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panel2.repaint();
                    }
                    case "panel3" -> {
                        zaj3=false;
                        panel3.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel[ID], panel3, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel[ID], panel3, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panel3.repaint();
                    }
                    case "panel4" -> {
                        zaj4=false;
                        panel4.removeAll();
                        if(ID==0)
                            CP.InsertGraph(iconlabel[ID], panel4, " ", " ", " ", " ", " ", " ");
                        else
                            CP.InsertGraph(iconlabel[ID], panel4, Integer.toString(insertKart.szukajATA(ID)), HP_J, Integer.toString(insertKart.szukajPAN(ID)), Integer.toString(insertKart.szukajKOSZT(ID)), InsertKart.szukajNazwa(ID), insertKart.szukajEfekty(ID));
                        panel4.repaint();
                    }
                }

            }
        }
    }

    private static void set_staty() {
        Zdrowie.setText(HP + "/20");
        E_Zdrowie.setText(HP_E + "/20");
        if (mana <= 10) mana = mana + 2;
        else mana = 10;

        manaG=mana;

        JLmana.setText(String.valueOf(manaG));
        progressBar.setValue(manaG);
    }

    private static void event_delete() {
        panel1.removeMouseListener(panel1MouseListener);
        panel2.removeMouseListener(panel2MouseListener);
        panel3.removeMouseListener(panel3MouseListener);
        panel4.removeMouseListener(panel4MouseListener);
        panelg1.removeMouseListener(panelg1MouseListener);
        panelg2.removeMouseListener(panelg2MouseListener);
        panelg3.removeMouseListener(panelg3MouseListener);
        panelg4.removeMouseListener(panelg4MouseListener);
        panelg5.removeMouseListener(panelg5MouseListener);

        repaint_panele();
    }


    private static void f_panele(CustomPanel panel, CustomPanel panel_G, InsertKart insertKart, int wartosc, String nazwa_panelu){
        CP.InsertGraph(iconlabel[wartosc], panel, Integer.toString(insertKart.szukajATA(wartosc)), Integer.toString(insertKart.szukajHP(wartosc)), Integer.toString(insertKart.szukajPAN(wartosc)), Integer.toString(insertKart.szukajKOSZT(wartosc)), InsertKart.szukajNazwa(wartosc), insertKart.szukajEfekty(wartosc));
        panel.repaint();

        panel_G.removeAll();
        panel_G.repaint();
        panel_G.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));

        message = insertKart.szukajID(wartosc) + ";" + nazwa_panelu;
        output.println(message);

        click = false;
        opcja = 0;
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    }
    private static void event_init(InsertKart insertKart, PrintWriter output) {
        panel1MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (zaj1) panel1.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                else panel1.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                switch (opcja) {
                    case 1 -> {
                        if (!zaj1) {
                            if (sprawdz(reka[0][0])) {
                                f_panele(panel1,panelg1,insertKart,reka[0][0],"panele1");
                                zaj1=true;
                            }
                        }
                    }
                    case 2 -> {
                        if (!zaj1) {
                            if (sprawdz(reka[1][0])) {
                                f_panele(panel1,panelg2,insertKart,reka[1][0],"panele1");
                                zaj1=true;
                            }
                        }
                    }
                    case 3 -> {
                        if (!zaj1) {
                            if (sprawdz(reka[2][0])) {
                                f_panele(panel1,panelg3,insertKart,reka[2][0],"panele1");
                                zaj1=true;
                            }
                        }
                    }
                    case 4 -> {
                        if (!zaj1) {
                            if (sprawdz(reka[3][0])) {
                                f_panele(panel1,panelg4,insertKart,reka[3][0],"panele1");
                                zaj1=true;
                            }
                        }
                    }

                    case 5 -> {
                        if (!zaj1) {
                            if (sprawdz(reka[4][0])) {
                                f_panele(panel1,panelg5,insertKart,reka[4][0],"panele1");
                                zaj1=true;
                            }
                        }
                    }
                }

                System.out.println("kliknieto panel1:" + n);
                n++;
            }
        };

        panel2MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (zaj2) panel2.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                else panel2.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                switch (opcja) {
                    case 1 -> {
                        if (sprawdz(reka[0][0])) {
                            if (!zaj2) {
                                f_panele(panel2,panelg1,insertKart,reka[0][0],"panele2");
                                zaj2=true;
                            }
                        }
                    }
                    case 2 -> {
                        if (!zaj2) {
                            if (sprawdz(reka[1][0])) {
                                f_panele(panel2,panelg2,insertKart,reka[1][0],"panele2");
                                zaj2=true;
                            }
                        }
                    }
                    case 3 -> {
                        if (!zaj2) {
                            if (sprawdz(reka[2][0])) {
                                f_panele(panel2,panelg3,insertKart,reka[2][0],"panele2");
                                zaj2=true;
                            }
                        }
                    }
                    case 4 -> {
                        if (!zaj2) {
                            if (sprawdz(reka[3][0])) {
                                f_panele(panel2,panelg4,insertKart,reka[0][3],"panele2");
                                zaj2=true;
                            }
                        }
                    }

                    case 5 -> {
                        if (!zaj2) {
                            if (sprawdz(reka[4][0])) {
                                f_panele(panel2,panelg5,insertKart,reka[4][0],"panele2");
                                zaj2=true;
                            }
                        }
                    }
                }

                System.out.println("kliknieto panel2:" + n);
                n++;
            }
        };

        panel3MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (zaj3) panel3.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                else panel3.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                switch (opcja) {
                    case 1 -> {
                        if (sprawdz(reka[0][0])) {
                            if (!zaj3) {
                                f_panele(panel3,panelg1,insertKart,reka[0][0],"panele3");
                                zaj3=true;
                            }
                        }
                    }
                    case 2 -> {
                        if (!zaj3) {
                            if (sprawdz(reka[1][0])) {
                                f_panele(panel3,panelg2,insertKart,reka[1][0],"panele3");
                                zaj3=true;

                            }
                        }
                    }
                    case 3 -> {
                        if (!zaj3) {
                            if (sprawdz(reka[2][0])) {
                                f_panele(panel3,panelg3,insertKart,reka[2][0],"panele3");
                                zaj3=true;
                            }
                        }
                    }
                    case 4 -> {
                        if (!zaj3) {
                            if (sprawdz(reka[3][0])) {
                                f_panele(panel3,panelg4,insertKart,reka[3][0],"panele3");
                                zaj3=true;
                            }
                        }
                    }

                    case 5 -> {
                        if (!zaj3) {
                            if (sprawdz(reka[4][0])) {
                                f_panele(panel3,panelg5,insertKart,reka[0][0],"panele3");
                                zaj3=true;
                            }
                        }
                    }
                }

                System.out.println("kliknieto panel3:" + n);
                n++;
            }
        };

        panel4MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (zaj4) panel4.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                else panel4.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                switch (opcja) {
                    case 1 -> {
                        if (sprawdz(reka[0][0])) {
                            if (!zaj4) {
                                f_panele(panel4,panelg1,insertKart,reka[0][0],"panele4");
                                zaj4=true;
                            }
                        }
                    }
                    case 2 -> {
                        if (!zaj4) {
                            if (sprawdz(reka[1][0])) {
                                f_panele(panel4,panelg2,insertKart,reka[1][0],"panele4");
                                zaj4=true;
                            }
                        }
                    }
                    case 3 -> {
                        if (!zaj4) {
                            if (sprawdz(reka[2][0])) {
                                f_panele(panel4,panelg3,insertKart,reka[2][0],"panele4");
                                zaj4=true;
                            }
                        }
                    }
                    case 4 -> {
                        if (!zaj4) {
                            if (sprawdz(reka[3][0])) {
                                f_panele(panel4,panelg4,insertKart,reka[3][0],"panele4");
                                zaj4=true;
                                CP.InsertGraph(iconlabel[reka[3][0]], panel4, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])), Integer.toString(insertKart.szukajPAN(reka[3][0])), Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]), insertKart.szukajEfekty(reka[3][0]));
                            }
                        }
                    }

                    case 5 -> {
                        if (!zaj4) {
                            if (sprawdz(reka[4][0])) {
                                f_panele(panel4,panelg5,insertKart,reka[4][0],"panele3");
                                zaj4=true;
                            }
                        }
                    }
                }

                System.out.println("kliknieto panel4:" + n);
                n++;
            }
        };

        panelg1MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (click) {
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                } else {
                    panelg1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
                    click = true;
                    opcja = 1;

                }
                System.out.println("kliknieto panelg1:" + n);
                n++;

            }
        };

        panelg2MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (click) {
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                } else {
                    panelg2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
                    click = true;
                    opcja = 2;
                }
                System.out.println("kliknieto panelg2:" + n);
                n++;

            }
        };

        panelg3MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (click) {
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                } else {
                    panelg3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
                    click = true;
                    opcja = 3;
                }
                System.out.println("kliknieto panelg3:" + n);
                n++;

            }
        };

        panelg4MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (click) {
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                } else {
                    panelg4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
                    click = true;
                    opcja = 4;
                }
                System.out.println("kliknieto panelg4:" + n);
                n++;

            }
        };

        panelg5MouseListener = new MouseAdapter() {
            int n = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (click) {
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                } else {
                    panelg5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
                    click = true;
                    opcja = 5;
                }
                System.out.println("kliknieto panelg5:" + n);
                n++;

            }
        };

        koniecMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                output.println("0;koniectury");
                tura = false;
                event_delete();
                koszt.setText("");
                koniec.removeMouseListener(koniecMouseListener);


            }
        };

        panel1.addMouseListener(panel1MouseListener);
        panel2.addMouseListener(panel2MouseListener);
        panel3.addMouseListener(panel3MouseListener);
        panel4.addMouseListener(panel4MouseListener);

        panelg1.addMouseListener(panelg1MouseListener);
        panelg2.addMouseListener(panelg2MouseListener);
        panelg3.addMouseListener(panelg3MouseListener);
        panelg4.addMouseListener(panelg4MouseListener);
        panelg5.addMouseListener(panelg5MouseListener);

        koniec.addMouseListener(koniecMouseListener);

        repaint_panele();


    }

    private static boolean sprawdz(int karta) {
        InsertKart insertKart = new InsertKart();
        if (insertKart.szukajKOSZT(karta) > manaG) {
            koszt.setText("Za malo many");
            return false;
        } else {
            koszt.setText("Twoja tura!");
            manaG = manaG - insertKart.szukajKOSZT(karta);
            JLmana.setText(String.valueOf(manaG));
            progressBar.setValue(manaG);
            setMana();
            return true;
        }
}
    private static void setMana(){
        if(manaG<0){
            JLmana.setText(String.valueOf(0));
            progressBar.setValue(0);
        }
    }

    private static void repaint_panele() {
        panel1.repaint();
        panel2.repaint();
        panel3.repaint();
        panel4.repaint();

        panelg1.repaint();
        panelg2.repaint();
        panelg3.repaint();
        panelg4.repaint();
        panelg5.repaint();

        panele1.repaint();
        panele2.repaint();
        panele3.repaint();
        panele4.repaint();
    }

    public static void pliki(String[] pliki) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "karty";
        String password = "1111";
        int i=0;
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Plik FROM Karty");
        while (resultSet.next()) {
            String plik = resultSet.getString("Plik");
            pliki[i]=plik;

            //System.out.println(pliki[i]);
            i++;
        }
    }
}
