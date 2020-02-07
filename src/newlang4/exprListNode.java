package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class exprListNode extends Node {
	Node handler;
	Environment env;

	List<Node> exprlist=new ArrayList<Node>();

	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL
			);


	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new exprListNode(first,env);
	}

	private exprListNode(LexicalUnit first,Environment env) {
		this.env=env;
		type=NodeType.EXPR_LIST;
	}

	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();

		while(true) {
			handler=exprNode.getHandler(first, env);
			handler.parse();
			exprlist.add(handler);
			first=env.getInput().get();
			if(first.getType()!=LexicalType.COMMA) {
				env.getInput().unget(first);
				break;
			}
			first=env.getInput().get();
		}
		return true;
	}

	public String toString() {
		String s="";
		for(Node E:exprlist) {
			s+=E.toString();
		}
		return s;
	}
}
