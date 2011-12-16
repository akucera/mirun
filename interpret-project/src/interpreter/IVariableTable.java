package interpreter;

import exception.VariableNotFoundException;

public interface IVariableTable {

	public void setVariable(Integer address, Object o);
	
	public Object getValue(Integer address) throws VariableNotFoundException;
	
	public boolean hasVariable(Integer address);
	
	// TODO implementovat odstraneni promenne jako instrukci - tupa nahrada garbage collection
	public void deleteVariable(Integer address);
	
}
