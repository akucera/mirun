/*
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test lexikalniho analyzatoru.
 */
public class LexerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNextToken() throws IOException {
        FileReader r = new FileReader("test/compiler/tokens.txt");
        Lexer lex = new Lexer(r);
        for (Token exp : Token.values()) {
            Token t = lex.nextToken();
            assertEquals(exp, t);
        }
    }

    @Test
    public void testNumberTooLarge() throws IOException {
        try {
            Reader r = new CharArrayReader("12345678900".toCharArray());
            Lexer lex = new Lexer(r);
            lex.nextToken();
            fail("NumberTooLarge");
        } catch (LexerException e) {
            // ok
        }
    }

    @Test
    public void testUnknownSymbol() throws IOException {
        try {
            Reader r = new CharArrayReader("%".toCharArray());
            Lexer lex = new Lexer(r);
            lex.nextToken();
            fail("UnknownSymbol");
        } catch (LexerException e) {
            // ok
        }
    }
}
