package restaurant_app;

import java.util.List;

public class Raporty extends RestauracjaSystem {
    private String dataRaportu;
    private String typRaportu; 
    private double przychod;
    private int liczbaZamowien;
    private int liczbaPracownikow;

    public Raporty(String dataRaportu, String typRaportu, double przychod, int liczbaZamowien, int liczbaPracownikow) {
        super();
        this.dataRaportu = dataRaportu;
        this.typRaportu = typRaportu;
        this.przychod = przychod;
        this.liczbaZamowien = liczbaZamowien;
        this.liczbaPracownikow = liczbaPracownikow;
    }

    public String getDataRaportu() { 
        return dataRaportu; 
    }
    public void setDataRaportu(String dataRaportu) { 
        this.dataRaportu = dataRaportu; 
    }
    public String getTypRaportu() { 
        return typRaportu; 
    }
    public void setTypRaportu(String typRaportu) { 
        this.typRaportu = typRaportu; 
    }
    public double getPrzychod() { 
        return przychod; 
    }
    public void setPrzychod(double przychod) { 
        this.przychod = przychod; 
    }
    public int getLiczbaZamowien() { 
        return liczbaZamowien; 
    }
    public void setLiczbaZamowien(int liczbaZamowien) { 
        this.liczbaZamowien = liczbaZamowien; 
    }
    public int getLiczbaPracownikow() { 
        return liczbaPracownikow; 
    }
    public void setLiczbaPracownikow(int liczbaPracownikow) { 
        this.liczbaPracownikow = liczbaPracownikow; 
    }

    public void generujRaport(String data, String typ) {
        // Generuje raport
    }

    public void wyswietlRaport() {
        // Wyświetla dane raportu
    }

    public void analizaPrzychodow(List<Zamowienia> zamowienia) {
        double totalPrzychod = 0;
        for (Zamowienia zamowienie : zamowienia) {
            totalPrzychod += zamowienie.getKwotaZamowienia();
        }
        System.out.println("Analiza przychodów: " + totalPrzychod);
    }
}