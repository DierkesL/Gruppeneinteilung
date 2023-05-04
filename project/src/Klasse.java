public class Klasse {
    private String bezeichnung;
    private Lehrer klassenlehrer;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Lehrer getKlassenlehrer() {
        return klassenlehrer;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setKlassenlehrer(Lehrer klassenlehrer){
        this.klassenlehrer = klassenlehrer;
    }
}
