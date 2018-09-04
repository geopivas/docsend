package br.gov.arsesp.assinador.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class FileUtils {

	public static byte[] getBytesDoDocumento(String nomeArquivo) throws Exception {		
		byte[] docEmBytes = null;
		URL systemResource = ClassLoader.getSystemResource(nomeArquivo);
		if (systemResource == null) {
			throw new IllegalStateException("Arquivo não localizaddo: " + nomeArquivo);
		}
		File file = new File(systemResource.getFile());
		InputStream input = new FileInputStream(file);
		docEmBytes = new byte[input.available()];
		input.read(docEmBytes);
		input.close();
		return docEmBytes;
	}
	
	public static InputStream getInputStreamDoDocumento(String nomeArquivo) throws Exception {
		File file = getArquivo(nomeArquivo);
		InputStream input = new FileInputStream(file);
		return input;
	}

	public static File getArquivo(String nomeArquivo) {
		URL systemResource = ClassLoader.getSystemResource(nomeArquivo);
		if (systemResource == null) {
			throw new IllegalStateException("Arquivo não localizaddo: " + nomeArquivo);
		}
		File file = new File(systemResource.getFile());
		return file;
	}
	
	

}
