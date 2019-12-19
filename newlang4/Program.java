package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Program extends Node{
	Node stmt_list;
	Environment env;

	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.WHILE,
			LexicalType.IF,
			LexicalType.DO
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit lu, Environment env) {

		return new Program(lu,env);
	}

	private Program(LexicalUnit first,Environment env) {
		this.env=env;
	}


	@Override
	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();

		if(stmtListNode.isFirst(first)) {
			stmt_list=stmtListNode.getHandler(first,env);
			return stmt_list.parse();
		}else {
			return false;
		}
	}
	public String toString() {
		return stmt_list.toString();
	}
}
	//LexicalAnalyzerImplを拡張し、ungetを用意しなければならない
//handlerを何処かにとっておく？
//toString()を拡張して、構文木を印刷する？
//getとungetを１つのメソッドにしておく
