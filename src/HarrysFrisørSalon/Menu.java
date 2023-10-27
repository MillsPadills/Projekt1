package HarrysFrisørSalon;

import java.util.Scanner;

public class Menu {
    private String overskrift;
    private String vælgMulighed;
    private String[] menuIndeks;

    public Menu(String overskrift, String vælgMulighed, String[] menuIndeks) {
        this.overskrift = overskrift;
        this.vælgMulighed = vælgMulighed;
        this.menuIndeks = menuIndeks;
    }

    public void printMenu() {
        String printString = overskrift + "\n";
        for (int i = 0; i < menuIndeks.length; i++)
            printString += menuIndeks[i] + "\n";
        System.out.println("\n" + printString);
    }

        //LAVER NOEGT OM HER!!
    public int brugerensValg() {
        int valg = -1;
        Scanner scanner = new Scanner(System.in);
        boolean valideretValg = false;

        while (!valideretValg) {
            System.out.println(vælgMulighed);
            if (scanner.hasNextInt()) {
                valg = scanner.nextInt();
                valideretValg = true;
            } else {
                scanner.nextLine(); // Scanner bug
            }
        }
        return valg;
    }
}
