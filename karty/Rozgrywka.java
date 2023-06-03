package com.example.karty;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class Rozgrywka extends CustomButton{
    static CustomButton b1,b2,b3,b4,b5, bg1,bg2, bg3, bg4, bp1,bp2,bp3,bp4;
    public static java.util.List<Integer> gracz1 = new ArrayList<>();


    public static void main(String[] args) throws SQLException {
        InsertKart insertKart = new InsertKart();




        SwingUtilities.invokeLater(() -> {
            try {
                insertKart.Pobierz();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            insertKart.pokaz();
            petla();

            String ent = "E_ent.png";

            ImageIcon icon1 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\"+ent);

            ImageIcon icon2 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\K_Golem.png");

            ImageIcon icon3 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\W._Kapitan.png");

            ImageIcon icon4 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\P_rider.png");

            ImageIcon icon5 = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\P_Lucznik.png");

            JFrame f=new JFrame("Gra");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            b1 = new CustomButton();
            b2 = new CustomButton();
            b3 = new CustomButton();
            b4 = new CustomButton();
            b5 = new CustomButton();

            bg1 = new CustomButton();
            bg2 = new CustomButton();
            bg3 = new CustomButton();
            bg4 = new CustomButton();

            bp1 = new CustomButton();
            bp2 = new CustomButton();
            bp3 = new CustomButton();
            bp4 = new CustomButton();

            JButton be1=new JButton();
            JButton be2=new JButton();
            JButton be3=new JButton();
            JButton be4=new JButton();
            JButton be5=new JButton();

            be1.setBounds(250,0,200,225);
            be2.setBounds(475,0,200,225);
            be3.setBounds(700,0,200,225);
            be4.setBounds(925,0,200,225);
            be5.setBounds(1150,0,200,225);

            b1.setIcon(icon1);
            b2.setIcon(icon2);
            b3.setIcon(icon3);
            b4.setIcon(icon4);
            b5.setIcon(icon5);

            b1.setBounds(250,785,200,225);
            b2.setBounds(475,785,200,225);
            b3.setBounds(700,785,200,225);
            b4.setBounds(925,785,200,225);
            b5.setBounds(1150,785,200,225);

            b1.setBorder(BorderFactory.createDashedBorder(null));
            b2.setBorder(BorderFactory.createDashedBorder(null));
            b3.setBorder(BorderFactory.createDashedBorder(null));
            b4.setBorder(BorderFactory.createDashedBorder(null));
            b5.setBorder(BorderFactory.createDashedBorder(null));

            b1.setBackground(Color.WHITE);
            b2.setBackground(Color.WHITE);
            b3.setBackground(Color.WHITE);
            b4.setBackground(Color.WHITE);
            b5.setBackground(Color.WHITE);

            bg1.setBounds(370,550,200,225);
            bg2.setBounds(590,550,200,225);
            bg3.setBounds(810,550,200,225);
            bg4.setBounds(1030,550,200,225);

            bp1.setBounds(370,235,200,225);
            bp2.setBounds(590,235,200,225);
            bp3.setBounds(810,235,200,225);
            bp4.setBounds(1030,235,200,225);




            b1.setLabelATAKText("2");
            b1.setLabelHPText("5");
            b1.setLabelMANAText("5");
            b1.setLabelPANText("0");
            b1.setLabelNAZWAText("Ent");
            b1.setLabelOPISText("BRAK");

            b2.setLabelNAZWAText("Golem");
            b2.setLabelATAKText("3");
            b2.setLabelHPText("4");
            b2.setLabelPANText("0");
            b2.setLabelMANAText("5");
            b2.setLabelOPISText("PRZEB(2) I DYS");

            b3.setLabelNAZWAText("Kapitan");
            b3.setLabelATAKText("4");
            b3.setLabelHPText("3");
            b3.setLabelPANText("1");
            b3.setLabelMANAText("5");
            b3.setLabelOPISText("BRAK");

            b4.setLabelNAZWAText("Goblini jeździec");
            b4.setLabelATAKText("3");
            b4.setLabelHPText("2");
            b4.setLabelPANText("0");
            b4.setLabelMANAText("3");
            b4.setLabelOPISText("BRAK");

            b5.setLabelNAZWAText("Orczy Łucznik");
            b5.setLabelATAKText("2");
            b5.setLabelHPText("1");
            b5.setLabelPANText("1");
            b5.setLabelMANAText("2");
            b5.setLabelOPISText("DYS");




            //B1 eventy
            b1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(b1.isSelected())
                        b1.revalidate();
                    else{
                        b1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        b1.revalidate();}
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(b1.isSelected())
                        b1.revalidate();
                    else{
                        b1.setBorder(null);
                        b1.revalidate();
                }}});

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b2.isSelected()||b3.isSelected()||b4.isSelected()||b5.isSelected())
                        b1.revalidate();
                    else{
                    if(b1.isSelected()){
                        b1.setSelected(false);
                        b1.setBorder(null);
                        bg1.setBorder(null);
                        bg2.setBorder(null);
                        bg3.setBorder(null);
                        bg4.setBorder(null);}
                    else{
                        b1.setSelected(true);
                        b1.setBorder(BorderFactory.createLineBorder(Color.RED,4));
                        bg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        bg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        bg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        bg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));}
                }}
            });

            //B2 eventy
            b2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(b2.isSelected())
                        b2.revalidate();
                    else{
                        b2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        b2.revalidate();}
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(b2.isSelected())
                        b2.revalidate();
                    else{
                        b2.setBorder(null);
                        b2.revalidate();
                    }}});

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b1.isSelected()||b3.isSelected()||b4.isSelected()||b5.isSelected())
                        b2.revalidate();
                    else{
                        if(b2.isSelected()){
                            b2.setSelected(false);
                            b2.setBorder(null);
                            bg1.setBorder(null);
                            bg2.setBorder(null);
                            bg3.setBorder(null);
                            bg4.setBorder(null);}
                        else{
                            b2.setSelected(true);
                            b2.setBorder(BorderFactory.createLineBorder(Color.RED,4));
                            bg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));}
                    }}
            });

            //B3 eventy
            b3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(b3.isSelected())
                        b3.revalidate();
                    else{
                        b3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        b3.revalidate();}
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(b3.isSelected())
                        b3.revalidate();
                    else{
                        b3.setBorder(null);
                        b3.revalidate();
                    }}});

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b1.isSelected()||b2.isSelected()||b4.isSelected()||b5.isSelected())
                        b3.revalidate();
                    else{
                        if(b3.isSelected()){
                            b3.setSelected(false);
                            b3.setBorder(null);
                            bg1.setBorder(null);
                            bg2.setBorder(null);
                            bg3.setBorder(null);
                            bg4.setBorder(null);}
                        else{
                            b3.setSelected(true);
                            b3.setBorder(BorderFactory.createLineBorder(Color.RED,4));
                            bg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));}
                    }}
            });

            //B4 eventy
            b4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(b4.isSelected())
                        b4.revalidate();
                    else{
                        b4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        b4.revalidate();}
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(b4.isSelected())
                        b4.revalidate();
                    else{
                        b4.setBorder(null);
                        b4.revalidate();
                    }}});

            b4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b1.isSelected()||b2.isSelected()||b3.isSelected()||b5.isSelected())
                        b4.revalidate();
                    else{
                        if(b4.isSelected()){
                            b4.setSelected(false);
                            b4.setBorder(null);
                            bg1.setBorder(null);
                            bg2.setBorder(null);
                            bg3.setBorder(null);
                            bg4.setBorder(null);}
                        else{
                            b4.setSelected(true);
                            b4.setBorder(BorderFactory.createLineBorder(Color.RED,4));
                            bg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));}
                    }}
            });

            //B5 eventy
            b5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(b5.isSelected())
                        b5.revalidate();
                    else{
                        b5.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                        b5.revalidate();}
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(b5.isSelected())
                        b5.revalidate();
                    else{
                        b5.setBorder(null);
                        b5.revalidate();
                    }}});

            b5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b1.isSelected()||b2.isSelected()||b3.isSelected()||b4.isSelected())
                        b5.revalidate();
                    else{
                        if(b5.isSelected()){
                            b5.setSelected(false);
                            b5.setBorder(null);
                            bg1.setBorder(null);
                            bg2.setBorder(null);
                            bg3.setBorder(null);
                            bg4.setBorder(null);}
                        else{
                            b5.setSelected(true);
                            b5.setBorder(BorderFactory.createLineBorder(Color.RED,4));
                            bg1.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg2.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg3.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
                            bg4.setBorder(BorderFactory.createLineBorder(Color.GREEN,4));}
                    }}
            });


           /* bg1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(b1.isSelected()){

                    }
                    else{
                        if(b2.isSelected()){

                        }
                    }
                    else{
                        if(b3.isSelected()){

                        }
                        else{
                            if(b4.isSelected()){

                            }
                            else{
                                if(b5.isSelected()){
                                    bg1.setLabelATAKText();
                                }
                                else
                                    bg1.revalidate();
                            }
                        }
                    }

                }
            }); */


            f.add(bg1);
            f.add(bg2);
            f.add(bg3);
            f.add(bg4);

            f.add(b1);
            f.add(b2);
            f.add(b3);
            f.add(b4);
            f.add(b5);

            f.add(be1);
            f.add(be2);
            f.add(be3);
            f.add(be4);
            f.add(be5);

            f.add(bp1);
            f.add(bp2);
            f.add(bp3);
            f.add(bp4);



            f.setSize(1680,1050);
            f.setLayout(null);
            f.setVisible(true);
            f.setLocationRelativeTo(null);
        });
    }

    public static void petla(){
        for(int i=1;i<=30;i++){
        gracz1.add(i);
    }}

    }

