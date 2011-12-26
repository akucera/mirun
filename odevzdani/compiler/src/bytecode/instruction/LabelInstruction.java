package bytecode.instruction;

/**
 * Instrukce reprezentujici label. Label se v bytecodu fyzicky neobjevi, je jen v instrukcich. V bytecodu se skace primo na pozici v bytech, ne na navesti.
 * @author lukaskukacka
 *
 */
public class LabelInstruction implements IInstruction {

	private String name = null;
	
	public LabelInstruction(String instrLine) {
		this.name = instrLine.substring(0, instrLine.lastIndexOf(":"));
	}
	
	public String getName() {
		return name;
	}

	@Override
	public byte[] getBytes() {
		return null;
	}

	@Override
	public int getLength() {
		return 0;
	}

}
