package newlang4;

import java.util.EnumSet;
import java.util.Set;

/*
<subst> ::=
	<leftvar> <EQ> <expr>
 */

public class substNode extends Node {
	Node handler;
	Environment env;
	VariableNode leftvar;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.NAME);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new substNode(first, env);
	}

	private substNode(LexicalUnit first, Environment env) {
		this.env = env;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();

		if (!VariableNode.isMatch(first.getType())) {
			throw new Exception("Undefined Variable.");
		}
		leftvar = ((VariableNode) VariableNode.getHandler(env.getInput().get().getType(), env));
		first = env.getInput().get();

		if (first.getType() == LexicalType.EQ) {
			first = env.getInput().get();
		}

		if (exprNode.isFirst(first)) {
			env.getInput().unget(first);
			handler = exprNode.getHandler(first, env);
			handler.parse();
		} else {
			env.getInput().unget(first);
			return false;
		}
		return true;
	}

	public String toString() {
		return leftvar.toString() + "=" + handler.toString();
	}
}