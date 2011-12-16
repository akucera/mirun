package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
	
}
