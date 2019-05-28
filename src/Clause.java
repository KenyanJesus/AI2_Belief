import java.util.ArrayList;

public class Clause {
    String clause;
    ArrayList<Literal> literals = new ArrayList<Literal>();

    Clause(Clause c){
        this.clause = c.clause;
        for (Literal l: c.literals) {
            this.literals.add(new Literal(l));
        }
    }

    Clause(String literal) {
        this.clause = literal;
        parseLiteral();
    }

    private void parseLiteral() {
        String[] arrOfStr = clause.split("[|]+");

        for (String a : arrOfStr) {
            Literal literal = new Literal(a);
            literals.add(literal);
            System.out.println("Literal: " + a);
        }
    }

    public ArrayList<Literal> getLiterals(){
        return literals;
    }

}