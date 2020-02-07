package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class exprNode extends Node {
	Node handler;
	Environment env;
	Node L;
	Node R;
	Value v;
	LexicalType operator;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.NAME, LexicalType.INTVAL, LexicalType.DOUBLEVAL,
			LexicalType.LITERAL);

	static final Set<LexicalType> ope = EnumSet.of(LexicalType.ADD, LexicalType.SUB, LexicalType.MUL, LexicalType.DIV);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static boolean isOperator(LexicalUnit opera) {

		return ope.contains(opera.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new exprNode(first, env);
	}

	private exprNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.EXPR;
	}

	@Override
	public boolean parse() throws Exception {
		LexicalUnit first = env.getInput().get();
		env.getInput().unget(first);
		L = getOperand(first);

		first = env.getInput().get();
		env.getInput().unget(first);
		operator = getOperator(first);
		if (operator == null) {
			return true;
		}

		first = env.getInput().get();
		env.getInput().unget(first);
		R = getOperand(first);

		return true;
	}

	public Node getOperand(LexicalUnit ope) throws Exception {

		if (!isFirst(ope)) {
			throw new Exception(ope + " is not an operand.");
		}

		LexicalUnit first = env.getInput().get();

		if (constNode.isFirst(first)) {
			env.getInput().unget(first);
			handler = constNode.getHandler(first, env);
			handler.parse();
		} else {
			//env.getInput().unget(first); //変数読むノードはsubstNodeとexprNodeだけ
			handler = env.getVariable(first.getValue().getSValue()); //変数はどれを使うときも同じだからenvで変数を管理している。よって変数を使うときはこの処理
			handler.parse();
		}
		return handler;
	}

	public LexicalType getOperator(LexicalUnit ope) throws Exception {

		if (!isOperator(ope)) {
			return null;
		}

		LexicalUnit first = env.getInput().get();

		return first.getType();
	}

	public String toString() {
		return handler.toString();
	}
}
