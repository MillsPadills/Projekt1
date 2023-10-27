package HarrysFrisørSalon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
    ArrayList<Kunde> kunder = new ArrayList<>();
    BookingKalender kalender = new BookingKalender();

    public static void main(String[] args) {
        new BookingSystem().run();
    }

    public void ledigeTider() {

    }

    public void opretAftale() {
        String navn;
        String telefonNummer;
        String tid;
        String dato = null;
        Kunde aftale;
        Scanner scanner = new Scanner(System.in);

        System.out.println("**OPRET NY AFTALE** \n");
        System.out.println("Vælg dato: \n");
        // lave metode for at vælge dato

        System.out.println("Ledige tider:\n");
        // lave metode for at vise ledige tider på den gældende dato

        System.out.println("Indtast navn: ");
        navn = scanner.nextLine();
        System.out.println("Indtast telefonnummer: ");
        telefonNummer = scanner.nextLine();
        System.out.println("Indtast tid: ");
        tid = scanner.nextLine();
        

        aftale = new Kunde(navn, telefonNummer, tid, dato);
        kunder.add(aftale);
        saveFile();
        System.out.println("Aftale oprettet.");

    }

    public void aflysAftale() {

    }

    public void manglendeBetaling() {

    }

    public void betalingsOversigt() {

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


    private void run() {
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
        while(kørProgram) {
            menu.printMenu();
            int brugervalg = menu.brugerensValg();

            switch (brugervalg) {
                case 1:
                    ledigeTider();
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