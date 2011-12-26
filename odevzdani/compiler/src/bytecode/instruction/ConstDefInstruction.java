package bytecode.instruction;

import java.util.StringTokenizer;

import bytecode.Util;

public class ConstDefInstruction implements IInstruction {

	private byte[] stringBytes;
	private Integer address = null;
	private String message = null;
	public static final byte START_BYTE = (byte)0xCD;
	public static final byte END_BYTE = (byte)0xCE;
	
	public ConstDefInstruction(String instrLine) {
		
		StringTokenizer st = new StringTokenizer(instrLine, " ");
		
		int word = 0;
		int textPos = 0;	// pocitani pozice v textu, protoze StringTokenizer by ostranil mezeru, ktera se eventuelne muze vyskytovat na konci retezce
		String token;
		while(st.hasMoreTokens()) {
			token = st.nextToken();
			
			if(word == 0) { // prvni token - nazev instrukce
				word++;
				textPos += token.length() +1;
				continue;
			}
			else if(word == 1) {
				address = new Integer(token);
				textPos += token.length() + 1;
			}
			else {
				// zprava je od druhe mezery az do konce, aby to neserezavalo pripadne mezery na konci a vicenasobne mezery mezi textem
				this.message = instrLine.substring(textPos, instrLine.length());
				break;
			}
				
			word++;
			
		}
		stringBytes = this.message.getBytes();
	}

	@Override
	public byte[] getBytes() {
		byte[] arr = new byte[getLength()];
		arr[0] = START_BYTE;
		byte[] addrBytes = Util.intToByteArray(address);
		System.arraycopy(addrBytes, 0, arr, 1, addrBytes.length);
		System.arraycopy(stringBytes, 0, arr, 5, stringBytes.length);
		arr[arr.length-1] = END_BYTE;
		return arr;
	}

	@Override
	public int getLength() {
		return stringBytes.length + 6;	// +1 instrukce, +4 adresa, +1 ukoncovaci znak
	}
	
}
