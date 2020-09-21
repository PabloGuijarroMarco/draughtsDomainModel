package main.java.code.draughts;

import main.java.code.utils.ClosedInterval;
import main.java.code.utils.Console;

enum Token {

	WMAN_TOKEN('w'),
	BMAN_TOKEN('b'),
	NULL_TOKEN(' '),
	WKING_TOKEN('W'),
	BKING_TOKEN('B');


	private char symbol;

	private Token(char symbol){
		this.symbol = symbol;
	}
	
	public boolean isNull() {
		return this.equals(Token.NULL_TOKEN);
	}

	void write() {
		Console.instance().write(this.symbol);
	}

	static Token get(int ordinal){
		assert new ClosedInterval(0, Token.values().length).isIncluded(ordinal);
		
		return Token.values()[ordinal];
	}

}
