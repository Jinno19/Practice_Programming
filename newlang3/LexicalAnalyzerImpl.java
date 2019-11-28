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
			reader.unread(ci);

			char c=(char)ci;
			if((c>='a' && c<='z')||(c>='A' && c<='Z')) {
				return getString();
			}

			int c1=(char)ci;
			if(c1>='0' && c1<='9') {
				return getInt();
			}
			
			char c2=(char)ci;
			if(c2=='"') {
				return getLiteral();
			}
			
			return specialGet();
			//それ以外を返す
		}
	}




	private LexicalUnit specialGet() {
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
		return new LexicalUnit(LexicalType.NAME,
				new ValueImpl(target,ValueType.STRING));
	}
		
		private LexicalUnit getInt() throws Exception { //
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
		return new LexicalUnit(LexicalType.NAME,
				new ValueImpl(target,ValueType.STRING));
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

}

