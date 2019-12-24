package newlang3;

import java.io.FileNotFoundException;

public class Main {

		public static void main(String[] args) {
		String fname="C:\\Users\\C0117157\\Documents\\src\\test.bas";
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

			System.out.println(lu);
			if(lu.getType() == LexicalType.EOF) break;
		}
		
		}
}