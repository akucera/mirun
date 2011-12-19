package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstTab {

	/**
     * Tabulka konstant.
     */
    private List<String> id = new ArrayList<String>();
    private VariableTree treeToBeMatched;
    
    public void insert(String value) {
    	if (id.contains(value)) {
    		return;
    	}
    	id.add(value);
    	if (treeToBeMatched != null) {
    		treeToBeMatched.setAddress(id.indexOf(value));
    		treeToBeMatched = null;
    	}
    }
	
    public boolean contains(String value) {
    	return id.contains(value);
    }
    
    public int getAddress(String value) {
    	return id.indexOf(value);
    }
    
    public void matchVariableToConstant(VariableTree varTree) {
    	treeToBeMatched = varTree;
    }
    
    public void genereta(Context ctx) {
    	for (String value : id) {
    		ctx.println("constdef " + id.indexOf(value) + " " + value);
    	}
    }
}
