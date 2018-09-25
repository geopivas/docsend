package br.gov.arsesp.assinador;

import java.io.File;
import java.io.InputStream;

import com.sun.jersey.core.util.Base64;

import br.gov.arsesp.assinador.utils.FileUtils;
import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {

	public void testArquivoTesteDoc() {
		File arquivo = FileUtils.getArquivo("testedoc.pdf");
		assertNotNull(arquivo);
	}
	
	public void testArquivoWord() {
		File arquivo = FileUtils.getArquivo("testedocword.docx");
		assertNotNull(arquivo);
	}
	
	public void testArquioEmBase64() throws Exception {
		byte[] bytesDoDocumento = FileUtils.getBytesDoDocumento("testedoc.pdf");
		byte[] encode = Base64.encode(bytesDoDocumento);
		System.out.println(new String(encode));
		assertTrue(Base64.isArrayByteBase64(encode));
	}
	
	public void testArquioInputStream() throws Exception {
		InputStream inputStreamDoDocumento = FileUtils.getInputStreamDoDocumento("testedoc.pdf");
		assertNotNull(inputStreamDoDocumento);
	}
	
	public void testConverterFileEmBase64() throws Exception {
		File arquivo = FileUtils.getArquivo("testedoc.pdf");
		String base64Encoded = FileUtils.getBase64DoArquivo(arquivo);
		assertTrue(Base64.isBase64(base64Encoded));
	}
	
	public void testConverterArrayDeBytesEmBase64() throws Exception {
		String base64DoArrayDeBytes = FileUtils.getBase64DoArrayDeBytes(FileUtils.getBytesDoDocumento("testedoc.pdf"));
		assertTrue(Base64.isBase64(base64DoArrayDeBytes));
	}
	
	
}
