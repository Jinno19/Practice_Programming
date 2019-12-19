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

	private stmtListNode(LexicalUnit first,Environment env) {
		this.env=env;
	}




	@Override
	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();

		while(true){
			if(stmtListNode.isFirst(first)) {
				stmt=stmtListNode.getHandler(first,env);
				return stmt.parse();
			}else {
				return false;
			}
		}
	}
	public String toString() {
		return stmt.toString();
	}
}
