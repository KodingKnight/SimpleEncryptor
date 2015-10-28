package edu.clarkson.bruskajp.cs242.project1.encryption;

import java.nio.charset.StandardCharsets;

public class SimpleEncryptor extends Encryptor{

	private String plainText = null;
	private String encryptedText = null;
	
	public byte encryptionKey;
	
	
	/** Encrypts the text provided and saves the key */
	
	@Override
	public void textEncrypt() {
		byte[] partiallyEncryptedText = plainText.getBytes(StandardCharsets.US_ASCII);
		
		createEncryptionKey();
		for (int counter = 0; counter < partiallyEncryptedText.length; ++counter){
			partiallyEncryptedText[counter] = (byte) ((partiallyEncryptedText[counter] + 4) ^ encryptionKey);
		}
		this.encryptedText = new String(partiallyEncryptedText);
	}

	
	/** Decrypts the text provided with the provided key */
	
	@Override
	public void textDecrypt() {
		byte[] partiallyEncryptedText = encryptedText.getBytes(StandardCharsets.US_ASCII);
		
		for (int counter = 0; counter < partiallyEncryptedText.length; ++counter){
			partiallyEncryptedText[counter] = (byte) ((partiallyEncryptedText[counter] ^ encryptionKey) - 4);
		}
		this.plainText = new String(partiallyEncryptedText);
	}
	
	
	/** Generates the key from the string of text provided */
	
	private void createEncryptionKey() {
		int asciiSum = 0;
		char character;
		
		for (int counter = 0; counter < plainText.length(); ++counter){
			character = plainText.charAt(counter);
			asciiSum += (int) character;
		}
		this.encryptionKey = (byte) (asciiSum % 128);
	}
	
	
	public String getPlainText(){
		return this.plainText;
	}
	
	public void setPlainText(String plainText){
		this.plainText = plainText;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encyptedText) {
		this.encryptedText = encyptedText;
	}

	public byte getEncryptionKey() {
		return encryptionKey;
	}
	
	public void setEncryptionKey(byte encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

}
