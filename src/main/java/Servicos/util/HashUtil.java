package Servicos.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	
	public static String  getSecureHash(String texto) {
		
		String hash = DigestUtils.sha256Hex(texto);
		
		return hash;
	}

}
