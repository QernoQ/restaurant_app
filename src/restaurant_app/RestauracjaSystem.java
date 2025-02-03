package restaurant_app;
import java.io.*;
import java.util.*;

public class RestauracjaSystem {

    public static void main(String[] args) throws IOException {
        List<Pracownicy> listaPracownikow = wczytajPracownikowZPliku();
        List<Stoliki> listaStolikow = wczytajStolikiZPliku();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz ID pracownika do logowania: ");
        int idPracownika = scanner.nextInt();

        Pracownicy pracownik = znajdzPracownikaPoId(idPracownika, listaPracownikow);
        if (pracownik != null) {
            System.out.println("Witaj " + pracownik.getImie().toUpperCase()+ "!" + "("+ pracownik.getStanowisko().toUpperCase() + "}");

            switch (pracownik.getStanowisko().toLowerCase()) {
                case "kierownik":
                    menuKierownika(pracownik, scanner);
                    break;
                case "kelner":
                    menuKelnera(scanner);
                    break;
                case "kucharz":
                    menuKucharza(pracownik, scanner);
                    break;
                default:
                    System.out.println("Nieznana rola.");
            }
        } else {
            System.out.println("Nie znaleziono pracownika o takim ID.");
        }

        scanner.close();
    }
    

    private static List<Pracownicy> wczytajPracownikowZPliku() throws IOException {
        List<Pracownicy> listaPracownikow = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("pracownicy.txt"));
        String linia;
        while ((linia = reader.readLine()) != null) {
            String[] dane = linia.split(",");
            int id = Integer.parseInt(dane[0]);
            String imie = dane[1];
            String nazwisko = dane[2];
            String stanowisko = dane[3];
            String dataZatrudnienia = dane[4];
            listaPracownikow.add(new Pracownicy(id, imie, nazwisko, stanowisko, dataZatrudnienia));
        }
        reader.close();
        return listaPracownikow;
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

    private static void menuKierownika(Pracownicy pracownik, Scanner scanner) {
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
            System.out.print("Podaj imie: ");
            String imie = scanner.next();
            System.out.print("Podaj nazwisko: ");
            String nazwisko = scanner.next();
            System.out.print("Podaj stanowisko: ");
            String stanowisko = scanner.next();
            pracownik.zatrudnijPracownika(imie, nazwisko, stanowisko);
            pracownik.wykonajOperacje();
            menuKierownika(pracownik, scanner);
                break;
            case 2:
                System.out.println("Podaj ID pracownika: ");
                int id_pracownika = scanner.nextInt();
                pracownik.zwolnijPracownika(id_pracownika);
                pracownik.wykonajOperacje();
                menuKierownika(pracownik, scanner);
                break;
            case 3:
                System.out.println("Podaj ID pracownika: ");
                int id_zmiany = scanner.nextInt();
                pracownik.zwolnijPracownika(id_zmiany);
                pracownik.wyswietlPracownika(id_zmiany);
                System.out.println("Podaj nowe stanowisko: ");
                String noweStanowisko = scanner.next();
                pracownik.zmienStanowisko(id_zmiany, noweStanowisko);
                menuKierownika(pracownik, scanner);
                break;
            case 4:
                pracownik.wyswietlPracownikow();
                menuKierownika(pracownik, scanner);
                break;
            case 5:
                System.out.println("RAPORTY");
                break;
            case 6:
                System.out.println("Zegnaj!");
                break;
            default:
                System.out.println("Niepoprawny wybór.");
        }
    }
    // Menu dla Kelnera
    private static void menuKelnera(Scanner scanner) {
        System.out.println("Menu Kelnera:");
        System.out.println("1. Przyjmij zamowienie");
        System.out.println("2. Zmien status stolika");
        System.out.println("3. Wyswietl stoliki");
        System.out.println("4. Dodaj stolik");
        System.out.println("5. Usun stolik");
        System.out.println("6. Zakoncz");

        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1:
                System.out.println("Przyjmij zamówienie");
                menuKelnera(scanner); 
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
                menuKelnera(scanner);
            case 3:
                Stoliki.wyswietlStoliki();
                menuKelnera(scanner);
                break;
            case 4:
                System.out.println("Wybierz stan stolika:");
                System.out.println("1. Zajety");
                System.out.println("2. Wolny");
                int stanWybory = scanner.nextInt();
                boolean stanNowy = (stanWybory == 1);
                Stoliki.dodajStolik(stanNowy);
                menuKelnera(scanner);
                break;
            case 5:
                System.out.println("Podaj ID stolika do usuniecia:");
                int idUsun = scanner.nextInt();
                Stoliki.usunStolik(idUsun); 
                menuKelnera(scanner);
                break;
            case 6:
                System.out.println("Zegnaj!");
                break;
            default:
                System.out.println("Niepoprawny wybor.");
    }
}

    // Menu dla Kucharza
    private static void menuKucharza(Pracownicy pracownik, Scanner scanner) {
        System.out.println("Menu Kucharza:");
        System.out.println("1. Zmiana statusu zamowienia");
        System.out.println("2. Zakoncz");
        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1:
                System.out.println("zmienstatuszaowienia");
                break;
            case 2:
                System.out.println("Zegnaj!");
                break;
            default:
                System.out.println("Niepoprawny wybor.");
        }
    } 
}