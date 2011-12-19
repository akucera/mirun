package bytecode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InstructionCompiler {

	private String instrString;
	private List<IInstruction> instrList;

	public InstructionCompiler(String instrString) {
		this.instrString = instrString;
		this.instrList = new ArrayList<IInstruction>();
	}

	public byte[] generate() {
		generateInstructionList();
		
		byte[] outArr = new byte[1024*1024];
		byte[] arr;
		int position = 0;
		for (IInstruction instr : instrList) {
			arr = instr.getBytes();
			System.arraycopy(arr, 0, outArr, position, arr.length);
			position += arr.length;
			//if(arr != null)
				//System.out.println(new String(instr.getBytes()));
		}
		
		return outArr;
	}

	private void generateInstructionList() {
		StringTokenizer st = new StringTokenizer(instrString, "\r\n");
		String line;
		IInstruction instruction;
		while (st.hasMoreTokens()) {
			line = st.nextToken();
			instruction = instructionFromLine(line);
			if(instruction != null)
				instrList.add(instruction);
		}
	}

	private IInstruction instructionFromLine(String line) {
		// odstranit komentare
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
			return new GenericInstruction(IInstruction.BADD_INSTR, (Integer)null);
		}
		if (line.startsWith(IInstruction.BSUB_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.BSUB_INSTR, (Integer)null);
		}
		if (line.startsWith(IInstruction.BMUL_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.BMUL_INSTR, (Integer)null);
		}
		if (line.startsWith(IInstruction.MJMP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.MJMP_INSTR, line);
		}
		if (line.startsWith(IInstruction.MRET_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.MRET_INSTR, (Integer)null);
		}
		if (line.startsWith(IInstruction.JMP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JMP_INSTR, line);
		}
		if (line.startsWith(IInstruction.JEQ_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JEQ_INSTR, line);
		}
		if (line.startsWith(IInstruction.JNEQ_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JNEQ_INSTR, line);
		}
		if (line.startsWith(IInstruction.JLT_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JLT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JGT_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JGT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JELT_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JELT_INSTR, line);
		}
		if (line.startsWith(IInstruction.JEGT_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.JEGT_INSTR, line);
		}
		if (line.startsWith(IInstruction.LAB_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.LAB_INSTR, line);
		}
		if (line.startsWith(IInstruction.CALL_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.CALL_INSTR, line);
		}
		if (line.startsWith(IInstruction.CONSTDEF_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.CONSTDEF_INSTR, line);
		}
		if (line.startsWith(IInstruction.STOP_INSTR_NAME)) {
			return new GenericInstruction(IInstruction.STOP_INSTR, (Integer)null);
		}

		return null;
	}
}
