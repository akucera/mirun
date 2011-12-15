/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.util.ArrayList;
import java.util.List;
import tree.AssignmentTree;
import tree.AvailableTree;
import tree.BinaryTree;
import tree.BinaryTree.Operator;
import tree.BodyListTree;
import tree.DeclarationTree;
import tree.ExpressionTree;
import tree.ForTree;
import tree.IdentifierTree;
import tree.IfTree;
import tree.LiteralTree;
import tree.MethodDeclarationTree;
import tree.MethodDeclarationTree.ReturnType;
import tree.ArrayDeclarationTree;
import tree.MethodDeclarationsTree;
import tree.MethodTab;
import tree.MethodTree;
import tree.Position;
import tree.PrintTree;
import tree.ProgramTree;
import tree.SymTab;
import tree.Type;
import tree.VariableDeclarationTree;
import tree.VariableDeclarationsTree;
import tree.VariableTree;
import tree.WhileTree;
import static compiler.Token.*;

/**
 * Syntakticky analyzator.
 */
public class Parser {

	/**
	 * Lexikalni analyzator.
	 */
	private Lexer lexer;
	/**
	 * Nacteny lexikalni symbol.
	 */
	private Token token;
	/**
	 * Tabulka symbolu.
	 */
	private SymTab symTab;
	/**
	 * Tabulka metod.
	 */
	private MethodTab methodTab;
	/**
	 * Pocet semantickych chyb nalezenych v parseru.
	 */
	private int errorCount;
	/**
	 * Jmeno programu.
	 */
	private String name;

	/**
	 * Vytvori syntakticky analyzator, ktery bude dostavat
	 * lexikalni symboly od zadaneho lexikalniho analyzatoru.
	 */
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		nextToken();
	}

	/**
	 * Nacte dalsi lexikalni symbol.
	 */
	void nextToken() {
		token = lexer.nextToken();
	}

	/**
	 * Vypise chybu.
	 */
	void error(Position p1, Position p2, String s) {
		System.out.printf("%s%s %s%n", p1, p2, s);
	}

	void error(String s) {
		Position p1 = lexer.getBeginPosition();
		Position p2 = lexer.getEndPosition();
		error(p1, p2, s);
	}

	/**
	 * Semanticka chyba, napr. neznamy identifikator.
	 * Nezpusobi zastaveni parseru.
	 */
	void semanticError(Position p1, Position p2, String s) {
		error(p1, p2, s);
		errorCount++;
	}

	/**
	 * Operace srovnani.
	 */
	void accept(Token expected) {
		if (token == expected) {
			nextToken();
		} else {
			System.out.printf("%s expected: %s, found: %s%n", lexer.getBeginPosition(), expected, token);
			throw new ParserException();
		}
	}

	/**
	 * Vstupni metoda syntaktickeho analyzatoru.
	 */
	public ProgramTree parse() {
		ProgramTree p = program();
		if (errorCount > 0) {
			throw new SemanticException();
		}
		accept(EOI);
		return p;
	}

	/*
	 * program : PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON;
	 */
	ProgramTree program() {
		symTab = new SymTab();
		methodTab = new MethodTab();
		Position p1 = lexer.getBeginPosition();
		accept(PROG);
		String id = lexer.getIdentifier();
		this.name = id;
		accept(ID);
		accept(SEMICOLON);
		accept(METHODS);
		MethodDeclarationsTree methods = methodDeclarations();
		accept(ENDMETHODS);
		accept(SEMICOLON);
		accept(DECLARATION);
		VariableDeclarationsTree declarations = declarationBlock();
		accept(ENDDECLARATION);
		accept(SEMICOLON);
		BodyListTree body = bodyList(symTab);
		accept(ENDPROG);
		accept(SEMICOLON);
		Position p2 = lexer.getLastEndPosition();
		return new ProgramTree(p1, p2, id, methods, declarations, body, symTab);
	}

	/*
	 * methodDeclarations : methodDeclaration methodDeclarationsRest;
	 *
	 */
	MethodDeclarationsTree methodDeclarations() {
		Position p1 = lexer.getBeginPosition();
		List<MethodDeclarationTree> methods = new ArrayList<MethodDeclarationTree>();
		methodDeclaration(methods);
		methodDeclarationsRest(methods);
		Position p2 = lexer.getLastEndPosition();
		return new MethodDeclarationsTree(p1, p2, methods);
	}

	/*
	 * declarationBlock	:	(varDeclaration|arrayDeclaration) declarationBlockRest
	 * 						| ;
	 */
	VariableDeclarationsTree declarationBlock() {
		List<DeclarationTree> declarations = new ArrayList<DeclarationTree>();
		Position p1 = lexer.getBeginPosition();
		switch (token) {
			case INTVAR:
			case STRINGVAR:
				declarations.add(varDeclaration(symTab));
				break;
			case ARRAYVAR:
				declarations.add(arrayDeclaration(symTab));
				break;
			default:
				break;
		}
		declarationBlockRest(declarations);
		Position p2 = lexer.getLastEndPosition();
		return new VariableDeclarationsTree(p1, p2, declarations);
	}

	/*
	 * bodyList : available bodyListRest
	 * 			  |	returnBlock
	 * 			  |	;
	 */
	BodyListTree bodyList(SymTab symTab) {
		List<AvailableTree> available = new ArrayList<AvailableTree>();
		Position p1 = lexer.getBeginPosition();
		switch (token) {
		case IF:
		case WHILE:
		case CALL:
		case PRINTLN:
		case LENGTH:
		case ROFL:
		case WTF:
		case FOR:
		case LBRACKET:
		case ID:
		case INTVAR:
		case STRINGVAR:
		case ARRAYVAR:
			available(available, symTab);
			bodyListRest(available, symTab);
			break;
		default:
			break;
		}
		Position p2 = lexer.getLastEndPosition();
		return new BodyListTree(p1, p2, available);
	}


	/*
	 * methodDeclaration : METHOD returnType ID LPAR params RPAR LBRACE bodyList RBRACE
	 * 					   | ;
	 *
	 */
	void methodDeclaration(List<MethodDeclarationTree> methods) {
		if (token == METHOD) {
			Position p1 = lexer.getBeginPosition();
			accept(METHOD);
			String id = lexer.getIdentifier();
			accept(ID);
			ReturnType r = returnType();
			accept(LPAR);
			List<Type> paramTypes = new ArrayList<Type>();
			SymTab paramSymTab = new SymTab();
			paramSymTab.setFreeSlot(0);
			params(paramTypes, paramSymTab);
			accept(RPAR);
			accept(LBRACE);
			BodyListTree body = bodyList(paramSymTab);
			ExpressionTree e = returnBlock(paramSymTab);
			accept(RBRACE);
			Position p2 = lexer.getLastEndPosition();
			if (methodTab.contains(id)) {
				semanticError(p1, p2, id + " method is already declared");
			}
			methodTab.insert(id, r, paramTypes);
			methods.add(new MethodDeclarationTree(p1, p2, id, r, paramTypes, body, e, paramSymTab));
		}
	}

	/*
	 * methodDeclarationsRest :	SEMICOLON methodDeclaration methodDeclarationsRest
	 * 							| ;
	 */
	void methodDeclarationsRest(List<MethodDeclarationTree> methods) {
		if (token == SEMICOLON) {
			accept(SEMICOLON);
			methodDeclaration(methods);
			methodDeclarationsRest(methods);
		}
	}

	/*
	 * returnBlock : RETURN vyraz? SEMICOLON;
	 */
	ExpressionTree returnBlock(SymTab paramSymTab) {
		accept(RETURN);
		if (token != SEMICOLON) {
			ExpressionTree e = vyraz(paramSymTab);
			return e;
		}
		return null;
	}


	/*
	 * varDeclaration : varType ID ASSIGN vyraz SEMICOLON;
	 *
	 */
	VariableDeclarationTree varDeclaration(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		Type type = varType();
		String n = lexer.getIdentifier();
		Position pident = lexer.getBeginPosition();
		accept(ID);
		Position p2 = lexer.getLastEndPosition();
		VariableTree v = new VariableTree(p1, p2, n);
		accept(ASSIGN);
		v.setType(type);
		if (symTab.contains(v.getName())) {
			semanticError(v.getStart(), v.getEnd(), v.getName() + " is already declared");
		} else {
			symTab.insert(v);
		}
		IdentifierTree i = new IdentifierTree(pident, p2, n);
		i.setVariable(v);
		i.setLeftValue(true);
		ExpressionTree e = vyraz(symTab);
		AssignmentTree a = new AssignmentTree(p1, p2, i, e, null, false);
		return new VariableDeclarationTree(p1, p2, a);
	}

	/*
	 * arrayDeclaration	: ARRAYVAR varType ID INT SEMICOLON;
	 *
	 */
	ArrayDeclarationTree arrayDeclaration(SymTab symTab) {
		//    	Position p1 = lexer.getBeginPosition();
		//    	Type type = Type.ARRAYVAR;
		//    	Type arrayType = varType();
		//    	String n = lexer.getIdentifier();
		//    	Position pident = lexer.getBeginPosition();
		//    	accept(ID);
		//    	Position p2 = lexer.getLastEndPosition();
		//    	VariableTree v = new VariableTree(p1, p2, n);
		//    	accept(ASSIGN);
		//    	v.setType(type);
		//    	if (symTab.contains(v.getName())) {
		//    		semanticError(v.getStart(), v.getEnd(), v.getName() + " is already declared");
		//    	} else {
		//    		symTab.insert(v);
		//    	}
		//    	IdentifierTree i = new IdentifierTree(pident, p2, n);
		//    	i.setVariable(v);
		//    	i.setLeftValue(true);
		//    	ExpressionTree e = vyraz(symTab);
		//    	AssignmentTree a = new AssignmentTree(p1, p2, i, e, null, false);
		//    	declarations.add(new VariableDeclarationTree(p1, p2, a));
		return new ArrayDeclarationTree(null, null);
	}

	/*
	 * declarationBlockRest	: (varDeclaration|arrayDeclaration) declarationBlockRest
	 * 						  |	;
	 */
	void declarationBlockRest(List<DeclarationTree> declarations) {
		if (token != ENDDECLARATION) {
			switch (token) {
				case INTVAR:
				case STRINGVAR:
					declarations.add(varDeclaration(symTab));
					break;
				case ARRAYVAR:
					declarations.add(arrayDeclaration(symTab));
					break;
				default:
					break;
			}
			declarationBlockRest(declarations);
		}
		return;
	}

	/*
	 * available : ifBlock
	 * 			   | whileBlock
	 * 			   | methodCall
	 * 			   | staticMethod
	 * 			   | forBlock
	 * 			   | arrAssignment
	 * 			   | assignment
	 * 			   | varDeclaration
	 * 			   | arrayDeclaration;
	 *
	 */
	void available(List<AvailableTree> available, SymTab symTab) {
		switch (token) {
		case IF:
			IfTree i = ifBlock(symTab);
			available.add(i);
			break;
		case WHILE:
			WhileTree w = whileBlock(symTab);
			available.add(w);
			break;
		case FOR:
			ForTree f = forBlock(symTab);
			available.add(f);
			break;
		case ID:
			AssignmentTree a = assignment(symTab);
			available.add(a);
			break;
		case PRINTLN: 
			PrintTree p = print(symTab);
			available.add(p);
			break;
		case LENGTH:
			//TODO
			break;
		case ROFL:
			//TODO
			break;
		case WTF:
			//TODO
			break;
		case CALL:
			// TODO rename to CallTree
			MethodTree m = methodBlock(symTab, false);
			available.add(m);
			break;
		case LBRACKET:
			//TODO
			break;
		case INTVAR:
		case STRINGVAR:
			//varDeclaration(available, symTab);
		case ARRAYVAR:
		default:
			error("expected: available, found: " + token);
			throw new ParserException();
		}
		return;
	}

	/*
	 * bodyListRest	: available bodyListRest
	 * 				  |	returnBlock
	 * 				  | ;
	 */
	void bodyListRest(List<AvailableTree> available, SymTab symTab) {
		if (token == RETURN) {
			returnBlock(symTab);
		} else if ((token != RBRACE) && (token != ENDPROG)) {
			available(available, symTab);
			bodyListRest(available, symTab);
		}
		return;
	}

	/*
	 * returnType : INTVAR
	 *		  | REALVAR
	 *  	  | VOID;
	 *
	 *
	 */
	ReturnType returnType() {
		switch (token) {
			case INTVAR:
				accept(INTVAR);
				return ReturnType.INTVAR;
			case VOID:
				accept(VOID);
				return ReturnType.VOID;
			case STRINGVAR:
				accept(STRINGVAR);
				return ReturnType.STRINGVAR;
			case ARRAYVAR:
				accept(ARRAYVAR);
				return ReturnType.ARRAYVAR;
			default:
				error("expected: returnType, found: " + token);
				throw new ParserException();
		}
	}

	/*
	 * params :	varType ID paramsRest
	 *        | ;
	 */
	void params(List<Type> paramTypes, SymTab paramSymTab) {
		Position p1 = lexer.getBeginPosition();
		switch (token) {
		case INTVAR:
			Type type = varType();
			String n = lexer.getIdentifier();
			accept(ID);
			accept(SEMICOLON);
			Position p2 = lexer.getLastEndPosition();
			VariableTree v = new VariableTree(p1, p2, n);
			v.setType(type);
			paramTypes.add(type);
			if (paramSymTab.contains(v.getName())) {
				semanticError(v.getStart(), v.getEnd(), v.getName() + " is already declared");
			} else {
				paramSymTab.insert(v);
			}
			paramsRest(paramTypes, paramSymTab);
			break;
		default:
			break;
		}
		return;
	}

	/*
	 * paramsRest : params;
	 */
	void paramsRest(List<Type> paramTypes, SymTab paramSymTab) {
		if (token != RPAR) {
			params(paramTypes, paramSymTab);
		}
		return;
	}

	/*
	 * varType : INTVAR | STRINGVAR;
	 */
	Type varType() {
		switch (token) {
		case INTVAR:
			accept(INTVAR);
			return Type.INTVAR;
		case STRINGVAR:
			accept(STRINGVAR);
			return Type.STRINGVAR;
		default:
			error("expected: INTVAR | STRINGVAR, found: " + token);
			throw new ParserException();
		}
	}

	/*
	 * ifBlock : IF condition LBRACE bodyList RBRACE
	 */
	IfTree ifBlock(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		accept(IF);
		BinaryTree c = condition(symTab);
		accept(LBRACE);
		BodyListTree b = bodyList(symTab);
		accept(RBRACE);
		Position p2 = lexer.getLastEndPosition();
		return new IfTree(p1, p2, c, b);
	}

	/*
	 * whileBlock : WHILE condition LBRACE bodyList RBRACE;
	 */
	WhileTree whileBlock(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		accept(WHILE);
		BinaryTree c = condition(symTab);
		accept(LBRACE);
		BodyListTree b = bodyList(symTab);
		accept(RBRACE);
		Position p2 = lexer.getLastEndPosition();
		return new WhileTree(p1, p2, c, b);
	}

	/*
	 * forBlock : FOR LPAR forCondition RPAR LBRACE bodyList RBRACE;
	 */
	ForTree forBlock(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		accept(FOR);
		accept(LPAR);
		ForTree ft = forCondition(p1, symTab);
		accept(RPAR);
		accept(LBRACE);
		BodyListTree body = bodyList(symTab);
		ft.setBody(body);
		accept(RBRACE);
		return ft;
	}

	/*
	 * assignment : ID ASSIGN vyraz SEMICOLON;
	 *
	 */
	AssignmentTree assignment(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		String n = lexer.getIdentifier();
		accept(ID);
		Position p2 = lexer.getLastEndPosition();
		VariableTree v = symTab.find(n);
		if (v == null) {
			semanticError(p1, p2, "unknown identifier: " + n);
		}
		IdentifierTree i = new IdentifierTree(p1, p2, n);
		i.setVariable(v);
		i.setLeftValue(true);
		accept(ASSIGN);
		if (token == CALL) {
			MethodTree m = methodBlock(symTab, true);
			accept(SEMICOLON);
			Position p3 = lexer.getLastEndPosition();
			return new AssignmentTree(p1, p3, i, null, m, true);
		}
		ExpressionTree e = vyraz(symTab);
		accept(SEMICOLON);
		Position p3 = lexer.getLastEndPosition();
		return new AssignmentTree(p1, p3, i, e, null, false);
	}

	/*
	 * print : PRINTLN LPAR vyraz RPAR SEMICOLON;
	 */
	PrintTree print(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		accept(PRINTLN);
		accept(LPAR);
		ExpressionTree e = vyraz(symTab);
		accept(RPAR);
		accept(SEMICOLON);
		Position p2 = lexer.getLastEndPosition();
		return new PrintTree(p1, p2, e);
	}
// TODO other static functions	
//	length			:	LENGTH LPAR ID RPAR;
//	rofl			:	ROFL LPAR stringId SEMICOLON ID RPAR SEMICOLON;
//	wtf			:	WTF LPAR ID SEMICOLON stringId RPAR SEMICOLON;

	/*
	 * methodBlock : METHOD ID LPAR methodBlockParams RPAR;
	 *
	 */
	MethodTree methodBlock(SymTab symTab, boolean isRight) {
		Position p1 = lexer.getBeginPosition();
		List<ExpressionTree> e = new ArrayList<ExpressionTree>();
		accept(METHOD);
		String n = lexer.getIdentifier();
		accept(ID);
		accept(LPAR);
		methodBlockParams(e, symTab);
		accept(RPAR);
		Position p2 = lexer.getLastEndPosition();
		if (!methodTab.contains(n)) {
			semanticError(p1, p2, n + " method is not declared");
		}
		return new MethodTree(p1, p2, this.name, n, methodTab.find(n), methodTab.findParamTypes(n), e, isRight);
	}

	/*
	 * methodBlockParams : vyraz SEMICOLON methodBlockParamsRest
	 *                   | ;
	 *
	 */
	void methodBlockParams(List<ExpressionTree> e, SymTab symTab) {
		if (token != RPAR) {
			e.add(vyraz(symTab));
			accept(SEMICOLON);
			methodBlockParamsRest(e, symTab);
		}
	}

	/*
	 * methodBlockParamsRest : methodBlockParams;
	 *
	 */
	void methodBlockParamsRest(List<ExpressionTree> e, SymTab symTab) {
		methodBlockParams(e, symTab);
		return;
	}

	/*
	 * condition : LPAR conditionBody RPAR;
	 *
	 */
	BinaryTree condition(SymTab symTab) {
		accept(LPAR);
		BinaryTree b = conditionBody(symTab);
		accept(RPAR);
		return b;
	}

	/*
	 * forCondition : varDeclaration conditionBody SEMICOLON forStep;
	 */
	ForTree forCondition(Position p1, SymTab symTab) {
		DeclarationTree var = varDeclaration(symTab);
		accept(SEMICOLON);
		BinaryTree condition = conditionBody(symTab);
		accept(SEMICOLON);
		AssignmentTree step = forStep(symTab);
		accept(SEMICOLON);
		accept(RPAR);
		Position p2 = lexer.getLastEndPosition();
		return new ForTree(p1, p2, var, condition, step, null);
	}

	/*
	 * forStep : assignment;
	 *
	 */
	AssignmentTree forStep(SymTab symTab) {
		return assignment(symTab);
	}

	/*
	 * conditionBody : vyraz compareOperator vyraz;
	 *
	 */
	BinaryTree conditionBody(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		ExpressionTree e1 = vyraz(symTab);
		Operator o = compareOperator();
		ExpressionTree e2 = vyraz(symTab);
		Position p2 = lexer.getLastEndPosition();
		return new BinaryTree(p1, p2, o, e1, e2);
	}

	/*
	 * compareOperator : EQ
	 *                 | NE
	 *                 | GT
	 *                 | LT
	 *                 | LE
	 *                 | GE;
	 *
	 */
	Operator compareOperator() {
		switch (token) {
		case EQ:
			accept(EQ);
			return Operator.EQ;
		case NE:
			accept(NE);
			return Operator.NE;
		case GT:
			accept(GT);
			return Operator.GT;
		case LT:
			accept(LT);
			return Operator.LT;
		case GE:
			accept(GE);
			return Operator.GE;
		case LE:
			accept(LE);
			return Operator.LE;
		default:
			error("expected: compareOperator, found: " + token);
			throw new ParserException();
		}
	}

	/*
	 *
	 *
	 *
	 *
	 *
	 */

	/*
	 * vyraz : clen vyraz2;
	 *
	 */
	ExpressionTree vyraz(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		ExpressionTree e1 = clen(symTab);
		return vyraz2(p1, e1, symTab);
	}

	/*
	 * vyraz2 :	PLUS clen vyraz2
	 *        | MINUS clen vyraz2
	 *        | ;
	 *
	 */
	ExpressionTree vyraz2(Position p1, ExpressionTree e1, SymTab symTab) {
		Operator o;
		ExpressionTree e2;
		Position p2;
		BinaryTree t;
		switch (token) {
			case PLUS:
				accept(PLUS);
				o = Operator.ADD;
				e2 = clen(symTab);
				p2 = lexer.getBeginPosition();
				t = new BinaryTree(p1, p2, o, e1, e2);
				return vyraz2(p1, t, symTab);
			case MINUS:
				accept(MINUS);
				o = Operator.SUB;
				e2 = clen(symTab);
				p2 = lexer.getBeginPosition();
				t = new BinaryTree(p1, p2, o, e1, e2);
				return vyraz2(p1, t, symTab);
			default:
				break;
		}
		return e1;
	}

	/*
	 * clen : faktor clen2;
	 *
	 */
	ExpressionTree clen(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		ExpressionTree e1 = faktor(symTab);
		return clen2(p1, e1, symTab);
	}

	/*
	 * clen2 : TIMES faktor clen2
	 *       | DIVIDED faktor clen2
	 *       | ;
	 *
	 */
	ExpressionTree clen2(Position p1, ExpressionTree e1, SymTab symTab) {
		Operator o;
		ExpressionTree e2;
		Position p2;
		BinaryTree t;
		switch (token) {
		case TIMES:
			accept(TIMES);
			o = Operator.MUL;
			e2 = faktor(symTab);
			p2 = lexer.getLastEndPosition();
			t = new BinaryTree(p1, p2, o, e1, e2);
			return clen2(p1, t, symTab);
		case DIVIDED:
			accept(DIVIDED);
			e2 = faktor(symTab);
			p2 = lexer.getLastEndPosition();
			o = Operator.IDIV;
			t = new BinaryTree(p1, p2, o, e1, e2);
			return clen2(p1, t, symTab);
		default:
			break;
		}
		return e1;
	}

	/*
	 * faktor :	LPAR vyraz RPAR
	 *        | konst
	 *        | ID;
	 *
	 */
	ExpressionTree faktor(SymTab symTab) {
		switch (token) {
		case LPAR: {
			accept(LPAR);
			ExpressionTree e = vyraz(symTab);
			accept(RPAR);
			return e;
		}
		case ID: {
			Position p1 = lexer.getBeginPosition();
			String n = lexer.getIdentifier();
			accept(ID);
			Position p2 = lexer.getLastEndPosition();
			VariableTree v = symTab.find(n);
			if (v == null) {
				semanticError(p1, p2, "unknown identifier: " + n);
			}
			IdentifierTree i = new IdentifierTree(p1, p2, n);
			i.setVariable(v);
			return i;
		}
		case INT:
			ExpressionTree e = konst(symTab);
			return e;
		default:
			error("expected: LPAR|ID|INT|REAL, found: " + token);
			throw new ParserException();
		}
	}

	/*
	 * konst : INT
	 *       | REAL;
	 *
	 */
	ExpressionTree konst(SymTab symTab) {
		switch (token) {
		case INT: {
			Position p1 = lexer.getBeginPosition();
			Object v = lexer.getValue();
			accept(INT);
			Position p2 = lexer.getLastEndPosition();
			return new LiteralTree(p1, p2, Type.INTVAR, v);
		}
		case STRING: {
			Position p1 = lexer.getBeginPosition();
			Object v = lexer.getValue();
			accept(STRING);
			Position p2 = lexer.getLastEndPosition();
			return new LiteralTree(p1, p2, Type.STRINGVAR, v);
		}
		default:
			break;
		}
		return null;
	}
}
