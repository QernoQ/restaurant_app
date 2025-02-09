package system;

import restaurant_app.Zamowienia;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Raporty {
    private String dataRaportu;
    private String typRaportu;
    private double przychod;
    private int liczbaZamowien;
    private int liczbaPracownikow;

    public Raporty(String dataRaportu, String typRaportu, double przychod, int liczbaZamowien, int liczbaPracownikow) {
        super();
        this.dataRaportu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));;
        this.typRaportu = typRaportu;
        this.przychod = przychod;
        this.liczbaZamowien = liczbaZamowien;
        this.liczbaPracownikow = liczbaPracownikow;
    }

    public void generujRaport(String data, String typ, boolean podzielony) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("raport.txt", true))) {
            writer.write("Raport z dnia: " + data);
            writer.newLine();
            writer.write("Typ raportu: " + typ);
            writer.newLine();
            writer.write("Przychod: " + przychod);
            writer.newLine();
            writer.write("Liczba zamowien: " + liczbaZamowien);
            writer.newLine();
            writer.write("Liczba pracownikow: " + liczbaPracownikow);
            writer.newLine();
            writer.write("Podzielony rachunek: " + podzielony);
            writer.newLine();
            writer.write("--------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wyswietlRaport() {
        try (BufferedReader reader = new BufferedReader(new FileReader("raport.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analizaPrzychodow(List<Zamowienia> zamowienia) {
        double totalPrzychod = 0;
        for (Zamowienia zamowienie : zamowienia) {
            totalPrzychod += zamowienie.getKwotaZamowienia();
        }
        System.out.println("Analiza przychodow: " + totalPrzychod);
    }
}
