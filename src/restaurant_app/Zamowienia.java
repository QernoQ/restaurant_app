package restaurant_app;

import java.util.*;

public class Zamowienia {
    private int idZamowienia;
    private List<String> listaPotraw;
    private String statusZamowienia;
    private Stoliki stolik;
    private double kwotaZamowienia;

    private static Map<Integer, Zamowienia> zamowieniaMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public Zamowienia(int idZamowienia, List<String> listaPotraw, String statusZamowienia, Stoliki stolik) {
        this.idZamowienia = idZamowienia;
        this.listaPotraw = listaPotraw;
        this.statusZamowienia = statusZamowienia;
        this.stolik = stolik;
        zamowieniaMap.put(idZamowienia, this);
    }

    public static List<Zamowienia> getWszystkieZamowienia() {
        return new ArrayList<>(zamowieniaMap.values());
    }

    public static void przyjmijZamowienie() {
        System.out.println("Podaj ID stolika:");
        int idStolika = scanner.nextInt();

        Stoliki stolik = Stoliki.getStolikById(idStolika);
        if (stolik == null) {
            System.out.println("Stolik o podanym ID nie istnieje.");
            return;
        }

        scanner.nextLine(); 
        System.out.println("Podaj potrawy (oddzielone przecinkiem):");
        String potrawyInput = scanner.nextLine();
        List<String> listaPotraw = new ArrayList<>(List.of(potrawyInput.split(",")));

        int noweId = znajdzWolneId();
        Zamowienia zamowienie = new Zamowienia(noweId, listaPotraw, "Przyjęte", stolik);
        
        System.out.println("Zamówienie przyjęte:");
        zamowienie.wyswietlZamowienie();
    }

    private static int znajdzWolneId() {
        int noweId = 1;
        while (zamowieniaMap.containsKey(noweId)) {
            noweId++;
        }
        return noweId;
    }

    public static void wyswietlWszystkieZamowienia() {
        for (Zamowienia zamowienie : zamowieniaMap.values()) {
            zamowienie.wyswietlZamowienie();
        }
    }

    public void wyswietlZamowienie() {
        System.out.println("ID Zamówienia: " + idZamowienia);
        System.out.println("Status: " + statusZamowienia);
        System.out.println("Stolik: " + stolik.getNumerStolika());
        System.out.println("Lista Potraw: " + String.join(", ", listaPotraw));
        System.out.println("Kwota: " + kwotaZamowienia + " PLN");
    }

    public double getKwotaZamowienia() {
        return kwotaZamowienia;
    }
}
