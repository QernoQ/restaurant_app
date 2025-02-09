package pracownicy;

import restaurant_app.Stoliki;
import restaurant_app.Zamowienia;
import system.Rachunek;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Kelner extends Pracownicy {

    private static Rachunek rachunek = new Rachunek(0, 0.0, false);

    public Kelner(int id, String imie, String nazwisko, String stanowisko, String dataZatrudnienia) {
        super(id, imie, nazwisko, stanowisko, dataZatrudnienia);
    }

    @Override
    public void menu() {
        System.out.println("Menu Kelnera:");
        System.out.println("1. Przyjmij zamowienie");
        System.out.println("2. Zmien status stolika");
        System.out.println("3. Wyswietl stoliki");
        System.out.println("4. Dodaj stolik");
        System.out.println("5. Usun stolik");
        System.out.println("6. Menu Rachunku");
        System.out.println("7. Zakoncz");

        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1:
                przyjmijZamowienie();
                menu();
                break;
            case 2:
                System.out.println("Podaj numer stolika: ");
                int idStolika = scanner.nextInt();
                Stoliki.wyswietlStolik(idStolika);
                System.out.println("Wybierz nowy stan stolika: ");
                System.out.println("1. Zajety");
                System.out.println("2. Wolny");
                int stan = scanner.nextInt();
                boolean nowyStan = (stan == 1);
                Stoliki.zmienStan(idStolika, nowyStan);
                Stoliki.wyswietlStolik(idStolika);
                menu();
                break;
            case 3:
                Stoliki.wyswietlStoliki();
                menu();
                break;
            case 4:
                System.out.println("Wybierz stan stolika:");
                System.out.println("1. Zajety");
                System.out.println("2. Wolny");
                int stanWybory = scanner.nextInt();
                boolean stanNowy = (stanWybory == 1);
                Stoliki.dodajStolik(stanNowy);
                menu();
                break;
            case 5:
                System.out.println("Podaj ID stolika do usuniecia:");
                int idUsun = scanner.nextInt();
                Stoliki.usunStolik(idUsun);
                menu();
                break;
            case 6:
                wyswietlMenuRachunku();
                menu();
                break;
            case 7:
                System.out.println("Zegnaj!");
                break;
            default:
                System.out.println("Niepoprawny wybor.");
        }
    }

    public void przyjmijZamowienie() {
        System.out.println("Wybierz pozycje z menu:");
        System.out.println("1. Sniadanie - 20.00 PLN");
        System.out.println("2. Obiad - 40.00 PLN");
        System.out.println("3. Kolacja - 30.00 PLN");
        System.out.println("4. Woda - 5.00 PLN");
        System.out.println("5. Napoj Gazowany - 8.00 PLN");
        System.out.println("6. Alkohol - 15.00 PLN");

        int wybor = scanner.nextInt();
        double cena = 0;

        switch (wybor) {
            case 1:
                cena = 20.00;
                break;
            case 2:
                cena = 40.00;
                break;
            case 3:
                cena = 30.00;
                break;
            case 4:
                cena = 5.00;
                break;
            case 5:
                cena = 8.00;
                break;
            case 6:
                cena = 15.00;
                break;
            default:
                System.out.println("Niepoprawny wybor.");
                return;
        }

        rachunek.dodajPlatnosc(cena);
        System.out.println("Zamowienie przyjete! Cena: " + cena + " PLN.");
    }

    public void wyswietlMenuRachunku() {
        System.out.println("Menu Rachunku:");
        System.out.println("1. Wyswietl rachunek");
        System.out.println("2. Podziel rachunek");
        System.out.println("3. Zamknij rachunek");
        System.out.println("4. Powrot");

        int wybor = scanner.nextInt();
        switch (wybor) {
             case 1:
                wyswietlRachunek();
                wyswietlMenuRachunku();
                break;
             case 2:
                 System.out.println("Na ile osob podzielic?");
                 int osoby = scanner.nextInt();
                 rachunek.dzielNaOsoby(osoby);
                 rachunek.setPodzielony(true);
             case 3:
                rachunek.setOplacony(true);
                System.out.println("Rachunek zamkniety.");
                generujRaportRachunku();
                break;
             case 4:
                menu();
                break;
             default:
                System.out.println("Niepoprawny wybor.");
        }
    }

    public void wyswietlRachunek() {
        System.out.println("Rachunek ID: " + rachunek.getIdRachunku());
        System.out.println("Kwota: " + rachunek.getKwota());
    }

    public void generujRaportRachunku() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("raport_rachunku.txt", true))) {
            writer.write("Rachunek ID: " + rachunek.getIdRachunku());
            writer.newLine();
            writer.write("Kwota: " + rachunek.getKwota());
            writer.newLine();
            writer.write("Oplacony: " + rachunek.isOplacony());
            writer.newLine();
            writer.write("--------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
