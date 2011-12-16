package interpreter;

import java.util.Date;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import utils.Util;
import exception.BytecodeOverflowException;
import exception.EmptyStackPopException;
import exception.InvalidInstructionException;
import exception.StackOverflowException;
import exception.VariableNotFoundException;

/*
 *  ==== SEZNAM INSTRUKCI ====
 * 
 *  pushc [cislo]
 *  	- vlozit konstantu (hodnotu) na zasobnik
 *  
 *  pushv [adresa-cislo]
 *  	- vlozit promennou (adresu) na zasobnik
 *  	- podiva se na tabulku promennych a hodnotu vlozi na zasobnik
 *  
 *  pop [adresa]
 *  	- vyjmuti ze zasobniku a ulozeni na adresu slotu
 *  	- vezme hodnotu z vrcholu zasobniku a ulozi ji na adresu
 *  
 *  arrdef [adresa pole]
 *  	- na zasobniku je pocet polozek
 *  	- po provedeni je pole na teto adrese
 *  
 *  arrpop [adresa pole]
 *  	- vlozi hodnotu do pole na adrese
 *  	- nejvyssi hodnota zas = index v poli
 *  	- 2. nejvyssi hodnota zas = hodnota
 *  
 *  arrpush [adresa pole]
 *  	- z pole na adrese vlozi hodnotu na vrchol zasobniku
 *  	- nejvyssi hodnota zas = index v poli
 *  	-> vlozi hodnotu na zasobniku
 *  
 *  badd
 *  	- scitani (addition)
 *  	- nacte 2 nejvyssi hodnoty ze zasobniku, secte a vysledek vlozi na zasobnik
 *  	
 *  bsub
 *  	- odcitani (substraction)
 *  	- nacte 2 nejvyssi hodnoty ze zasobniku, odecte a vysledek vlozi na zasobnik
 *  
 *  bmul - nasobeni (multiplication)
 *  	- nacte 2 nejvyssi hodnoty ze zasobniku, vynasobi a vysledek vlozi na zasobnik
 *  
 *  v bytecode neni treba
 *  lab1: (aa:, fff4:, ...)
 *  	- navesti (identifikator a dvojtecka)
 *  
 *  mjmp [navesti]
 *  	- skoci na navesti metody
 *  	- vytvori kopii environmentu, vlozi do stacku enviromentu a nastavi callstack
 *  
 *  mret
 *  	- ukonceni metody
 *  	- po zavolani se vraci dle callstacku na pozici callstack+1
 *  
 *  jmp [navesti]
 *  	- nepodmineny skok
 *  
 *  jeq [navesti]
 *  	- podmineny skok
 *  	- skace, kdyz 2 nejvyssi hodnoty na zasobniku jsou stejne
 *  
 *  jneq [navesti]
 *  	- skok, dyz nejsou stejne
 *  
 *  jlt [navesti]
 *  	- skok, kdyz nejvyssi hodnota na zasobniku je mensi nez 2 nejvyssi
 *  
 *  jgt [navesti]
 *  	- skok, kdyz nejvyssi hodnota na zasobniku je vetsi nez 2 nejvyssi
 *  
 *  jelt [navesti]
 *  	- mensi nebo rovno
 *  
 *  jegt
 *  	- vetsi nebo rovno
 *  	
 *  call [adresa v method table]
 *  	- volani staticke metody (metoda interpretru) na adrese v tabulce metod
 *  
 *  stop
 *  	- ukonceni programu
 */

/**
 * Trida reprezentuje samotnou logiku interpreteru
 * 
 * @author lukaskukacka
 * 
 */
public class Interpreter {

	private static final int MAX_STACK_SIZE = 1024;

	protected Bytecode bc;
	private Date startTime;
	private Stack s;
	private VariablesTable varTable;
	private Environment env;

	// seznam instrukci
	/*
	 * Dec: 1 ... Hex: 01 Dec: 17 ... Hex: 11 Dec: 18 ... Hex: 12 Dec: 32 ...
	 * Hex: 20 Dec: 42 ... Hex: 2A Dec: 47 ... Hex: 2F Dec: 48 ... Hex: 30 Dec:
	 * 49 ... Hex: 31 Dec: 50 ... Hex: 32 Dec: 64 ... Hex: 40 Dec: 79 ... Hex:
	 * 4F Dec: 80 ... Hex: 50 Dec: 90 ... Hex: 5A Dec: 91 ... Hex: 5B Dec: 92
	 * ... Hex: 5C Dec: 93 ... Hex: 5D Dec: 94 ... Hex: 5E Dec: 95 ... Hex: 5F
	 * Dec: -96 ... Hex: A0 Dec: -64 ... Hex: C0
	 */
	public static final byte POP_INSTR = (byte) 0x01;
	public static final byte PUSHC_INSTR = (byte) 0x11;
	public static final byte PUSHV_INSTR = (byte) 0x12;
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
	public static final byte CALL_INSTR = (byte) 0xC0;
	public static final byte STOP_INSTR = (byte) 0xFF;

	/**
	 * Kontruktor. Interpreter se vytvari s objekterm bytecodu
	 * 
	 * @param bytecode
	 */
	public Interpreter(Bytecode bytecode) {
		this.bc = bytecode;
		this.startTime = new Date();
		this.s = new Stack(MAX_STACK_SIZE);
		this.varTable = new VariablesTable();
	}

	/**
	 * metoda spusti vyhodnocovani bytecodu
	 */
	public void run() {
		try {
			/*
			 * for (int i = 0; i < bc.size(); i++) { b = bc.nextByte();
			 * System.out.println("Dec: " + b + " ... Hex: " +
			 * Util.toHexString(b)); }
			 */
			while (true) {
				processByte(bc.nextInstruction());
			}

			/*
			 * for (int i = 0; i < bc.size(); i++) { //b = bc.nextByte(); b =
			 * bc.nextInstruction(); processByte(b); // test instrukci
			 * //System.out.println("Dec: " + b + " ... Hex: " // +
			 * Util.toHexString(b));
			 * 
			 * // test integeru //System.out.println("Int "+bc.nextInteger()); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * velky switch, ktery vyhodnocuje nacteny byte instrukce
	 * 
	 * @param b
	 * @throws InvalidInstructionException
	 * @throws VariableNotFoundException
	 */
	private void processByte(byte b) throws InvalidInstructionException,
			BytecodeOverflowException, StackOverflowException,
			EmptyStackPopException, VariableNotFoundException {

		String str = "";

		switch (b) {

		case POP_INSTR:
			str = "POP_INSTR";
			doPopInstruction();
			break;

		case PUSHC_INSTR:
			str = "PUSHC_INSTR";
			doPushCInstruction();
			break;

		case PUSHV_INSTR:
			str = "PUSHV_INSTR";
			doPushVInstruction();
			break;

		case ARRDEF_INSTR:
			str = "ARRDEF_INSTR";
			doArrdefInstruction();
			break;
		case ARRPOP_INSTR:
			str = "ARRPOP_INSTR";
			doArrpopInstruction();
			break;
		case ARRPUSH_INSTR:
			str = "ARRPUSH_INSTR";
			doArrpushInstruction();
			break;
		case BADD_INSTR:
			str = "BADD_INSTR";
			doBaddInstruction();
			break;
		case BSUB_INSTR:
			str = "BSUB_INSTR";
			doBsubInstruction();
			break;
		case BMUL_INSTR:
			str = "BMUL_INSTR";
			doBmulInstruction();
			break;
		/*
		 * case LAB_INSTR: str = "LAB_INSTR"; break;
		 */
		case MJMP_INSTR:
			str = "MJMP_INSTR";
			break;
		case MRET_INSTR:
			str = "MRET_INSTR";
			break;
		case JMP_INSTR:
			str = "JMP_INSTR";
			doJmpInstruction();
			break;
		case JEQ_INSTR:
			str = "JEQ_INSTR";
			doJeqInstruction();
			break;
		case JNEQ_INSTR:
			str = "JNEQ_INSTR";
			doJneqInstruction();
			break;
		case JLT_INSTR:
			doJltInstruction();
			str = "JLT_INSTR";
			break;
		case JGT_INSTR:
			doJgtInstruction();
			str = "JGT_INSTR";
			break;
		case JELT_INSTR:
			doJeltInstruction();
			str = "JELT_INSTR";
			break;
		case JEGT_INSTR:
			doJegtInstruction();
			str = "JEGT_INSTR";
			break;
		case CALL_INSTR:
			str = "CALL_INSTR";
			break;
		/*
		 * ukoncovaci instrukce - nejvetsi instrukce kdyz najdu tohle instrukci,
		 * program povazuju za spravne ukonceny
		 */
		case STOP_INSTR:
			str = "STOP_INSTR";
			doStopInstruction();
			break;

		// if instruction is not recognized, throw exception
		default:
			throw new InvalidInstructionException(
					"Unknown instruction called: 0x" + Util.toHexString(b));
		}

		Util.debugMsg("Instruction:\t0x" + Util.toHexString(b) + "\t" + str);
	}

	/*
	 * kazda instrukce ma vlastni metody, at je to prehlednejsi
	 */

	/*
	 * pop [adresa] - vyjmuti ze zasobniku a ulozeni na adresu slotu - vezme
	 * hodnotu z vrcholu zasobniku a ulozi ji na adresu
	 */
	private void doPopInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer value = ((Integer) s.pop()).intValue();

		varTable.setVariable(addr, value);

		Util.debugMsg("  Poped " + value + ", setting to addres " + addr);
	}

	/*
	 * pushc [cislo] - vlozit konstantu (hodnotu) na zasobnik
	 */
	private void doPushCInstruction() throws BytecodeOverflowException,
			StackOverflowException {
		Integer i = bc.nextInteger();
		s.push(i);

		Util.debugMsg("  Pushing " + i);
	}

	/*
	 * pushv [adresa-cislo] - vlozit promennou (adresu) na zasobnik - podiva se
	 * na tabulku promennych a hodnotu vlozi na zasobnik
	 */
	private void doPushVInstruction() throws BytecodeOverflowException,
			VariableNotFoundException, StackOverflowException {
		Integer adr = bc.nextInteger();
		Object o = varTable.getValue(adr);
		s.push(o);

		Util.debugMsg("  Push value from address " + adr + " (" + o
				+ ") to stack");
	}

	/*
	 * arrdef [adresa pole]
	 * 
	 * - na zasobniku je pocet polozek - po provedeni je pole na teto adrese
	 */
	private void doArrdefInstruction() throws EmptyStackPopException, BytecodeOverflowException {
		Integer arrSize = (Integer)s.pop();
		Integer addr = bc.nextInteger();
		
		Object[] arr = new Object[arrSize];
		varTable.setVariable(addr, arr);
		
		Util.debugMsg("  New array Object["+arrSize+"] create on address "+addr);
	}
	
	/*
	 * arrpop [adresa pole] - vlozi hodnotu do pole na adrese - nejvyssi hodnota
	 * zas = index v poli - 2. nejvyssi hodnota zas = hodnota
	 */
	private void doArrpopInstruction() throws BytecodeOverflowException, EmptyStackPopException, VariableNotFoundException {
		Integer addr = bc.nextInteger();
		Integer index = (Integer)s.pop();
		Integer value = (Integer)s.pop();
		
		((Object[])varTable.getValue(addr))[index] = value;
		/*
		Object[] arr = (Object[])varTable.getValue(addr);
		arr[index] = value;
		varTable.setVariable(addr, arr);
		*/
		Integer valueCheck = (Integer)(((Object[])varTable.getValue(addr))[index]);
		Util.debugMsg("  Object[] array at address "+addr+" changed: arr["+index+"]="+valueCheck);
	}
	
	/*
	 * arrpush [adresa pole] - z pole na adrese vlozi hodnotu na vrchol
	 * zasobniku - nejvyssi hodnota zas = index v poli -> vlozi hodnotu na
	 * zasobniku
	 */
	private void doArrpushInstruction() throws BytecodeOverflowException, EmptyStackPopException, VariableNotFoundException, StackOverflowException {
		Integer addr = bc.nextInteger();
		Integer index = (Integer)s.pop();
		
		Integer value = (Integer)(((Object[])varTable.getValue(addr))[index]);
		s.push(value);
		//arr[index] = value;
		//varTable.setVariable(addr, arr);
		
		Util.debugMsg("  Object "+value+" from array Object[] address "+addr+" pushed to stack (stack top: "+(Integer)s.top()+")");
	}

	/*
	 * badd - scitani (addition) - nacte 2 nejvyssi hodnoty ze zasobniku, secte
	 * a vysledek vlozi na zasobnik
	 */
	private void doBaddInstruction() throws EmptyStackPopException,
			StackOverflowException {
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();
		s.push(new Integer(i1 + i2));

		Util.debugMsg("   Add " + i2 + " + " + i1 + ", result on stack: "
				+ (Integer) s.top());
	}

	/*
	 * bsub - odcitani (substraction) - nacte 2 nejvyssi hodnoty ze zasobniku,
	 * odecte a vysledek vlozi na zasobnik.
	 * 
	 * Nejvyssi cislo (prvni vracene) je odectene od cisla pod nim (drive
	 * vlozeneho)
	 */
	private void doBsubInstruction() throws EmptyStackPopException,
			StackOverflowException {
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();
		s.push(new Integer(i2 - i1));

		Util.debugMsg("   Substract " + i2 + " - " + i1 + ", result on stack: "
				+ (Integer) s.top());
	}

	/*
	 * bmul - nasobeni (multiplication) - nacte 2 nejvyssi hodnoty ze zasobniku,
	 * vynasobi a vysledek vlozi na zasobnik
	 */
	private void doBmulInstruction() throws EmptyStackPopException,
			StackOverflowException {
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();
		s.push(new Integer(i1 * i2));

		Util.debugMsg("   Multiply " + i2 + " * " + i1 + ", result on stack: "
				+ s.top());
	}
	
	/*
	 * mjmp [navesti]
	 *  	- skoci na navesti metody
	 *  	- vytvori kopii environmentu, vlozi do stacku enviromentu a nastavi callstack
	 */
	private void doMjmpInstruction() {
		
	}
	
	/*
	 *  mret
	 *  	- ukonceni metody
	 *  	- po zavolani se vraci dle callstacku na pozici callstack+1
	 */

	/*
	 * jmp [navesti] - nepodmineny skok
	 */
	private void doJmpInstruction() throws BytecodeOverflowException {
		Integer addr = bc.nextInteger();
		bc.jumpTo(addr);

		Util.debugMsg("   Jump to " + addr);
	}

	/*
	 * jeq [navesti] - podmineny skok - skace, kdyz 2 nejvyssi hodnoty na
	 * zasobniku jsou stejne
	 */
	private void doJeqInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() == i2.intValue()) {
			// kdyz jsou stejne, skoc
			bc.jumpTo(addr);
			Util.debugMsg("   Jeq true: " + i1.intValue() + "=="
					+ i2.intValue() + ", jump to " + addr);
		} else
			Util.debugMsg("   Jeq false: " + i1.intValue() + "!="
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * jneq [navesti] - skok, dyz nejsou stejne
	 */
	private void doJneqInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() != i2.intValue()) {
			// kdyz nejsou stejne, skoc
			bc.jumpTo(addr);
			Util.debugMsg("   Jneq true: " + i1.intValue() + "!="
					+ i2.intValue() + ", jump to " + addr);
		} else
			Util.debugMsg("   Jneq false: " + i1.intValue() + "=="
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * jlt [navesti] - skok, kdyz nejvyssi hodnota na zasobniku je mensi nez 2
	 * nejvyssi
	 */
	private void doJltInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() < i2.intValue()) {
			bc.jumpTo(addr);
			Util.debugMsg("   Jlt true: " + i1.intValue() + "<" + i2.intValue()
					+ ", jump to " + addr);
		} else
			Util.debugMsg("   Jlt false: " + i1.intValue() + ">="
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * jgt [navesti] - skok, kdyz nejvyssi hodnota na zasobniku je vetsi nez 2
	 * nejvyssi
	 */
	private void doJgtInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() > i2.intValue()) {
			bc.jumpTo(addr);
			Util.debugMsg("   Jgt true: " + i1.intValue() + ">" + i2.intValue()
					+ ", jump to " + addr);
		} else
			Util.debugMsg("   Jgt false: " + i1.intValue() + "<="
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * jelt [navesti] - mensi nebo rovno
	 */
	private void doJeltInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() <= i2.intValue()) {
			bc.jumpTo(addr);
			Util.debugMsg("   Jgt true: " + i1.intValue() + "<="
					+ i2.intValue() + ", jump to " + addr);
		} else
			Util.debugMsg("   Jgt false: " + i1.intValue() + ">"
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * jegt - vetsi nebo rovno
	 */
	private void doJegtInstruction() throws BytecodeOverflowException,
			EmptyStackPopException {
		Integer addr = bc.nextInteger();
		Integer i1 = (Integer) s.pop();
		Integer i2 = (Integer) s.pop();

		if (i1.intValue() >= i2.intValue()) {
			bc.jumpTo(addr);
			Util.debugMsg("   Jgt true: " + i1.intValue() + ">="
					+ i2.intValue() + ", jump to " + addr);
		} else
			Util.debugMsg("   Jgt false: " + i1.intValue() + "<"
					+ i2.intValue() + ", continuing execution");

	}

	/*
	 * stop - ukonceni programu
	 */
	private void doStopInstruction() {
		Date endDate = new Date();
		double secs = (double) ((endDate.getTime() - startTime.getTime()) / 1000.0);
		System.out.println("\nProgram ended with status 0 (OK) in " + secs
				+ " seconds");
		s.printStack();
		varTable.printMemory();
		System.exit(0);
	}

}
