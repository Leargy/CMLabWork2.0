package equasions;

import equasions.interfaces.Calculatable;

import java.util.HashMap;
import java.util.Map;

public class EquationLibrary {
    private Map<Integer, Calculatable> equationLibra;

    public EquationLibrary() {
        equationLibra = new HashMap<>();

        equationLibra.put(1, new Polinom());
    }

    public Calculatable getEquationByKey(Integer key) {
        return equationLibra.get(key);
    }

    public String getListOfEquations() {
        String list = "";
        int size = equationLibra.size();

        for(int i = 1; i <= size; i++) {
            list = list.concat("[" + i + "] " + equationLibra.get(i).toString() + "\n");
        }

        return list;
    }

    public int getNumbOfEquations() {
        return equationLibra.size();
    }
}
