package HarrysFrisørSalon;

public class Kunde {
    private String navn;
    private String telefonNummer;
    private String tid;
    private String dato;

    public Kunde(String navn, String telefonNummer, String tid, String dato) {
        this.navn = navn;
        this.telefonNummer = telefonNummer;
        this.tid = tid;
        this.dato = dato;
    }

    public String getNavn() {
        return navn;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public String getTid() {
        return tid;
    }

    public String getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return "Dato: " + getDato() +
                "\nTid: " + getTid() +
                "\nNavn: " + getNavn() +
                "\nTelefonnummer: " + getTelefonNummer() +
                "\n";

    }
}
