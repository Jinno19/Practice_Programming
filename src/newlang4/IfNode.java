package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class IfNode extends Node {
	Node handler; // 条件を保存
	Node tAction; // true時の動作
	Node fAction; // false時の動作
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.IF, LexicalType.ELSEIF);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new IfNode(first, env);
	}

	private IfNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.IF_BLOCK;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();

		if (!IfNode.isFirst(first)) {
			throw new Exception("Undefined If.");
		}

		LexicalUnit second = env.getInput().get();
		env.getInput().unget(second);
		if (!CondNode.isFirst(second)) {
			throw new Exception("Undefined Cond.");
		}
		handler = CondNode.getHandler(second, env);
		handler.parse();

		first = env.getInput().get();
		if (first.getType() != LexicalType.THEN) {
			throw new Exception("Not found THEN.");
		}

		first = env.getInput().get();
		while (true) { // NLが来る限り飛ばし続ける
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
		tAction = stmtListNode.getHandler(first, env);
		tAction.parse();

		first = env.getInput().get();
		if (LexicalType.ENDIF == first.getType()) {
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
		if (LexicalType.ELSE == first.getType()) {
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
			fAction = stmtListNode.getHandler(first, env);
			fAction.parse();
			first = env.getInput().get();
			if (LexicalType.ENDIF == first.getType()) {
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
			}
		}

		if (LexicalType.ELSEIF == first.getType()) {
			env.getInput().unget(first);
			fAction = IfNode.getHandler(first, env);
			fAction.parse();
			return true;
		}
		return true;
	}

	@Override
	public String toString() {

		if (fAction == null) {
			return "IF " + handler.toString() + "THEN " + tAction.toString();

		} else {
			return "ELSEIF" + handler.toString() + fAction.toString();
		}
	}

}
