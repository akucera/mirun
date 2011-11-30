// $ANTLR 3.4 /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g 2011-11-28 13:05:56

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class mirunLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public mirunLexer() {} 
    public mirunLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public mirunLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g"; }

    // $ANTLR start "PROG"
    public final void mPROG() throws RecognitionException {
        try {
            int _type = PROG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:101:7: ( 'program' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:101:9: 'program'
            {
            match("program"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROG"

    // $ANTLR start "ENDPROG"
    public final void mENDPROG() throws RecognitionException {
        try {
            int _type = ENDPROG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:102:10: ( 'margorp' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:102:12: 'margorp'
            {
            match("margorp"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDPROG"

    // $ANTLR start "INTVAR"
    public final void mINTVAR() throws RecognitionException {
        try {
            int _type = INTVAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:103:9: ( 'int' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:103:11: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTVAR"

    // $ANTLR start "REALVAR"
    public final void mREALVAR() throws RecognitionException {
        try {
            int _type = REALVAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:104:10: ( 'real' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:104:12: 'real'
            {
            match("real"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REALVAR"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:105:7: ( 'void' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:105:9: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "STRINGVAR"
    public final void mSTRINGVAR() throws RecognitionException {
        try {
            int _type = STRINGVAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:106:11: ( 'string' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:106:13: 'string'
            {
            match("string"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRINGVAR"

    // $ANTLR start "ARRAYVAR"
    public final void mARRAYVAR() throws RecognitionException {
        try {
            int _type = ARRAYVAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:107:10: ( 'array' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:107:12: 'array'
            {
            match("array"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ARRAYVAR"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:109:6: ( ( '0' .. '9' )+ )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:109:8: ( '0' .. '9' )+
            {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:109:8: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:110:7: ( ( '0' .. '9' )+ DOT ( '0' .. '9' )* )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:110:9: ( '0' .. '9' )+ DOT ( '0' .. '9' )*
            {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:110:9: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            mDOT(); 


            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:110:23: ( '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "PRINTLN"
    public final void mPRINTLN() throws RecognitionException {
        try {
            int _type = PRINTLN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:112:10: ( 'println' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:112:12: 'println'
            {
            match("println"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PRINTLN"

    // $ANTLR start "DECLARATION"
    public final void mDECLARATION() throws RecognitionException {
        try {
            int _type = DECLARATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:113:13: ( 'declare' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:113:15: 'declare'
            {
            match("declare"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECLARATION"

    // $ANTLR start "METHODS"
    public final void mMETHODS() throws RecognitionException {
        try {
            int _type = METHODS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:114:10: ( 'methods' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:114:12: 'methods'
            {
            match("methods"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "METHODS"

    // $ANTLR start "METHOD"
    public final void mMETHOD() throws RecognitionException {
        try {
            int _type = METHOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:115:9: ( 'method' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:115:11: 'method'
            {
            match("method"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "METHOD"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:116:8: ( 'while' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:116:10: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:117:5: ( 'if' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:117:7: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:118:6: ( 'for' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:118:8: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "ENDDECLARATION"
    public final void mENDDECLARATION() throws RecognitionException {
        try {
            int _type = ENDDECLARATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:119:16: ( 'eralced' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:119:18: 'eralced'
            {
            match("eralced"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDDECLARATION"

    // $ANTLR start "ENDMETHODS"
    public final void mENDMETHODS() throws RecognitionException {
        try {
            int _type = ENDMETHODS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:120:12: ( 'sdohtem' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:120:14: 'sdohtem'
            {
            match("sdohtem"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDMETHODS"

    // $ANTLR start "ENDMETHOD"
    public final void mENDMETHOD() throws RecognitionException {
        try {
            int _type = ENDMETHOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:121:11: ( 'dohtem' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:121:13: 'dohtem'
            {
            match("dohtem"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDMETHOD"

    // $ANTLR start "ENDWHILE"
    public final void mENDWHILE() throws RecognitionException {
        try {
            int _type = ENDWHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:122:10: ( 'elihw' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:122:12: 'elihw'
            {
            match("elihw"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDWHILE"

    // $ANTLR start "ENDIF"
    public final void mENDIF() throws RecognitionException {
        try {
            int _type = ENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:123:8: ( 'fi' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:123:10: 'fi'
            {
            match("fi"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDIF"

    // $ANTLR start "ENDFOR"
    public final void mENDFOR() throws RecognitionException {
        try {
            int _type = ENDFOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:124:9: ( 'rof' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:124:11: 'rof'
            {
            match("rof"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDFOR"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:125:11: ( ';' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:125:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:127:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:127:7: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:127:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "LPAR"
    public final void mLPAR() throws RecognitionException {
        try {
            int _type = LPAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:130:7: ( '(' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:130:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAR"

    // $ANTLR start "RPAR"
    public final void mRPAR() throws RecognitionException {
        try {
            int _type = RPAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:131:7: ( ')' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:131:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAR"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:132:6: ( '.' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:132:8: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:134:9: ( '=' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:134:11: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:135:7: ( '+' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:135:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:136:8: ( '-' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:136:10: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:137:8: ( '*' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:137:10: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "DIVIDED"
    public final void mDIVIDED() throws RecognitionException {
        try {
            int _type = DIVIDED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:138:10: ( '/' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:138:12: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVIDED"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:140:5: ( '==' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:140:7: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:141:5: ( '!=' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:141:7: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:142:5: ( '>' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:142:7: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:143:5: ( '<' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:143:7: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:144:5: ( '<=' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:144:7: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:145:5: ( '>=' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:145:7: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:148:10: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:148:12: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:148:17: (~ ( '\\n' | '\\r' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:148:31: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:148:31: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:150:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:150:7: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:150:7: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\t' && LA7_0 <= '\n')||LA7_0=='\r'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:8: ( PROG | ENDPROG | INTVAR | REALVAR | VOID | STRINGVAR | ARRAYVAR | INT | REAL | PRINTLN | DECLARATION | METHODS | METHOD | WHILE | IF | FOR | ENDDECLARATION | ENDMETHODS | ENDMETHOD | ENDWHILE | ENDIF | ENDFOR | SEMICOLON | ID | LPAR | RPAR | DOT | ASSIGN | PLUS | MINUS | TIMES | DIVIDED | EQ | NE | GT | LT | LE | GE | COMMENT | WS )
        int alt8=40;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:10: PROG
                {
                mPROG(); 


                }
                break;
            case 2 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:15: ENDPROG
                {
                mENDPROG(); 


                }
                break;
            case 3 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:23: INTVAR
                {
                mINTVAR(); 


                }
                break;
            case 4 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:30: REALVAR
                {
                mREALVAR(); 


                }
                break;
            case 5 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:38: VOID
                {
                mVOID(); 


                }
                break;
            case 6 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:43: STRINGVAR
                {
                mSTRINGVAR(); 


                }
                break;
            case 7 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:53: ARRAYVAR
                {
                mARRAYVAR(); 


                }
                break;
            case 8 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:62: INT
                {
                mINT(); 


                }
                break;
            case 9 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:66: REAL
                {
                mREAL(); 


                }
                break;
            case 10 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:71: PRINTLN
                {
                mPRINTLN(); 


                }
                break;
            case 11 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:79: DECLARATION
                {
                mDECLARATION(); 


                }
                break;
            case 12 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:91: METHODS
                {
                mMETHODS(); 


                }
                break;
            case 13 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:99: METHOD
                {
                mMETHOD(); 


                }
                break;
            case 14 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:106: WHILE
                {
                mWHILE(); 


                }
                break;
            case 15 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:112: IF
                {
                mIF(); 


                }
                break;
            case 16 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:115: FOR
                {
                mFOR(); 


                }
                break;
            case 17 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:119: ENDDECLARATION
                {
                mENDDECLARATION(); 


                }
                break;
            case 18 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:134: ENDMETHODS
                {
                mENDMETHODS(); 


                }
                break;
            case 19 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:145: ENDMETHOD
                {
                mENDMETHOD(); 


                }
                break;
            case 20 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:155: ENDWHILE
                {
                mENDWHILE(); 


                }
                break;
            case 21 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:164: ENDIF
                {
                mENDIF(); 


                }
                break;
            case 22 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:170: ENDFOR
                {
                mENDFOR(); 


                }
                break;
            case 23 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:177: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 24 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:187: ID
                {
                mID(); 


                }
                break;
            case 25 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:190: LPAR
                {
                mLPAR(); 


                }
                break;
            case 26 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:195: RPAR
                {
                mRPAR(); 


                }
                break;
            case 27 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:200: DOT
                {
                mDOT(); 


                }
                break;
            case 28 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:204: ASSIGN
                {
                mASSIGN(); 


                }
                break;
            case 29 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:211: PLUS
                {
                mPLUS(); 


                }
                break;
            case 30 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:216: MINUS
                {
                mMINUS(); 


                }
                break;
            case 31 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:222: TIMES
                {
                mTIMES(); 


                }
                break;
            case 32 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:228: DIVIDED
                {
                mDIVIDED(); 


                }
                break;
            case 33 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:236: EQ
                {
                mEQ(); 


                }
                break;
            case 34 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:239: NE
                {
                mNE(); 


                }
                break;
            case 35 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:242: GT
                {
                mGT(); 


                }
                break;
            case 36 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:245: LT
                {
                mLT(); 


                }
                break;
            case 37 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:248: LE
                {
                mLE(); 


                }
                break;
            case 38 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:251: GE
                {
                mGE(); 


                }
                break;
            case 39 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:254: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 40 :
                // /Users/lukaskukacka/Skola/FIT/mgr03/RUN/semestralka/mirun/mirun.g:1:262: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\7\16\1\46\4\16\5\uffff\1\60\3\uffff\1\62\1\uffff\1\64\1"+
        "\66\1\uffff\4\16\1\74\6\16\2\uffff\4\16\1\107\2\16\10\uffff\4\16"+
        "\1\116\1\uffff\1\16\1\120\7\16\1\130\1\uffff\6\16\1\uffff\1\137"+
        "\1\uffff\1\140\6\16\1\uffff\6\16\2\uffff\2\16\1\157\2\16\1\162\1"+
        "\16\1\164\3\16\1\171\1\172\1\16\1\uffff\1\16\1\175\1\uffff\1\16"+
        "\1\uffff\1\177\1\u0080\1\u0081\1\u0082\2\uffff\1\u0083\1\u0084\1"+
        "\uffff\1\u0085\7\uffff";
    static final String DFA8_eofS =
        "\u0086\uffff";
    static final String DFA8_minS =
        "\1\11\1\162\1\141\1\146\1\145\1\157\1\144\1\162\1\56\1\145\1\150"+
        "\1\151\1\154\5\uffff\1\75\3\uffff\1\57\1\uffff\2\75\1\uffff\1\151"+
        "\1\162\2\164\1\60\1\141\1\146\1\151\1\162\1\157\1\162\2\uffff\1"+
        "\143\1\150\1\151\1\162\1\60\1\141\1\151\10\uffff\1\147\1\156\1\147"+
        "\1\150\1\60\1\uffff\1\154\1\60\1\144\1\151\1\150\1\141\1\154\1\164"+
        "\1\154\1\60\1\uffff\1\154\1\150\1\162\1\164\2\157\1\uffff\1\60\1"+
        "\uffff\1\60\1\156\1\164\1\171\1\141\2\145\1\uffff\1\143\1\167\1"+
        "\141\1\154\1\162\1\144\2\uffff\1\147\1\145\1\60\1\162\1\155\1\60"+
        "\1\145\1\60\1\155\1\156\1\160\2\60\1\155\1\uffff\1\145\1\60\1\uffff"+
        "\1\144\1\uffff\4\60\2\uffff\2\60\1\uffff\1\60\7\uffff";
    static final String DFA8_maxS =
        "\1\172\1\162\1\145\1\156\2\157\1\164\1\162\1\71\1\157\1\150\1\157"+
        "\1\162\5\uffff\1\75\3\uffff\1\57\1\uffff\2\75\1\uffff\1\157\1\162"+
        "\2\164\1\172\1\141\1\146\1\151\1\162\1\157\1\162\2\uffff\1\143\1"+
        "\150\1\151\1\162\1\172\1\141\1\151\10\uffff\1\147\1\156\1\147\1"+
        "\150\1\172\1\uffff\1\154\1\172\1\144\1\151\1\150\1\141\1\154\1\164"+
        "\1\154\1\172\1\uffff\1\154\1\150\1\162\1\164\2\157\1\uffff\1\172"+
        "\1\uffff\1\172\1\156\1\164\1\171\1\141\2\145\1\uffff\1\143\1\167"+
        "\1\141\1\154\1\162\1\144\2\uffff\1\147\1\145\1\172\1\162\1\155\1"+
        "\172\1\145\1\172\1\155\1\156\1\160\2\172\1\155\1\uffff\1\145\1\172"+
        "\1\uffff\1\144\1\uffff\4\172\2\uffff\2\172\1\uffff\1\172\7\uffff";
    static final String DFA8_acceptS =
        "\15\uffff\1\27\1\30\1\31\1\32\1\33\1\uffff\1\35\1\36\1\37\1\uffff"+
        "\1\42\2\uffff\1\50\13\uffff\1\10\1\11\7\uffff\1\41\1\34\1\47\1\40"+
        "\1\46\1\43\1\45\1\44\5\uffff\1\17\12\uffff\1\25\6\uffff\1\3\1\uffff"+
        "\1\26\7\uffff\1\20\6\uffff\1\4\1\5\16\uffff\1\7\2\uffff\1\16\1\uffff"+
        "\1\24\4\uffff\1\15\1\6\2\uffff\1\23\1\uffff\1\1\1\12\1\2\1\14\1"+
        "\22\1\13\1\21";
    static final String DFA8_specialS =
        "\u0086\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\32\2\uffff\1\32\22\uffff\1\32\1\27\6\uffff\1\17\1\20\1\25"+
            "\1\23\1\uffff\1\24\1\21\1\26\12\10\1\uffff\1\15\1\31\1\22\1"+
            "\30\2\uffff\32\16\6\uffff\1\7\2\16\1\11\1\14\1\13\2\16\1\3\3"+
            "\16\1\2\2\16\1\1\1\16\1\4\1\6\2\16\1\5\1\12\3\16",
            "\1\33",
            "\1\34\3\uffff\1\35",
            "\1\37\7\uffff\1\36",
            "\1\40\11\uffff\1\41",
            "\1\42",
            "\1\44\17\uffff\1\43",
            "\1\45",
            "\1\47\1\uffff\12\10",
            "\1\50\11\uffff\1\51",
            "\1\52",
            "\1\54\5\uffff\1\53",
            "\1\56\5\uffff\1\55",
            "",
            "",
            "",
            "",
            "",
            "\1\57",
            "",
            "",
            "",
            "\1\61",
            "",
            "\1\63",
            "\1\65",
            "",
            "\1\70\5\uffff\1\67",
            "\1\71",
            "\1\72",
            "\1\73",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "",
            "",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\110",
            "\1\111",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "\1\117",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "",
            "",
            "\1\155",
            "\1\156",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\160",
            "\1\161",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\163",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\165",
            "\1\166",
            "\1\167",
            "\12\16\7\uffff\32\16\6\uffff\22\16\1\170\7\16",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\1\173",
            "",
            "\1\174",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "\1\176",
            "",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "\12\16\7\uffff\32\16\6\uffff\32\16",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PROG | ENDPROG | INTVAR | REALVAR | VOID | STRINGVAR | ARRAYVAR | INT | REAL | PRINTLN | DECLARATION | METHODS | METHOD | WHILE | IF | FOR | ENDDECLARATION | ENDMETHODS | ENDMETHOD | ENDWHILE | ENDIF | ENDFOR | SEMICOLON | ID | LPAR | RPAR | DOT | ASSIGN | PLUS | MINUS | TIMES | DIVIDED | EQ | NE | GT | LT | LE | GE | COMMENT | WS );";
        }
    }
 

}