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
	
	public int count() {
		return varMap.size();
	}
	
	public void printMemory() {
		
		//System.out.println("\nVariable table content - "+varMap.size()+" items:\n key\tvalue");
		
		if(varMap.size() == 0) {
			System.out.println("Empty");
			return;
		}
		
		Object o = null;
		Set<Integer> keys = varMap.keySet();
		
		for (Integer key : keys) {
			System.out.print(" " + key + "\t");
			
			o = varMap.get(key);
			
			if(o.getClass() == Integer.class) {
				System.out.println("Integer: " + ((Integer)o).intValue());
			} else if(o.getClass() == Object[].class) {
				System.out.println("Object[]: "+Arrays.toString((Object[])o));
			} else if(o.getClass() == Integer[].class) {
				System.out.println("Integer[]: "+Arrays.toString((Integer[])o));
			} else if(o.getClass() == String.class) {
				System.out.println("String: \"" + ((String)o).toString()+"\"");
			} else {
				System.out.println("Unknown Object: " + o.toString());
			}
			
		}
		
	}
	
	public void printMemory(int level) {
		
		Set<Integer> keys = varMap.keySet();
		Object o = null;
		
		if(varMap.size() == 0) {
			for (int i = 0; i < level; i++) {
				System.out.print("   ");
			}
			System.out.println("Empty");
		}
		
		for (Integer key : keys) {
			for (int i = 0; i < level; i++) {
				System.out.print("   ");
			}
			
			if(keys.size() == 0) {
				System.out.println("Variables table is empty");
				continue;
			}
			
			System.out.print(key + "\t");
			
			o = varMap.get(key);
			
			if(o.getClass() == Integer.class) {
				System.out.println("Integer: " + ((Integer)o).intValue());
			} else if(o.getClass() == Object[].class) {
				System.out.println("Object[]: "+Arrays.toString((Object[])o));
			} else if(o.getClass() == Integer[].class) {
				System.out.println("Integer[]: "+Arrays.toString((Integer[])o));
			} else if(o.getClass() == String.class) {
				System.out.println("String: \"" + ((String)o).toString()+"\"");
			} else {
				System.out.println("Unknown Object: " + o.toString());
			}
			
		}
		
	}
	
}
