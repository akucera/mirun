package compiler;

public class AddressResolver {
	
	private static AddressResolver instance;
	private int freeAddress;
	
	
	private AddressResolver() {
		freeAddress = 0;
	}
	
	public static AddressResolver getInstance() {
		if (instance == null) {
			instance = new AddressResolver();
		}
		return instance;
	}
	
	public int getFreeAddress() {
		return freeAddress++;
	}

}
