package utils;

import main.Main;

public class Util {

	public static void debugMsg(String str) {
		if(Main.DEBUG)
			System.out.println(str);
	}

	public static final byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}

	public static final int byteArrayToInt(byte[] b) {
		// System.out.println(b.length);
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
				+ (b[3] & 0xFF);
	}

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
	
	public static String toString(Integer[] arr, String delim) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < arr.length; i++) {
			if(i!=0) sb.append(delim);
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	/*
	 * Pretypovani pro vypis. Neni to moc pekne, ale nevim, jak to jinak udelat
	 */
	public static String toString(Object o, String delim) {
		if(o.getClass() == Integer[].class) return toString((Integer[])o, delim);
		return o.toString();
	}

}
