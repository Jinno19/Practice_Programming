package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class stmtNode extends Node {
	Node subst,call_sub,For,End;
	Environment env;

	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END
			);


	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new stmtNode(first,env);
	}

	private stmtNode(LexicalUnit first,Environment env) {
		this.env=env;
		type=NodeType.STMT;
	}


	@Override
	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();

			if(EndNode.isFirst(first)) {
				env.getInput().unget(first);
				End=EndNode.getHandler(first,env);
				End.parse();
			}else {
			env.getInput().unget(first);
			return false;
			}
			return true;
		}

	@Override
	public String toString()  {

		return End.toString();
	}
}