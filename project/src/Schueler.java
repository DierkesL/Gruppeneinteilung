public class Schueler {
    private String vorname;
    private String nachname;
    private Klasse klasse;

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }
}
