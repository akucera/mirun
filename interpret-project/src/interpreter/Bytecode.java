package interpreter;

import java.io.File;
import java.io.IOException;

import exception.BytecodeOverflowException;

import utils.FileUtil;
import utils.Util;

public class Bytecode {
	
	public static final int INT_LENGTH = 4;
	
	private byte[] bytes;
	private int position = 0;
	
	/**
	 * Vytvori bytecode z pole bajtu
	 * @param bytes
	 */
	public Bytecode(byte[] bytes) {
		this.bytes = bytes;
	}
	
	/**
	 * Nacte bajty ze souboru a vlozi je do pole.
	 * Pokud se nacitani bytu neporadi, program skonci chybou.
	 * @param f
	 */
	public Bytecode(File f) {
		try {
			this.bytes = FileUtil.getBytesFromFile(f);
		} catch (IOException e) {
			System.err.println("Bytecode error:\n\t"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * accessory vlastnosti 
	 * ----------------------------------------------
	 */ 
	public int size() {
		return bytes.length;
	}
	
	public int position() {
		return position;
	}
	
	/*
	 * metody pro operace s bytecodem
	 * ----------------------------------------------
	 */
	public byte nextByte() throws BytecodeOverflowException {
		
		if(position >= size()) throw new BytecodeOverflowException("Bytecode overflow - interpreter tried to read bytes after end of bytes array");
		
		return bytes[position++];
	}
	
	public byte nextInstruction() throws BytecodeOverflowException {
		return nextByte();
	}
	
	public int nextInt() throws BytecodeOverflowException {
		if(position + INT_LENGTH > size()) throw new BytecodeOverflowException("Bytecode overflow - interpreter tried to read bytes after end of bytes array");
		
		// vytvor integer ze 4 nasledujich bytu
		byte[] intBytes = bytesSubarray(position, INT_LENGTH);
		position += INT_LENGTH;
		
		return Util.byteArrayToInt(intBytes);
	}
	
	public Integer nextInteger() throws BytecodeOverflowException {
		return new Integer(nextInt());
	}
	
	public void jumpTo(int newPosition) {
		this.position = newPosition;
	}
	
	/**
	 * vytvori vyrez pole od indexu start s delkou length
	 * @param start
	 * @param length
	 * @return
	 */
	private byte[] bytesSubarray(int start, int length) {
		byte[] intBytes = new byte[length];
		for(int i = 0; i < intBytes.length; i++) {
			intBytes[i] = bytes[start+i];
		}
		return intBytes;
	}

}
