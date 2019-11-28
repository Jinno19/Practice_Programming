package newlang3;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader; 

public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	PushbackReader reader;

	public LexicalAnalyzerImpl(InputStream in) throws FileNotFoundException {


		Reader ir=new InputStreamReader(in);
		reader=new PushbackReader(ir);
	}


	public LexicalAnalyzerImpl(String fname) {
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

			char c=(char)ci;
			if((c>='a' && c<='z')||(c>='A' && c<='Z')) {
				return getString();
			}

			int i=(int)ci;
			if(i>='0' && i<='9') {
				return getInt();
			}
			
			char c1=(char)ci;
			if(c1=='"') {
				return getLiteral();
			}
			
			
			
		}

		return specialGet;
		//それ以外を返す
	}




	private LexicalUnit getLiteral() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	private LexicalUnit getInt() {
		// TODO 自動生成されたメソッド・スタブ
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

	private LexicalUnit getString() throws Exception {
		String target="";

		while(true) {
			int ci=reader.read();
			if(ci<0) break;
			char c=(char) ci;
			if((c>='a'&&c<='z') || (c>='A' && c<='Z')){
				target+=c;
				continue;
			}
			reader.unread(ci);
			break;
		}
		return new LexicalUnit(LexicalType.NAME,
				new ValueImpl(target,ValueType.STRING));
	}

}
