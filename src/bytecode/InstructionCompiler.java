package bytecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import bytecode.instruction.ConstDefInstruction;
import bytecode.instruction.GenericInstruction;
import bytecode.instruction.GenericJmpInstruction;
import bytecode.instruction.IInstruction;
import bytecode.instruction.LabelInstruction;

public class InstructionCompiler {

	private String instrString;
	private List<IInstruction> instrList;
	private Map<String, Integer> labTable;
	private int byteCodeLength;

	public InstructionCompiler(String instrString) {
		System.out.println(instrString);
		this.instrString = instrString;
		this.instrList = new ArrayList<IInstruction>();
		this.labTable = new HashMap<String, Integer>();
	}

	public byte[] generate() {
		generateInstructionList();
		processLabels();

		byte[] outArr = new byte[byteCodeLength];
		byte[] arr;
		int position = 0;
		for (IInstruction instr : instrList) {
			if (instr.getLength() != 0) {
				arr = instr.getBytes();
				System.arraycopy(arr, 0, outArr, position, arr.length);
				position += arr.length;
			}
			// if(arr != null)
			// System.out.println(new String(instr.getBytes()));
		}

		return outArr;
	}

	private void processLabels() {

		// napocitam si pozice labelu v bytecodu
		int position = 0;
		for (IInstruction instr : instrList) {
			// kdyz najdu label
			if (instr.getClass() == LabelInstruction.class) {
				String labelName = ((LabelInstruction) instr).getName();
				labTable.put(labelName, new Integer(position));
				System.out.println("Label " + labelName + " addr: " + position);
			}
			position += instr.getLength();
		}
		byteCodeLength = position;

		// projdu znovu instrukce a nastavim vsem jumpum adresu labelu
		for (IInstruction instr : instrList) {
			if (instr.getClass() == GenericJmpInstruction.class) {
				GenericJmpInstruction jmpInstr = (GenericJmpInstruction) instr;
				String targetLabel = jmpInstr.getTargetLabel();
				Integer address = labTable.get(targetLabel);
				jmpInstr.setAddress(address);
			}
		}
	}

	private void generateInstructionList() {
		StringTokenizer st = new StringTokenizer(instrString, "\r\n");
		String line;
		IInstruction instruction;
		while (st.hasMoreTokens()) {
			line = st.nextToken();
			instruction = instructionFromLine(line);
			if (instruction != null)
				instrList.add(instruction);
		}
	}

	private IInstruction instructionFromLine(String line) {
		// odstranit komentare
		if (line.startsWith(";"))
			return null;

		if (line.startsWith(IInstruction.POP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.POP_INSTR, line);
		}
		if (line.startsWith(IInstruction.PUSHC_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.PUSHC_INSTR, line);
		}
		if (line.startsWith(IInstruction.PUSHV_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.PUSHV_INSTR, line);
		}
		if (line.startsWith(IInstruction.PUSHSC_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.PUSHSC_INSTR, line);
		}
		if (line.startsWith(IInstruction.ARRDEF_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.ARRDEF_INSTR, line);
		}
		if (line.startsWith(IInstruction.ARRPOP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.ARRPOP_INSTR, line);
		}
		if (line.startsWith(IInstruction.ARRPUSH_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.ARRPUSH_INSTR, line);
		}
		if (line.startsWith(IInstruction.BADD_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.BADD_INSTR,
					(Integer) null);
		}
		if (line.startsWith(IInstruction.BSUB_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.BSUB_INSTR,
					(Integer) null);
		}
		if (line.startsWith(IInstruction.BMUL_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.BMUL_INSTR,
					(Integer) null);
		}
		if (line.startsWith(IInstruction.MJMP_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.MJMP_INSTR, line);
		}
		if (line.startsWith(IInstruction.MRET_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.MRET_INSTR,
					(Integer) null);
		}
		if (line.startsWith(IInstruction.JMP_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JMP_INSTR, line);
		}
		if (line.startsWith(IInstruction.JEQ_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JEQ_INSTR, line);
		}
		if (line.startsWith(IInstruction.JNEQ_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JNEQ_INSTR, line);
		}
		if (line.startsWith(IInstruction.JLT_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JLT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JGT_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JGT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JELT_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JELT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JEGT_INSTR_NAME)) {
			return new GenericJmpInstruction(IInstruction.JEGT_INSTR, line);
		}
		if (line.startsWith(IInstruction.CALL_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.CALL_INSTR, line);
		}
		if (line.startsWith(IInstruction.CONSTDEF_INSTR_NAME)) {
			return new ConstDefInstruction(line);
		}
		if (line.startsWith(IInstruction.STOP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.STOP_INSTR,
					(Integer) null);
		}
		if (line.endsWith(":")) {	// jedina instrukce LABEL  je jedno jak zacina, ale musi koncit dvojteckou
			return new LabelInstruction(line);
		}
		return null;
	}
}
