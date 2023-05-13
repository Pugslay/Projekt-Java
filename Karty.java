package com.example.karty;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Karty {

    static Random rand = new Random();
    static int[] id = new int[32];
    static int[] atak = new int[32];
    static int[] hp = new int[32];
    static int[] pancerz = new int[32];
    static int[] koszt = new int[32];
    static String[] efekt = new String[32];
    static String[] efekt_2 = new String[32];
    static String[] nazwa = new String[32];

    static int y = 3;
    static int x = 5;
    static int[][] pozycja = new int[y][x];

    static int[] reka = new int[5];
    static int mana;
    static int mana_gr;
    static int licz = 0;
    static boolean schlop;


    public static void main(String[] args) throws SQLException {
        pobierz();
        pusty();
        losuj_reka();

        pozycja[0][0] = 1;
        pozycja[1][0] = 2;


        int wyj = 0;
        mana = 1;
        while (wyj != 10) {
            mana_gr = mana;

            System.out.print("""
                    Co chcesz zrobić:\s
                    1. Sprawdz stan stołu
                    2. Sprawdz mane
                    3. Sprawdz rękę
                    4. Rzuc karte
                    5. Skończ turę
                    """);
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();

            switch (a) {
                case 1 -> sprawdz();
                case 2 -> System.out.println("Twoja mana wynosi: " + mana_gr);
                case 3 -> {
                    sprawdz_Reka();
                    System.out.println();
                }
                case 4 -> {
                    wybor();
                    Chlopy();
                }
                case 5 -> {
                    wyj++;
                    mana++;
                    koniec_tury();
                    sprawdz_l_kart();
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

        for (int a = 0; a < 30; a++) {
            System.out.println("ID: " + id[a] + ", ATA: " + atak[a] + ", HP: " + hp[n] + ", PANCERZ: " + pancerz[a] + ", KOSZT: " + koszt[a] + ", NAZWA: " + nazwa[a] + ", EFEKTY: " + efekt[a] + " " + efekt_2[a]);
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
                System.out.print(nazwa[pozycja[i][j]] + " | ");
            }
        }

    }

    //losuje reke
    public static void losuj_reka() {
        for (int i = 0; i <= 4; i++) {
            int a = rand.nextInt(1, 31);
            reka[i] = id[a];
        }
    }

    //sprawdza reke gracza
    public static void sprawdz_Reka() {
        System.out.println("Twoja ręka:");
        for (int i = 0; i <= 4; i++) {
            System.out.println((i + 1) + "." + nazwa[reka[i]] + " HP:" + hp[reka[i]] + " ATAK:" + atak[reka[i]] + " OBRONA:" + pancerz[reka[i]] + " KOSZT:" + koszt[reka[i]] + " EFEKTY:" + efekt[reka[i]] + " | " + efekt_2[reka[i]]);

        }
    }

    //wybor karty z reki
    public static void wybor() {
        sprawdz_Reka();
        System.out.print("Wybierz karte: \n");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        switch (a) {
            case 1 -> miejsce(0);
            case 2 -> miejsce(1);
            case 3 -> miejsce(2);
            case 4 -> miejsce(3);
            case 5 -> miejsce(4);
            default -> System.out.println("NIE MA TAKIEJ OPCJI");
        }


    }

    //pozwala na rzucenie karty na dane WOLNE miejsce
    public static void miejsce(int Kid) {
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
                        pozycja[0][0] = reka[Kid];
                        Sprawdz_chlopa(pozycja[0][0]);
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 2 -> {
                    if (sprawdz_miejsce(pozycja[0][1])) {
                        pozycja[0][1] = reka[Kid];
                        Sprawdz_chlopa(pozycja[0][1]);
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 3 -> {
                    if (sprawdz_miejsce(pozycja[0][2])) {
                        pozycja[0][2] = reka[Kid];
                        Sprawdz_chlopa(pozycja[0][2]);
                        wyj = true;
                    } else
                        System.out.println("Tutaj jest juz karta");
                }
                case 4 -> {
                    if (sprawdz_miejsce(pozycja[0][3])) {
                        pozycja[0][3] = reka[Kid];
                        Sprawdz_chlopa(pozycja[0][3]);
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

    public static void Sprawdz_chlopa(int a) {
        schlop = efekt[a].equals("CHLP");
    }

    //sprawdza czy dany miejsce jest zajete
    public static boolean sprawdz_miejsce(int poz) {
        if (poz == 0)
            return true;
        else
            return false;

    }

    //sprawdza i dodaje karte gdy brakuje karty
    public static void sprawdz_l_kart() {
        for (int i = 0; i <= 4; i++) {
            if (nazwa[reka[i]] == null) {
                int a = rand.nextInt(1, 31);
                reka[i] = id[a];
                break;
            }

        }
    }

    public static void koniec_tury() {
        for (int j = 0; j <= 4; j++) {
            //Przebicie
            System.out.println(efekt[pozycja[0][j]]+"|"+efekt_2[pozycja[0][j]]);
            if (efekt[pozycja[0][j]].equals("p1") || efekt_2[pozycja[0][j]].equals("p1"))
                hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]] - 1));
            else {
                if (efekt[pozycja[0][j]].equals("p2") || efekt_2[pozycja[0][j]].equals("p2")) {
                    hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - (pancerz[pozycja[0][j]] - 2));
                }
            }

            //sprawdzenie czy karta zostala zniszczona
            if (hp[pozycja[0][j]] <= 0 && nazwa[pozycja[0][j]] != null) {
                System.out.println(nazwa[pozycja[0][j]] + " została zniszczona\n");
                pozycja[0][j] = 0;
            }

            if (hp[pozycja[1][j]] <= 0 && nazwa[pozycja[1][j]] != null) {
                System.out.println(nazwa[pozycja[1][j]] + " została zniszczona\n");
                pozycja[1][j] = 0;
                continue;
            }


            //bez przebicia
            hp[pozycja[0][j]] = hp[pozycja[0][j]] - (atak[pozycja[1][j]] - pancerz[pozycja[0][j]]);
            if (hp[pozycja[0][j]] <= 0 && nazwa[pozycja[0][j]] != null) {
                System.out.println(nazwa[pozycja[0][j]] + " została zniszczona\n");
                pozycja[0][j] = 0;
            }

            hp[pozycja[1][j]] = hp[pozycja[1][j]] - atak[pozycja[0][j]];
            if (hp[pozycja[1][j]] <= 0 && nazwa[pozycja[1][j]] != null) {
                System.out.println(nazwa[pozycja[1][j]] + " została zniszczona\n");
                pozycja[1][j] = 0;
            }
        }

    }

    public static void Chlopy() {
        licz++;
        if (licz >= 2) {
            for (int j = 0; j < 4; j++) {
                if (efekt[pozycja[0][j]] == "CHLP") {
                    hp[pozycja[0][j]] = hp[pozycja[0][j]] * 2;
                    atak[pozycja[0][j]] = atak[pozycja[0][j]] * 2;
                }
            }
        }
    }
}
