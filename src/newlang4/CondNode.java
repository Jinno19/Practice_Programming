package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class CondNode extends Node{

		Environment env;
		Node left,right;
		LexicalType op;

		static final Set<LexicalType> firstSet=EnumSet.of(
				LexicalType.NAME,
				LexicalType.INTVAL,
				LexicalType.DOUBLEVAL,
				LexicalType.LITERAL
				);

		static final Set<LexicalType> operators=EnumSet.of(
				LexicalType.EQ,
				LexicalType.GT,
				LexicalType.LT,
				LexicalType.GE,
				LexicalType.LE,
				LexicalType.NE
				);


	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}


	public static Node getHandler(LexicalUnit first, Environment env) {

		return new CondNode(first,env);
	}

	private CondNode(LexicalUnit first,Environment env) {
		this.env=env;
		type=NodeType.COND;
	}

	public boolean parse() throws Exception{
		LexicalUnit first=env.getInput().get();
		env.getInput().unget(first);
		if(!exprNode.isFirst(first)) {
			throw new Exception("比較できません");
		}
		left=exprNode.getHandler(first, env);
		left.parse();

		first=env.getInput().get();
		if(!operators.contains(first.getType())) {
			throw new Exception("Undifined Operator.");
		}
		op=first.getType();

		first=env.getInput().get();
		env.getInput().unget(first);
		if(!exprNode.isFirst(first)) {
			throw new Exception("比較できません");
		}
		right=exprNode.getHandler(first, env);
		right.parse();

		return true;
	}


	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return left.toString()+op+right.toString();
	}



}
