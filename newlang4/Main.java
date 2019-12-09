package newlang4;

import newlang3.LexicalAnalyzerImpl;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;
	        Node			program;
	  
	        System.out.println("basic parser");
	        lex = new LexicalAnalyzerImpl("test.bas");
	        env = new Environment(lex);
	        first = lex.get();
	        
	        if (Program.isFirst(first)) {
	        	Node handler = Program.getHandler(first, env);
	        	handler.parse();
	        	System.out.println(handler);
	        }
	        else System.out.println("syntax error");
	}

}
