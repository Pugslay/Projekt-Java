package com.example.karty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class TESTY {
    public static boolean clickg1=false;
    public static boolean clickg2=false;
    public static boolean clickg3=false;
    public static boolean clickg4=false;
    public static boolean clickg5=false;

    public static void main(String[] args) throws SQLException {
        /*
    InsertKart insertKart = new InsertKart();
    insertKart.Pobierz();
    insertKart.pokaz();
    insertKart.szukaj(3);
    System.out.println(insertKart.szukajATA(3));
         */
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

        CustomPanel panel2 = new CustomPanel();
        panel2.setBounds(590,550,200,225);
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));

        CustomPanel panel3 = new CustomPanel();
        panel3.setBounds(810,550,200,225);
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));

        CustomPanel panel4 = new CustomPanel();
        panel4.setBounds(1030,550,200,225);
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createLineBorder(Color.RED,3,true));


        ImageIcon icon1 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\W._Kapitan.png");
        JLabel iconLabel = new JLabel(icon1);
        iconLabel.setBounds(-150, -145, icon1.getIconWidth(), icon1.getIconHeight());

        ImageIcon icon2 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\K_Golem.png");
        JLabel iconLabel2 = new JLabel(icon2);
        iconLabel2.setBounds(-150, -145, icon2.getIconWidth(), icon2.getIconHeight());

        ImageIcon icon3 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\K_Woj.png");
        JLabel iconLabel3 = new JLabel(icon3);
        iconLabel3.setBounds(-150, -145, icon3.getIconWidth(), icon3.getIconHeight());

        ImageIcon icon4 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\L_kusznik.png");
        JLabel iconLabel4 = new JLabel(icon4);
        iconLabel4.setBounds(-150, -145, icon4.getIconWidth(), icon4.getIconHeight());

        ImageIcon icon5 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\P_behemot.png");
        JLabel iconLabel5 = new JLabel(icon5);
        iconLabel5.setBounds(-150, -145, icon5.getIconWidth(), icon5.getIconHeight());



        panelg1.add(iconLabel);
        panelg2.add(iconLabel2);
        panelg3.add(iconLabel3);
        panelg4.add(iconLabel4);
        panelg5.add(iconLabel5);

        panelg1.setLabelATAKText("1");
        panelg1.setLabelHPText("2");
        panelg1.setLabelMANAText("3");
        panelg1.setLabelPANText("4");
        panelg1.setLabelNAZWAText("dupa");
        panelg1.setLabelOPISText("gówniana");


        iconLabel.setFocusable(true);

        panelg1.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clickg1){
                    panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
                    clickg1 = false;
                }
                else{
                    panelg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    clickg1 = true;
                }
                System.out.println("kliknieto panelg1:"+n);
                n++;

            }
        });

        panel1.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clickg1){
                        panel1.add(iconLabel);
                        panel1.setLabelATAKText("1");
                        panel1.setLabelHPText("2");
                        panel1.setLabelMANAText("3");
                        panel1.setLabelPANText("4");
                        panel1.setLabelNAZWAText("dupa");
                        panel1.setLabelOPISText("gówniana");
                        panel1.repaint();

                        panelg1.removeAll();
                        panelg1.repaint();
                        panelg1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
                        clickg1=false;
                }
                System.out.println("kliknieto panel1:"+n);
                n++;
            }
        });


        panelg2.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clickg2){
                    panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
                    clickg2 = false;
                }
                else{
                    panelg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
                    clickg2 = true;
                }
                System.out.println("kliknieto panelg2:"+n);
                n++;

            }
        });

        panel2.addMouseListener(new MouseAdapter() {
            int n=0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clickg2){
                    panel2.add(iconLabel);
                    panel2.setLabelATAKText("1");
                    panel2.setLabelHPText("2");
                    panel2.setLabelMANAText("3");
                    panel2.setLabelPANText("4");
                    panel2.setLabelNAZWAText("dupa");
                    panel2.setLabelOPISText("gówniana");
                    panel2.repaint();

                    panelg2.removeAll();
                    panelg2.repaint();
                    panelg2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3,true));
                    clickg1=false;
                }
                System.out.println("kliknieto panel 2:"+n);
                n++;
            }
        });


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

    static void test(CustomPanel Panel, int atak,int hp,int pancerz,int ){

    }
    }

