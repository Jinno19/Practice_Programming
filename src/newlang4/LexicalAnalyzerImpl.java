package newlang4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	PushbackReader reader;
	Deque<LexicalUnit> luDeque=new ArrayDeque<>();

	    Map<String, LexicalUnit> resword = new HashMap<String, LexicalUnit>();
	    Map<String, LexicalUnit> ope =new HashMap<String,LexicalUnit>();
	    Map<String, LexicalUnit> newline =new HashMap<String,LexicalUnit>();

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
	        newline.put("\n",new LexicalUnit(LexicalType.NL));
	        newline.put("\r",new LexicalUnit(LexicalType.NL));
	        newline.put("\r\n",new LexicalUnit(LexicalType.NL));
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

	public LexicalAnalyzerImpl(String fname) throws FileNotFoundException { //動作が何も書いてない！
		InputStream in = new FileInputStream(fname);
		Reader ir=new InputStreamReader(in);
		reader=new PushbackReader(ir);
	}


	@Override
	public LexicalUnit get() throws Exception {
		int ci;

		while(true) {

			if(luDeque.isEmpty()!=true) {
			return luDeque.pop();
			}

			ci=reader.read();

			reader.unread(ci);

			String str2=String.valueOf((char) ci);
			boolean b = Pattern.matches("\\ |\\t", str2);
			if(b==true) {
			reader.skip(1);
			}

			char c=(char) ci;
			if((c>='a' && c<='z')||(c>='A' && c<='Z')) {
				return getString();
			}

			char c1=(char) ci;
			if(c1>='0' && c1<='9') {
				return getNumber();
			}

			char c2=(char) ci;
			if(c2=='"') {
				return getLiteral();
			}

			char c3=(char)ci;
			String str=String.valueOf(c3);
			if(str.equals("+")||(str.equals("-"))||(str.equals("*"))||(str.equals("/"))
					||(str.equals(","))||(str.equals("."))||(str.equals("="))||(str.equals("<"))
					||(str.equals(">"))||(str.equals("("))||(str.equals(")"))) {
			return getSpecial();
			}

			char c4=(char) ci;
			String str3=String.valueOf(c4);
			if(newline.containsKey(str3)) {
				return getNL();
			}

			if(ci==-1 || ci==(char)-1) {
				return new LexicalUnit(LexicalType.EOF);
			}
			continue;
		}
	}
	private LexicalUnit getNL() throws Exception{
		String str,str2;

			while(true) {
			int ci=reader.read();
			char c4=(char)ci;
			str=String.valueOf(c4);
			if(newline.containsKey(str)) {
				int next=reader.read();
				char cx=(char)next;
				str2=String.valueOf(cx);
				if(newline.containsKey(str2)) {
					return new LexicalUnit(LexicalType.NL);
				}
				return new LexicalUnit(LexicalType.NL);
			}else {
			reader.unread(ci);
			break;
			}
			}


			return null;
	}



	private LexicalUnit getString() throws Exception { //
		String target="";
		while(true) {
			int ci=reader.read();
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
				String st=String.valueOf((char)ci);
				if(ci>='0' && ci<='9') {
					target+=st;
					continue;
				}
				reader.unread(ci);
				break;
			}
		return new LexicalUnit(LexicalType.INTVAL,
				new ValueImpl(target,ValueType.INTEGER));
		//Integer.parseInt(target)
	}

		private LexicalUnit getLiteral() throws Exception {
			String target="";
			String str;

			while(true) {
				int ci=reader.read();
				char c2=(char)ci;
					str=String.valueOf(c2);
					target+=str;

			boolean b = Pattern.matches("^\".*\"$",target);
			if(b==true) {
			target=target.substring(1,target.length()-1);
			return new LexicalUnit(LexicalType.LITERAL,
					new ValueImpl(target,ValueType.STRING));
			}

			}
		}

	private LexicalUnit getSpecial() throws Exception{
		String target="";
		String str;

		while(true) {
			int ci=reader.read();
			char c3=(char)ci;
			str=String.valueOf(c3);
			if(str.equals("+")||(str.equals("-"))||(str.equals("*"))||(str.equals("/"))
					||(str.equals(","))||(str.equals("."))||(str.equals("="))||(str.equals("<"))
					||(str.equals(">"))||(str.equals("("))
					||(str.equals(")"))) {
				target+=str;
				continue;
			}
			reader.unread(ci);
			break;
		}

			if(ope.containsKey(target)) {
				str=String.valueOf(ope.get(target));
				return new LexicalUnit(LexicalType.valueOf(str));
			}else {
			return null;
		}
	}


	@Override
	public boolean expect(LexicalType type) throws Exception {
		return false;
	}

	@Override
	public void unget(LexicalUnit token) throws Exception {
		luDeque.push(token);

	}
}

