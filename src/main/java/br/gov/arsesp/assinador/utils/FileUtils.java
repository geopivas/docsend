package br.gov.arsesp.assinador.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.sun.jersey.core.util.Base64;

public class FileUtils {

	public static byte[] getBytesDoDocumento(String nomeArquivo) throws Exception {
		URL systemResource = ClassLoader.getSystemResource(nomeArquivo);
		if (systemResource == null) {
			throw new IllegalStateException("Arquivo não localizaddo: " + nomeArquivo);
		}
		File file = new File(systemResource.getFile());
		return getArrayDeBytesDoFile(file);
	}

	public static byte[] getArrayDeBytesDoFile(File file) throws FileNotFoundException, IOException {
		InputStream input = new FileInputStream(file);
		byte[] docEmBytes = new byte[input.available()];
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
	
	public static String getBase64DoArquivo(File arquivo) throws FileNotFoundException, IOException {
		byte[] arrayDeBytesDoFile = getArrayDeBytesDoFile(arquivo);
		return getBase64DoArrayDeBytes(arrayDeBytesDoFile);
	}

	public static String getBase64DoArrayDeBytes(byte[] arrayDeBytesDoFile) {
		byte[] encode = Base64.encode(arrayDeBytesDoFile);
		return new String(encode);
	}
	
	

}
