package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class callNode extends Node {
	Node handler;
	Environment env;
	Function funcname;// 関数名を保持

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.NAME);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new callNode(first, env);
	}

	private callNode(LexicalUnit first, Environment env) {
		this.env = env;
	}

	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();
		System.out.println(first.toString());
		funcname = env.getFunction(first.getValue().getSValue());
		first = env.getInput().get();

		// 「()」の存在を判断する
		boolean isBracket = false;
		if (first.getType() == LexicalType.LP) {
			isBracket = true;
		} else {
			env.getInput().unget(first);
		}

		first=env.getInput().get();
		env.getInput().unget(first);
		handler = exprListNode.getHandler(first, env);
		handler.parse();

		if (isBracket == true) {
			first = env.getInput().get();
			if (first.getType() != LexicalType.RP) {
				throw new Exception(")で閉じて");
			}

		}
		return true;
	}

	@Override
	public String toString() {


		System.out.println(funcname);
		System.out.println(handler);
		return funcname.toString()+" ("+handler.toString()+")";
	}

}
