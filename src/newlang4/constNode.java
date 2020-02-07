package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class constNode extends Node {
	Node handler;
	Environment env;
	Value v;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.INTVAL, LexicalType.DOUBLEVAL, LexicalType.LITERAL);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		return new constNode(first, env);
	}

	private constNode(LexicalUnit first, Environment env) throws Exception {
		this.env = env;
		switch (first.getType()) {
		case INTVAL:
			type = NodeType.INT_CONSTANT;
			break;
		case DOUBLEVAL:
			type = NodeType.DOUBLE_CONSTANT;
			break;
		case LITERAL:
			type = NodeType.STRING_CONSTANT;
			break;
		default:
			throw new Exception("Exception in constNode. first: " + first);
		}
	}

	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();
		System.out.println(first);
		if (constNode.isFirst(first)) {
			v = first.getValue();
		} else {
			throw new Exception("Not found ConstNode.");

		}
		return true;

	}

	public String toString() {
		return v.getSValue();
	}

}
