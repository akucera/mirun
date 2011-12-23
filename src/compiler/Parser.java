/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import static compiler.Token.ARRAYVAR;
import static compiler.Token.ASSIGN;
import static compiler.Token.CALL;
import static compiler.Token.DECLARATION;
import static compiler.Token.ENDDECLARATION;
import static compiler.Token.ENDMETHODS;
import static compiler.Token.ENDPROG;
import static compiler.Token.EOI;
import static compiler.Token.EQ;
import static compiler.Token.FOR;
import static compiler.Token.GE;
import static compiler.Token.GT;
import static compiler.Token.ID;
import static compiler.Token.IF;
import static compiler.Token.INT;
import static compiler.Token.INTVAR;
import static compiler.Token.LBRACE;
import static compiler.Token.LBRACKET;
import static compiler.Token.LE;
import static compiler.Token.LPAR;
import static compiler.Token.LT;
import static compiler.Token.METHOD;
import static compiler.Token.METHODS;
import static compiler.Token.MINUS;
import static compiler.Token.NE;
import static compiler.Token.PLUS;
import static compiler.Token.PROG;
import static compiler.Token.RBRACE;
import static compiler.Token.RBRACKET;
import static compiler.Token.RETURN;
import static compiler.Token.RPAR;
import static compiler.Token.SEMICOLON;
import static compiler.Token.STRING;
import static compiler.Token.STRINGVAR;
import static compiler.Token.TIMES;
import static compiler.Token.VOID;
import static compiler.Token.WHILE;

import java.util.ArrayList;
import java.util.List;

import tree.ArrayAccessorTree;
import tree.ArrayAssignmentTree;
import tree.ArrayDeclarationTree;
import tree.ArrayTree;
import tree.AssignmentTree;
import tree.AvailableTree;
import tree.BinaryTree;
import tree.BinaryTree.Operator;
import tree.BodyListTree;
import tree.ConstTab;
import tree.DeclarationTree;
import tree.ExpressionTree;
import tree.ForTree;
import tree.IdentifierTree;
import tree.IfTree;
import tree.LiteralTree;
import tree.MethodDeclarationTree;
import tree.MethodDeclarationTree.ReturnType;
import tree.MethodDeclarationsTree;
import tree.MethodTab;
import tree.MethodTree;
import tree.Position;
import tree.ProgramTree;
import tree.ReturnTree;
import tree.SymTab;
import tree.Type;
import tree.VariableDeclarationTree;
import tree.VariableDeclarationsTree;
import tree.VariableTree;
import tree.WhileTree;

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
	 * Tabulka konstant.
	 */
	private ConstTab constTab;
	/**
	 * Tabulka metod.
	 */
	private MethodTab methodTab;
	/**
	 * Tabulka vestavenych metod jazyka.
	 */
	private MethodTab builtInMethods;
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
	public Parser(Lexer lexer, MethodTab builtInMethods) {
		this.lexer = lexer;
		this.builtInMethods = builtInMethods;
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
		symTab = new SymTab(null);
		constTab = new ConstTab();
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
		return new ProgramTree(p1, p2, id, methods, declarations, body, symTab, constTab);
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
		case FOR:
		case LBRACKET:
		case ID:
		case INTVAR:
		case STRINGVAR:
		case ARRAYVAR:
			available(available, symTab);
			bodyListRest(available, symTab);
			break;
		case RETURN:
			ReturnTree retTree = returnBlock(symTab);
			available.add(retTree);
			break;
		default:
			break;
		}
		Position p2 = lexer.getLastEndPosition();
		return new BodyListTree(p1, p2, available);
	}
	
	/*
	 * bodyListRest	: available bodyListRest
	 * 				  |	returnBlock
	 * 				  | ;
	 */
	void bodyListRest(List<AvailableTree> available, SymTab symTab) {
		if (token == RETURN) {
			ReturnTree retTree = returnBlock(symTab);
			available.add(retTree);
		} else if ((token != RBRACE) && (token != ENDPROG)) {
			available(available, symTab);
			bodyListRest(available, symTab);
		}
		return;
	}


	/*
	 * methodDeclaration : METHOD returnType ID LPAR params RPAR LBRACE bodyList RBRACE
	 * 					   | ;
	 */
	void methodDeclaration(List<MethodDeclarationTree> methods) {
		if (token == METHOD) {
			Position p1 = lexer.getBeginPosition();
			accept(METHOD);
			ReturnType r = returnType();
			String id = lexer.getIdentifier();
			accept(ID);
			accept(LPAR);
			List<Type> paramTypes = new ArrayList<Type>();
			List<AssignmentTree> paramsList = new ArrayList<AssignmentTree>();
			SymTab paramSymTab = new SymTab(this.symTab);
			params(paramTypes, paramsList, paramSymTab);
			accept(RPAR);
			accept(LBRACE);
			BodyListTree body = bodyList(paramSymTab);
			accept(RBRACE);
			Position p2 = lexer.getLastEndPosition();
			if (builtInMethods.contains(id)) {
				semanticError(p1, p2, id + " method is built-in language method!");
			}
			if (methodTab.contains(id)) {
				semanticError(p1, p2, id + " method is already declared");
			}
			methodTab.insert(id, r, paramTypes);
			methods.add(new MethodDeclarationTree(p1, p2, id, r, paramTypes, paramsList, body, paramSymTab));
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
	ReturnTree returnBlock(SymTab paramSymTab) {
		Position p1 = lexer.getBeginPosition();
		accept(RETURN);
		ExpressionTree e = null;
		if (token != SEMICOLON) {
			e = vyraz(paramSymTab);
		}
		accept(SEMICOLON);
		Position p2 = lexer.getLastEndPosition();
		return new ReturnTree(p1, p2, e);
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
		if (type == Type.STRINGVAR)
			constTab.matchVariableToConstant(v);
		ExpressionTree e = vyraz(symTab);
		accept(SEMICOLON);
		AssignmentTree a = new AssignmentTree(p1, p2, i, e, null, false, false);
		return new VariableDeclarationTree(p1, p2, a);
	}

	/*
	 * arrayDeclaration	: ARRAYVAR varType ID vyraz SEMICOLON;
	 *
	 */
	ArrayDeclarationTree arrayDeclaration(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		accept(Token.ARRAYVAR);
		Type type = varType();
		String n = lexer.getIdentifier();
		accept(ID);
		ExpressionTree e = vyraz(symTab);
		//Integer arrLength;
//		try {
//			arrLength = Integer.valueOf(lexer.getValue().toString());
//		} catch (NumberFormatException e) {
//			semanticError(lexer.getBeginPosition(), lexer.getEndPosition(), n + " has incorrect length (must be int value)!");
//			return null;
//		}
//		accept(INT);
		accept(SEMICOLON);
		Position p2 = lexer.getLastEndPosition();
		ArrayTree arrTree = new ArrayTree(p1, p2, n, e);
		if (symTab.contains(n)) {
			semanticError(p1, p2, n + " is already declared");
		} else {
			symTab.insert(arrTree);
		}
		ArrayDeclarationTree arrDec = new ArrayDeclarationTree(p1, p2, arrTree);
		return arrDec;
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
		case CALL:
			// TODO rename to CallTree
			MethodTree m = methodCall(symTab, false);
			available.add(m);
			break;
		case LBRACKET:
			ArrayAssignmentTree arrAssignment = arrayAssignment(symTab);
			available.add(arrAssignment);
			break;
		case INTVAR:
		case STRINGVAR:
			VariableDeclarationTree v = varDeclaration(symTab);
			available.add(v);
			break;
		case ARRAYVAR:
			ArrayDeclarationTree arrDec = arrayDeclaration(symTab);
			available.add(arrDec);
			break;
		default:
			error("expected: available, found: " + token);
			throw new ParserException();
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
	void params(List<Type> paramTypes, List<AssignmentTree> paramsList, SymTab paramSymTab) {
		Position p1 = lexer.getBeginPosition(), pident, p2;
		Type type;
		String n;
		IdentifierTree i;
		AssignmentTree a;
		switch (token) {
			case INTVAR:
			case STRINGVAR:
				type = varType();
				n = lexer.getIdentifier();
				pident = lexer.getBeginPosition();
				accept(ID);
				p2 = lexer.getLastEndPosition();
				VariableTree v = new VariableTree(p1, p2, n);
				v.setType(type);
				paramTypes.add(type);
				if (paramSymTab.contains(v.getName())) {
					semanticError(v.getStart(), v.getEnd(), v.getName() + " is already declared");
				} else {
					paramSymTab.insert(v);
				}
				i = new IdentifierTree(pident, p2, n);
				i.setVariable(v);
				i.setLeftValue(true);
				a = new AssignmentTree(p1, p2, i, null, null, false, true);
				paramsList.add(0, a);
				paramsRest(paramTypes, paramsList, paramSymTab);
				break;
			case ARRAYVAR:
				type = varType();
				n = lexer.getIdentifier();
				pident = lexer.getBeginPosition();
				accept(ID);
				p2 = lexer.getLastEndPosition();
				ArrayTree arrTree = new ArrayTree(p1, p2, n, null);
				arrTree.setType(type);
				paramTypes.add(type);
				if (paramSymTab.contains(arrTree.getName())) {
					semanticError(arrTree.getStart(), arrTree.getEnd(), arrTree.getName() + " is already declared");
				} else {
					paramSymTab.insert(arrTree);
				}
				i = new IdentifierTree(pident, p2, n);
				i.setVariable(arrTree);
				i.setLeftValue(true);
				a = new AssignmentTree(p1, p2, i, null, null, false, true);
				paramsList.add(0, a);
				paramsRest(paramTypes, paramsList, paramSymTab);
				break;
			default:
				break;
		}
		return;
	}

	/*
	 * paramsRest : SEMICOLON params
	 * 			  | ;
	 */
	void paramsRest(List<Type> paramTypes, List<AssignmentTree> paramsList, SymTab paramSymTab) {
		if (token != RPAR) {
			accept(SEMICOLON);
			params(paramTypes, paramsList, paramSymTab);
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
		case ARRAYVAR:
			accept(ARRAYVAR);
			return Type.ARRAYVAR;
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
			MethodTree m = methodCall(symTab, true);
			accept(SEMICOLON);
			Position p3 = lexer.getLastEndPosition();
			return new AssignmentTree(p1, p3, i, null, m, true, false);
		}
		ExpressionTree e = vyraz(symTab);
		accept(SEMICOLON);
		Position p3 = lexer.getLastEndPosition();
		return new AssignmentTree(p1, p3, i, e, null, false, false);
	}
	
	/*
	 * arrAssignment : arrAccessor ASSIGN vyraz SEMICOLON;
	 */
	ArrayAssignmentTree arrayAssignment(SymTab symTab) {
		Position p1 = lexer.getBeginPosition();
		MethodTree m = null;
		ExpressionTree e = null;
		ArrayAccessorTree arrAccTree = arrayAcces(symTab, false);
		if (arrAccTree == null) {
			return null;
		}
		accept(ASSIGN);
		if (token == CALL) {
			m = methodCall(symTab, true);
		} else {
			e = vyraz(symTab);
		}
		accept(SEMICOLON);
		Position p3 = lexer.getLastEndPosition();
		return new ArrayAssignmentTree(p1, p3, arrAccTree, e, m);
	}

	/*
	 * arrAccessor : LBRACKET vyraz RBRACKET ID;
	 */
	ArrayAccessorTree arrayAcces(SymTab symTab, boolean isAccess) {
		Position p1 = lexer.getBeginPosition();
		accept(LBRACKET);
		MethodTree accM = null;
		ExpressionTree accE = null;
		if (token == CALL) {
			accM = methodCall(symTab, true);
		} else {
			accE = vyraz(symTab);
		}
		accept(RBRACKET);
		String n = lexer.getIdentifier();
		accept(ID);
		Position p2 = lexer.getLastEndPosition();
		Object v = symTab.find(n);
		if (v == null) {
			semanticError(p1, p2, "unknown identifier: " + n);
			return null;
		}
		if (!(v instanceof ArrayTree)) {
			semanticError(p1, p2, "not array: " + n);
			return null;
		}
		ArrayTree arrTree = (ArrayTree) v;
		return new ArrayAccessorTree(p1, p2, arrTree, accE, accM, isAccess);
	}

	/*
	 * methodCall :	CALL ID LPAR methodCallParams RPAR SEMICOLON?;
	 */
	MethodTree methodCall(SymTab symTab, boolean isRight) {
		Position p1 = lexer.getBeginPosition();
		List<ExpressionTree> e = new ArrayList<ExpressionTree>();
		accept(CALL);
		String n = lexer.getIdentifier();
		accept(ID);
		accept(LPAR);
		methodCallParams(e, symTab);
		accept(RPAR);
		if (token == SEMICOLON)
			accept(SEMICOLON);
		Position p2 = lexer.getLastEndPosition();
		if (!methodTab.contains(n) && !builtInMethods.contains(n)) {
			semanticError(p1, p2, n + " method is not declared nor built-in language method");
			return null;
		}
		MethodTree methodTree = new MethodTree(p1, p2, this.name, n, methodTab.find(n), methodTab.findParamTypes(n), e, isRight);
		if (builtInMethods.contains(n))
			methodTree.setStatic(true);
		return methodTree;
	}

	/*
	 * methodCallParams	: vyraz methodCallParamsRest
	 * 					| ;
	 */
	void methodCallParams(List<ExpressionTree> e, SymTab symTab) {
		if (token != RPAR) {
			e.add(vyraz(symTab));
			methodCallParamsRest(e, symTab);
		}
	}
	
	/*
	 * methodCallParamsRest	: SEMICOLON methodCallParams
	 * 						| ;
	 */
	void methodCallParamsRest(List<ExpressionTree> e, SymTab symTab) {
		if (token != RPAR) {
			accept(SEMICOLON);
			methodCallParams(e, symTab);
		}
	}
	/*
	 * condition : LPAR conditionBody RPAR;
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
		BinaryTree condition = conditionBody(symTab);
		accept(SEMICOLON);
		AssignmentTree step = forStep(symTab);
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
//		case DIVIDED:
//			accept(DIVIDED);
//			e2 = faktor(symTab);
//			p2 = lexer.getLastEndPosition();
//			o = Operator.IDIV;
//			t = new BinaryTree(p1, p2, o, e1, e2);
//			return clen2(p1, t, symTab);
		default:
			break;
		}
		return e1;
	}

	/*
	 * faktor :	LPAR vyraz RPAR
	 * 			| konst
	 * 			| ID
	 * 			| arrAccessor
	 * 			| methodCall;
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
		case STRING:
			ExpressionTree e = konst(symTab);
			return e;
		case LBRACKET:
			ArrayAccessorTree arrayAccessorTree = arrayAcces(symTab, true);
			return arrayAccessorTree;
		case CALL:
			MethodTree callTree = methodCall(symTab, true);
			return callTree;
		default:
			error("expected: LPAR|ID|ARR|CALL|STRING, found: " + token);
			throw new ParserException();
		}
	}

	/*
	 * konst : INT
	 */
	ExpressionTree konst(SymTab symTab) {
		switch (token) {
		case INT: {
			Position p1 = lexer.getBeginPosition();
			Object v = lexer.getValue();
			accept(INT);
			Position p2 = lexer.getLastEndPosition();
			return new LiteralTree(p1, p2, Type.INTVAR, v, constTab);
		}
		case STRING: {
			Position p1 = lexer.getBeginPosition();
			Object v = lexer.getValue();
			accept(STRING);
			Position p2 = lexer.getLastEndPosition();
			constTab.insert(v.toString());
			return new LiteralTree(p1, p2, Type.STRINGVAR, v, constTab);
		}
		default:
			break;
		}
		return null;
	}
}
