package bytecode;

public class Util {

	/**
	 * Prevadi int na pole byte
	 * @param value	int, ktery ma byt preveden
	 * @return	pole bytu reprezentujici integer jako byty
	 */
	public static final byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}

	/**
	 * Prevede pole bytu na int
	 * @param b pole, ktere ma byt prevedeno na int
	 * @return	int cislo vytvorene z pole
	 */
	public static final int byteArrayToInt(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
				+ (b[3] & 0xFF);
	}
	
	/**
	 * Vytvori textovou reprezentaci jednotliveho bytu
	 * @param b byte
	 * @return	String reprezentace ve tvaru XX
	 */
	public static String toHexString(byte b) {
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[2];
		int v;
		v = b & 0xFF;
		hexChars[0] = hexArray[v / 16];
		hexChars[1] = hexArray[v % 16];
		return new String(hexChars);
	}

	/**
	 * Prevede pole bytu na String citelny hex retezec
	 * @see toHexString(byte b)
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v / 16];
			hexChars[j * 2 + 1] = hexArray[v % 16];
		}
		return new String(hexChars);
	}
	
}
