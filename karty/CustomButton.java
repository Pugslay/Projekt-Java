package com.example.karty;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public JLabel atak;
    public JLabel HP;

    public JLabel mana;
    public JLabel pancerz;
    public JLabel nazwa;
    public JLabel opis;

    public CustomButton() {
        setLayout(null);

        atak = new JLabel();
        atak.setBounds(12, 65, 100, 200); // Ustaw pozycję i rozmiar pierwszej etykiety
        atak.setFont(new Font("Footlight", Font.BOLD, 20));
        add(atak);

        HP = new JLabel();
        HP.setBounds(172, 65, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        HP.setFont(new Font("Footlight", Font.BOLD, 20));
        add(HP);

        mana = new JLabel();
        mana.setBounds(12, -43, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        mana.setFont(new Font("Footlight", Font.BOLD, 20));
        add(mana);

        pancerz = new JLabel();
        pancerz.setBounds(172, -43, 100, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        pancerz.setFont(new Font("Footlight", Font.BOLD, 20));
        add(pancerz);

        nazwa = new JLabel();
        nazwa.setBounds(45, -80, 200, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        nazwa.setFont(new Font("MS Gothic", Font.BOLD, 14));
        add(nazwa);

        opis = new JLabel();
        opis.setBounds(45, 105, 200, 200); // Ustaw pozycję i rozmiar drugiej etykiety
        opis.setFont(new Font("Footlight", Font.BOLD, 13));
        add(opis);
    }

    public void setLabelATAKText(String text) {
        atak.setText(text);
    }

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
}