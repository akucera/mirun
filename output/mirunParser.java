// $ANTLR 3.4 /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g 2011-11-28 13:05:56

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class mirunParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARRAYVAR", "ASSIGN", "COMMENT", "DECLARATION", "DIVIDED", "DOT", "ENDDECLARATION", "ENDFOR", "ENDIF", "ENDMETHOD", "ENDMETHODS", "ENDPROG", "ENDWHILE", "EQ", "FOR", "GE", "GT", "ID", "IF", "INT", "INTVAR", "LE", "LPAR", "LT", "METHOD", "METHODS", "MINUS", "NE", "PLUS", "PRINTLN", "PROG", "REAL", "REALVAR", "RPAR", "SEMICOLON", "STRINGVAR", "TIMES", "VOID", "WHILE", "WS"
    };

    public static final int EOF=-1;
    public static final int ARRAYVAR=4;
    public static final int ASSIGN=5;
    public static final int COMMENT=6;
    public static final int DECLARATION=7;
    public static final int DIVIDED=8;
    public static final int DOT=9;
    public static final int ENDDECLARATION=10;
    public static final int ENDFOR=11;
    public static final int ENDIF=12;
    public static final int ENDMETHOD=13;
    public static final int ENDMETHODS=14;
    public static final int ENDPROG=15;
    public static final int ENDWHILE=16;
    public static final int EQ=17;
    public static final int FOR=18;
    public static final int GE=19;
    public static final int GT=20;
    public static final int ID=21;
    public static final int IF=22;
    public static final int INT=23;
    public static final int INTVAR=24;
    public static final int LE=25;
    public static final int LPAR=26;
    public static final int LT=27;
    public static final int METHOD=28;
    public static final int METHODS=29;
    public static final int MINUS=30;
    public static final int NE=31;
    public static final int PLUS=32;
    public static final int PRINTLN=33;
    public static final int PROG=34;
    public static final int REAL=35;
    public static final int REALVAR=36;
    public static final int RPAR=37;
    public static final int SEMICOLON=38;
    public static final int STRINGVAR=39;
    public static final int TIMES=40;
    public static final int VOID=41;
    public static final int WHILE=42;
    public static final int WS=43;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public mirunParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public mirunParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return mirunParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g"; }



    // $ANTLR start "program"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:10:1: program : PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON ;
    public final void program() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:10:11: ( PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:10:13: PROG ID SEMICOLON METHODS methodDeclarations ENDMETHODS SEMICOLON DECLARATION declarationBlock ENDDECLARATION SEMICOLON bodyList ENDPROG SEMICOLON
            {
            match(input,PROG,FOLLOW_PROG_in_program30); 

            match(input,ID,FOLLOW_ID_in_program32); 

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_program34); 

            match(input,METHODS,FOLLOW_METHODS_in_program36); 

            pushFollow(FOLLOW_methodDeclarations_in_program38);
            methodDeclarations();

            state._fsp--;


            match(input,ENDMETHODS,FOLLOW_ENDMETHODS_in_program40); 

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_program42); 

            match(input,DECLARATION,FOLLOW_DECLARATION_in_program44); 

            pushFollow(FOLLOW_declarationBlock_in_program46);
            declarationBlock();

            state._fsp--;


            match(input,ENDDECLARATION,FOLLOW_ENDDECLARATION_in_program48); 

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_program50); 

            pushFollow(FOLLOW_bodyList_in_program52);
            bodyList();

            state._fsp--;


            match(input,ENDPROG,FOLLOW_ENDPROG_in_program54); 

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_program56); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "program"



    // $ANTLR start "methodDeclarations"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:14:1: methodDeclarations : methodDeclaration methodDeclarationsRest ;
    public final void methodDeclarations() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:14:20: ( methodDeclaration methodDeclarationsRest )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:14:22: methodDeclaration methodDeclarationsRest
            {
            pushFollow(FOLLOW_methodDeclaration_in_methodDeclarations66);
            methodDeclaration();

            state._fsp--;


            pushFollow(FOLLOW_methodDeclarationsRest_in_methodDeclarations68);
            methodDeclarationsRest();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "methodDeclarations"



    // $ANTLR start "methodDeclaration"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:16:1: methodDeclaration : ( METHOD ID returnType LPAR params RPAR bodyList ENDMETHOD |);
    public final void methodDeclaration() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:16:19: ( METHOD ID returnType LPAR params RPAR bodyList ENDMETHOD |)
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==METHOD) ) {
                alt1=1;
            }
            else if ( (LA1_0==ENDMETHODS||LA1_0==SEMICOLON) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:16:21: METHOD ID returnType LPAR params RPAR bodyList ENDMETHOD
                    {
                    match(input,METHOD,FOLLOW_METHOD_in_methodDeclaration76); 

                    match(input,ID,FOLLOW_ID_in_methodDeclaration78); 

                    pushFollow(FOLLOW_returnType_in_methodDeclaration80);
                    returnType();

                    state._fsp--;


                    match(input,LPAR,FOLLOW_LPAR_in_methodDeclaration82); 

                    pushFollow(FOLLOW_params_in_methodDeclaration84);
                    params();

                    state._fsp--;


                    match(input,RPAR,FOLLOW_RPAR_in_methodDeclaration86); 

                    pushFollow(FOLLOW_bodyList_in_methodDeclaration88);
                    bodyList();

                    state._fsp--;


                    match(input,ENDMETHOD,FOLLOW_ENDMETHOD_in_methodDeclaration90); 

                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:17:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "methodDeclaration"



    // $ANTLR start "methodDeclarationsRest"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:19:1: methodDeclarationsRest : ( SEMICOLON methodDeclaration |);
    public final void methodDeclarationsRest() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:19:24: ( SEMICOLON methodDeclaration |)
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMICOLON) ) {
                alt2=1;
            }
            else if ( (LA2_0==ENDMETHODS) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:19:26: SEMICOLON methodDeclaration
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_methodDeclarationsRest104); 

                    pushFollow(FOLLOW_methodDeclaration_in_methodDeclarationsRest106);
                    methodDeclaration();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:20:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "methodDeclarationsRest"



    // $ANTLR start "returnType"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:22:1: returnType : ( INTVAR | REALVAR | STRINGVAR | ARRAYVAR | VOID );
    public final void returnType() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:22:13: ( INTVAR | REALVAR | STRINGVAR | ARRAYVAR | VOID )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            {
            if ( input.LA(1)==ARRAYVAR||input.LA(1)==INTVAR||input.LA(1)==REALVAR||input.LA(1)==STRINGVAR||input.LA(1)==VOID ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "returnType"



    // $ANTLR start "params"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:24:1: params : ( varType ID paramsRest |);
    public final void params() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:24:10: ( varType ID paramsRest |)
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ARRAYVAR||LA3_0==INTVAR||LA3_0==REALVAR||LA3_0==STRINGVAR) ) {
                alt3=1;
            }
            else if ( (LA3_0==RPAR) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:24:12: varType ID paramsRest
                    {
                    pushFollow(FOLLOW_varType_in_params147);
                    varType();

                    state._fsp--;


                    match(input,ID,FOLLOW_ID_in_params149); 

                    pushFollow(FOLLOW_paramsRest_in_params151);
                    paramsRest();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:25:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "params"



    // $ANTLR start "paramsRest"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:27:1: paramsRest : ( SEMICOLON params |);
    public final void paramsRest() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:27:13: ( SEMICOLON params |)
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==SEMICOLON) ) {
                alt4=1;
            }
            else if ( (LA4_0==RPAR) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:27:15: SEMICOLON params
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_paramsRest166); 

                    pushFollow(FOLLOW_params_in_paramsRest168);
                    params();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:28:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "paramsRest"



    // $ANTLR start "declarationBlock"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:32:1: declarationBlock : ( varDeclaration declarationBlockRest |);
    public final void declarationBlock() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:32:18: ( varDeclaration declarationBlockRest |)
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ARRAYVAR||LA5_0==INTVAR||LA5_0==REALVAR||LA5_0==STRINGVAR) ) {
                alt5=1;
            }
            else if ( (LA5_0==ENDDECLARATION) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:32:20: varDeclaration declarationBlockRest
                    {
                    pushFollow(FOLLOW_varDeclaration_in_declarationBlock184);
                    varDeclaration();

                    state._fsp--;


                    pushFollow(FOLLOW_declarationBlockRest_in_declarationBlock186);
                    declarationBlockRest();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:33:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "declarationBlock"



    // $ANTLR start "declarationBlockRest"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:35:1: declarationBlockRest : ( SEMICOLON varDeclaration declarationBlockRest |);
    public final void declarationBlockRest() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:35:22: ( SEMICOLON varDeclaration declarationBlockRest |)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMICOLON) ) {
                alt6=1;
            }
            else if ( (LA6_0==ENDDECLARATION) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:35:24: SEMICOLON varDeclaration declarationBlockRest
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarationBlockRest200); 

                    pushFollow(FOLLOW_varDeclaration_in_declarationBlockRest202);
                    varDeclaration();

                    state._fsp--;


                    pushFollow(FOLLOW_declarationBlockRest_in_declarationBlockRest204);
                    declarationBlockRest();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:36:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "declarationBlockRest"



    // $ANTLR start "varDeclaration"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:38:1: varDeclaration : varType ID ASSIGN vyraz ;
    public final void varDeclaration() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:38:17: ( varType ID ASSIGN vyraz )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:38:19: varType ID ASSIGN vyraz
            {
            pushFollow(FOLLOW_varType_in_varDeclaration219);
            varType();

            state._fsp--;


            match(input,ID,FOLLOW_ID_in_varDeclaration221); 

            match(input,ASSIGN,FOLLOW_ASSIGN_in_varDeclaration223); 

            pushFollow(FOLLOW_vyraz_in_varDeclaration225);
            vyraz();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "varDeclaration"



    // $ANTLR start "varType"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:40:1: varType : ( INTVAR | REALVAR | STRINGVAR | ARRAYVAR );
    public final void varType() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:40:11: ( INTVAR | REALVAR | STRINGVAR | ARRAYVAR )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            {
            if ( input.LA(1)==ARRAYVAR||input.LA(1)==INTVAR||input.LA(1)==REALVAR||input.LA(1)==STRINGVAR ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "varType"



    // $ANTLR start "bodyList"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:43:1: bodyList : ( available bodyListRest |);
    public final void bodyList() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:43:12: ( available bodyListRest |)
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FOR||(LA7_0 >= ID && LA7_0 <= IF)||LA7_0==PRINTLN||LA7_0==WHILE) ) {
                alt7=1;
            }
            else if ( ((LA7_0 >= ENDFOR && LA7_0 <= ENDMETHOD)||(LA7_0 >= ENDPROG && LA7_0 <= ENDWHILE)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:43:14: available bodyListRest
                    {
                    pushFollow(FOLLOW_available_in_bodyList258);
                    available();

                    state._fsp--;


                    pushFollow(FOLLOW_bodyListRest_in_bodyList260);
                    bodyListRest();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:44:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "bodyList"



    // $ANTLR start "available"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:46:1: available : ( ifBlock | whileBlock | forBlock | assignment | print );
    public final void available() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:46:12: ( ifBlock | whileBlock | forBlock | assignment | print )
            int alt8=5;
            switch ( input.LA(1) ) {
            case IF:
                {
                alt8=1;
                }
                break;
            case WHILE:
                {
                alt8=2;
                }
                break;
            case FOR:
                {
                alt8=3;
                }
                break;
            case ID:
                {
                alt8=4;
                }
                break;
            case PRINTLN:
                {
                alt8=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:46:14: ifBlock
                    {
                    pushFollow(FOLLOW_ifBlock_in_available275);
                    ifBlock();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:47:6: whileBlock
                    {
                    pushFollow(FOLLOW_whileBlock_in_available282);
                    whileBlock();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:48:6: forBlock
                    {
                    pushFollow(FOLLOW_forBlock_in_available289);
                    forBlock();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:49:6: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_available296);
                    assignment();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:50:6: print
                    {
                    pushFollow(FOLLOW_print_in_available303);
                    print();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "available"



    // $ANTLR start "bodyListRest"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:52:1: bodyListRest : ( SEMICOLON available bodyListRest |);
    public final void bodyListRest() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:52:15: ( SEMICOLON available bodyListRest |)
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==SEMICOLON) ) {
                alt9=1;
            }
            else if ( ((LA9_0 >= ENDFOR && LA9_0 <= ENDMETHOD)||(LA9_0 >= ENDPROG && LA9_0 <= ENDWHILE)) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:52:17: SEMICOLON available bodyListRest
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_bodyListRest312); 

                    pushFollow(FOLLOW_available_in_bodyListRest314);
                    available();

                    state._fsp--;


                    pushFollow(FOLLOW_bodyListRest_in_bodyListRest316);
                    bodyListRest();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:53:6: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "bodyListRest"



    // $ANTLR start "ifBlock"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:56:1: ifBlock : IF condition bodyList ENDIF ;
    public final void ifBlock() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:56:11: ( IF condition bodyList ENDIF )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:56:13: IF condition bodyList ENDIF
            {
            match(input,IF,FOLLOW_IF_in_ifBlock333); 

            pushFollow(FOLLOW_condition_in_ifBlock335);
            condition();

            state._fsp--;


            pushFollow(FOLLOW_bodyList_in_ifBlock337);
            bodyList();

            state._fsp--;


            match(input,ENDIF,FOLLOW_ENDIF_in_ifBlock339); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "ifBlock"



    // $ANTLR start "whileBlock"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:58:1: whileBlock : WHILE condition bodyList ENDWHILE ;
    public final void whileBlock() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:58:13: ( WHILE condition bodyList ENDWHILE )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:58:15: WHILE condition bodyList ENDWHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_whileBlock348); 

            pushFollow(FOLLOW_condition_in_whileBlock350);
            condition();

            state._fsp--;


            pushFollow(FOLLOW_bodyList_in_whileBlock352);
            bodyList();

            state._fsp--;


            match(input,ENDWHILE,FOLLOW_ENDWHILE_in_whileBlock354); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "whileBlock"



    // $ANTLR start "forBlock"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:60:1: forBlock : FOR forCondition bodyList ENDFOR ;
    public final void forBlock() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:60:11: ( FOR forCondition bodyList ENDFOR )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:60:13: FOR forCondition bodyList ENDFOR
            {
            match(input,FOR,FOLLOW_FOR_in_forBlock363); 

            pushFollow(FOLLOW_forCondition_in_forBlock365);
            forCondition();

            state._fsp--;


            pushFollow(FOLLOW_bodyList_in_forBlock367);
            bodyList();

            state._fsp--;


            match(input,ENDFOR,FOLLOW_ENDFOR_in_forBlock369); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "forBlock"



    // $ANTLR start "condition"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:63:1: condition : LPAR conditionBody RPAR ;
    public final void condition() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:63:12: ( LPAR conditionBody RPAR )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:63:14: LPAR conditionBody RPAR
            {
            match(input,LPAR,FOLLOW_LPAR_in_condition379); 

            pushFollow(FOLLOW_conditionBody_in_condition381);
            conditionBody();

            state._fsp--;


            match(input,RPAR,FOLLOW_RPAR_in_condition383); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "condition"



    // $ANTLR start "forCondition"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:65:1: forCondition : LPAR varDeclaration SEMICOLON conditionBody SEMICOLON forStep SEMICOLON RPAR ;
    public final void forCondition() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:65:15: ( LPAR varDeclaration SEMICOLON conditionBody SEMICOLON forStep SEMICOLON RPAR )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:65:17: LPAR varDeclaration SEMICOLON conditionBody SEMICOLON forStep SEMICOLON RPAR
            {
            match(input,LPAR,FOLLOW_LPAR_in_forCondition392); 

            pushFollow(FOLLOW_varDeclaration_in_forCondition394);
            varDeclaration();

            state._fsp--;


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_forCondition396); 

            pushFollow(FOLLOW_conditionBody_in_forCondition398);
            conditionBody();

            state._fsp--;


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_forCondition400); 

            pushFollow(FOLLOW_forStep_in_forCondition402);
            forStep();

            state._fsp--;


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_forCondition404); 

            match(input,RPAR,FOLLOW_RPAR_in_forCondition406); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "forCondition"



    // $ANTLR start "conditionBody"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:67:1: conditionBody : vyraz compareOperator vyraz ;
    public final void conditionBody() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:67:16: ( vyraz compareOperator vyraz )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:67:18: vyraz compareOperator vyraz
            {
            pushFollow(FOLLOW_vyraz_in_conditionBody415);
            vyraz();

            state._fsp--;


            pushFollow(FOLLOW_compareOperator_in_conditionBody417);
            compareOperator();

            state._fsp--;


            pushFollow(FOLLOW_vyraz_in_conditionBody419);
            vyraz();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conditionBody"



    // $ANTLR start "forStep"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:69:1: forStep : assignment ;
    public final void forStep() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:69:11: ( assignment )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:69:13: assignment
            {
            pushFollow(FOLLOW_assignment_in_forStep429);
            assignment();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "forStep"



    // $ANTLR start "assignment"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:71:1: assignment : ID ASSIGN vyraz ;
    public final void assignment() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:71:13: ( ID ASSIGN vyraz )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:71:15: ID ASSIGN vyraz
            {
            match(input,ID,FOLLOW_ID_in_assignment438); 

            match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment440); 

            pushFollow(FOLLOW_vyraz_in_assignment442);
            vyraz();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "assignment"



    // $ANTLR start "print"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:73:1: print : PRINTLN LPAR vyraz RPAR ;
    public final void print() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:73:9: ( PRINTLN LPAR vyraz RPAR )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:73:11: PRINTLN LPAR vyraz RPAR
            {
            match(input,PRINTLN,FOLLOW_PRINTLN_in_print452); 

            match(input,LPAR,FOLLOW_LPAR_in_print454); 

            pushFollow(FOLLOW_vyraz_in_print456);
            vyraz();

            state._fsp--;


            match(input,RPAR,FOLLOW_RPAR_in_print458); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "print"



    // $ANTLR start "compareOperator"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:75:1: compareOperator : ( EQ | NE | GT | LT | LE | GE );
    public final void compareOperator() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:75:17: ( EQ | NE | GT | LT | LE | GE )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            {
            if ( input.LA(1)==EQ||(input.LA(1) >= GE && input.LA(1) <= GT)||input.LA(1)==LE||input.LA(1)==LT||input.LA(1)==NE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "compareOperator"



    // $ANTLR start "vyraz"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:78:1: vyraz : clen vyraz2 ;
    public final void vyraz() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:78:7: ( clen vyraz2 )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:78:9: clen vyraz2
            {
            pushFollow(FOLLOW_clen_in_vyraz496);
            clen();

            state._fsp--;


            pushFollow(FOLLOW_vyraz2_in_vyraz498);
            vyraz2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "vyraz"



    // $ANTLR start "vyraz2"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:80:1: vyraz2 : ( PLUS clen vyraz2 | MINUS clen vyraz2 |);
    public final void vyraz2() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:80:8: ( PLUS clen vyraz2 | MINUS clen vyraz2 |)
            int alt10=3;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt10=1;
                }
                break;
            case MINUS:
                {
                alt10=2;
                }
                break;
            case ENDDECLARATION:
            case ENDFOR:
            case ENDIF:
            case ENDMETHOD:
            case ENDPROG:
            case ENDWHILE:
            case EQ:
            case GE:
            case GT:
            case LE:
            case LT:
            case NE:
            case RPAR:
            case SEMICOLON:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:80:10: PLUS clen vyraz2
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_vyraz2507); 

                    pushFollow(FOLLOW_clen_in_vyraz2509);
                    clen();

                    state._fsp--;


                    pushFollow(FOLLOW_vyraz2_in_vyraz2511);
                    vyraz2();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:81:4: MINUS clen vyraz2
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_vyraz2516); 

                    pushFollow(FOLLOW_clen_in_vyraz2518);
                    clen();

                    state._fsp--;


                    pushFollow(FOLLOW_vyraz2_in_vyraz2520);
                    vyraz2();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:82:4: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "vyraz2"



    // $ANTLR start "clen"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:84:1: clen : faktor clen2 ;
    public final void clen() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:84:6: ( faktor clen2 )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:84:8: faktor clen2
            {
            pushFollow(FOLLOW_faktor_in_clen533);
            faktor();

            state._fsp--;


            pushFollow(FOLLOW_clen2_in_clen535);
            clen2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "clen"



    // $ANTLR start "clen2"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:86:1: clen2 : ( TIMES faktor clen2 | DIVIDED faktor clen2 |);
    public final void clen2() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:86:7: ( TIMES faktor clen2 | DIVIDED faktor clen2 |)
            int alt11=3;
            switch ( input.LA(1) ) {
            case TIMES:
                {
                alt11=1;
                }
                break;
            case DIVIDED:
                {
                alt11=2;
                }
                break;
            case ENDDECLARATION:
            case ENDFOR:
            case ENDIF:
            case ENDMETHOD:
            case ENDPROG:
            case ENDWHILE:
            case EQ:
            case GE:
            case GT:
            case LE:
            case LT:
            case MINUS:
            case NE:
            case PLUS:
            case RPAR:
            case SEMICOLON:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:86:9: TIMES faktor clen2
                    {
                    match(input,TIMES,FOLLOW_TIMES_in_clen2544); 

                    pushFollow(FOLLOW_faktor_in_clen2546);
                    faktor();

                    state._fsp--;


                    pushFollow(FOLLOW_clen2_in_clen2548);
                    clen2();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:87:4: DIVIDED faktor clen2
                    {
                    match(input,DIVIDED,FOLLOW_DIVIDED_in_clen2553); 

                    pushFollow(FOLLOW_faktor_in_clen2555);
                    faktor();

                    state._fsp--;


                    pushFollow(FOLLOW_clen2_in_clen2557);
                    clen2();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:88:4: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "clen2"



    // $ANTLR start "faktor"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:90:1: faktor : ( LPAR vyraz RPAR | konst | ID );
    public final void faktor() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:90:9: ( LPAR vyraz RPAR | konst | ID )
            int alt12=3;
            switch ( input.LA(1) ) {
            case LPAR:
                {
                alt12=1;
                }
                break;
            case INT:
            case REAL:
                {
                alt12=2;
                }
                break;
            case ID:
                {
                alt12=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:90:11: LPAR vyraz RPAR
                    {
                    match(input,LPAR,FOLLOW_LPAR_in_faktor571); 

                    pushFollow(FOLLOW_vyraz_in_faktor573);
                    vyraz();

                    state._fsp--;


                    match(input,RPAR,FOLLOW_RPAR_in_faktor575); 

                    }
                    break;
                case 2 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:91:5: konst
                    {
                    pushFollow(FOLLOW_konst_in_faktor582);
                    konst();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:92:5: ID
                    {
                    match(input,ID,FOLLOW_ID_in_faktor589); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "faktor"



    // $ANTLR start "konst"
    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:94:1: konst : ( INT | REAL );
    public final void konst() throws RecognitionException {
        try {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:94:8: ( INT | REAL )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            {
            if ( input.LA(1)==INT||input.LA(1)==REAL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "konst"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROG_in_program30 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_program32 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_program34 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_METHODS_in_program36 = new BitSet(new long[]{0x0000004010000000L});
    public static final BitSet FOLLOW_methodDeclarations_in_program38 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ENDMETHODS_in_program40 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_program42 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DECLARATION_in_program44 = new BitSet(new long[]{0x0000009001000410L});
    public static final BitSet FOLLOW_declarationBlock_in_program46 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ENDDECLARATION_in_program48 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_program50 = new BitSet(new long[]{0x0000040200648000L});
    public static final BitSet FOLLOW_bodyList_in_program52 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ENDPROG_in_program54 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_program56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_methodDeclarations66 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_methodDeclarationsRest_in_methodDeclarations68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_METHOD_in_methodDeclaration76 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_methodDeclaration78 = new BitSet(new long[]{0x0000029001000010L});
    public static final BitSet FOLLOW_returnType_in_methodDeclaration80 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_LPAR_in_methodDeclaration82 = new BitSet(new long[]{0x000000B001000010L});
    public static final BitSet FOLLOW_params_in_methodDeclaration84 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_methodDeclaration86 = new BitSet(new long[]{0x0000040200642000L});
    public static final BitSet FOLLOW_bodyList_in_methodDeclaration88 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ENDMETHOD_in_methodDeclaration90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_methodDeclarationsRest104 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_methodDeclaration_in_methodDeclarationsRest106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varType_in_params147 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_params149 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_paramsRest_in_params151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_paramsRest166 = new BitSet(new long[]{0x0000009001000010L});
    public static final BitSet FOLLOW_params_in_paramsRest168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDeclaration_in_declarationBlock184 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_declarationBlockRest_in_declarationBlock186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_declarationBlockRest200 = new BitSet(new long[]{0x0000009001000010L});
    public static final BitSet FOLLOW_varDeclaration_in_declarationBlockRest202 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_declarationBlockRest_in_declarationBlockRest204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varType_in_varDeclaration219 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_varDeclaration221 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ASSIGN_in_varDeclaration223 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_vyraz_in_varDeclaration225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_available_in_bodyList258 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_bodyListRest_in_bodyList260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifBlock_in_available275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileBlock_in_available282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forBlock_in_available289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_available296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_available303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_bodyListRest312 = new BitSet(new long[]{0x0000040200640000L});
    public static final BitSet FOLLOW_available_in_bodyListRest314 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_bodyListRest_in_bodyListRest316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_ifBlock333 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_condition_in_ifBlock335 = new BitSet(new long[]{0x0000040200641000L});
    public static final BitSet FOLLOW_bodyList_in_ifBlock337 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ENDIF_in_ifBlock339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_whileBlock348 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_condition_in_whileBlock350 = new BitSet(new long[]{0x0000040200650000L});
    public static final BitSet FOLLOW_bodyList_in_whileBlock352 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ENDWHILE_in_whileBlock354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_forBlock363 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_forCondition_in_forBlock365 = new BitSet(new long[]{0x0000040200640800L});
    public static final BitSet FOLLOW_bodyList_in_forBlock367 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ENDFOR_in_forBlock369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAR_in_condition379 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_conditionBody_in_condition381 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_condition383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAR_in_forCondition392 = new BitSet(new long[]{0x0000009001000010L});
    public static final BitSet FOLLOW_varDeclaration_in_forCondition394 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_forCondition396 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_conditionBody_in_forCondition398 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_forCondition400 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_forStep_in_forCondition402 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_forCondition404 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_forCondition406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vyraz_in_conditionBody415 = new BitSet(new long[]{0x000000008A1A0000L});
    public static final BitSet FOLLOW_compareOperator_in_conditionBody417 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_vyraz_in_conditionBody419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_forStep429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment438 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment440 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_vyraz_in_assignment442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINTLN_in_print452 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_LPAR_in_print454 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_vyraz_in_print456 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_print458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clen_in_vyraz496 = new BitSet(new long[]{0x0000000140000000L});
    public static final BitSet FOLLOW_vyraz2_in_vyraz498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_vyraz2507 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_clen_in_vyraz2509 = new BitSet(new long[]{0x0000000140000000L});
    public static final BitSet FOLLOW_vyraz2_in_vyraz2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_vyraz2516 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_clen_in_vyraz2518 = new BitSet(new long[]{0x0000000140000000L});
    public static final BitSet FOLLOW_vyraz2_in_vyraz2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_faktor_in_clen533 = new BitSet(new long[]{0x0000010000000100L});
    public static final BitSet FOLLOW_clen2_in_clen535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_clen2544 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_faktor_in_clen2546 = new BitSet(new long[]{0x0000010000000100L});
    public static final BitSet FOLLOW_clen2_in_clen2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDED_in_clen2553 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_faktor_in_clen2555 = new BitSet(new long[]{0x0000010000000100L});
    public static final BitSet FOLLOW_clen2_in_clen2557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAR_in_faktor571 = new BitSet(new long[]{0x0000000804A00000L});
    public static final BitSet FOLLOW_vyraz_in_faktor573 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_faktor575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_konst_in_faktor582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_faktor589 = new BitSet(new long[]{0x0000000000000002L});

}