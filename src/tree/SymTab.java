/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Tabulka symbolu.
 */
public class SymTab {

    /**
     * Prvni volny slot (ve slotu 0 je args).
     */
    private int freeSlot = 1;

    /**
     * Tabulka promennych.
     */
    private Map<String, VariableTree> ids = new HashMap<String, VariableTree>();

    /**
     * Vlozi promennou do tabulky.
     */
    public void insert(VariableTree var) {
        var.setSlot(freeSlot);
        Type t = var.getType();
        freeSlot += t.getSize();
        String n = var.getName();
        ids.put(n.toLowerCase(), var);
    }

    /**
     * Vytvori misto pro docasnou promennou.
     */
    public int allocTempVar(Type type) {
        int s = freeSlot;
        freeSlot += type.getSize();
        return s;
    }

    /**
     * Vrati promennou z tabulky symbolu.
     */
    public VariableTree find(String name) {
        return ids.get(name.toLowerCase());
    }

    /**
     * Vrati true, pokud tabulka obsahuje identifikator name.
     */
    public boolean contains(String name) {
        return ids.containsKey(name.toLowerCase());
    }

    /**
     * Nastavi 1. volny slot (vyuzivano u metod)
     * @param slot
     */
    public void setFreeSlot(int slot) {
        this.freeSlot = slot;
    }

    /**
     * Vrati pocet pouzitych slotu.
     */
    public int getSize() {
        return freeSlot;
    }

    @Override
    public String toString() {
        return "SymTab: " + ids;
    }
}
