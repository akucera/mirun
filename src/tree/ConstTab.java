package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstTab {

	/**
     * Tabulka konstant.
     */
    private Map<String, Integer> ids = new HashMap<String, Integer>();
    private List<String> id = new ArrayList<String>();
    
    public void insert(String value) {
    	if (id.contains(value)) {
    		return;
    	}
    	id.add(value);
    }
	
    public boolean contains(String value) {
    	return id.contains(value);
    }
    
    public int getAddress(String value) {
    	return id.indexOf(value);
    }
    
    public void genereta(Context ctx) {
    	for (String value : id) {
    		ctx.println("constdef " + id.indexOf(value) + " " + value);
    	}
    }
}
