package restaurant_app;

public class Rachunek {
    private int idRachunku;
    private double kwota;
    private boolean oplacony;

    public Rachunek(int idRachunku, double kwota, boolean oplacony) {
        this.idRachunku = idRachunku;
        this.kwota = kwota;
        this.oplacony = oplacony;
    }

    public int getIdRachunku() { 
        return idRachunku; 
    }
    public void setIdRachunku(int idRachunku) { 
        this.idRachunku = idRachunku; 
    }
    public double getKwota() { 
        return kwota; 
    }
    public void setKwota(double kwota) { 
        this.kwota = kwota; 
    }
    public boolean isOplacony() { 
        return oplacony; 
    }
    public void setOplacony(boolean oplacony) { 
        this.oplacony = oplacony; 
    }

    public void dodajPlatnosc(double kwota) {
        this.kwota += kwota;
        if (this.kwota > 0) {
            this.oplacony = true;
        }
    }

    public void dzielNaOsoby(int liczbaOsob) {
        if (liczbaOsob > 0) {
            this.kwota /= liczbaOsob;
        }
    }

    public void wyswietlRachunek() {
        System.out.println("Rachunek ID: " + idRachunku + " Kwota: " + kwota + " Opłacony: " + oplacony);
    }
}