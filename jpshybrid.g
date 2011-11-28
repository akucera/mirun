grammar jpshybrid;

options {
    k = 1;
}

/*
*	Parser rules
*/
program			:	PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON;


//cast deklarace metod
methodDeclarations	:	methodDeclaration methodDeclarationsRest;

methodDeclaration	:	METHOD ID returnType LPAR params RPAR bodyList ENDMETHOD
			|	;

methodDeclarationsRest	:	SEMICOLON methodDeclaration
			|	;

returnType		:	INTVAR
			|	REALVAR
			|	VOID;

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

varDeclaration		:	varType ID ASSIGN vyraz;

varType			:	INTVAR | REALVAR;
//konec cast deklarace promennych	
//cast telo programu
bodyList 		:	available bodyListRest
			|	;

available		:	ifBlock
			|	whileBlock
			|	forBlock
			|	assignment
			|	print;

bodyListRest		:	SEMICOLON available bodyListRest
			|	;
//konec cast telo programu
//cast bloky prikazu
ifBlock			:	IF condition bodyList ENDIF;

whileBlock		:	WHILE condition bodyList ENDWHILE;

forBlock		:	FOR forCondition bodyList ENDFOR;
//konec cast bloky prikazu
//cast pomocna pravidla
condition		:	LPAR conditionBody RPAR;

forCondition		:	LPAR varDeclaration SEMICOLON conditionBody SEMICOLON forStep SEMICOLON RPAR;

conditionBody		:	vyraz compareOperator vyraz;

forStep			:	assignment;

assignment		:	ID ASSIGN vyraz;

print			:	PRINTLN LPAR vyraz RPAR;

compareOperator :	EQ | NE | GT | LT | LE | GE ;
//konec cast pomocna pravidla
//pouzita gramatika vyrazu ze cviceni
vyraz	:	clen vyraz2 ;

vyraz2	:	PLUS clen vyraz2
	|	MINUS clen vyraz2
	|	;
	
clen	:	faktor clen2 ;

clen2	:	TIMES faktor clen2
	|	DIVIDED faktor clen2
	|	;
	
faktor		:	LPAR vyraz RPAR 
		|	konst	
		|	ID;
	
konst		:	INT | REAL;
//konec vyraz

/*
*	Lexer rules
*/

PROG		:	'program';
ENDPROG		:	'margorp';
INTVAR		:	'int';
REALVAR		:	'real';
VOID		:	'void';

INT		:	'0'..'9'+;
REAL		:	'0'..'9'+ DOT '0'..'9'*;

PRINTLN		:	'println';
DECLARATION	:	'declare';
METHODS		:	'methods';
METHOD		:	'method';
WHILE		:	'while';
IF		:	'if';
FOR		:	'for';
ENDDECLARATION	:	'eralced';
ENDMETHODS	:	'sdohtem';
ENDMETHOD	:	'dohtem';
ENDWHILE	:	'elihw';
ENDIF		:	'fi';
ENDFOR		:	'rof';
SEMICOLON	:	';';

ID		:	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;


LPAR		:	'(';
RPAR		:	')';
DOT		:	'.';

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
