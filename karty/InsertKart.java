package com.example.karty;


import java.sql.*;
import java.util.*;

public class InsertKart {
    public static List<Kartyv2> kartyv2= new ArrayList<>();



    public void Pobierz() throws SQLException {

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
            dodaj(id,nazwa,atak,hp,pancerz,koszt,efekt,efekt_2,plik);
        }

    }
    public void dodaj(int ID, String nazwa, int atak, int hp, int pancerz, int koszt, String efekt, String efekt_2,String plik) {
        kartyv2.add(new Kartyv2(ID, nazwa, atak, hp, koszt, pancerz, efekt, efekt_2,plik));
    }

    public void pokaz(){
        for(Kartyv2 karty : kartyv2){
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

    public void szukaj(int ID){
        for(Kartyv2 karty : kartyv2){
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

    public int szukajATA(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.getata();

        }
        return -1;
    }

    public String szukajNazwa(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.getnazwa();

        }
        return null;
    }

    public int szukajHP(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.gethp();

        }
        return -1;
    }

    public int szukajPAN(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.getpancerz();

        }
        return -1;
    }

    public int szukajKOSZT(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.getkoszt();

        }
        return -1;
    }

    public String szukajEfekty(int ID){
        for(Kartyv2 karty : kartyv2){
            if(karty.getid()==ID)
                return karty.getefekt()+" "+karty.getefekt_2();

        }
        return null;
    }



}
