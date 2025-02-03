package restaurant_app;

import java.util.ArrayList;
import java.util.List;

public class Kasa {
    private List<Rachunek> rachunki;

    public Kasa() {
        this.rachunki = new ArrayList<>();
    }

    public List<Rachunek> getRachunki() { 
        return rachunki; 
    }

    public void setRachunki(List<Rachunek> rachunki) { 
        this.rachunki = rachunki; 
    }

    public void dodajRachunek(Zamowienia zamowienie, double kwota) {
        Rachunek nowyRachunek = new Rachunek(zamowienie.getIdZamowienia(), kwota, false);
        rachunki.add(nowyRachunek);
    }

    public void pobierzOplate(int idRachunku, double kwota) {
        for (Rachunek rachunek : rachunki) {
            if (rachunek.getIdRachunku() == idRachunku) {
                rachunek.dodajPlatnosc(kwota);
                break;
            }
        }
    }

    public void dzielRachunek(int idRachunku, int liczbaOsob) {
        for (Rachunek rachunek : rachunki) {
            if (rachunek.getIdRachunku() == idRachunku) {
                rachunek.dzielNaOsoby(liczbaOsob);
                break;
            }
        }
    }

    public void wydrukujRachunek(int idRachunku) {
        for (Rachunek rachunek : rachunki) {
            if (rachunek.getIdRachunku() == idRachunku) {
                rachunek.wyswietlRachunek();
                break;
            }
        }
    }
}