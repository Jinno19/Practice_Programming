package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Program extends Node{
	
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

		return new StmtList();
	}
	
	
	@Override
	public boolean parse() throws Exception{
		
		LexicalUnit first=env.getInput().get();
		
		while(true){
		if(Stmt.isFirst(first)) {
			Node handler=Stmt.getHandler(first,env);
			handler.parse();
		}else if(Block.isFirst(first)) {
			Node handler=Block.getHandler(first,env);
			handler.parse();
		}else {
			break;
		}
		}
	}//LexicalAnalyzerImplを拡張し、ungetを用意しなければならない
	}//handlerを何処かにとっておく？
//toString()を拡張して、構文木を印刷する？
