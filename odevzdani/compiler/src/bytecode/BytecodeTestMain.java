package bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Tahle trida slouzi jen pro testovani prevodu instrukci do bytecodu ze statickeho souboru
 * @author lukaskukacka
 *
 */
public class BytecodeTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = args[0];
		String instrString = null;

		try {
			instrString = readFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		/*
		InstructionCompiler compiler = new InstructionCompiler(instrString);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename+".bin");
			fos.write(compiler.generate());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		*/
	}
	
	private static String readFile(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
		}


}
