package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class EndNode extends Node {
	Node End;
	Environment env;

	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.END
			);


	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new EndNode(first,env);
	}

	private EndNode(LexicalUnit first,Environment env) {
		this.env=env;
		type=NodeType.END;
	}
	

	@Override
	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();


		if(EndNode.isFirst(first)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return "END";
	}
}