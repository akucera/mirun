package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class FileUtil {


	/**
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
    public static byte[] getBytesFromFile(File f) throws IOException {
        InputStream is = new FileInputStream(f);
    
        long length = f.length();
    
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File is too big");
        }
    
        // vytvor pole bytu na ulozeni binarky do pameti
        byte[] bytes = new byte[(int)length];
    
        // postupne nacti byty
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // over, ze vsechna data byla v poradku nactena
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+f.getName());
        }
    
        is.close();
        return bytes;
    }
    
    public static void writeTextToFile(Object o, File f, boolean append) throws IOException {
    	try {
			// zapis data na disk, prepis existujici soubor (false = no append)
			FileOutputStream fos = new FileOutputStream(f, append);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter out = new BufferedWriter(osw);
			
			out.write(o.toString());

			out.close();
		} catch (IOException e) {// Catch exception if any
			throw e;
		}
    }
    
    
	
}
