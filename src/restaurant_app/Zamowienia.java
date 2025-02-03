package restaurant_app;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Zamowienia extends RestauracjaSystem  {
    private int idZamowienia;
    private List<String> listaPotraw; 
    private String statusZamowienia; 
    private Stoliki stolik; 
    private double kwotaZamowienia;

    private static Map<Integer, Zamowienia> zamowieniaMap = new HashMap<>();

    public Zamowienia(int idZamowienia, List<String> listaPotraw, String statusZamowienia, Stoliki stolik, double kwotaZamowienia) {
        super();
        this.idZamowienia = idZamowienia;
        this.listaPotraw = listaPotraw;
        this.statusZamowienia = statusZamowienia;
        this.stolik = stolik;
        this.kwotaZamowienia = kwotaZamowienia;
    }

    public int getIdZamowienia() { 
        return idZamowienia; 
    }
    public void setIdZamowienia(int idZamowienia) { 
        this.idZamowienia = idZamowienia; 
    }
    public List<String> getListaPotraw() { 
        return listaPotraw; 
    }
    public void setListaPotraw(List<String> listaPotraw) { 
        this.listaPotraw = listaPotraw; 
    }
    public String getStatusZamowienia() { 
        return statusZamowienia; 
    }
    public void setStatusZamowienia(String statusZamowienia) { 
        this.statusZamowienia = statusZamowienia; 
    }
    public Stoliki getStolik() { 
        return stolik; 
    }
    public void setStolik(Stoliki stolik) { 
        this.stolik = stolik; 
    }
    public double getKwotaZamowienia() { 
        return kwotaZamowienia; 
    }
    public void setKwotaZamowienia(double kwotaZamowienia) { 
        this.kwotaZamowienia = kwotaZamowienia; 
    }
    public void przyjmijZamowienie(Stoliki stolik, List<String> potrawy) {
        this.stolik = stolik;
        this.listaPotraw = potrawy;
        this.idZamowienia = zamowieniaMap.size() + 1;
        zamowieniaMap.put(idZamowienia, this);
    }

    public void zmienStatus(String nowyStatus) {
        this.statusZamowienia = nowyStatus;
    }

    public void obliczKwote() {
        this.kwotaZamowienia = listaPotraw.size() * 15.00; 
    }

    public void wyswietlZamowienie() {
        System.out.println("ID Zamówienia: " + idZamowienia);
        System.out.println("Status: " + statusZamowienia);
        System.out.println("Stolik: " + stolik);
        System.out.println("Lista Potraw: " + String.join(", ", listaPotraw));
        System.out.println("Kwota: " + kwotaZamowienia + " PLN");
    }

    public static void wyswietlWszystkieZamowienia() {
        for (Zamowienia zamowienie : zamowieniaMap.values()) {
            zamowienie.wyswietlZamowienie();
        }
    }
}
