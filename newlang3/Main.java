package newlang3;

import java.io.FileNotFoundException;

public class Main {

		public static void main(String[] args) throws FileNotFoundException {
		
		String fname="test.bas";
		
		LexicalUnit lexunit=null;
		
		LexicalAnalyzer analyzedContent=new LexicalAnalyzerImpl(fname);
		
		while(true){
		try {
			lexunit=analyzedContent.get();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println(lexunit);
		if(lexunit.getType() == LexicalType.EOF){
			break;
		}
		
		}
		}
}