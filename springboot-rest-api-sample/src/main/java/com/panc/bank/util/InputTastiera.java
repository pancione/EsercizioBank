package com.panc.bank.util;

import java.util.Scanner;

public class InputTastiera {

	private Scanner in;
	
	private static InputTastiera instance;
	
	private InputTastiera() {
		in = new Scanner(System.in);
	}
	
	public static InputTastiera getInstance() {
		if(instance == null)
			instance = new InputTastiera();
		return instance;
	}
	
	public int getInt() {
		int out;			
		try {
			out = in.nextInt();
		} finally {
			in.nextLine();	
		}
		return out;
	}
	
	public String getString() {
		return in.nextLine();
	}
}
