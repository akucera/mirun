package interpreter;

import java.util.HashMap;
import java.util.Map;

import exception.VariableNotFoundException;

public class VariablesTable {

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
	
}
