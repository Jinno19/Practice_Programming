package newlang4;

public class ValueImpl extends Value{

String Sg;
ValueType Vt;
int it;

	public ValueImpl(String s, ValueType t) {
		super(s, t);
		Sg=s;
		Vt=t;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ValueImpl(int i, ValueType t) {
		super(i,t);
		it=i;
		Vt=t;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	

	@Override
	public String get_sValue() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getSValue() {
		// TODO 自動生成されたメソッド・スタブ
		return Sg;
	}

	@Override
	public int getIValue() {
		// TODO 自動生成されたメソッド・スタブ
		return it;
	}

	@Override
	public double getDValue() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean getBValue() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public ValueType getType() {
		// TODO 自動生成されたメソッド・スタブ
		return Vt;
	}

}
