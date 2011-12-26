package interpreter;

import interpreter.staticmethod.StaticMethod;

import java.util.HashMap;
import java.util.Map;

import exception.MethodNotFoundException;
import exception.MethodRedefinitionException;

public class MethodTable {

	private Map<Integer, StaticMethod> methodTable;

	public MethodTable() {
		this.methodTable = new HashMap<Integer, StaticMethod>();
	}

	public void registerMethod(Integer address, StaticMethod method)
			throws MethodRedefinitionException {

		if (methodTable.containsKey(address))
			throw new MethodRedefinitionException(
					"Method already defined for address " + address + ": ");
		
		methodTable.put(address, method);
	}

	public StaticMethod method(Integer address) throws MethodNotFoundException {

		if (!hasMethod(address))
			throw new MethodNotFoundException("No method found with address "
					+ address);

		return methodTable.get(address);
	}

	public boolean hasMethod(Integer address) {
		return methodTable.containsKey(address);
	}

}
