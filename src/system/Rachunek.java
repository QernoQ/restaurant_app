package system;

import java.io.*;

public class Rachunek {
    private static int lastId = 0;
    private int idRachunku;
    private double kwota;
    private boolean oplacony;
    private boolean podzielony;

    public Rachunek(int idRachunku, double kwota, boolean oplacony) {
        this.idRachunku = lastId+1;
        this.kwota = kwota;
        this.oplacony = false;
        this.podzielony = false;
    }

    public int getIdRachunku() {
        return idRachunku = lastId+1;
    }
    public double getKwota() {
        return kwota;
    }
    public boolean isOplacony() {
        return oplacony;
    }
    public void setOplacony(boolean oplacony) {
        this.oplacony = oplacony;
    }
    public boolean isPodzielony() {
        return podzielony;
    }
    public void setPodzielony(boolean podzielony) {
        this.podzielony = podzielony;
    }
    public void dodajPlatnosc(double kwota) {
        this.kwota += kwota;
        if (this.kwota > 0) {
            this.oplacony = true;
        }
    }
    
    public void dzielNaOsoby(int liczbaOsob) {
        if (liczbaOsob > 0) {
            this.kwota /= liczbaOsob;
            this.podzielony = true;
            this.kwota = 0;
        }
    }

    public void wyswietlRachunek() {
        System.out.println("Rachunek ID: " + idRachunku + " Kwota: " + kwota + " Podzielony: " + podzielony);
    }


    public void generujRaport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("raport_rachunku.txt", true))) {
            writer.write("Raport z dnia: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            writer.newLine();
            writer.write("ID Rachunku: " + idRachunku);
            writer.newLine();
            writer.write("Kwota: " + kwota);
            writer.newLine();
            writer.write("Oplacony: " + oplacony);
            writer.newLine();
            writer.write("Podzielony: " + podzielony);
            writer.newLine();
            writer.write("--------------");
            writer.newLine();
            this.kwota = 0;
        } catch (IOException e) {
            System.out.println("Bład podczas zapisywania raportu: " + e.getMessage());
        }
    }
}
