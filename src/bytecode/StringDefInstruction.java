package bytecode;

public class StringDefInstruction implements IInstruction {

	private byte[] stringBytes;
	public static final byte START_BYTE = (byte)0xCD;
	public static final byte END_BYTE = (byte)0xCE;
	
	public StringDefInstruction(String instrLine) {
		
		int messageStart = instrLine.indexOf(" ")+1;
		String message = instrLine.substring(messageStart, instrLine.length()-1);
		
		stringBytes = message.getBytes();
	}

	@Override
	public byte[] getBytes() {
		byte[] arr = new byte[getLength()];
		arr[0] = START_BYTE;
		System.arraycopy(stringBytes, 0, arr, 1, arr.length);
		arr[arr.length-1] = END_BYTE;
		return arr;
	}

	@Override
	public int getLength() {
		return stringBytes.length + 2;
	}
	
}
