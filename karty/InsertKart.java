package com.example.karty;


import javax.swing.*;
import java.sql.*;
import java.util.*;

//Klasa odpowiedzialna za pobieranie danych z bazy danych oraz są w niej funkcje, które wspierają logikę oraz wyświetlanie kart
public class InsertKart {
    public static List<Karty_obiekt> kartyobiekt = new ArrayList<>();
    public static List<Serwer> serwer = new ArrayList<>();



    public static void Pobierz(boolean SYG) throws SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "karty";
        String password = "1111";
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID,Nazwa,ATA,HP,PANCERZ,KOSZT,Efekt,Efekt_2,Plik FROM Karty");
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nazwa = resultSet.getString("Nazwa");
            int atak = resultSet.getInt("ATA");
            int hp = resultSet.getInt("HP");
            int pancerz = resultSet.getInt("PANCERZ");
            int koszt = resultSet.getInt("KOSZT");
            String efekt = resultSet.getString("Efekt");
            String efekt_2 = resultSet.getString("Efekt_2");
            String plik = resultSet.getString("Plik");
            dodaj(id,nazwa,atak,hp,pancerz,koszt,efekt,efekt_2,plik,SYG);

        }

    }
    public static void dodaj(int ID, String nazwa, int atak, int hp, int pancerz, int koszt, String efekt, String efekt_2, String plik, boolean SYG) {
        if(SYG)
            serwer.add(new Serwer(ID, nazwa, atak, hp, koszt, pancerz, efekt, efekt_2, plik));
        else
            kartyobiekt.add(new Karty_obiekt(ID, nazwa, atak, hp, koszt, pancerz, efekt, efekt_2,plik));
    }

    public void pokaz(){
        for(Karty_obiekt karty : kartyobiekt){
            System.out.println("ID:"+karty.getid());
            System.out.println("Nazwa: "+karty.getnazwa());
            System.out.println("Atak: "+karty.getata());
            System.out.println("HP: "+karty.gethp());
            System.out.println("Pancerz: "+karty.getpancerz());
            System.out.println("Koszt: "+karty.getkoszt());
            System.out.println("Efekt: "+karty.getefekt()+" "+karty.getefekt_2());
            System.out.println("Plik: "+karty.getPLIK());
            System.out.println("-------------------------------------------------");
        }
    }

    static void ladowanie(ImageIcon[] icon, JLabel[] iconlabel, JLabel[] iconlabel2){
        for(int i=0; i<=30;i++) {
            icon[i] = new ImageIcon("C:\\Users\\pingw\\Desktop\\Gra karciana\\Gotowe Grafiki\\" + szukajPlik(i));
            //System.out.println(szukajPlik(i));
            iconlabel[i] = new JLabel(icon[i]);
            iconlabel[i].setBounds(-150, -145, icon[i].getIconWidth(), icon[i].getIconHeight());

            iconlabel2[i] = new JLabel(icon[i]);
            iconlabel2[i].setBounds(-150, -145, icon[i].getIconWidth(), icon[i].getIconHeight());
        }

    }


    public void szukaj(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID){
                System.out.println("ID:"+karty.getid());
                System.out.println("Nazwa: "+karty.getnazwa());
                System.out.println("Atak: "+karty.getata());
                System.out.println("HP: "+karty.gethp());
                System.out.println("Pancerz: "+karty.getpancerz());
                System.out.println("Koszt: "+karty.getkoszt());
                System.out.println("Efekt: "+karty.getefekt()+" "+karty.getefekt_2());
                System.out.println("Plik: "+karty.getPLIK());
                System.out.println("-------------------------------------------------");}
        }
    }


    public String nic(){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==8)
                return karty.getefekt_2();

        }
        return "nic";
    }
    public String p1(){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==10)
                return karty.getefekt_2();

        }
        return "nic";
    }
    public String p2(){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==6)
                return karty.getefekt_2();

        }
        return "nic";
    }
    public String dys(){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==10)
                return karty.getefekt();

        }
        return "nic";
    }

    public int szukajATA(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getata();

        }
        return -1;
    }

    public int szukajID(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getid();

        }
        return 0;
    }

    public static String szukajNazwa(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getnazwa();

        }
        return null;
    }

    public int szukajHP(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.gethp();

        }
        return -1;
    }

    public int szukajPAN(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getpancerz();

        }
        return -1;
    }

    public int szukajKOSZT(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getkoszt();

        }
        return -1;
    }

    public String szukajEfekty(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getefekt()+" "+karty.getefekt_2();

        }
        return null;
    }

    public static String szukajPlik(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getPLIK();

        }
        return null;
    }


    public String szukajEfektyS_1(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getefekt();

        }
        return "Nic";
    }

    public String szukajEfektyS_2(int ID){
        for(Karty_obiekt karty : kartyobiekt){
            if(karty.getid()==ID)
                return karty.getefekt_2();

        }
        return "Nic";
    }

    public int szukajATAS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.getata();

        }
        return 0;
    }


    public int szukajIDS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.getid();

        }
        return 0;
    }

    public String szukajNazwaS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.getnazwa();

        }
        return "Nic";
    }

    public int szukajHPS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.gethp();

        }
        return -1;
    }

    public int szukajPANS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.getpancerz();

        }
        return -1;
    }


    public String szukajEfektyS(int ID){
        for(Serwer serw : serwer){
            if(serw.getid()==ID)
                return serw.getefekt()+" "+serw.getefekt_2();

        }
        return null;
    }




}
