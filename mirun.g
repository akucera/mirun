grammar mirun;

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

methodDeclarationsRest	:	SEMICOLON methodDeclaration
			|	;

methodCall		:	ID LPAR vyraz RPAR;

returnType		:	INTVAR | REALVAR | STRINGVAR | ARRAYVAR | VOID;

params			:	varType ID paramsRest
			|	;

paramsRest		:	SEMICOLON params
			|	;

//konec cast deklarace metod
//cast deklarace promennych
declarationBlock	:	varDeclaration declarationBlockRest
			|	;

declarationBlockRest	:	SEMICOLON varDeclaration declarationBlockRest
			|	;
// deklarace pole ve tvaru
//	array typ jmeno velikost;
//	priklad    array int pole 10;
arrayDeclaration	:	ARRAYVAR varType ID INT SEMICOLON;

varDeclaration		:	varType ID ASSIGN vyraz SEMICOLON;


varType			:	INTVAR | REALVAR | STRINGVAR;
//konec cast deklarace promennych	
//cast telo programu
bodyList 		:	available bodyListRest
			|	;

available		:	ifBlock
			|	whileBlock
						|	staticMethod
			|	forBlock
						|	arrAssignment
			|	assignment
			|	varDeclaration
						|	arrayDeclaration
						|	returnBlock;

bodyListRest		:	available bodyListRest
			|	;
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

arrAssignment		:	arrAccessor ASSIGN vyraz SEMICOLON;
// prirazeni pole ve tvaru
//	pole[index] = hodnota;
//	priklad:	hodnoty[2] = 10;
assignment		:	ID ASSIGN vyraz SEMICOLON;


// TODO TADY JAK S INT VS ID ??
arrAccessor		:	LBRACKET vyraz RBRACKET ID;
arrLength		:	LENGTH ID;
//arrAccessor		:	LBRACKET INT RBRACKET ID;


// definice statickych metod jazyka
staticMethod		:	print
			|	length
			|	rofl
						|	wtf;
print			:	PRINTLN LPAR vyraz RPAR SEMICOLON;
length			:	LENGTH LPAR ID RPAR;
rofl			:	ROFL LPAR stringId SEMICOLON ID RPAR SEMICOLON;
wtf			:	WTF LPAR ID SEMICOLON stringId RPAR SEMICOLON;

compareOperator :	EQ | NE | GT | LT | LE | GE ;
//konec cast pomocna pravidla
//pouzita gramatika vyrazu ze cviceni
vyraz	:	clen vyraz2;

vyraz2	:	PLUS clen vyraz2
	|	MINUS clen vyraz2		
	|	;
	
clen	:	faktor clen2
	|	staticMethod
	|	arrAccessor
		;

clen2	:	TIMES faktor clen2
	|	DIVIDED faktor clen2
	|	;
	
faktor		:	LPAR vyraz RPAR 
		|	konst	
		|	ID;
stringId	:	(STRING | ID);
konst		:	INT | REAL | STRING;

arrayObjType	:	INT
			REAL
			STRING;
//konec vyraz

/*
*	Lexer rules
*/
// nastaveni klicovych slov vyskytujicich se v programu

PROG		:	'program';
ENDPROG		:	'margorp';
INTVAR		:	'int';
REALVAR		:	'real';
VOID		:	'void';
STRINGVAR	:	'string';
ARRAYVAR	:	'array';

INT		:	'0'..'9'+;
REAL		:	'0'..'9'+ DOT '0'..'9'*;
STRING		:	QUOTE ('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'-'|'_'|' ')* QUOTE;
// efinice stringu, podporuje a-z, A-Z, 0-9, tecku, pomlcku, podrtzitko
// string musi byt v uvozovkach ... co stringPromenne? :-/


PRINTLN		:	'println';
DECLARATION	:	'declare';
METHODS		:	'methods';
METHOD		:	'method';
WHILE		:	'while';
IF		:	'if';
FOR		:	'for';
RETURN		:	'return';
ENDDECLARATION	:	'eralced';
ENDMETHODS	:	'sdohtem';
ENDMETHOD	:	'dohtem';
ENDWHILE	:	'elihw';
ENDIF		:	'fi';
ENDFOR		:	'rof';
SEMICOLON	:	';';

//staticke metody jazyka
LENGTH		:	'length';
ROFL		:	'readfile';		// Read O FiLe - cteni ze souboru
WTF		:	'writefile';	// Write TO FIle - zapis do souboru

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
