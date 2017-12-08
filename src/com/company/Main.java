package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Podaj ilosc punktow losowych");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double promien = 2;
        Kolo k1=new Kolo(new Punkt(2,2), promien);
        Kolo k2=new Kolo(new Punkt(5,2), promien);
        Kolo k3=new Kolo(new Punkt(4,5), promien);

        Punkt[] zp = new Punkt[n];
        for(int i=0; i<n;i++){
            zp[i] = new Punkt(Math.random()*10, Math.random()*10);
        }

        System.out.println("\n\n--------podzbiory punktów z1 należących do koła k1-----------");
        ZP zp1 = new ZP(k1, n);
        Punkt[] z1 = zp1.wspolne_punkty(zp);
        System.out.println(" \t\t\tWspólnych punktów z k1 jest: "+ z1.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--------podzbiory punktów z2 należących do koła k2-----------");
        ZP zp2 = new ZP(k2, n);
        Punkt[] z2 = zp2.wspolne_punkty(zp);
        System.out.println(" \t\t\tWspólnych punktów z k2 jest: "+ z2.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--------podzbiory punktów z3 należących do koła k3-----------");
        ZP zp3 = new ZP(k3, n);
        Punkt[] z3 = zp3.wspolne_punkty(zp);
        System.out.println(" \t\t\tWspólnych punktów z k3 jest: "+ z3.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--podzbiory punktów z12 należących do części wspólnej k1 i k2--");
        Punkt[] z12 = zp1.wspolne_punkty(z2);
        System.out.println(" \t\t\tWspólnych punktów podzbioru k1 i k2 jest: "+ z12.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--podzbiory punktów z23 należących do części wspólnej k2 i k3--");
        Punkt[] z23 = zp2.wspolne_punkty(z3);
        System.out.println(" \t\t\tWspólnych punktów podzbioru k1 i k2 jest: "+ z23.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--podzbiory punktów z31 należących do części wspólnej k3 i k1--");
        Punkt[] z31 = zp3.wspolne_punkty(z1);
        System.out.println(" \t\t\tWspólnych punktów podzbioru k1 i k2 jest: "+ z31.length);
        System.out.println("-------------------------------------------------------------");

        System.out.println("\n\n--podzbiory punktów z123 należących do części wspólnej k1, k2 i k3--");
        Punkt[] z123 = zp3.wspolne_punkty(z12);
        System.out.println(" \t\t\tWspólnych punktów podzbioru k1, k2 i k3 jest: "+ z123.length);
        System.out.println("-------------------------------------------------------------");

    }
}

class Punkt{
    double x,y;
    public Punkt(double x, double y){
        this.x=x;
        this.y=y;
    }
    double odl(Punkt p){
        double ax=x-p.x;
        double ay=y-p.y;
        return Math.sqrt(ax*ax+ay*ay);
    }
}

class Kolo{
    Punkt sr;
    double promien;
    public Kolo (Punkt sr, double promien){
        this.sr=new Punkt(sr.x,sr.y);
        this.promien=promien;
    }
    boolean isinside(Punkt p){
        double x = p.odl(sr);
        return (x <= promien);
    }
}

class ZP{
    Punkt[] fit_t;
    int n;
    Kolo kk;
    public ZP(Kolo kk, int n){
        this.kk = kk;
        this.n = n;
        this.fit_t = new Punkt[n];
    }
    Punkt[] fit_this(int j, Punkt[] fit_it){
        Punkt[] fited = new Punkt[j];
        for (int i=0; i<j;i++) {
            fited[i] = fit_it[i];
        }
        return fited;
    }
    Punkt[] wspolne_punkty(Punkt[] zp){
        Punkt[] zp_kolo = new Punkt[zp.length];
        int j=0;
        for(int i=0; i<zp.length;i++){
            if (kk.isinside(zp[i])){
                System.out.println("\t\t[ "+zp[i].x+"   \t"+zp[i].y+"\t]");
                zp_kolo[j++]=zp[i];
            }
        }
        this.n=j;
        this.fit_t = this.fit_this(j, zp_kolo);
        return this.fit_t;
    }
}