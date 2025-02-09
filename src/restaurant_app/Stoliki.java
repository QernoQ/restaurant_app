package restaurant_app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Stoliki  {
    private int idStolika;
    private int numerStolika;
    private Boolean stan; // true - zajęty, false - wolny

    private static final String FILE_NAME = "stoliki.txt";

    public Stoliki(int idStolika, int numerStolika, boolean stan) {
        this.idStolika = idStolika;
        this.numerStolika = numerStolika;
        this.stan = stan;
    }

    public int getIdStolika() {
        return idStolika;
    }
    public void setIdStolika(int idStolika) {
        this.idStolika = idStolika;
    }
    public int getNumerStolika() {
        return numerStolika;
    }
    public void setNumerStolika(int numerStolika) {
        this.numerStolika = numerStolika;
    }
    public Boolean getStan() {
        return stan;
    }
    public void setStan(Boolean stan) {
        this.stan = stan;
    }

    public static List<Stoliki> wczytajStoliki() {
        List<Stoliki> stoliki = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                if (dane.length < 3) {
                    System.out.println("Nieprawidlowy format danych w wierszu: " + linia);
                    continue;
                }

                int idStolika = Integer.parseInt(dane[0]);
                int numerStolika = Integer.parseInt(dane[1]);
                boolean stan = Boolean.parseBoolean(dane[2]);

                stoliki.add(new Stoliki(idStolika, numerStolika, stan));
            }
        } catch (IOException e) {
            System.out.println("Blad podczas odczytu pliku: " + e.getMessage());
        }
        return stoliki;
    }

    public static void zapiszStoliki(List<Stoliki> stoliki) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Stoliki stolik : stoliki) {
                writer.write(stolik.getIdStolika() + "," + stolik.getNumerStolika() + "," + stolik.getStan());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Blad podczas zapisu pliku: " + e.getMessage());
        }
    }

    public static void wyswietlStoliki() {
        List<Stoliki> stoliki = wczytajStoliki();
        for (Stoliki stolik : stoliki) {
            System.out.println(stolik);
        }
    }
    
    public static void wyswietlStolik(int idStolika) {
    List<Stoliki> stoliki = wczytajStoliki();
    for (Stoliki stolik : stoliki) {
        if (stolik.getIdStolika() == idStolika) {
            System.out.println(stolik);
            return;
        }
    }
    System.out.println("Nie znaleziono stolika o ID " + idStolika);
}

    public static void zmienStan(int idStolika, boolean nowyStan) {
        List<Stoliki> stoliki = wczytajStoliki();
        for (Stoliki stolik : stoliki) {
            if (stolik.getIdStolika() == idStolika) {
                stolik.setStan(nowyStan);
                zapiszStoliki(stoliki);
                System.out.println("Stan stolika o ID " + idStolika + " zostal zmieniony.");
                return;
            }
        }
        System.out.println("Nie znaleziono stolika o ID " + idStolika);
    }
    private static int znajdzWolneId(List<Stoliki> stoliki) {
    int noweId = 1;
    boolean idZnalezione;
    while (true) {
        idZnalezione = false;
        for (Stoliki stolik : stoliki) {
            if (stolik.getIdStolika() == noweId) {
                idZnalezione = true;
                break;
            }
        }
        if (!idZnalezione) {
            break;
        }
        noweId++;
    }
    return noweId;
}
    public static void dodajStolik(boolean stan) {
    List<Stoliki> stoliki = wczytajStoliki();
    int noweId = znajdzWolneId(stoliki);
    int numerStolika = noweId;
    Stoliki nowyStolik = new Stoliki(noweId, numerStolika, stan);
    stoliki.add(nowyStolik);
    stoliki.sort(Comparator.comparingInt(Stoliki::getIdStolika));
    zapiszStoliki(stoliki);
    System.out.println("Dodano stolik o numerze: " + numerStolika + " z stanem: " + (stan ? "Zajety" : "Wolny"));
}
    
    public static Stoliki getStolikById(int idStolika) {
    List<Stoliki> stoliki = wczytajStoliki();
    for (Stoliki stolik : stoliki) {
        if (stolik.getIdStolika() == idStolika) {
            return stolik;
        }
    }
    return null; 
}

    public static void usunStolik(int idStolika) {
        List<Stoliki> stoliki = wczytajStoliki();
        stoliki.removeIf(stolik -> stolik.getIdStolika() == idStolika);
        zapiszStoliki(stoliki);
        System.out.println("Stolik o ID " + idStolika + " zostal usuniety.");
    }

    @Override
    public String toString() {
        return "ID: " + idStolika + ", Numer stolika: " + numerStolika + ", Stan: " + (stan ? "Zajety" : "Wolny");
    }

}
