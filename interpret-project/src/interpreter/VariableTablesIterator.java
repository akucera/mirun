package interpreter;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * tabulky promennych je treba prochazet odzadu, abych sel od nejhlubsi tabulky smerem ke globalni
 * @author lukaskukacka
 *
 */
public class VariableTablesIterator implements Iterable<VariablesTable>, Iterator<VariablesTable> {

	private int position;
	private List<VariablesTable> varTablesList;
	private boolean positive;
	
	public VariableTablesIterator(List<VariablesTable> varTablesList, boolean positive) {
		this.varTablesList = varTablesList;
		this.positive = positive;
		
		if(this.positive) this.position = 0;
		else this.position = varTablesList.size()-1;
	}
	
	@Override
	public boolean hasNext() {
		if(this.positive) return position < varTablesList.size();
		return position > 0;
	}

	@Override
	public VariablesTable next() {
		if(!hasNext()){
            throw new NoSuchElementException();
		}
		
		if(this.positive) return varTablesList.get(position++);
		else return varTablesList.get(position--);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<VariablesTable> iterator() {
		return this;
	}

}
