package pracownicy;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import mainfile.RestauracjaSystem;

public abstract class Pracownicy implements IMenu {
    protected int id;
    protected String imie;
    protected String nazwisko;
    protected String stanowisko;
    protected String dataZatrudnienia;
    
    Scanner scanner;

    protected static final String FILE_NAME = "pracownicy.txt";

    public int getId() {
        return id;
    }
    public String getImie() {
        return imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
    public void setDataZatrudnienia(String dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }
    public String getStanowisko() {
        return stanowisko;
    }
    public String getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public Pracownicy(int id, String imie, String nazwisko, String stanowisko,String dataZatrudnienia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.dataZatrudnienia = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.scanner = new Scanner(System.in);
    }
    
    public void wyswietlPracownikow() {
        List<Pracownicy> pracownicy = RestauracjaSystem.wczytajPracownikow();
        for (Pracownicy pracownik : pracownicy) {
            System.out.println(pracownik);
        }
    }
    public void wyswietlPracownika(int id) { 
    List<Pracownicy> pracownicy = RestauracjaSystem.wczytajPracownikow();
    for (Pracownicy pracownik : pracownicy) {
        if (pracownik.getId() == id) {
            System.out.println(pracownik);
            return;
        }
    }
    System.out.println("Nie znaleziono pracownika o ID: " + id);
}
    public Pracownicy getPracownik() {
        return this;   
    }
    public abstract void menu();

    @Override
    public String toString() {
        return "ID: " + id + ", Imie: " + imie + ", Nazwisko: " + nazwisko + ", Stanowisko: " + stanowisko +
                ", Data Zatrudnienia: " + dataZatrudnienia;
    }
}

