package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class stmtNode extends Node {
	Node handler;
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.NAME, LexicalType.FOR, LexicalType.END);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new stmtNode(first, env);
	}

	private stmtNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.STMT;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();

		if (EndNode.isFirst(first)) {
			env.getInput().unget(first);
			handler = EndNode.getHandler(first, env);
			return handler.parse();
		}

		if (substNode.isFirst(first)) {
			LexicalUnit second = env.getInput().get();
			env.getInput().unget(second);// スタックはLIFO処理のため、先にsecondをungetする
			env.getInput().unget(first);// firstをungetする
			if (second.getType() == LexicalType.LP || exprListNode.isFirst(second)) {
				handler = callNode.getHandler(first, env);
				return handler.parse();
			}
			if (second.getType() == LexicalType.EQ) {
				handler = substNode.getHandler(second, env);
				return handler.parse();
			}

		}
		return false;
	}

	@Override
	public String toString() {

		return handler.toString();
	}
}