package bytecode.instruction;

import java.util.StringTokenizer;

import tree.MethodTab;

import bytecode.Util;

public class CallInstruction implements IInstruction {

	private Integer address = null;
	private String methodName = null;
	public static final byte START_BYTE = (byte)0xCA;
	
	public CallInstruction(String instrLine, MethodTab staticMethodsTab) {
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		
		st.nextToken();
		methodName = st.nextToken();
		address = staticMethodsTab.methodNumber(methodName.toLowerCase());
	}

	@Override
	public byte[] getBytes() {
		byte[] arr = new byte[getLength()];
		arr[0] = START_BYTE;
		byte[] addrBytes = Util.intToByteArray(address);
		System.arraycopy(addrBytes, 0, arr, 1, addrBytes.length);
		return arr;
	}

	@Override
	public int getLength() {
		return 5;	// instrukce + int adresa
	}
	
}
