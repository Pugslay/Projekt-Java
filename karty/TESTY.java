package com.example.karty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class TESTY {

    static ArrayList<Integer> lista = new ArrayList<>();
    public static boolean click=false;
    public static int opcja=0;

    public static boolean zaj1=false;
    public static boolean zaj2=false;
    public static boolean zaj3=false;
    public static boolean zaj4=false;

    static int[] tab=new int[30];
    static  ImageIcon[] icon = new ImageIcon[31];
    static JLabel[] iconlabel = new JLabel[31];
    static String[] pliki = new String[30];


    static Random rand = new Random();

    static int[][] reka = new int[5][2];




    public static void main(String[] args) throws SQLException {
        /*

    insertKart.pokaz();
    insertKart.szukaj(3);
    System.out.println(insertKart.szukajATA(3));
         */
        tablica();
        losuj();
        pliki();
        ladowanie();
        InsertKart insertKart = new InsertKart();
        insertKart.Pobierz();

        JFrame frame = new JFrame("Panel z ikoną i JLabel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        CustomPanel panelg1 = new CustomPanel();
        panelg1.setBounds(250,785,200,225);
        panelg1.setLayout(null);
        panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));

        CustomPanel panelg2 = new CustomPanel();
        panelg2.setBounds(475,785,200,225);
        panelg2.setLayout(null);
        panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));

        CustomPanel panelg3 = new CustomPanel();
        panelg3.setBounds(700,785,200,225);
        panelg3.setLayout(null);
        panelg3.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));

        CustomPanel panelg4 = new CustomPanel();
        panelg4.setBounds(925,785,200,225);
        panelg4.setLayout(null);
        panelg4.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));

        CustomPanel panelg5 = new CustomPanel();
        panelg5.setBounds(1150,785,200,225);
        panelg5.setLayout(null);
        panelg5.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));

//---------------------------------------------------------------------------------------------------------
        CustomPanel panel1 = new CustomPanel();
        panel1.setBounds(370,550,200,225);
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));

        CustomPanel panel3 = new CustomPanel();
        panel3.setBounds(810,550,200,225);
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));

        CustomPanel panel2 = new CustomPanel();
        panel2.setBounds(590,550,200,225);
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));

        CustomPanel panel4 = new CustomPanel();
        panel4.setBounds(1030,550,200,225);
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));




        ImageIcon icon2 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\"+insertKart.szukajPlik(reka[1][0]));
        JLabel iconLabel2 = new JLabel(icon2);
        iconLabel2.setBounds(-150, -145, icon2.getIconWidth(), icon2.getIconHeight());

        ImageIcon icon3 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\"+insertKart.szukajPlik(reka[2][0]));
        JLabel iconLabel3 = new JLabel(icon3);
        iconLabel3.setBounds(-150, -145, icon3.getIconWidth(), icon3.getIconHeight());

        ImageIcon icon4 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\"+insertKart.szukajPlik(reka[3][0]));
        JLabel iconLabel4 = new JLabel(icon4);
        iconLabel4.setBounds(-150, -145, icon4.getIconWidth(), icon4.getIconHeight());

        ImageIcon icon5 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\"+insertKart.szukajPlik(reka[4][0]));
        JLabel iconLabel5 = new JLabel(icon5);
        iconLabel5.setBounds(-150, -145, icon5.getIconWidth(), icon5.getIconHeight());




        System.out.println("wartosc 1 karty:"+ insertKart.szukajNazwa(reka[0][0]));
        System.out.println("wartosc 2 karty:"+ insertKart.szukajNazwa(reka[1][0]));
        System.out.println("wartosc 3 karty:"+ insertKart.szukajNazwa(reka[2][0]));
        System.out.println("wartosc 4 karty:"+ insertKart.szukajNazwa(reka[3][0]));
        System.out.println("wartosc 5 karty:"+ insertKart.szukajNazwa(reka[4][0]));
        InsertGraph(iconlabel[reka[0][0]],panelg1, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])),Integer.toString(insertKart.szukajPAN(reka[0][0])),
                Integer.toString(insertKart.szukajKOSZT(reka[0][0])), insertKart.szukajNazwa(reka[0][0]),insertKart.szukajEfekty(reka[0][0]));

        InsertGraph(iconlabel[reka[1][0]],panelg2, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])),Integer.toString(insertKart.szukajPAN(reka[1][0])),
                Integer.toString(insertKart.szukajKOSZT(reka[1][0])), insertKart.szukajNazwa(reka[1][0]),insertKart.szukajEfekty(reka[1][0]));

        InsertGraph(iconlabel[reka[2][0]],panelg3, Integer.toString(insertKart.szukajATA(reka[2][0])), Integer.toString(insertKart.szukajHP(reka[2][0])),Integer.toString(insertKart.szukajPAN(reka[2][0])),
                Integer.toString(insertKart.szukajKOSZT(reka[2][0])), insertKart.szukajNazwa(reka[2][0]),insertKart.szukajEfekty(reka[2][0]));

        InsertGraph(iconlabel[reka[3][0]],panelg4, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])),Integer.toString(insertKart.szukajPAN(reka[3][0])),
                Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]),insertKart.szukajEfekty(reka[3][0]));

        InsertGraph(iconlabel[reka[4][0]],panelg5, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])),Integer.toString(insertKart.szukajPAN(reka[4][0])),
                Integer.toString(insertKart.szukajKOSZT(reka[4][0])), insertKart.szukajNazwa(reka[4][0]),insertKart.szukajEfekty(reka[4][0]));




        panelg1.setFocusable(true);
        iconLabel2.setFocusable(true);
        iconLabel3.setFocusable(true);
        iconLabel4.setFocusable(true);
        iconLabel5.setFocusable(true);

        panelg1.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(click){
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                }
                else{
                    panelg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    click = true;
                    opcja = 1;
                }
                System.out.println("kliknieto panelg1:"+n);
                n++;

            }
        });

        panelg2.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(click){
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                }
                else{
                    panelg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    click = true;
                    opcja = 2;
                }
                System.out.println("kliknieto panelg2:"+n);
                n++;

            }
        });

        panelg3.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(click){
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                }
                else{
                    panelg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    click = true;
                    opcja = 3;
                }
                System.out.println("kliknieto panelg3:"+n);
                n++;

            }
        });

        panelg4.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(click){
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                }
                else{
                    panelg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    click = true;
                    opcja = 4;
                }
                System.out.println("kliknieto panelg4:"+n);
                n++;

            }
        });

        panelg5.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(click){
                    ramka(panelg1, panelg2, panelg3, panelg4, panelg5);
                }
                else{
                    panelg5.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    click = true;
                    opcja = 5;
                }
                System.out.println("kliknieto panelg5:"+n);
                n++;

            }
        });


        panel1.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(zaj1)
                    panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                else
                    panel1.setBorder(BorderFactory.createLineBorder(Color.red,3));
                switch (opcja) {
                    case 1 -> {
                        if(!zaj1){
                            InsertGraph(iconlabel[reka[0][0]],panel1, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])),Integer.toString(insertKart.szukajPAN(reka[0][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[0][0])), insertKart.szukajNazwa(reka[0][0]),insertKart.szukajEfekty(reka[0][0]));
                            panel1.repaint();

                            panelg1.removeAll();
                            panelg1.repaint();
                            panelg1.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj1=true;
                            opcja=0;
                            panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));}
                    }
                    case 2 -> {
                        if(!zaj1){
                            InsertGraph(iconlabel[reka[1][0]],panel1, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])),Integer.toString(insertKart.szukajPAN(reka[1][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[1][0])), insertKart.szukajNazwa(reka[1][0]),insertKart.szukajEfekty(reka[1][0]));
                        panel1.repaint();

                        panelg2.removeAll();
                        panelg2.repaint();
                        panelg2.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                        click = false;
                        zaj1=true;
                        opcja=0;
                        panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                    }}
                    case 3 -> {
                        if(!zaj1){
                            InsertGraph(iconlabel[reka[3][0]],panel1, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])),Integer.toString(insertKart.szukajPAN(reka[3][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]),insertKart.szukajEfekty(reka[3][0]));
                                panel1.repaint();

                                panelg3.removeAll();
                                panelg3.repaint();
                                panelg3.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                                click = false;
                                zaj1 = true;
                                opcja=0;
                                panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                            }
                    }
                    case 4 -> {
                        if(!zaj1){
                            InsertGraph(iconlabel[reka[4][0]],panel1, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])),Integer.toString(insertKart.szukajPAN(reka[4][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[4][0])), insertKart.szukajNazwa(reka[4][0]),insertKart.szukajEfekty(reka[4][0]));
                        panel1.repaint();

                        panelg4.removeAll();
                        panelg4.repaint();
                        panelg4.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                        click = false;
                        zaj1=true;
                        opcja=0;
                        panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                    }}

                    case 5 -> {
                        if(!zaj1){
                            InsertGraph(iconlabel[reka[5][0]],panel1, Integer.toString(insertKart.szukajATA(reka[5][0])), Integer.toString(insertKart.szukajHP(reka[5][0])),Integer.toString(insertKart.szukajPAN(reka[5][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[5][0])), insertKart.szukajNazwa(reka[5][0]),insertKart.szukajEfekty(reka[5][0]));
                        panel1.repaint();

                        panelg5.removeAll();
                        panelg5.repaint();
                        panelg5.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                        click = false;
                        zaj1=true;
                        opcja=0;
                        panel1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                    }}
                }

                System.out.println("kliknieto panel1:"+n);
                n++;
            }
        });


        panel2.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(zaj2)
                    panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                else
                    panel2.setBorder(BorderFactory.createLineBorder(Color.red,3));
                switch (opcja) {
                    case 1 -> {
                        if(!zaj2){
                            InsertGraph(iconlabel[reka[0][0]],panel2, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])),Integer.toString(insertKart.szukajPAN(reka[0][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[0][0])), insertKart.szukajNazwa(reka[0][0]),insertKart.szukajEfekty(reka[0][0]));
                            panel2.repaint();

                            panelg1.removeAll();
                            panelg1.repaint();
                            panelg1.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj2=true;
                            opcja=0;
                            panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));}
                    }
                    case 2 -> {
                        if(!zaj2){
                            InsertGraph(iconlabel[reka[1][0]],panel2, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])),Integer.toString(insertKart.szukajPAN(reka[1][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[1][0])), insertKart.szukajNazwa(reka[1][0]),insertKart.szukajEfekty(reka[1][0]));
                            panel2.repaint();

                            panelg2.removeAll();
                            panelg2.repaint();
                            panelg2.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj2=true;
                            opcja=0;
                            panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                    case 3 -> {
                        if(!zaj2){
                            InsertGraph(iconlabel[reka[2][0]],panel2, Integer.toString(insertKart.szukajATA(reka[2][0])), Integer.toString(insertKart.szukajHP(reka[2][0])),Integer.toString(insertKart.szukajPAN(reka[2][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[2][0])), insertKart.szukajNazwa(reka[2][0]),insertKart.szukajEfekty(reka[2][0]));
                            panel2.repaint();

                            panelg3.removeAll();
                            panelg3.repaint();
                            panelg3.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj2 = true;
                            opcja=0;
                            panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }
                    }
                    case 4 -> {
                        if(!zaj2){
                            InsertGraph(iconlabel[reka[3][0]],panel2, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])),Integer.toString(insertKart.szukajPAN(reka[3][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]),insertKart.szukajEfekty(reka[3][0]));
                            panel2.repaint();

                            panelg4.removeAll();
                            panelg4.repaint();
                            panelg4.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj2=true;
                            opcja=0;
                            panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}

                    case 5 -> {
                        if(!zaj2){
                            InsertGraph(iconlabel[reka[4][0]],panel2, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])),Integer.toString(insertKart.szukajPAN(reka[4][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[4][0])), insertKart.szukajNazwa(reka[4][0]),insertKart.szukajEfekty(reka[4][0]));
                            panel2.repaint();

                            panelg5.removeAll();
                            panelg5.repaint();
                            panelg5.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj2=true;
                            opcja=0;
                            panel2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                }

                System.out.println("kliknieto panel2:"+n);
                n++;
            }
        });

        panel3.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(zaj3)
                    panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                else
                    panel3.setBorder(BorderFactory.createLineBorder(Color.red,3));
                switch (opcja) {
                    case 1 -> {
                        if(!zaj3){
                            InsertGraph(iconlabel[reka[0][0]],panel3, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])),Integer.toString(insertKart.szukajPAN(reka[0][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[0][0])), insertKart.szukajNazwa(reka[0][0]),insertKart.szukajEfekty(reka[0][0]));
                            panel3.repaint();

                            panelg1.removeAll();
                            panelg1.repaint();
                            panelg1.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj3=true;
                            opcja=0;
                            panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));}
                    }
                    case 2 -> {
                        if(!zaj3){
                            InsertGraph(iconlabel[reka[1][0]],panel3, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])),Integer.toString(insertKart.szukajPAN(reka[1][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[1][0])), insertKart.szukajNazwa(reka[1][0]),insertKart.szukajEfekty(reka[1][0]));
                            panel3.repaint();

                            panelg2.removeAll();
                            panelg2.repaint();
                            panelg2.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj3=true;
                            opcja=0;
                            panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                    case 3 -> {
                        if(!zaj3){
                            InsertGraph(iconlabel[reka[2][0]],panel3, Integer.toString(insertKart.szukajATA(reka[2][0])), Integer.toString(insertKart.szukajHP(reka[2][0])),Integer.toString(insertKart.szukajPAN(reka[2][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[2][0])), insertKart.szukajNazwa(reka[2][0]),insertKart.szukajEfekty(reka[2][0]));
                            panel3.repaint();

                            panelg3.removeAll();
                            panelg3.repaint();
                            panelg3.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj3 = true;
                            opcja=0;
                            panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }
                    }
                    case 4 -> {
                        if(!zaj3){
                            InsertGraph(iconlabel[reka[3][0]],panel3, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])),Integer.toString(insertKart.szukajPAN(reka[3][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]),insertKart.szukajEfekty(reka[3][0]));
                            panel3.repaint();

                            panelg4.removeAll();
                            panelg4.repaint();
                            panelg4.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj3=true;
                            opcja=0;
                            panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}

                    case 5 -> {
                        if(!zaj3){
                            InsertGraph(iconlabel[reka[4][0]],panel3, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])),Integer.toString(insertKart.szukajPAN(reka[4][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[4][0])), insertKart.szukajNazwa(reka[4][0]),insertKart.szukajEfekty(reka[4][0]));
                            panel3.repaint();

                            panelg5.removeAll();
                            panelg5.repaint();
                            panelg5.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj3=true;
                            opcja=0;
                            panel3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                }

                System.out.println("kliknieto panel3:"+n);
                n++;
            }
        });

        panel4.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(zaj4)
                    panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                else
                    panel4.setBorder(BorderFactory.createLineBorder(Color.red,3));
                switch (opcja) {
                    case 1 -> {
                        if(!zaj4){
                            InsertGraph(iconlabel[reka[0][0]],panel4, Integer.toString(insertKart.szukajATA(reka[0][0])), Integer.toString(insertKart.szukajHP(reka[0][0])),Integer.toString(insertKart.szukajPAN(reka[0][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[0][0])), insertKart.szukajNazwa(reka[0][0]),insertKart.szukajEfekty(reka[0][0]));
                            panel4.repaint();

                            panelg1.removeAll();
                            panelg1.repaint();
                            panelg1.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj4=true;
                            opcja=0;
                            panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));}
                    }
                    case 2 -> {
                        if(!zaj4){
                            InsertGraph(iconlabel[reka[1][0]],panel4, Integer.toString(insertKart.szukajATA(reka[1][0])), Integer.toString(insertKart.szukajHP(reka[1][0])),Integer.toString(insertKart.szukajPAN(reka[1][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[1][0])), insertKart.szukajNazwa(reka[1][0]),insertKart.szukajEfekty(reka[1][0]));
                            panel4.repaint();

                            panelg2.removeAll();
                            panelg2.repaint();
                            panelg2.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj4=true;
                            opcja=0;
                            panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                    case 3 -> {
                        if(!zaj4){
                            InsertGraph(iconlabel[reka[2][0]],panel4, Integer.toString(insertKart.szukajATA(reka[2][0])), Integer.toString(insertKart.szukajHP(reka[2][0])),Integer.toString(insertKart.szukajPAN(reka[2][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[2][0])), insertKart.szukajNazwa(reka[2][0]),insertKart.szukajEfekty(reka[2][0]));
                            panel4.repaint();

                            panelg3.removeAll();
                            panelg3.repaint();
                            panelg3.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj4 = true;
                            opcja=0;
                            panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }
                    }
                    case 4 -> {
                        if(!zaj4){
                            InsertGraph(iconlabel[reka[3][0]],panel4, Integer.toString(insertKart.szukajATA(reka[3][0])), Integer.toString(insertKart.szukajHP(reka[3][0])),Integer.toString(insertKart.szukajPAN(reka[3][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[3][0])), insertKart.szukajNazwa(reka[3][0]),insertKart.szukajEfekty(reka[3][0]));
                            panel4.repaint();

                            panelg4.removeAll();
                            panelg4.repaint();
                            panelg4.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj4=true;
                            opcja=0;
                            panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}

                    case 5 -> {
                        if(!zaj4){
                            InsertGraph(iconlabel[reka[4][0]],panel4, Integer.toString(insertKart.szukajATA(reka[4][0])), Integer.toString(insertKart.szukajHP(reka[4][0])),Integer.toString(insertKart.szukajPAN(reka[4][0])),
                                    Integer.toString(insertKart.szukajKOSZT(reka[4][0])), insertKart.szukajNazwa(reka[4][0]),insertKart.szukajEfekty(reka[4][0]));
                            panel4.repaint();

                            panelg5.removeAll();
                            panelg5.repaint();
                            panelg5.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
                            click = false;
                            zaj4=true;
                            opcja=0;
                            panel4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                        }}
                }

                System.out.println("kliknieto panel4:"+n);
                n++;
            }
        });







        frame.getContentPane().setBackground(Color.BLUE);

        frame.add(panelg1);
        frame.add(panelg2);
        frame.add(panelg3);
        frame.add(panelg4);
        frame.add(panelg5);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);

        frame.setSize(1680,1050);
        frame.setVisible(true);
    }

    private static void ramka(CustomPanel panelg1, CustomPanel panelg2, CustomPanel panelg3, CustomPanel panelg4, CustomPanel panelg5) {
        panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
        panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
        panelg3.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
        panelg4.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
        panelg5.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
        click = false;
        opcja = 0;
    }

    static void InsertGraph(JLabel Icon,CustomPanel Panel, String atak,String hp,String pancerz,String koszt,String nazwa, String opis ){
        Panel.add(Icon);
        Panel.setLabelATAKText(atak);
        Panel.setLabelHPText(hp);
        Panel.setLabelMANAText(koszt);
        Panel.setLabelPANText(pancerz);
        Panel.setLabelNAZWAText(nazwa);
        Panel.setLabelOPISText(opis);

    }
    private static void tablica(){
        for(int i=0;i<30;i++){
            lista.add(i+1);
        }
    }
    private static void losuj(){
        for(int i=0;i<5;i++){
            int liczba = rand.nextInt(1,lista.size());
            reka[i][0] = lista.get(liczba);
            lista.remove(liczba);
            System.out.println(reka[i][0]);

    }
    }

    private static void pliki() throws SQLException {
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

    private static void ladowanie() throws SQLException {
        InsertKart insert = new InsertKart();
        insert.Pobierz();
        for(int i=0; i<=30;i++) {
            icon[i] = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\" + insert.szukajPlik(i));
            //System.out.println(insert.szukajPlik(i));
            iconlabel[i] = new JLabel(icon[i]);
            iconlabel[i].setBounds(-150, -145, icon[i].getIconWidth(), icon[i].getIconHeight());
        }

    }
    }

