package com.example.karty;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

public class Kartyv2 {

    static Random rand = new Random();
    int id;
    int ATA;
    int hp;
    int pancerz;
    int koszt;
    String efekt;
    String efekt_2;
    String nazwa;

    String plik;

    static int y = 3;
    static int x = 5;
    static int[][] pozycja = new int[y][x];

    static int[][] reka = new int[5][2];
    static int mana;
    static int mana_gr;
    static int licz = 0;
    static int HP_GRACZ_1=2;
    static int HP_GRACZ_2=2;


     public Kartyv2(int id,String nazwa, int ATA, int HP, int koszt, int pancerz, String ef1, String ef2,String plik){
         this.id=id;
        this.nazwa=nazwa;
        this.ATA=ATA;
        this.hp=HP;
        this.pancerz=pancerz;
        this.koszt=koszt;
        this.efekt=ef1;
        this.efekt_2=ef2;
        this.plik=plik;
    }
    public int getid(){ return id;}
    public String getnazwa() {
        return nazwa;
    }
    public int getata() {
        return ATA;
    }
    public int gethp() {
        return hp;
    }
    public int getpancerz() {
        return pancerz;
    }
    public int getkoszt() {
        return koszt;
    }
    public String getefekt() {
        return efekt;
    }
    public String getefekt_2() {
        return efekt_2;
    }

    public String getPLIK(){return plik;}

    /*
    public static boolean sprawdz_hp(){
        if(HP_GRACZ_1<=0){
            if(HP_GRACZ_2<=0) {
                System.out.println("REMIS");
                return true;
            }
            else {
                System.out.println("Wygrywa gracz nr 2");
                return true;
            }
        }
        else{
            if(HP_GRACZ_2<=0){
                System.out.println("Wygrywa gracz nr 1");
            }
        }
        return false;
    }
    public static void main(String[] args) throws SQLException {
         InsertKart IK = new InsertKart();
        IK.Pobierz();
        IK.pokaz();
        pusty();
        losuj_reka();

        pozycja[0][0] = 1;
        pozycja[1][0] = 2;


        int wyj = 0;
        mana = 1;
        int gracz = 0;
        while (wyj != 10) {
            if(sprawdz_hp()){
                break;
            }

            mana_gr = mana;

            System.out.println("Tura: "+(wyj+1));
            System.out.println("Gracz: "+(gracz+1));
            System.out.print("""
                    Co chcesz zrobić:\s
                    1. Sprawdz stan stołu
                    2. Sprawdz mane
                    3. Sprawdz rękę
                    4. Rzuc karte
                    5. Sprawdz HP
                    6. Skończ turę
                    """);
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();

            switch (a) {
                case 1 -> sprawdz();
                case 2 -> System.out.println("Twoja mana wynosi: " + mana_gr/mana);
                case 3 -> {
                    sprawdz_Reka(gracz);
                    System.out.println();
                }
                case 4 -> {
                    wybor(gracz);
                    System.out.println(licz);
                    //Chlopy();
                    for (int j = 0; j < 4; j++) {
                        if(efekt[pozycja[0][j]].equals("CHLP")){
                            licz++;
                        }
                    }
                    if (licz >= 2) {
                        for (int j = 0; j < 4; j++) {
                            if (efekt[pozycja[0][j]].equals("CHLP")) {
                                hp[pozycja[0][j]] = hp[pozycja[0][j]] * 2;
                                atak[pozycja[0][j]] = atak[pozycja[0][j]] * 2;
                                System.out.println(hp[pozycja[0][j]]+" "+hp[pozycja[0][j]]);
                            }
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Zdrowie gracza 1 wynosi: "+HP_GRACZ_1);
                    System.out.println("Zdrowie gracza 2 wynosi: "+HP_GRACZ_2);
                }
                case 6 -> {
                    wyj++;
                    mana++;
                    koniec_tury();
                    sprawdz_l_kart(gracz);
                    gracz++;
                    if(gracz>=2)
                        gracz=0;
                }
                default -> System.out.println("NIE MA TAKIEJ OPCJI");
            }

        }
    }

    public static void pobierz() throws SQLException {
        int n = 1;

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "karty";
        String password = "1111";
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID,Nazwa,ATA,HP,PANCERZ,KOSZT,Efekt,Efekt_2 FROM Karty");
        while (resultSet.next()) {
            id[n] = resultSet.getInt("ID");
            nazwa[n] = resultSet.getString("Nazwa");
            atak[n] = resultSet.getInt("ATA");
            hp[n] = resultSet.getInt("HP");
            pancerz[n] = resultSet.getInt("PANCERZ");
            koszt[n] = resultSet.getInt("KOSZT");
            efekt[n] = resultSet.getString("Efekt");
            efekt_2[n] = resultSet.getString("Efekt_2");
            n++;
        }

        if (!resultSet.next()) {
            System.out.println("No results found."); // wyświetla komunikat, jeśli zapytanie nie zwróciło wyników
        }

        for (int a = 0; a < 31; a++) {
            System.out.println("ID: " + id[a] + ", ATA: " + atak[a] + ", HP: " + hp[a] + ", PANCERZ: " + pancerz[a] + ", KOSZT: " + koszt[a] + ", NAZWA: " + nazwa[a] + ", EFEKTY: " + efekt[a] + " " + efekt_2[a]);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    public static void pusty(){
        nazwa[0]="NIC";
        efekt[0]="NIC";
        efekt_2[0]="NIC";
    }
    public static void sprawdz() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
            for (int j = 0; j < 4; j++) {
                System.out.print(nazwa[pozycja[i][j]] + " " + atak[pozycja[i][j]] + " | " );
            }
        }

    }

    //losuje reke
    public static void losuj_reka() {
        for(int j = 0;j<=1;j++){
            for (int i = 0; i <= 4; i++) {
                int a = rand.nextInt(1, 31);
                reka[i][j] = id[a];
            }}
    }

    //sprawdza reke gracza
    public static void sprawdz_Reka(int j) {
        System.out.println("Twoja ręka:");
        for (int i = 0; i <= 4; i++) {
            System.out.println((i + 1) + "." + nazwa[reka[i][j]] + " HP:" + hp[reka[i][j]] + " ATAK:" + atak[reka[i][j]] + " OBRONA:" + pancerz[reka[i][j]] + " KOSZT:" + koszt[reka[i][j]] + " EFEKTY:" + efekt[reka[i][j]] + " | " + efekt_2[reka[i][j]]);

        }
    }

    //wybor karty z reki
    public static void wybor(int j) {
        sprawdz_Reka(j);
        System.out.print("Wybierz karte: \n");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        switch (a) {
            case 1 -> {
                if(koszt[reka[0][j]]<=mana_gr){
                    mana_gr=mana_gr-koszt[reka[0][j]];
                    miejsce(0,j);}
                else
                    System.out.println("Nie masz tyle many!");
            }
            case 2 -> {
                if(koszt[reka[1][j]]<=mana_gr){
                    mana_gr=mana_gr-koszt[reka[1][j]];
                    miejsce(1,j);}
                else
                    System.out.println("Nie masz tyle many!");
            }
            case 3 -> {
                if(koszt[reka[2][j]]<=mana_gr){
                    mana_gr=mana_gr-koszt[reka[2][j]];
                    miejsce(2,j);}
                else
                    System.out.println("Nie masz tyle many!");
            }
            case 4 -> {
                if(koszt[reka[3][j]]<=mana_gr){
                    mana_gr=mana_gr-koszt[reka[1][j]];
                    miejsce(3,j);}
                else
                    System.out.println("Nie masz tyle many!");
            }
            case 5 -> {
                if(koszt[reka[4][j]]<=mana_gr){
                    mana_gr=mana_gr-koszt[reka[1][j]];
                    miejsce(4,j);
                }
                else
                    System.out.println("Nie masz tyle many!");
            }
            default -> System.out.println("NIE MA TAKIEJ OPCJI");
        }


    }

    //pozwala na rzucenie karty na dane WOLNE miejsce
    public static void miejsce(int Kid, int j) {
        sprawdz();
        boolean wyj = false;
        int a;
        while (!wyj) {
            System.out.println("Wybierz miejsce: \n");
            Scanner scan = new Scanner(System.in);
            a = scan.nextInt();
            switch (a) {
                case 1 -> {
                    if (sprawdz_miejsce(pozycja[0][0])) {
                        pozycja[0][0] = reka[Kid][j];
                        reka[Kid][j]=0;
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 2 -> {
                    if (sprawdz_miejsce(pozycja[0][1])) {
                        pozycja[0][1] = reka[Kid][j];
                        reka[Kid][j]=0;
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 3 -> {
                    if (sprawdz_miejsce(pozycja[0][2])) {
                        pozycja[0][2] = reka[Kid][j];
                        reka[Kid][j]=0;
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 4 -> {
                    if (sprawdz_miejsce(pozycja[0][3])) {
                        pozycja[0][3] = reka[Kid][j];
                        reka[Kid][j]=0;
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                default -> wyj = true;
            }
        }
        mana_gr = mana_gr - koszt[Kid];
        sprawdz();
    }


    //sprawdza czy dany miejsce jest zajete
    public static boolean sprawdz_miejsce(int poz) {
        if (poz == 0)
            return true;
        else
            return false;

    }

    //sprawdza i dodaje karte gdy brakuje karty
    public static void sprawdz_l_kart(int j) {
        for (int i = 0; i <= 4; i++) {
            if (nazwa[reka[i][j]] == "NIC") {
                int a = rand.nextInt(1, 31);
                reka[i][j] = id[a];
                break;
            }

        }
    }

    public static void koniec_tury() {
        for (int j = 0; j <= 4; j++) {

            if(nazwa[pozycja[0][j]]=="NIC"&&nazwa[pozycja[1][j]]!="NIC")
                HP_GRACZ_1=HP_GRACZ_1-atak[pozycja[1][j]];

            if(nazwa[pozycja[1][j]]=="NIC"&&nazwa[pozycja[0][j]]!="NIC")
                HP_GRACZ_2=HP_GRACZ_2-atak[pozycja[0][j]];

            //Dystansowe
            if(efekt[pozycja[0][j]].equals("DYS")){//T
                if(efekt[pozycja[1][j]].equals("DYS"))//TT
                    przeb(j);
                else{//TN
                    //Gracz 1 ma atak dystansowy
                    if(efekt[pozycja[0][j]].equals("P1") || efekt_2[pozycja[0][j]].equals("P1")){
                        if(atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-1)>0)
                            hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-1));}
                    else{
                        if(efekt[pozycja[0][j]].equals("P2") || efekt_2[pozycja[0][j]].equals("P2")){
                            if(atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-2)>0)
                                hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-2));}
                    }

                    if(atak[pozycja[0][j]] - pancerz[pozycja[1][j]]>0)
                        hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - pancerz[pozycja[1][j]]);

                    if (hp[pozycja[1][j]] <= 0 && nazwa[pozycja[0][j]] != "NIC") {
                        System.out.println(nazwa[pozycja[1][j]] + " została zniszczona\n");
                        pozycja[1][j] = 0;
                        continue;}
                    else{ //atak karty gracza 2 po dystansowym
                        if(efekt[pozycja[1][j]].equals("P1") || efekt_2[pozycja[1][j]].equals("P1")){
                            if(atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-1)>0)
                                hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-1));}
                        else{
                            if(efekt[pozycja[1][j]].equals("P2") || efekt_2[pozycja[1][j]].equals("P2")){
                                if(atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-2)>0)
                                    hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-2));}
                        }

                        if(atak[pozycja[1][j]] - pancerz[pozycja[0][j]]>0)
                            hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - pancerz[pozycja[0][j]]);

                        if (hp[pozycja[0][j]] <= 0 && nazwa[pozycja[1][j]] != "NIC") {
                            System.out.println(nazwa[pozycja[0][j]] + " została zniszczona\n");
                            pozycja[0][j] = 0;
                            continue;}
                    }
                }
            }
            else{
                if(!efekt[pozycja[1][j]].equals("DYS"))
                    przeb(j);
                else{ //atak karty gracza 2 po dystansowym
                    if(efekt[pozycja[0][j]].equals("P1") || efekt_2[pozycja[0][j]].equals("P1")){
                        if(atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-1)>0)
                            hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-1));}
                    else{
                        if(efekt[pozycja[0][j]].equals("P2") || efekt_2[pozycja[0][j]].equals("P2")){
                            if(atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-2)>0)
                                hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-2));}
                    }

                    if(atak[pozycja[0][j]] - pancerz[pozycja[1][j]]>0)
                        hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - pancerz[pozycja[1][j]]);

                    if (hp[pozycja[1][j]] <= 0 && nazwa[pozycja[0][j]] != "NIC") {
                        System.out.println(nazwa[pozycja[1][j]] + " została zniszczona\n");
                        pozycja[1][j] = 0;
                        continue;}

                }
            }
        }
    }

    private static void przeb(int j) {
        if (efekt[pozycja[0][j]].equals("p1") || efekt_2[pozycja[0][j]].equals("p1"))
            if((hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-1)))>0)
                hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] + ((pancerz[pozycja[1][j]] - 1)));
            else {
                if (efekt[pozycja[0][j]].equals("p2") || efekt_2[pozycja[0][j]].equals("p2")) {
                    if((hp[pozycja[1][j]] - (atak[pozycja[0][j]] - (pancerz[pozycja[1][j]]-2)))>0)
                        hp[pozycja[1][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - ((pancerz[pozycja[1][j]] - 2)));

                }
                else
                if((hp[pozycja[1][j]] - (atak[pozycja[0][j]] - pancerz[pozycja[1][j]]))>0)
                    hp[pozycja[0][j]] = hp[pozycja[1][j]] - (atak[pozycja[0][j]] - pancerz[pozycja[1][j]]);
            }


        if (efekt[pozycja[1][j]].equals("p1") || efekt_2[pozycja[1][j]].equals("p1")){
            if((hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-1)))>0)
                hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] + ((pancerz[pozycja[0][j]] - 1)));}
        else {
            if (efekt[pozycja[1][j]].equals("p2") || efekt_2[pozycja[1][j]].equals("p2")) {
                if((hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]]-2)))>0)
                    hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] + ((pancerz[pozycja[0][j]] - 2)));

            }
            else{
                if((hp[pozycja[0][j]] - (atak[pozycja[1][j]] - pancerz[pozycja[0][j]]))>0)
                    hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - pancerz[pozycja[0][j]]);}
        }

        if (hp[pozycja[0][j]] <= 0 && nazwa[pozycja[0][j]] != "NIC") {
            System.out.println(nazwa[pozycja[0][j]] + " została zniszczona\n");
            pozycja[0][j] = 0;
        }

        hp[pozycja[1][j]] = hp[pozycja[1][j]] - atak[pozycja[0][j]];
        if (hp[pozycja[1][j]] <= 0 && nazwa[pozycja[1][j]] != "NIC") {
            System.out.println(nazwa[pozycja[1][j]] + " została zniszczona\n");
            pozycja[1][j] = 0;
        }

    }

     */

}
