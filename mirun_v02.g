grammar mirun_v02;

options {
    k = 1;
}

/*
*	Parser rules
*/
program			:	PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON;


//cast deklarace metod
methodDeclarations	:	methodDeclaration methodDeclarationsRest;

methodDeclaration	:	METHOD returnType ID LPAR params RPAR LBRACE bodyList RBRACE
			|	;

methodDeclarationsRest	:	SEMICOLON methodDeclaration methodDeclarationsRest
			|	;

methodCall		:	CALL ID LPAR methodCallParams RPAR SEMICOLON;
methodCallParams	:	vyraz methodCallParamsRest
			|	;
			
methodCallParamsRest	:	SEMICOLON methodCallParams
			|	;

returnType		:	INTVAR | STRINGVAR | ARRAYVAR | VOID;

params			:	varType ID paramsRest
			|	;

paramsRest		:	SEMICOLON params
			|	;

//konec cast deklarace metod
//cast deklarace promennych
declarationBlock	:	(varDeclaration|arrayDeclaration) declarationBlockRest
			|	;

declarationBlockRest	:	(varDeclaration|arrayDeclaration) declarationBlockRest
			|	;
// deklarace pole ve tvaru
//	array typ jmeno velikost;
//	priklad    array int pole 10;
arrayDeclaration	:	ARRAYVAR varType ID INT SEMICOLON;

varDeclaration		:	varType ID ASSIGN vyraz SEMICOLON;


varType			:	INTVAR | STRINGVAR;
//konec cast deklarace promennych	
//cast telo programu
bodyList 		:	available bodyListRest
			|	returnBlock
			|	;

available		:	ifBlock
			|	whileBlock
			|	methodCall
			|	forBlock
			|	arrAssignment
			|	assignment
			|	varDeclaration
			|	arrayDeclaration;

bodyListRest		:	available bodyListRest
			|	returnBlock
			| ;
//konec cast telo programu
//cast bloky prikazu
ifBlock			:	IF condition LBRACE bodyList RBRACE;

whileBlock		:	WHILE condition LBRACE bodyList RBRACE;

forBlock		:	FOR LPAR forCondition RPAR LBRACE bodyList RBRACE;

// muze vracet vyraz neb nic
returnBlock		:	RETURN vyraz* SEMICOLON;
//konec cast bloky prikazu
//cast pomocna pravidla
condition		:	LPAR conditionBody RPAR;

forCondition		:	varDeclaration conditionBody SEMICOLON forStep;

conditionBody		:	vyraz compareOperator vyraz;

forStep			:	assignment;

arrAssignment		:	LBRACKET vyraz RBRACKET ID ASSIGN vyraz SEMICOLON;
// prirazeni pole ve tvaru
//	pole[index] = hodnota;
//	priklad:	hodnoty[2] = 10;
assignment		:	ID ASSIGN vyraz SEMICOLON;

arrAccessor		:	LBRACKET vyraz RBRACKET ID;

// ponechano z historickych duvodu, staticke metody se budou volat pres call
// definice statickych metod jazyka
//staticMethod	: print | length | rofl | wtf;
//print		: PRINTLN LPAR vyraz RPAR SEMICOLON;
//length	: LENGTH LPAR ID RPAR;
//rofl		: ROFL LPAR STRING SEMICOLON ID RPAR SEMICOLON;
//wtf		: WTF LPAR ID SEMICOLON STRING RPAR SEMICOLON;

compareOperator :	EQ | NE | GT | LT | LE | GE ;
//konec cast pomocna pravidla
//pouzita gramatika vyrazu ze cviceni
vyraz		:	clen vyraz2;

vyraz2		:	PLUS clen vyraz2
		|	MINUS clen vyraz2		
		|	;
	
clen		:	faktor clen2;

clen2		:	TIMES faktor clen2
		|	DIVIDED faktor clen2
		|	;
	
faktor		:	LPAR vyraz RPAR 
		|	konst	
		|	ID
		|	arrAccessor
		|	methodCall;

konst		:	INT | STRING;

arrayObjType	:	INT
			STRING;
//konec vyraz

/*
*	Lexer rules
*/
// nastaveni klicovych slov vyskytujicich se v programu

PROG		:	'program';
ENDPROG		:	'margorp';
INTVAR		:	'int';
VOID		:	'void';
STRINGVAR	:	'string';
ARRAYVAR	:	'array';

INT		:	'0'..'9'+;
STRING		:	QUOTE ('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'-'|'_'|' '|'\\')* QUOTE;
// efinice stringu, podporuje a-z, A-Z, 0-9, tecku, pomlcku, podrtzitko
// string musi byt v uvozovkach ... co stringPromenne? :-/


DECLARATION	:	'declare';
METHODS		:	'methods';
METHOD		:	'method';
WHILE		:	'while';
IF		:	'if';
FOR		:	'for';
RETURN		:	'return';
ENDDECLARATION	:	'eralced';
ENDMETHODS	:	'sdohtem';
CALL		:	'call';
SEMICOLON	:	';';

// ponechano z historickych duvodu, staticke metody se budou volat pres call
//staticke metody jazyka
//LENGTH	:	'length';
//ROFL		:	'readfile';	// Read O FiLe - cteni ze souboru
//WTF		:	'writefile';	// Write TO FIle - zapis do souboru
//PRINTLN	:	'println';

ID		:	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;

LPAR		:	'(';
RPAR		:	')';
LBRACE		:	'{';
RBRACE		:	'}';
LBRACKET	:	'[';
RBRACKET	:	']';
DOT		:	'.';
QUOTE		:	'"';
COLON		:	':';

ASSIGN		:	'=';
PLUS		:	'+';
MINUS		:	'-';
TIMES		:	'*';
DIVIDED		:	'/';

EQ		:	'==';
NE		:	'!=';
GT		:	'>';
LT		:	'<';
LE		:	'<=';
GE		:	'>=';


COMMENT		:	'//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};

WS		:	(' ' | '\t' | '\r' | '\n')+ { skip(); };
