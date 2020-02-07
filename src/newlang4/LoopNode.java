package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class LoopNode extends Node{
	Node handler;
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


		LexicalUnit first=env.getInput().get();
		if(first.getType()!=LexicalType.DO) {
			throw new Exception("Not found DO.");
		}

		first=env.getInput().get();
		if(first.getType()!=LexicalType.UNTIL) {
			throw new Exception("Not found UNTIL.");
		}

		first=env.getInput().get();
		env.getInput().unget(first);
		if(!CondNode.isFirst(first)) {
			throw new Exception("Undefined Cond.");
		}
	}


}
