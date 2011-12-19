package bytecode;

import java.util.StringTokenizer;

/**
 * Trida pro generickou obecnou instructi, ktera muze za sebou mit jeden integer
 * @author lukaskukacka
 *
 */
public class GenericInstruction implements IInstruction {

	private byte instrByte;
	private int length;
	private Integer paramInt = null;
	
	public GenericInstruction(byte instrByte, String instrLine) {
		this.instrByte = instrByte;
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		st.nextToken();
		
		if(st.hasMoreTokens()) {
			try {
				this.paramInt = new Integer(st.nextToken());
			} catch (NumberFormatException e) {
				this.paramInt = null;
			}
		}
		if(paramInt != null) this.length += 4;
	}
	
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
