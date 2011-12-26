package bytecode.instruction;

import java.util.StringTokenizer;

import bytecode.Util;

/**
 * Trida pro generickou obecnou instrukci, ktera muze a nemusi za sebou mit jeden integer (vetsinou adresa)
 * @author lukaskukacka
 *
 */
public class GenericInstruction implements IInstruction {

	private byte instrByte;
	private int length = 1;
	private Integer paramInt = null;
	
	/**
	 * Konstruktor, ktery sestavi instrukci z jejiho bytu a radku instrukce
	 * @param instrByte
	 * @param instrLine
	 */
	public GenericInstruction(byte instrByte, String instrLine) {
		this.instrByte = instrByte;
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		st.nextToken();
		
		if(st.hasMoreTokens()) {
			try {
				this.paramInt = new Integer(Integer.parseInt(st.nextToken()));
			} catch (NumberFormatException e) {
				this.paramInt = null;
			}
		}
		
		if(paramInt != null) this.length += 4;	// kdyz mam za sebou parametr cislo (vetsinou adresa), delka je o 4 vetsi
	}
	
	/**
	 * Vytvori instrukci primo z byte a integeru
	 * @param instrByte
	 * @param paramInt
	 */
	public GenericInstruction(byte instrByte, Integer paramInt) {
		this.instrByte = instrByte;
		this.length = 1;
		if(paramInt != null) this.length += 4;
		this.paramInt = paramInt;
	}
	
	@Override
	public byte[] getBytes() {
		byte[] arr = new byte[length];
		
		arr[0] = instrByte;
		
		if(arr.length == 5) {
			byte[] intByte = Util.intToByteArray(paramInt.intValue());
			System.arraycopy(intByte, 0, arr, 1, intByte.length);
		}
		
		return arr;
	}

	@Override
	public int getLength() {
		return length;
	}


}
