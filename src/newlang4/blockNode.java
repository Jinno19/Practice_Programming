package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class blockNode extends Node {
	Node handler;
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.IF, LexicalType.DO, LexicalType.WHILE);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new blockNode(first, env);
	}

	private blockNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.BLOCK;
	}

	@Override
	public boolean parse() throws Exception {
		
		LexicalUnit first=env.getInput().get();
		env.getInput().unget(first);
		
		if(IfNode.isFirst(first)) {
			handler=IfNode.getHandler(first, env);
			return handler.parse();
		}
		
		
	}

}
