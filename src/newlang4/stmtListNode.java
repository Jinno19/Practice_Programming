package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class stmtListNode extends Node {
	Node handler;
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.NAME, LexicalType.FOR, LexicalType.END,
			LexicalType.WHILE, LexicalType.IF, LexicalType.DO);

	private List<Node> stmts = new ArrayList<Node>();

	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		return new stmtListNode(first, env);
	}

	private stmtListNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.STMT_LIST;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();
		while (true) { // NLが来る限り飛ばし続ける
			if (first.getType() == LexicalType.NL) {
				first = env.getInput().get();
				// continue;
			} else {
				env.getInput().unget(first);
				break;
			}
		}

		while (true) {
			if (stmtNode.isFirst(first)) {
				handler = stmtNode.getHandler(first, env);
				stmts.add(handler);
				handler.parse();
				first = env.getInput().get();
			} else if (blockNode.isFirst(first)) {
				handler = blockNode.getHandler(first, env);
				handler.parse();
				first = env.getInput().get();
			}else if (first.getType() == LexicalType.NL) {
				first = env.getInput().get();
			} else {
				env.getInput().unget(first);
				break;
			}

		}
		return true;
	}

	@Override
	public String toString() {

		return stmts.toString();
	}
}
