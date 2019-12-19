package newlang4;

import java.io.FileInputStream;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
			FileInputStream fin = null;
	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;
	        Node			program;
	  
	        System.out.println("basic parser");
	        fin = new FileInputStream("end.bas");
	        lex = new LexicalAnalyzerImpl(fin);
	        env = new Environment(lex);
	        first = lex.get();
	        lex.unget(first);
	        
	        if (Program.isFirst(first)) {
	        	Node handler = Program.getHandler(first, env);
	        	handler.parse();
	        	System.out.println(handler);
	        }
	        else System.out.println("syntax error");
	}

}

//19~24行目を使い回すらしい
