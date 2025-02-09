package mainfile;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import pracownicy.Kelner;
import pracownicy.Kierownik;
import pracownicy.Pracownicy;
import restaurant_app.Stoliki;

public class RestauracjaSystem {

    public static void main(String[] args) throws IOException {
        List<Pracownicy> listaPracownikow = wczytajPracownikow();
        List<Stoliki> listaStolikow = wczytajStolikiZPliku();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Na potrzeby prezentacji: 1 - kierownik 2 - kelner (Zalozeie jest takie ze kazdy pracownik musi znac zswoje id jako zabezpieczenie np id 45131");
        System.out.println("Wybierz ID pracownika do logowania: ");
        int idPracownika = scanner.nextInt();

        Pracownicy pracownik = znajdzPracownikaPoId(idPracownika, listaPracownikow);
        if (pracownik != null) {
            System.out.println("Witaj " + pracownik.getImie().toUpperCase()+ "!" + "("+ pracownik.getStanowisko().toUpperCase() + "}");
            pracownik.menu();
        } else {
            System.out.println("Nie znaleziono pracownika o takim ID.");
        }

        scanner.close();
    } 
    
      public static List<Pracownicy> wczytajPracownikow() {
        List<Pracownicy> pracownicy = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pracownicy.txt"))) {
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
                switch (stanowisko) {
                case "kierownik":
                    pracownicy.add(new Kierownik(id, imie, nazwisko, stanowisko, dataZatrudnienia));
                    break;
                case "kelner":
                    pracownicy.add(new Kelner(id, imie, nazwisko, stanowisko, dataZatrudnienia));
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format liczby w danych: " + e.getMessage());
        }
        return pracownicy;
    }
    
    
    private static List<Stoliki> wczytajStolikiZPliku() throws IOException {
        List<Stoliki> listaStolikow = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("stoliki.txt"));
        String linia;
        while ((linia = reader.readLine()) != null) {
            String[] dane = linia.split(",");
            int idStolika = Integer.parseInt(dane[0]);
            int numerStolika = Integer.parseInt(dane[1]);
            boolean stan = Boolean.parseBoolean(dane[2]);
            listaStolikow.add(new Stoliki(idStolika, numerStolika, stan));
        }
        reader.close();
        return listaStolikow;
    }


    private static Pracownicy znajdzPracownikaPoId(int id, List<Pracownicy> listaPracownikow) {
        for (Pracownicy p : listaPracownikow) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
} 