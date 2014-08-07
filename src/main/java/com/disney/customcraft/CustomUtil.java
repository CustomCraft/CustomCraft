package com.disney.customcraft;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CustomUtil {
	
	/**
	 * Attempts to open a jar file to read a class from inside it.
	 * @param classLoc The full location of the class to be read.
	 * @return The byte array of the provided class.<br><b>null</b> if the class could not be read.
	 */
	public static byte[] readDummyClass(String classLoc) {
		byte[] bytes = null;
		ZipFile zipFile = null;
		InputStream inputStream = null;
	    
		try {
			zipFile = new ZipFile(CustomLoader.location);
			ZipEntry zipEntry = zipFile.getEntry(String.format("%s.class", new Object[] { classLoc.replace('.', '/') }));
			if(zipEntry == null) throw new IOException("Could not find file");

			int size = (int)zipEntry.getSize();
			
			bytes = new byte[size];
			inputStream = zipFile.getInputStream(zipEntry);
			
			int offset = 0;
			int ret = 0;
			while (offset < size && (ret = inputStream.read(bytes, offset, size - offset)) >= 0) {
				offset += ret;
			}
			
			if (offset < size) {
				throw new IOException("Could not completely read file");
			}
		} catch (Exception e) {
			return null;
		} finally {
			try{
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}

				if (zipFile != null) {
					zipFile.close();
					zipFile = null;
				}
			} catch(Exception e) {}
		}

	    return bytes;
	}

}
