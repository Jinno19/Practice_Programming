package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class stmtListNode extends Node {
	Node stmt,block;
	Environment env;

	static final Set<LexicalType> firstSet=EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.WHILE,
			LexicalType.IF,
			LexicalType.DO
			);


	private List<Node> stmts=new ArrayList<Node>();


	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception{

		return new stmtListNode(first, env);
	}

	private stmtListNode(LexicalUnit first,Environment env) {
		this.env=env;
		type=NodeType.STMT_LIST;
	}

	@Override
	public boolean parse() throws Exception{

		LexicalUnit first=env.getInput().get();

		while(true){
			if(stmtNode.isFirst(first)) {
				env.getInput().unget(first);
				stmt=stmtNode.getHandler(first,env);
				stmts.add(stmt);
				stmt.parse();
				}else {
			env.getInput().unget(first);
			return false;
			}
			first=env.getInput().get();
		}
	}

	@Override
	public String toString()  {

		return stmt.toString();
	}
}
