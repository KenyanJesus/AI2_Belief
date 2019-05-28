
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        belief();
    }

    public static void belief() {
        BeliefeBase bb = new BeliefeBase();

        System.out.println("PRESS 1 TO ADD CNF");
        System.out.println("PRESS 2 TO CHECK FORMULA USING PROPOSITIONAL LOGIC");
        System.out.println("PRESS 3 TO PRINT BELIEFBASE");
        System.out.println("PRESS 9 TO CLOSE PROGRAM");

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.nextLine();
        bb.addtoBB("p|q");
        for (; ; ) {
            switch (input) {
                case 1:
                    System.out.println("Input sentence in CNF: ");
                    String input2 = sc.nextLine();
                    CNF cnf = new CNF(input2);
                    if (bb.beliefBase.isEmpty()) {
                        System.out.println("added " + input2 + " to the base");
                        bb.addtoBB(input2);
                    }
                    break;
                case 2:
                    System.out.println("Seperat literals by |, e.g. q|r");
                    System.out.println("INPUT FIRST CLAUSE");
                    Clause c1 = new Clause(sc.nextLine());
                    System.out.println("INPUT SECOND CLAUSE");
                    Clause c2 = new Clause(sc.nextLine());
                    bb.propResolve(c1, c2);
                    break;

                case 3:
                    bb.printBB();
                    break;

                case 9:
                    System.exit(0);

                default:
                    break;
            }
        }
    }
}