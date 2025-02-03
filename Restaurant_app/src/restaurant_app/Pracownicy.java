package restaurant_app;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Pracownicy implements IOperations {
    private int id;
    private String imie;
    private String nazwisko;
    private String stanowisko;
    private String dataZatrudnienia;

    private static final String FILE_NAME = "pracownicy.txt";

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
    }

    public void zatrudnijPracownika(String imie, String nazwisko, String stanowisko) {
        List<Pracownicy> pracownicy = wczytajPracownikow();
        int noweId = pracownicy.size() + 1;
        Pracownicy nowyPracownik = new Pracownicy(noweId, imie, nazwisko, stanowisko, dataZatrudnienia);
        pracownicy.add(nowyPracownik);
        zapiszPracownikow(pracownicy);
    }

    public void zwolnijPracownika(int id) {
        List<Pracownicy> pracownicy = wczytajPracownikow();
        pracownicy.removeIf(pracownik -> pracownik.getId() == id);
        zapiszPracownikow(pracownicy);
    }

    public void wyswietlPracownikow() {
        List<Pracownicy> pracownicy = wczytajPracownikow();
        for (Pracownicy pracownik : pracownicy) {
            System.out.println(pracownik);
        }
    }
    public void wyswietlPracownika(int id) {
    List<Pracownicy> pracownicy = wczytajPracownikow();
    for (Pracownicy pracownik : pracownicy) {
        if (pracownik.getId() == id) {
            System.out.println(pracownik);
            return;
        }
    }
    System.out.println("Nie znaleziono pracownika o ID: " + id);
}


    public void zmienStanowisko(int id, String noweStanowisko) {
        List<Pracownicy> pracownicy = wczytajPracownikow();
        for (Pracownicy pracownik : pracownicy) {
            if (pracownik.getId() == id) {
                pracownik.setStanowisko(noweStanowisko);
                
                break;
            }
        }
        zapiszPracownikow(pracownicy);
    }

    private List<Pracownicy> wczytajPracownikow() {
        List<Pracownicy> pracownicy = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                if (dane.length < 5) {
                    System.out.println("Nieprawidłowy format danych w wierszu: " + linia);
                    continue;
                }

                int id = Integer.parseInt(dane[0]);
                String imie = dane[1];
                String nazwisko = dane[2];
                String stanowisko = dane[3];
                String dataZatrudnienia = dane[4];

                pracownicy.add(new Pracownicy(id, imie, nazwisko, stanowisko, dataZatrudnienia));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format liczby w danych: " + e.getMessage());
        }
        return pracownicy;
    }

    private void zapiszPracownikow(List<Pracownicy> pracownicy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pracownicy pracownik : pracownicy) {
                writer.write(pracownik.getId() + "," + pracownik.getImie() + "," + pracownik.getNazwisko() + "," +
                        pracownik.getStanowisko() + "," + pracownik.getDataZatrudnienia());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu pliku: " + e.getMessage());
        }
    }
    @Override
    public void wykonajOperacje() {
        System.out.println("Wykonano!");
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Imie: " + imie + ", Nazwisko: " + nazwisko + ", Stanowisko: " + stanowisko +
                ", Data Zatrudnienia: " + dataZatrudnienia;
    }
}

