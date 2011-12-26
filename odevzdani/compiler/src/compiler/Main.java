/*
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 *
 * pravidla jazyka :
 * bloky se uzaviraji zacatkem psanym pozadu (if fi, for rof, while elihw, program margorp apod.)
 * program je pojmenovan identifikatorem
 * posledni prikaz bloku nekonci ;
 * vsechny parametry metody jsou zakonceny ;
 * ... viz. gramatika
 */
package compiler;

import java.io.IOException;

/**
 *
 * @author aka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("arguments: <input.pas> <output.j>");
                System.exit(0);
            }
            Compiler c = new Compiler();
            c.compile(args[0], args[1]);
            //c.compile("intput.source", "output.j");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
