package interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.Main;

import utils.Util;

import exception.ConstantRedefinitionException;
import exception.GlobalVariableTableDestroyException;
import exception.VariableNotFoundException;

public class Environment implements IVariableTable {

	public static final int GLOBAL_DEPTH = 0;

	private List<VariablesTable> varTablesList;
	private VariablesTable constantTable;
	private int position = 0;

	public Environment() {
		this.constantTable = new VariablesTable();

		this.varTablesList = new ArrayList<VariablesTable>();

		VariablesTable globalVarTable = new VariablesTable();
		this.varTablesList.add(position++, globalVarTable);
	}

	/**
	 * Metoda je volana pri zanoreni do volane metody Vytvori novou tabulku
	 * promennych pro metodu a vlozi ji na vrchol zasobniku tabulek
	 */
	public void pushLevel() {
		varTablesList.add(position++, new VariablesTable());
	}

	/**
	 * Metoda je volana pri navratu z metody. Smaze nejvyssi tabulku promennych
	 * ze zasobniku.
	 * 
	 * @throws GlobalVariableTableDestroyException
	 */
	public void popLevel() throws GlobalVariableTableDestroyException {
		if (position == 0)
			throw new GlobalVariableTableDestroyException(
					"Trying to remove global variables tables from environment");
		
		if(Main.DEBUG) printMemory();
		varTablesList.remove(--position);
	}

	private VariablesTable topTable() {
		return varTablesList.get(position - 1);
	}

	@Override
	public void setVariable(Integer address, Object o) {
		topTable().setVariable(address, o);
	}

	@Override
	public Object getValue(Integer address) throws VariableNotFoundException {
		//printMemory();

		Iterator<VariablesTable> it = variableTablesNegativeIterator();
		VariablesTable table;
		int level = varTablesList.size() - 1;

		while (it.hasNext()) {
			table = it.next();
			if (table.hasVariable(address)) {
				Util.debugMsg("  Variable on adress " + address
						+ " found in level " + level);
				return table.getValue(address);
			}
			level--;
		}
		throw new VariableNotFoundException("   Variable with address "
				+ address + " not found in any variable table");
	}

	@Override
	public boolean hasVariable(Integer address) {

		Iterator<VariablesTable> it = variableTablesNegativeIterator();
		VariablesTable table;

		while (it.hasNext()) {
			table = it.next();
			if (table.hasVariable(address))
				return true;
		}

		return false;
	}

	public Object getConstant(Integer address) throws VariableNotFoundException {
		return constantTable.getValue(address);
	}

	public void defineConstant(Integer address, Object o) throws ConstantRedefinitionException, VariableNotFoundException {
		
		// kdyz uz konstanta existuje, vyhod vyjimku, protoze konstantu po prvni definici nelze menit
		if(constantTable.hasVariable(address)) {
			throw new ConstantRedefinitionException("Bytecode tried to redefine constant at address "+address+" (already set to \""+constantTable.getValue(address).toString()+"\")");
		}
		constantTable.setVariable(address, o);
	}

	@Override
	public void deleteVariable(Integer address) {
		// TODO Auto-generated method stub

	}

	/**
	 * Vraci nula (GLOBAL_DEPTH), pokud jsem na globalni urovni (globalni
	 * tabulka promennych) S kazdou volanou metodou se zvysi o 1
	 * 
	 * @return
	 */
	public int depth() {
		return varTablesList.size() - 1;
	}

	private Iterator<VariablesTable> variableTablesNegativeIterator() {
		return new VariableTablesIterator(varTablesList, false);
	}

	private Iterator<VariablesTable> variableTablesPositiveIterator() {
		return new VariableTablesIterator(varTablesList, true);
	}

	public void printMemory() {
		Iterator<VariablesTable> it = variableTablesPositiveIterator();
		VariablesTable table;
		int level = 0;

		System.out.println("\n=== Memory variable tables ==========================");

		System.out.println("-- Constant table -----------------------------------");
		constantTable.printMemory();
		
		while (it.hasNext()) {
			System.out.println("---- Table "+level+" ----------------------------------------");
			table = it.next();
			table.printMemory(level++);
		}
	}

}
