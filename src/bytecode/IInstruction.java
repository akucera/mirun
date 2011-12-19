package bytecode;

public interface IInstruction {

	public static final String POP_INSTR_NAME = "pop";
	public static final String PUSHC_INSTR_NAME = "pushc";
	public static final String PUSHV_INSTR_NAME = "pushv";
	public static final String PUSHSC_INSTR_NAME = "pushsc";
	public static final String ARRDEF_INSTR_NAME = "arrdef";
	public static final String ARRPOP_INSTR_NAME = "arrpop";
	public static final String ARRPUSH_INSTR_NAME = "arrpush";
	public static final String BADD_INSTR_NAME = "badd";
	public static final String BSUB_INSTR_NAME = "bsub";
	public static final String BMUL_INSTR_NAME = "bmul";
	public static final String MJMP_INSTR_NAME = "mjmp";
	public static final String MRET_INSTR_NAME = "mret";
	public static final String JMP_INSTR_NAME = "jmp";
	public static final String JEQ_INSTR_NAME = "jeq";
	public static final String JNEQ_INSTR_NAME = "jneq";
	public static final String JLT_INSTR_NAME = "jlt";
	public static final String JGT_INSTR_NAME = "jgt";
	public static final String JELT_INSTR_NAME = "jelt";
	public static final String JEGT_INSTR_NAME = "jegt";
	public static final String LAB_INSTR_NAME = "lab";
	public static final String CALL_INSTR_NAME = "call";
	public static final String CONSTDEF_INSTR_NAME = "constdef";
	public static final String STOP_INSTR_NAME = "stop";
	
	public static final byte POP_INSTR = (byte) 0x01;
	public static final byte PUSHC_INSTR = (byte) 0x11;
	public static final byte PUSHV_INSTR = (byte) 0x12;
	public static final byte PUSHSC_INSTR = (byte) 0x13;
	public static final byte ARRDEF_INSTR = (byte) 0x20;
	public static final byte ARRPOP_INSTR = (byte) 0x2A;
	public static final byte ARRPUSH_INSTR = (byte) 0x2F;
	public static final byte BADD_INSTR = (byte) 0x30;
	public static final byte BSUB_INSTR = (byte) 0x31;
	public static final byte BMUL_INSTR = (byte) 0x32;
	public static final byte MJMP_INSTR = (byte) 0x40;
	public static final byte MRET_INSTR = (byte) 0x4F;
	public static final byte JMP_INSTR = (byte) 0x50;
	public static final byte JEQ_INSTR = (byte) 0x5A;
	public static final byte JNEQ_INSTR = (byte) 0x5B;
	public static final byte JLT_INSTR = (byte) 0x5C;
	public static final byte JGT_INSTR = (byte) 0x5D;
	public static final byte JELT_INSTR = (byte) 0x5E;
	public static final byte JEGT_INSTR = (byte) 0x5F;
	public static final byte LAB_INSTR = (byte) 0xA0;
	public static final byte CALL_INSTR = (byte) 0xCA;
	public static final byte CONSTDEF_INSTR = (byte) 0xCD;
	public static final byte STOP_INSTR = (byte) 0xFF;

	public byte[] getBytes();

	public int getLength();
}
