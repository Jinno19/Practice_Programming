package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class LoopNode extends Node {
	Node handler;
	Node catchStmtList; // stmtListの値保持
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.DO, LexicalType.WHILE);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());

	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new LoopNode(first, env);
	}

	private LoopNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.LOOP_BLOCK;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();
		if (first.getType() != LexicalType.DO) {
			throw new Exception("Not found DO.");
		}

		first = env.getInput().get();
		if (first.getType() != LexicalType.UNTIL) {
			throw new Exception("Not found UNTIL.");
		}

		LexicalUnit second = env.getInput().get();
		env.getInput().unget(second);
		if (!CondNode.isFirst(second)) {
			throw new Exception("Undefined Cond.");
		}
		handler = CondNode.getHandler(second, env);
		handler.parse();

		first = env.getInput().get();
		while (true) {
			if (first.getType() == LexicalType.NL) {
				first = env.getInput().get();
				continue;
			} else {
				env.getInput().unget(first);
				break;
			}
		}

		first = env.getInput().get();
		env.getInput().unget(first);
		if (!stmtListNode.isFirst(first)) {
			throw new Exception("Not stmtList.");
		}
		catchStmtList = stmtListNode.getHandler(first, env);
		catchStmtList.parse();

		first = env.getInput().get();
		if (first.getType() != LexicalType.LOOP) {
			throw new Exception("Not found LOOP.");
		}

		first = env.getInput().get();
		while (true) {
			if (first.getType() == LexicalType.NL) {
				first = env.getInput().get();
				continue;
			} else {
				env.getInput().unget(first);
				break;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ

		return "DO UNTIL" + handler.toString() + catchStmtList.toString() + "LOOP";
	}

}
