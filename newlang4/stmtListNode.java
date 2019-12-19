package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class stmtListNode extends Node {
	Node stmt,block;
	Environment env;
	
	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.WHILE,
			LexicalType.IF,
			LexicalType.DO
			);
	

	public static boolean isFirst(LexicalUnit first) {
		
		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {
		
		return new stmtListNode(first,env);
	}
	
	

}
