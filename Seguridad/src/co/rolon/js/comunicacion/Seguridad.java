package co.rolon.js.comunicacion;

import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Seguridad {
	
	private final static String ASIMETRICO = "RSA";
	
	private final static String SIMETRICO = "AES";
	
	public static String descifrarAsimetrico(PublicKey key, String str) throws Exception {
		byte[] bytes = Transformacion.destransformar(str);
		Cipher rsaCipher = Cipher.getInstance(ASIMETRICO);
		rsaCipher.init(Cipher.DECRYPT_MODE, key);
		byte[] desciphered = rsaCipher.doFinal(bytes);
		return Transformacion.transformar(desciphered);
	}
	
	public static String cifrarSimetrico(SecretKey key, String str) throws Exception {
		byte[] bytes = Transformacion.destransformar(str);
		Cipher aesCipher = Cipher.getInstance(SIMETRICO);
		aesCipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] desciphered = aesCipher.doFinal(bytes);
		return Transformacion.transformar(desciphered);
	}
	
	public static String descifrarSimetrico(SecretKey key, String str) throws Exception {
		byte[] bytes = Transformacion.destransformar(str);
		Cipher aesCipher = Cipher.getInstance(SIMETRICO);
		aesCipher.init(Cipher.DECRYPT_MODE, key);
		byte[] desciphered = aesCipher.doFinal(bytes);
		return Transformacion.transformar(desciphered);
	} 
}
