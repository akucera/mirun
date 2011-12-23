/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.HashMap;
import java.util.Map;

import compiler.AddressResolver;

/**
 * Tabulka symbolu.
 */
public class SymTab {
	
	private SymTab parentTab;
	
	public SymTab(SymTab parentTab) {
		this.parentTab = parentTab;
	}
	
    /**
     * Tabulka promennych.
     */
    private Map<String, VariableTree> ids = new HashMap<String, VariableTree>();

    /**
     * Vlozi promennou do tabulky.
     */
    public void insert(VariableTree var) {
    	if (var.getType() != Type.STRINGVAR) {
    		var.setAddress(AddressResolver.getInstance().getFreeAddress());
    	}
   		Type t = var.getType();
   		String n = var.getName();
   		ids.put(n.toLowerCase(), var);
    }

    /**
     * Vrati promennou z tabulky symbolu.
     */
    public VariableTree find(String name) {
    	VariableTree retTree = ids.get(name.toLowerCase());
    	if ((retTree == null) && (parentTab != null)) {
    		retTree = parentTab.find(name);
    	}
        return retTree;
    }

    /**
     * Vrati true, pokud tabulka obsahuje identifikator name.
     */
    public boolean contains(String name) {
        return ids.containsKey(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "SymTab: " + ids;
    }
}
