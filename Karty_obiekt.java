package com.example.karty;

public class Karty_obiekt {

    int id;
    int ATA;
    int hp;
    int pancerz;
    int koszt;
    String efekt;
    String efekt_2;
    String nazwa;

    String plik;
     public Karty_obiekt(int id, String nazwa, int ATA, int HP, int koszt, int pancerz, String ef1, String ef2, String plik){
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
}
