package pracownicy;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import mainfile.RestauracjaSystem;
import system.Raporty;

public class Kierownik extends Pracownicy {
    
    public Kierownik(int id, String imie, String nazwisko, String stanowisko,String dataZatrudnienia) {
    super(id,imie,nazwisko,stanowisko,dataZatrudnienia);
}
        
        public void zapiszPracownikow(List<Pracownicy> pracownicy,String nowyPracownik) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pracownicy pracownik : pracownicy) {
                writer.write(pracownik.getId() + "," + pracownik.getImie() + "," + pracownik.getNazwisko() + "," +
                        pracownik.getStanowisko() + "," + pracownik.getDataZatrudnienia());
                writer.newLine();
            }
            if(!nowyPracownik.equals(""))
                writer.write(nowyPracownik);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu pliku: " + e.getMessage());
        }
    }
        
    public void zatrudnijPracownika() {
        List<Pracownicy> pracownicy = RestauracjaSystem.wczytajPracownikow();
        Set<Integer> istniejąceId = new HashSet<>();
        for (Pracownicy pracownik : pracownicy) {
            istniejąceId.add(pracownik.getId()); 
        }
        int noweId = 1;
        while (istniejąceId.contains(noweId)) {
        noweId++;
    }
         String dataZatrudnieniaP = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         System.out.print("Podaj imie: ");
         String imiePracownik = scanner.next();
         System.out.print("Podaj nazwisko: ");
         String nazwiskoPracownik = scanner.next();
         System.out.println("Podaj stanowisko:");
         System.out.println("1. Kierownik");
         System.out.println("2. Kelner");
         String mess = "";
         int wybor = scanner.nextInt();
         switch (wybor) {
                case 1:
                    mess = noweId + "," + imiePracownik + "," + nazwiskoPracownik + ",kierownik," + dataZatrudnieniaP;
                    break;
                case 2:
                    mess = noweId + "," + imiePracownik + "," + nazwiskoPracownik + ",kelner," + dataZatrudnieniaP;
                    break;
                default:
                    break;
                }
        zapiszPracownikow(pracownicy,mess);
    }
    
        public void zmienStanowisko(int id, String noweStanowisko) {
        List<Pracownicy> pracownicy = RestauracjaSystem.wczytajPracownikow();
        for (Pracownicy pracownik : pracownicy) {
            if (pracownik.getId() == id) {
                pracownik.setStanowisko(noweStanowisko);
                
                break;
            }
        }
        zapiszPracownikow(pracownicy,"");
    }

    public void zwolnijPracownika(int id) {
        List<Pracownicy> pracownicy = RestauracjaSystem.wczytajPracownikow();
        pracownicy.removeIf(pracownik -> pracownik.getId() == id);
        zapiszPracownikow(pracownicy,"");
    }
      @Override
    public void menu() {
        System.out.println("Menu Kierownika:");
        System.out.println("1. Zatrudnij nowego pracownika");
        System.out.println("2. Zwolnij pracownika");
        System.out.println("3. Zmien stanowisko");
        System.out.println("4. Wyswietl liste pracownikow");
        System.out.println("5. Raporty");
        System.out.println("6. Zakoncz");
        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1:
                zatrudnijPracownika();
                menu();
                break;
            case 2:
                System.out.println("Podaj ID pracownika: ");
                int id_pracownika = scanner.nextInt();
                zwolnijPracownika(id_pracownika);
                menu();
                break;
            case 3:
                System.out.println("Podaj ID pracownika: ");
                int id_zmiany = scanner.nextInt();
                wyswietlPracownika(id_zmiany);
                System.out.println("Podaj stanowisko:");
                System.out.println("1. Kierownik");
                System.out.println("2. Kelner");
                int choose = scanner.nextInt();
                String noweStanowisko = scanner.next();
                switch (choose) {
                    case 1 -> noweStanowisko = "kierownik";
                    case 2 -> noweStanowisko = "kelner";
                    default -> {
                    }
                }
                zmienStanowisko(id_zmiany, noweStanowisko);
                menu();
                break;
            case 4:
                wyswietlPracownikow();
                menu();
                break;
            case 5:
                Raporty.wyswietlRaport();
                break;
            case 6:
                System.out.println("Zegnaj!");
                break;
            default:
                System.out.println("Niepoprawny wybor.");
        }
    }

}
