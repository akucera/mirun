package bytecode.instruction;

import java.util.StringTokenizer;

import bytecode.Util;

/**
 * Trida reprezentujici obecnou instrukci JUMP. Vsechny jump instrukce jsou stejne, maji tvar "JXXX adresa", takze pro ne staci jedna instrukce
 * @author lukaskukacka
 *
 */
public class GenericJmpInstruction implements IInstruction {
	
	private byte instrByte;
	private int address;
	private String targetLabel = null;
	
	public GenericJmpInstruction(byte instrByte, String instrLine) {
		
		this.instrByte = instrByte;
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		
		int word = 0;
		String token;
		while(st.hasMoreTokens()) {
			token = st.nextToken();
			
			if(word == 0) { // prvni token - nazev instrukce
				word++;
				continue;
			}
			else if(word == 1) {
				targetLabel = token;
				break;
			}
		}
	}

	@Override
	public byte[] getBytes() {
		byte[] arr = new byte[getLength()];
		arr[0] = instrByte;
		byte[] addrArr = Util.intToByteArray(address);
		System.arraycopy(addrArr, 0, arr, 1, addrArr.length);
		return arr;
	}

	@Override
	public int getLength() {
		return 5;
	}

	public String getTargetLabel() {
		return targetLabel;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
}
