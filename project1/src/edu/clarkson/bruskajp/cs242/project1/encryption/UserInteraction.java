package edu.clarkson.bruskajp.cs242.project1.encryption;

import java.io.File;
import java.util.Scanner;

public class UserInteraction {
	
	/** Main Method
	 * 	Runs the code to begin the program's user interface
	 *	Takes in command line input to run the encryption, decryption, file encryption, or file decryption 
	 *	
	 *	To use the SimpleEncryptor the triggering argument is "-e" and the second argument is the string to be encrypted
	 *		-e "Input String"
	 *	To use the SimpleDecryptor the triggering argument is "-d", the second argument is the string to be decrypted, and the third argument is the encryption key
	 *		-d "Input String" #
	 *	To use the FileEncryptor the triggering argument is "-fe", the second argument is the file to encrypt, and the third argument is the file to output to
	 *		-fe InputFile.txt OutputFile.txt 
	 *	To use the FileDecryptor the triggering argument is "-fd", the second argument is the file to decrypt, and the third argument is the file to output to
	 * 		-fd InputFile.txt OutputFile,txt #
	 * 	If an incorrect number of arguments are presented then it defaults to the normal menu version
	 * */ 
	
	public void userInteractionMain(String[] args){
		Scanner inputScanner = new Scanner(System.in);
		
		if(args.length == 0){
			menu(inputScanner);
		}else{
			switch(args[0]){
				case "-e":
					if(args.length == 2){
						encryptCommandLine(args[1]);
						break;
					}else{
						menu(inputScanner);
						break;
					}
				case "-d":
					if(args.length == 3){
						decryptCommandLine(args[1], args[2]);
						break;
					}else{
						menu(inputScanner);
						break;
					}
				case "-fe":	
					if(args.length == 3){
						fileEncryptCommandLine(args[1], args[2]);
						break;
					}else{
						menu(inputScanner);
						break;
					}
				case "-fd": 
					if(args.length == 4){
						fileDecryptCommandLine(args[1], args[2], args[3]);
						break;
					}else{
						menu(inputScanner);
						break;
					}
				default: 	
					menu(inputScanner);
			}	
		}		
	}
	

	/** Menu for the user interface */
	
	private void menu(Scanner inputScanner){
		System.out.println("Press q to Quit");
		System.out.println("Press 1 for Normal Encyption");
		System.out.println("Press 2 for Normal Decryption");
		System.out.println("Press 3 for File Encyption");
		System.out.println("Press 4 for File Decryption");
		System.out.print("Input: ");
		String input = inputScanner.nextLine();

		switch(input){
			case "q": 
				System.out.println("Quitting Program");
				break;
			case "1":	
				encrypt(inputScanner);
				break;
			case "2": 
				decrypt(inputScanner);
				break;
			case "3":
				fileEncrypt(inputScanner);
				break;
			case "4":
				fileDecrypt(inputScanner);
				break;
			default: 		
				System.out.println("Invalid Input");
				menu(inputScanner);
		}

		inputScanner.close();
	}
	
	
	/** User interface for encryption in the program */
	
	private void encrypt(Scanner inputScanner){
		SimpleEncryptor simpleEncryptor = new SimpleEncryptor();
		System.out.print("Plain Text: ");
		simpleEncryptor.setPlainText(inputScanner.nextLine());
		simpleEncryptor.textEncrypt();
		System.out.print("Encrypted Text: ");
		System.out.println(simpleEncryptor.getEncryptedText());
		System.out.print("Encryption Key: ");
		System.out.println(simpleEncryptor.getEncryptionKey());
	}
	
	
	/** User interface for decryption in the program */
	
	private void decrypt(Scanner inputScanner){
		SimpleEncryptor simpleEncryptor = new SimpleEncryptor();
		System.out.print("Encrypted Text: ");
		simpleEncryptor.setEncryptedText(inputScanner.nextLine());
		System.out.print("Encryption Key: ");
		simpleEncryptor.setEncryptionKey(inputScanner.nextByte());
		simpleEncryptor.textDecrypt();
		System.out.print("Plain Text: ");
		System.out.println(simpleEncryptor.getPlainText());
	}
	
	
	/** User interface for encryption in the command line */
	
	private void encryptCommandLine(String plainText){
		SimpleEncryptor simpleEncryptor = new SimpleEncryptor();
		simpleEncryptor.setPlainText(plainText);
		simpleEncryptor.textEncrypt();
		System.out.print("Encrypted Text: ");
		System.out.println(simpleEncryptor.getEncryptedText());
		System.out.print("Encryption Key: ");
		System.out.println(simpleEncryptor.getEncryptionKey());
	}
	
	
	/** User interface for decryption in the command line */
	
	private void decryptCommandLine(String encryptedText, String encryptionKey){
		SimpleEncryptor simpleEncryptor = new SimpleEncryptor();
		simpleEncryptor.setEncryptedText(encryptedText);
		simpleEncryptor.setEncryptionKey((byte) Integer.parseInt(encryptionKey));
		simpleEncryptor.textDecrypt();
		System.out.print("Plain Text: ");
		System.out.println(simpleEncryptor.getPlainText());
	}
	
	/** User interface for encryption of a file in the program */
	private void fileEncrypt(Scanner inputScanner){
		FileEncryptor fileEncryptor = new FileEncryptor();
		File inputFile;
		File outputFile;
		int counter = 0;
		
		do{
			if (counter > 4){
				System.out.println("File not found. Try again later.");
				System.exit(1);
			}else if(counter != 0){
				System.out.println("Invalid Input File Name");
			}
			System.out.print("Input File Name: ");
			inputFile = new File(inputScanner.nextLine());
			++counter;
		} while (!inputFile.exists());
		
		System.out.print("Output File Name: ");
		outputFile = new File(inputScanner.nextLine());
		
		
		fileEncryptor.setInputFile(inputFile);
		fileEncryptor.setOutputFile(outputFile);
		fileEncryptor.textEncrypt();
		System.out.print("Encryption Key: ");
		System.out.println(fileEncryptor.getEncryptionKey());
	}
	
	/** User interface for decryption of a file in the program */
	private void fileDecrypt(Scanner inputScanner){
		FileEncryptor fileEncryptor = new FileEncryptor();
		File inputFile;
		File outputFile;
		int counter = 0;
		
		do{
			if (counter > 4){
				System.out.println("File not found. Try again later.");
				System.exit(1);
			}else if(counter != 0){
				System.out.println("Invalid Input File Name");
			}
			System.out.print("Input File Name: ");
			inputFile = new File(inputScanner.nextLine());
			++counter;
		} while (!inputFile.exists());
		
		System.out.print("Output File Name: ");
		outputFile = new File(inputScanner.nextLine());
		
		fileEncryptor.setInputFile(inputFile);
		fileEncryptor.setOutputFile(outputFile);
		System.out.print("Encryption Key: ");
		fileEncryptor.setEncryptionKey(inputScanner.nextByte());
		fileEncryptor.textDecrypt();
		System.out.print("Done");
	}
	
	/** User interface for encryption of a file in the command line */
	private void fileEncryptCommandLine(String inputFileName, String outputFileName){
		FileEncryptor fileEncryptor = new FileEncryptor();
		Scanner inputScanner = new Scanner(System.in);
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		int counter = 0;
		
		while (!inputFile.exists()){
			if (counter > 4){
				System.out.println("File not found. Try again later.");
				System.exit(1);
			}else {
				System.out.println("Invalid Input File Name");
				System.out.print("Input File Name: ");
				inputFile = new File(inputScanner.nextLine());
				++counter;
			}
		} 
		inputScanner.close();
		
		fileEncryptor.setInputFile(inputFile);
		fileEncryptor.setOutputFile(outputFile);
		fileEncryptor.textEncrypt();
		System.out.print("Encryption Key: ");
		System.out.println(fileEncryptor.getEncryptionKey());
	}
	
	/** User interface for decryption of a file in the command line */
	private void fileDecryptCommandLine(String inputFileName, String outputFileName, String encryptionKey){
		FileEncryptor fileEncryptor = new FileEncryptor();
		Scanner inputScanner = new Scanner(System.in);
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		int counter = 0;
		
		while (!inputFile.exists()){
			if (counter > 4){
				System.out.println("File not found. Try again later.");
				System.exit(1);
			}else {
				System.out.println("Invalid Input File Name");
				System.out.print("Input File Name: ");
				inputFile = new File(inputScanner.nextLine());
				++counter;
			}
		} 
		inputScanner.close();
		
		fileEncryptor.setInputFile(inputFile);
		fileEncryptor.setOutputFile(outputFile);
		fileEncryptor.setEncryptionKey((byte) Integer.parseInt(encryptionKey));
		fileEncryptor.textDecrypt();
		System.out.print("Done");
	}
	
}
