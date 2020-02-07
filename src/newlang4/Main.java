package newlang4;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;
	        Node			handler;

	        System.out.println("basic parser");
	        lex = new LexicalAnalyzerImpl("src\\test2.bas");
	        env = new Environment(lex);
	        first = lex.get();
	        lex.unget(first);

	        if (Program.isFirst(first)) {

	        	handler = Program.getHandler(first, env);
	        	handler.parse();
	        	System.out.println(handler);
	        }
	        else System.out.println("syntax error");
	}

}

//19~24行目を使い回すらしい
