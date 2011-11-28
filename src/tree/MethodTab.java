/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tree.MethodDeclarationTree.ReturnType;

/**
 * Tabulka symbolu.
 */
public class MethodTab {

    /**
     * Tabulka metod.
     */
    private Map<String, ReturnType> ids = new HashMap<String, ReturnType>();
    private Map<String, List<Type>> idr = new HashMap<String, List<Type>>();
    

    /**
     * Vlozi metodu do tabulek (navratoveho typu a typu promenych).
     */
    public void insert(String name, ReturnType ret, List<Type> paramTypes) {
        ids.put(name.toLowerCase(), ret);
        idr.put(name.toLowerCase(), paramTypes);
    }

    /**
     * Vrati navratovy typ metody.
     */
    public ReturnType find(String name) {
        return ids.get(name.toLowerCase());
    }

    /**
     * Vrati seznam typu parametru.
     */
    public List<Type> findParamTypes(String name) {
        return idr.get(name.toLowerCase());
    }

    /**
     * Vrati true, pokud tabulka obsahuje identifikator name.
     */
    public boolean contains(String name) {
        return ids.containsKey(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "MethodTab: " + ids;
    }
}
