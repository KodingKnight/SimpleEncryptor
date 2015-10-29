package edu.clarkson.bruskajp.cs242.project1.encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEncryptor extends Encryptor{
	
	private File inputFile = null;
	private File outputFile = null;
	
	private char normalCharacter;
	private char encryptedCharacter;
	
	public byte encryptionKey;
	
	
	/** Encrypts the text provided and saves the key */
	
	@Override
	public void textEncrypt() {
		createEncryptionKey();
		
		BufferedReader bufferedReader;
		BufferedWriter bufferedWriter;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
			byte partiallyEncryptedCharacter;
			int tempInt;
			
			while((tempInt = readCharacterFromFile(bufferedReader)) != -1){
				normalCharacter = (char) tempInt;
				partiallyEncryptedCharacter = (byte) normalCharacter;
				partiallyEncryptedCharacter = (byte) ((partiallyEncryptedCharacter + 4) ^ this.encryptionKey);
				
				this.encryptedCharacter = (char) (partiallyEncryptedCharacter);
				
				writeCharacterToFile(bufferedWriter, this.encryptedCharacter);
			}
			
			bufferedReader.close();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/** Decrypts the text provided with the provided key */
	
	@Override
	public void textDecrypt() {		
		BufferedReader bufferedReader;
		BufferedWriter bufferedWriter;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
			int tempInt;
			
			while((tempInt = readCharacterFromFile(bufferedReader)) != -1){
				this.encryptedCharacter = (char) tempInt;
				
				byte partiallyEncryptedCharacter = (byte) encryptedCharacter;
				partiallyEncryptedCharacter = (byte) ((partiallyEncryptedCharacter ^ this.encryptionKey) - 4);
				
				this.normalCharacter = (char) (partiallyEncryptedCharacter);
				
				writeCharacterToFile(bufferedWriter, this.normalCharacter);
			}
			
			bufferedReader.close();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Imports text from file and stores it in the program */
	
	public int readCharacterFromFile(BufferedReader bufferedReader){	
		int integerCharacter = 0;
			
		try {
				integerCharacter = bufferedReader.read();
				return integerCharacter;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private void writeCharacterToFile(BufferedWriter bufferedWriter, char characterToWrite){
		try {
			bufferedWriter.write((int) characterToWrite);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	
	/** Generates the key from the string of text provided */
	
	private void createEncryptionKey() {
		long fileLength = 0;
		
		fileLength = inputFile.length();
		this.encryptionKey = (byte) (fileLength % 128);
	}
	
	
	public Character getCurrentCharacter(){
		return this.normalCharacter;
	}
	
	public void setcurrentCharacter(Character currentCharacter){
		this.normalCharacter = currentCharacter;
	}

	public Character getEncryptedCharacter() {
		return encryptedCharacter;
	}

	public void setEncryptedCharacter(Character encyptedCharacter) {
		this.encryptedCharacter = encyptedCharacter;
	}

	public byte getEncryptionKey() {
		return encryptionKey;
	}
	
	public void setEncryptionKey(byte encryptionKey) {
		this.encryptionKey = encryptionKey;
	}
	
	public File getInputFile() {
		return inputFile;
	}


	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public File getOutputFile() {
		return outputFile;
	}


	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

}
