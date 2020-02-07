package newlang4;

import java.util.HashMap;
import java.util.Map;

public class BinOperatorNode extends Node{
	Node handler;
	Environment env;
	Node L;
	Node R;
	LexicalType O;

	
	private static final Map<LexicalType,Integer> OPE=new HashMap<>();
	{
		OPE.put(LexicalType.MUL,1);
		OPE.put(LexicalType.DIV,1);
		OPE.put(LexicalType.ADD,2);
		OPE.put(LexicalType.SUB,2);
	}
	

	public static boolean isFirst(LexicalType first) {

		return OPE.containsKey(first);
	}
	
	public static Node getHandler(LexicalUnit first, Environment env) {

		return new BinOperatorNode(first,env);
	}
	
	private BinOperatorNode(LexicalUnit first, Environment env) {
		this.env=env;
	}
	
	

}
