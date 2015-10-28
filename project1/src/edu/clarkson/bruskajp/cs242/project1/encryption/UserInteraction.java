package edu.clarkson.bruskajp.cs242.project1.encryption;

import java.util.Scanner;

public class UserInteraction {
	
	/** Main Method
	 * 	- runs the code to begin the program's user interface
	 *	- takes in command line input to run the encryption or decryption 
	 * */ 
	
	public static void main(String[] args){
		Scanner inputScanner = new Scanner(System.in);
		SimpleEncryptor simpleEncryptor = new SimpleEncryptor();
		
		if(args.length == 0){
			menu(inputScanner, simpleEncryptor);
		}else{
			switch(args[0]){
				case "-e":	encryptCommandLine(simpleEncryptor, args[1]);
								break;
				case "-d": 	decryptCommandLine(simpleEncryptor, args[1], args[2]);
								break;
				default: 	menu(inputScanner, simpleEncryptor);
			}	
		}		
	}
	
	
	/** Menu for the user interface */
	
	private static void menu(Scanner inputScanner, SimpleEncryptor simpleEncryptor){
		System.out.println("Encypt");
		System.out.println("Decrypt");
		System.out.print("Input: ");
		String input = inputScanner.nextLine();

		switch(input){
			case "Encrypt":	encrypt(simpleEncryptor, inputScanner);
				break;
			case "Decrypt": decrypt(simpleEncryptor, inputScanner);
				break;
			default: 		System.out.println("Invalid input");
				break;
		}

		inputScanner.close();
	}
	
	
	/** User interface for encryption in the program */
	
	private static void encrypt(SimpleEncryptor simpleEncryptor, Scanner inputScanner){
		System.out.print("Plain Text: ");
		simpleEncryptor.setPlainText(inputScanner.nextLine());
		simpleEncryptor.textEncrypt();
		System.out.print("Encrypted Text: ");
		System.out.println(simpleEncryptor.getEncryptionKey());
		System.out.print("Encryption Key: ");
		System.out.println(simpleEncryptor.getEncryptedText());
	}
	
	
	/** User interface for decryption in the program */
	
	private static void decrypt(SimpleEncryptor simpleEncryptor, Scanner inputScanner){
		System.out.print("Encrypted Text: ");
		simpleEncryptor.setEncryptedText(inputScanner.nextLine());
		System.out.print("Encryption Key: ");
		simpleEncryptor.setEncryptionKey(inputScanner.nextByte());
		simpleEncryptor.textDecrypt();
		System.out.print("Plain Text: ");
		System.out.println(simpleEncryptor.getPlainText());
	}
	
	
	/** User interface for encryption in the command line */
	
	private static void encryptCommandLine(SimpleEncryptor simpleEncryptor, String plainText){
		simpleEncryptor.setPlainText(plainText);
		simpleEncryptor.textEncrypt();
		System.out.print("Encrypted Text: ");
		System.out.println(simpleEncryptor.getEncryptedText());
		System.out.print("Encryption Key: ");
		System.out.println(simpleEncryptor.getEncryptionKey());
	}
	
	
	/** User interface for decryption in the command line */
	
	private static void decryptCommandLine(SimpleEncryptor simpleEncryptor, String encryptedText, String encrytionKey){
		simpleEncryptor.setEncryptedText(encryptedText);
		simpleEncryptor.setEncryptionKey((byte) Integer.parseInt(encrytionKey));
		simpleEncryptor.textDecrypt();
		System.out.print("Plain Text: ");
		System.out.println(simpleEncryptor.getPlainText());
	}
	
}
