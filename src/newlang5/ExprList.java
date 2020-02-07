package newlang5;

import java.util.EnumSet;
import java.util.Set;

public class ExprList extends Node{
	
	
	static Set<LexicalType> first = EnumSet.of(
			LexicalType.NAME, LexicalType.INTVAL, LexicalType.LP);
			
	
	static public boolean isFirst(LexicalUnit u) {
		return first.contains(u.getType());
	}
	
	static public Node getHandler(LexicalUnit f, Environment e) {
		if(isFirst(f)) {
			return new ExprList(e);
		}
		return null;
	}
	private ExprList(Environment env) {
		this.env=env;
	}
	
	@Override
	public boolean parse() throws Exception{
		while(true) {
		LexicalUnit lu1=env.getInput().get();
		env.getInput().unget(lu1);
		if(!Expr.isFirst(lu1)) {
		
		return true;
		}
		Node e1=Expr.getHandler(lu1,env);
		e1.parse();
		child.add(e1);
		LexicalUnit lu2=env.getInput().get();
		if(lu2.getType() == LexicalType.COMMA) continue;
		env.getInput().unget(lu1);
		return true;
		}
	}

}
