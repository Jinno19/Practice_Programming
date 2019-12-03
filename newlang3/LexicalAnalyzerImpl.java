package newlang3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	PushbackReader reader;



	    Map<String, LexicalUnit> resword = new HashMap<String, LexicalUnit>();
	    Map<String, LexicalUnit> ope =new HashMap<String,LexicalUnit>();
	      {
	    	  {
	        resword.put("IF", new LexicalUnit(LexicalType.IF));
	        resword.put("THEN", new LexicalUnit(LexicalType.THEN));
	        resword.put("ELSE", new LexicalUnit(LexicalType.ELSE));
	        resword.put("FOR",new LexicalUnit(LexicalType.FOR));
	        resword.put("FORALL",new LexicalUnit(LexicalType.FORALL));
	        resword.put("NEXT",new LexicalUnit(LexicalType.NEXT));
	        resword.put("DIM",new LexicalUnit(LexicalType.DIM));
	        resword.put("AS",new LexicalUnit(LexicalType.AS));
	        resword.put("END",new LexicalUnit(LexicalType.END));
	        resword.put("EOF",new LexicalUnit(LexicalType.EOF));
	        ope.put("\\n",new LexicalUnit(LexicalType.NL));
	        ope.put("\\r",new LexicalUnit(LexicalType.NL));
	        ope.put("\\r\\n'",new LexicalUnit(LexicalType.NL));
	        ope.put("=",new LexicalUnit(LexicalType.EQ));
	        ope.put("<",new LexicalUnit(LexicalType.LT));
	        ope.put(">",new LexicalUnit(LexicalType.GT));
	        ope.put("<=",new LexicalUnit(LexicalType.LE));
	        ope.put("=<",new LexicalUnit(LexicalType.LE));
	        ope.put(">=",new LexicalUnit(LexicalType.GE));
	        ope.put("=>",new LexicalUnit(LexicalType.GE));
	        ope.put("<>",new LexicalUnit(LexicalType.NE));
	        ope.put("(",new LexicalUnit(LexicalType.LP));
	        ope.put(")",new LexicalUnit(LexicalType.RP));
	        ope.put(".",new LexicalUnit(LexicalType.DOT));
	        ope.put("+",new LexicalUnit(LexicalType.ADD));
	        ope.put("-",new LexicalUnit(LexicalType.SUB));
	        ope.put("*",new LexicalUnit(LexicalType.MUL));
	        ope.put("/",new LexicalUnit(LexicalType.DIV));
	        ope.put(",",new LexicalUnit(LexicalType.COMMA));
	        resword.put("WHILE",new LexicalUnit(LexicalType.WHILE));
	        resword.put("DO",new LexicalUnit(LexicalType.DO));
	        resword.put("UNTIL",new LexicalUnit(LexicalType.UNTIL));
	        resword.put("LOOP",new LexicalUnit(LexicalType.LOOP));
	        resword.put("TO",new LexicalUnit(LexicalType.TO));
	        resword.put("WEND",new LexicalUnit(LexicalType.WEND));

	    	  }
	      }




	public LexicalAnalyzerImpl(String fname) throws FileNotFoundException { //動作が何も書いてない！
		InputStream in = new FileInputStream(fname);
		Reader ir=new InputStreamReader(in);
		reader=new PushbackReader(ir);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	@Override
	public LexicalUnit get() throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		while(true) {
			int ci=reader.read();
			if(ci<0) {
			return new LexicalUnit(LexicalType.EOF);
			}
			reader.unread(ci);
			
			String str2=String.valueOf((char) ci);
			boolean b = Pattern.matches("[\\s]", str2);
			if(b==true) {
			reader.skip(ci);
			}
			

			char c=(char) ci;
			if((c>='a' && c<='z')||(c>='A' && c<='Z')) {
				return getString();
			}

			int c1=(char)ci;
			if(c1>='0' && c1<='9') {
				return getNumber();
			}else if(c1<0){
			return new LexicalUnit(LexicalType.EOF);
			}

			char c2=(char) ci;
			if(c2=='"') {
				return getLiteral();
			}
			

			char c3=(char)ci;
			String str=String.valueOf(c3);
			if(str.equals("+")||(str.equals("-"))||(str.equals("*"))||(str.equals("/"))
					||(str.equals(","))||(str.equals("."))||(str.equals("="))||(str.equals("<"))
					||(str.equals(">"))||(str.equals("\\n"))||(str.equals("\\r"))||(str.equals("("))
					||(str.equals(")"))) {
			return getSpecial();
			}
		}
	}
	
	

	private LexicalUnit getString() throws Exception { //
		String target="";
		while(true) {
			int ci=reader.read();
			if(ci<0) break;
			char c=(char)ci;
			if((c>='a'&&c<='z') || (c>='A' && c<='Z')){
				target+=c;
				continue;
			}
			reader.unread(ci);
			break;

		}
		if(resword.containsKey(target)) {
			return new LexicalUnit(LexicalType.valueOf(target));
		}
		return new LexicalUnit(LexicalType.NAME,
				new ValueImpl(target,ValueType.STRING));
	}

		private LexicalUnit getNumber() throws Exception { //
			String target="";

			while(true) {
				int ci=reader.read();
				if(ci<0) break;
				int c1=(char)ci;
				if(c1>='0' && c1<='9') {
					target+=c1;
					continue;
				}
				reader.unread(ci);
				break;
			}
		return new LexicalUnit(LexicalType.INTVAL,
				new ValueImpl(target,ValueType.INTEGER));
	}

		private LexicalUnit getLiteral() throws Exception { //
			String target="";

			while(true) {
				int ci=reader.read();
				if(ci<0) break;
				char c2=(char)ci;
				if(c2=='"') {
					target+=c2;
					continue;
				}
				reader.unread(ci);
				break;
			}
		return new LexicalUnit(LexicalType.NAME,
				new ValueImpl(target,ValueType.STRING));
	}


	private LexicalUnit getSpecial() throws Exception{
		String target="";

		while(true) {
			int ci=reader.read();
			if(ci<0) break;
			char c3=(char)ci;
			String str=String.valueOf(c3);
			if(str.equals("+")||(str.equals("-"))||(str.equals("*"))||(str.equals("/"))
					||(str.equals(","))||(str.equals("."))||(str.equals("="))||(str.equals("<"))
					||(str.equals(">"))||(str.equals("\\n"))||(str.equals("\\r"))||(str.equals("("))
					||(str.equals(")"))) {
				target+=str;
				continue;
			}
			reader.unread(ci);
			break;
		}
			if(ope.containsKey(target)) {
				return new LexicalUnit(LexicalType.valueOf(target));
			}
			return null;
		}





	@Override
	public boolean expect(LexicalType type) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void unget(LexicalUnit token) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}


}

