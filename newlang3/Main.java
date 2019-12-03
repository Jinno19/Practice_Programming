package newlang3;

import java.io.FileNotFoundException;

public class Main {

		public static void main(String[] args) {
		String fname="test.bas";
		if(args.length>0)fname = args[0];
		
				
		LexicalAnalyzer la = null;
		try {
			la = new LexicalAnalyzerImpl(fname);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		while(true){
			LexicalUnit lu = null;
			try {
				lu = la.get();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		if(lu.getType() == LexicalType.EOF) break;
			System.out.println(lu);
		}
		
		}
}