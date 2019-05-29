
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeliefeBase {

    private CNF cnf;
    ArrayList<Object> beliefBase = new ArrayList<>();


    public void addtoBB(String input){
        if (isCNF(input)){
            cnf = new CNF(input);
            beliefBase.add(cnf);
        }
    }

    public ArrayList<Clause> getClausesBB(ArrayList<Object> bb){
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        for(Object obj: beliefBase) {
            if(obj.getClass().equals(CNF.class)) {
                cnf = (CNF) obj;
                clauses.addAll(cnf.getClauses());
            }
        }
        return clauses;
    }



    public void printBB(){
        int bBLength = beliefBase.size();
        Object obj = (Object) beliefBase;
        for (int i = 0; i<bBLength;i++) {
            if (obj.getClass().equals(CNF.class)) {
                cnf = (CNF) obj;
                System.out.println(cnf.getCNF());
            }
        }
    }


    public  ArrayList<Clause> propResolve(Clause c1, Clause c2){
        boolean resolve = false;
        Clause tc1 = new Clause(c1);
        Clause tc2 = new Clause(c2);
        ArrayList<Clause> arr = new ArrayList<>();
        Set<String> arr2 = new HashSet<String>();
        StringBuilder newSB = new StringBuilder();

            for (Literal tmp1: c1.literals ){
                for (Literal tmp2: c2.literals){
                    if (tmp1.symbol.charAt(0) == '!' && tmp1.symbol.charAt(1) == tmp2.symbol.charAt(0)){
                        resolve = true;
                    }
                    else if (tmp2.symbol.charAt(0) == '!' && tmp2.symbol.charAt(1) == tmp1.symbol.charAt(0)){
                        resolve = true;
                    }

                if (resolve){

                    tc1.clause = tc1.clause.replace(tmp1.symbol, "");
                    tc2.clause = tc2.clause.replace(tmp2.symbol, "");


                    for (int i = 0; i<tc1.literals.size(); i++){
                        if (tc1.literals.get(i).symbol.equals(tmp1.symbol)){
                            tc1.literals.remove(i);
                        }
                    }
                    for (int j = 0; j <tc2.literals.size(); j++){
                        if (tc2.literals.get(j).symbol.equals(tmp2.symbol)){
                            tc2.literals.remove(j);
                        }
                    }

                    for (Literal lit1: tc1.literals){
                        arr2.add(lit1.symbol);
                    }
                    for (Literal lit2: tc2.literals){
                        arr2.add(lit2.symbol);
                    }

                    System.out.println(arr2);

                    for (String strings : arr2){
                        newSB.append(strings);
                        if (arr2.size()>1){
                            newSB.append("|");
                        }
                    }

                    Clause temporary = new Clause(newSB.toString());
                    arr.add(temporary);
                    resolve = false;
                }
            }
    }
        return arr;
    }

    private boolean isCNF(String sentence) {
        Pattern pattern;
        pattern = Pattern.compile("([!]?[a-zA-Z](\\s?[|]\\s?([!]?[a-zA-Z]))+)|(([!]?[a-zA-Z]\\s?)|([(]([!]?[a-zA-Z](\\s?[|]\\s?[!]?[a-zA-Z])+)[)]\\s?))([&]\\s?(([!]?[a-zA-Z]\\s?)|([(]([!]?[a-zA-Z](\\s?[|]\\s?[!]?[a-zA-Z])+)[)]\\s?)))*");
        Matcher matcher = pattern.matcher(sentence);
        return matcher.matches();
    }
}