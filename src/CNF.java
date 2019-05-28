import org.omg.CORBA.Object;

import java.util.ArrayList;

public class CNF {

    ArrayList<Clause> clauses = new ArrayList<Clause>();
    private String Cnf;

    CNF(String cnf) {
        this.Cnf = cnf;
        parseClauses();
    }

    private void parseClauses() {
        String[] arrOfStr = Cnf.split("[&]+");

        for (String strings : arrOfStr) {
            strings = strings.replaceAll("[()]", "");
            Clause clause = new Clause(strings);
            clauses.add(clause);
        }
    }

    public String getCNFSentence() {
        return Cnf;
    }

    public ArrayList<Clause> getClauses(){
        return clauses;
    }
}