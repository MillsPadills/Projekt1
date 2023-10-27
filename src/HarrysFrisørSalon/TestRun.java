package HarrysFrisørSalon;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TestRun {
    ArrayList<Kunde> kunder = new ArrayList<>();
    // BookingKalender kalender = new BookingKalender();

    public static void main(String[] args) {
        new TestRun().run();
    }

    public void kodeord() {
        String kodeord = "harryhairy";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indtast kodeord: ");
        String input = scanner.nextLine();
        if (input.equals(kodeord)) {
            System.out.println("Du har fået adgang til konsollen.");
        } else {
            System.out.println("Forkert adgnagskode. Adgang nægtet.");
            System.exit(0);
        }
    }

    public void listeOverAftaler() {
        System.out.println("** LISTE OVER AFTALER **");
        for (int i = 0; i < kunder.size(); i++) {
            System.out.println(kunder.get(i));
            // for (Kunde kunde : kunder) {
            //System.out.println(kunde);
        }
    }

    public void opretAftale() {
        String navn;
        String telefonNummer;
        String tid;
        String dato = null; // HER ER DER FEJL, DER SKAL IKKE STÅ NULL
        Kunde aftale;
        Scanner scanner = new Scanner(System.in);

        System.out.println("** OPRET NY AFTALE **");
        System.out.println("Indtast en dato (dd/mm-yyyy):");

        // NYT JEG HAR LAVET TIL AT SKRIVE EN STRING SOM EN DATO I SCANNER
        String scanDato = scanner.nextLine();
        Date valgtDato = konveterDato(scanDato);

        if (valgtDato != null) {
            System.out.println(formaterDato(valgtDato));

            //NYT OM HVORDAN MAN VISER LEDIGE TIDER PÅ VALGTE DATO
            ArrayList<String> ledigeTider = findLedigeTider(valgtDato);
            if (!ledigeTider.isEmpty()) {
                System.out.println("Ledige tider:\n");
                for (int i = 0; i < ledigeTider.size(); i++) {
                    //System.out.println((i + 1) + ": " + ledigeTider.get(i)); //HER
                    System.out.println(ledigeTider.get(i));
                }

                System.out.println("Indtast nummer for ønskede tid: ");
                int valgtNummer = scanner.nextInt();

                if (valgtNummer >= 1 && valgtNummer <= ledigeTider.size()) {
                    tid = ledigeTider.get(valgtNummer - 1);
                    fjernLedigTid(ledigeTider, valgtNummer, valgtDato);

                    scanner.nextLine(); // SCANNER BUG
                    System.out.println("Indtast navn: ");
                    navn = scanner.nextLine();
                    System.out.println("Indtast telefonnummer: ");
                    telefonNummer = scanner.nextLine();

                    aftale = new Kunde(navn, telefonNummer, tid, dato); // HER
                    kunder.add(aftale);
                    saveFile();
                    saveLedigeTider(valgtDato, ledigeTider);
                    System.out.println("Aftale oprettet.");
                } else {
                    System.out.println("Ugyldigt nummer. Prøv venligst igen");
                }
            } else {
                System.out.println("Der er ingen ledige tider på den valgte dato. Prøv venligst igen.");
            }
        } else {
            System.out.println("Du har indtastet ugyldig data. Indtast venligst datoen i formatet dd/mm-yyyy. Tak!");
        }
    }


    public void saveFile() {
        File file = new File("aftaler.txt");
        try {
            PrintStream ps = new PrintStream(file);
            for (Kunde kunder : kunder) {
                ps.println(kunder.getNavn() + "," + kunder.getTelefonNummer() + "," + kunder.getTid() + "," + kunder.getDato());
            }
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // NY FIL TIL AT GEMME LEDIGE TIDER I
    public void saveLedigeTider(Date dato, ArrayList<String> ledigeTider) {
        File file = new File("ledigTid.txt");
        String datoString = formaterDato(dato);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            writer.println(datoString);
            for (String tid : ledigeTider) {
                writer.println(tid);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //NY METODE TIL AT SLETTE TID FRA LISTE OVER LEDIGE TIDER
    public void fjernLedigTid(ArrayList<String> ledigTider, int valgtNummer, Date dato) {
        if (valgtNummer >= 1 && valgtNummer <= ledigTider.size()) {
            ledigTider.remove(valgtNummer - 1);
            saveLedigeTider(dato, ledigTider);
        }
    }

    // NY METODE TIL AT KONVERTERE EN STRING DATO VI SKRIVER IND TIL EN ALMINDELIG DATO
    public Date konveterDato(String datoString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        try {
            return dateFormat.parse(datoString);
        } catch (Exception e) {
            return null;
        }
    }

    //NY METODE TIL AT FORMATERE EN DATO TIL EN STRENG
    public String formaterDato(Date dato) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        return dateFormat.format(dato);
    }


    //NY ARRAYLIST SOM ER EN METODE (HVORFOR?) TIL AT FINDE LEDIGE TIDER PÅ VALGTE DATO
    public ArrayList<String> findLedigeTider(Date dato) {
       ArrayList<String> ledigeTider = new ArrayList<>();

       Calendar kalender = Calendar.getInstance();
       kalender.setTime(dato);
       int ugedag = kalender.get(Calendar.DAY_OF_WEEK);

       if (ugedag >= Calendar.MONDAY && ugedag <= Calendar.FRIDAY) {
           int startTid = 10;
           for (int i = 1; i <= 8; i++) {
               ledigeTider.add("#" + i + ": Kl. " + startTid + "-" + (startTid + 1));
               startTid++;
           }
       }

        String datoString = formaterDato(dato);
        File file = new File("ledigTid.txt");
        try {
            Scanner inFile = new Scanner(file);
            String fileDato = inFile.nextLine();
            if (datoString.equals(fileDato)) {
                while (inFile.hasNextLine()) {
                    ledigeTider.add(inFile.nextLine());
                }
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ledigeTider;
    }


    public void aflysAftale() {

    }

    public void manglendeBetaling() {

    }

    public void betalingsOversigt() {

    }

    private void run() {
        kodeord();

        boolean kørProgram;
        Menu menu = new Menu("**** HARRYS SALON ****", "Vælg en mulighed:", new String[]{
                "1. Se liste over aftaler",
                "2. Opret aftale",
                "3. Slet aftale",
                "4. Oversigt over udestående betaling",
                "5. Betalings oversigt",
                "9. Luk program ned"
        });

        kørProgram = true;
        while (kørProgram) {
            menu.printMenu();
            int brugervalg = menu.brugerensValg();

            switch (brugervalg) {
                case 1:
                    listeOverAftaler();
                    break;
                case 2:
                    opretAftale();
                    break;
                case 3:
                    aflysAftale();
                    break;
                case 4:
                    manglendeBetaling();
                    break;
                case 5:
                    betalingsOversigt();
                    break;
                case 9:
                    kørProgram = false;
                    break;
                default:
                    System.out.println("Dette er ikke en valgmulighed. Prøv venligst igen.");
            }
        }
    }
}
