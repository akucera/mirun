package interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exception.VariableNotFoundException;

public class VariablesTable implements IVariableTable {

	private Map<Integer, Object> varMap;
	
	public VariablesTable() {
		this.varMap = new HashMap<Integer, Object>();
	}
	
	public void setVariable(Integer address, Object o) {
		varMap.put(address, o);
	}
	
	public Object getValue(Integer address) throws VariableNotFoundException {
		if(!hasVariable(address)) throw new VariableNotFoundException("Trying to read variable from address "+address+" which is not set");
		return varMap.get(address);
	}
	
	public boolean hasVariable(Integer address) {
		return varMap.containsKey(address);
	}
	
	// TODO implementovat odstraneni promenne jako instrukci - tupa nahrada garbage collection
	public void deleteVariable(Integer address) {
		varMap.remove(address);
	}
	
	public void printMemory() {
		
		System.out.println("\nVariable table content - "+varMap.size()+" items:\n key\tvalue");
		
		Set<Integer> keys = varMap.keySet();
		Object o = null;
		
		for (Integer key : keys) {
			System.out.print(" " + key + "\t");
			
			o = varMap.get(key);
			
			if(o.getClass() == Integer.class) {
				System.out.println("Integer: " + ((Integer)o).intValue());
			} else if(o.getClass() == Object[].class) {
				System.out.println("Integer[]: "+Arrays.toString((Object[])o));
			} else if(o.getClass() == String.class) {
				System.out.println("String: " + ((String)o).toString());
			} else {
				System.out.println("Unknown Object: " + o.toString());
			}
			
		}
		
	}
	
	public void printMemory(int level) {
		
		Set<Integer> keys = varMap.keySet();
		Object o = null;
		
		for (Integer key : keys) {
			for (int i = 0; i < level; i++) {
				System.out.print("   ");
			}
			
			System.out.print(key + "\t");
			
			o = varMap.get(key);
			
			if(o.getClass() == Integer.class) {
				System.out.println("Integer: " + ((Integer)o).intValue());
			} else if(o.getClass() == Object[].class) {
				System.out.println("Integer[]: "+Arrays.toString((Object[])o));
			} else if(o.getClass() == String.class) {
				System.out.println("String: " + ((String)o).toString());
			} else {
				System.out.println("Unknown Object: " + o.toString());
			}
			
		}
		
	}
	
}
