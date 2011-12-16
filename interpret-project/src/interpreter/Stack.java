package interpreter;

import exception.EmptyStackPopException;
import exception.StackOverflowException;

public class Stack {

	private int itemsCount;
	
	private int position;
	private Object[] arr;
	
	public Stack(int maxSize) {
		this.itemsCount = 0;
		this.position = 0;
		this.arr = new Object[maxSize];
	}
	
	public Object pop() throws EmptyStackPopException {
		if(position == 0) throw new EmptyStackPopException("Trying to pop from empty stack");
		
		Object o = arr[--position];
		arr[position+1] = null;
		
		return o;
	}
	
	public Object top() throws EmptyStackPopException {
		if(position == 0) throw new EmptyStackPopException("Trying to look to top of empty stack");
		return arr[position-1];
	}
	
	public void push(Object o) throws StackOverflowException {
		if(this.size() >= capacity()) throw new StackOverflowException("Pushing to full stack (max stack size = " + capacity());
		arr[position++] = o;
	}
	
	public int size() {
		return itemsCount;
	}
	
	public int capacity() {
		return arr.length;
	}
	
	public void printStack() {
		System.out.println("Stack content:");
		Object o = null;
		
		for (int i = 0; i < position; i++) {
			o = arr[i];
			// tohle je jen pro debug, v kodu nebude pouzito
			// nevhodne, protoze pouziva typecast
			
			System.out.print("  "+i+":\t");
			
			if(o.getClass() == Integer.class) {
				System.out.println(((Integer)arr[i]).intValue());
			} else if(o.getClass() == String.class) {
				System.out.println(((String)o).toString());
			}
			
		}
	}
	
}
