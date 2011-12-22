package bytecode.instruction;

import java.util.Map;
import java.util.StringTokenizer;

import bytecode.Util;

public class CallInstruction implements IInstruction {

	private Integer address = null;
	private String methodName = null;
	public static final byte START_BYTE = (byte)0xCA;
	
	public CallInstruction(String instrLine, Map<String, Integer> mAddrTable) {
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		
		st.nextToken();
		methodName = st.nextToken();
		address = mAddrTable.get(methodName);
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
