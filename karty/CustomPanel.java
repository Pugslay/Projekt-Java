package com.example.karty;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Klasa pozwalająca na tworzenie panelu z danymi właściwościami, służy do pokazywania kart
public class CustomPanel extends JPanel {
    public JLabel atak;
    public JLabel HP;

    public JLabel mana;
    public JLabel pancerz;
    public JLabel nazwa;
    public JLabel opis;


    public CustomPanel() throws IOException, FontFormatException {
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\Studia\\Java\\testowanko\\Karty\\src\\main\\java\\com\\example\\karty\\alagard.ttf"));
        customFont = customFont.deriveFont(20f);
        customFont2 = customFont.deriveFont(14f);

        setLayout(null);

        atak = new JLabel();
        atak.setBounds(12, 65, 100, 200); // Ustaw pozycję i rozmiar pierwszej etykiety
        atak.setFont(customFont);
        add(atak);

        HP = new JLabel();
        HP.setBounds(172, 65, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        HP.setFont(customFont);
        add(HP);

        mana = new JLabel();
        mana.setBounds(12, -43, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        mana.setFont(customFont);
        add(mana);

        pancerz = new JLabel();
        pancerz.setBounds(172, -43, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        pancerz.setFont(customFont);
        add(pancerz);

        nazwa = new JLabel();
        nazwa.setBounds(45, -80, 200, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        nazwa.setFont(customFont2);
        add(nazwa);

        opis = new JLabel();
        opis.setBounds(45, 105, 200, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        opis.setFont(customFont2);
        // opis.setFont(new Font("MS Gothic", Font.BOLD, 13));
        add(opis);

    }

    public void setLabelATAKText(String text) {atak.setText(text);}

    public void setLabelHPText(String text) {
        HP.setText(text);
    }

    public void setLabelMANAText(String text) {
        mana.setText(text);
    }

    public void setLabelPANText(String text) {
        pancerz.setText(text);
    }

    public void setLabelNAZWAText(String text) {
        nazwa.setText(text);
    }

    public void setLabelOPISText(String text) {
        opis.setText(text);
    }

    //Funkcja odpowiedzialna za dodawanie danych elementów do panelu
    public void InsertGraph(JLabel Icon, CustomPanel Panel, String atak, String hp, String pancerz, String koszt, String nazwa, String opis) {
        Panel.add(Icon);
        Panel.setLabelATAKText(atak);
        Panel.setLabelHPText(hp);
        Panel.setLabelMANAText(koszt);
        Panel.setLabelPANText(pancerz);
        Panel.setLabelNAZWAText(nazwa);
        Panel.setLabelOPISText(opis);
        Panel.repaint();

    }

    public void InsertGraph(CustomPanel Panel, String atak, String hp, String pancerz, String koszt, String nazwa, String opis) {
        Panel.setLabelATAKText(atak);
        Panel.setLabelHPText(hp);
        Panel.setLabelMANAText(koszt);
        Panel.setLabelPANText(pancerz);
        Panel.setLabelNAZWAText(nazwa);
        Panel.setLabelOPISText(opis);
        Panel.repaint();

    }



}