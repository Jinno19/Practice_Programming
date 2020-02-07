package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class IfNode extends Node {
	Node handler,tAction,fAction;
	Environment env;

	static final Set<LexicalType> firstSet = EnumSet.of(LexicalType.IF);

	public static boolean isFirst(LexicalUnit first) {

		return firstSet.contains(first.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {

		return new IfNode(first, env);
	}

	private IfNode(LexicalUnit first, Environment env) {
		this.env = env;
		type = NodeType.IF_BLOCK;
	}

	@Override
	public boolean parse() throws Exception {

		LexicalUnit first=env.getInput().get();

		if(!IfNode.isFirst(first)) {
			throw new Exception("Undifined If.");
		}

		LexicalUnit second=env.getInput().get();
		env.getInput().unget(second);
		if(!CondNode.isFirst(second)) {
			throw new Exception("Undifined Cond.");
		}
		handler=CondNode.getHandler(second, env);
		handler.parse();

		first=env.getInput().get();
		if(first.getType()!=LexicalType.THEN) {
			throw new Exception("Not found THEN.");
		}

		first=env.getInput().get();
		while (true) {//NLが来る限り飛ばし続ける
			if (first.getType() == LexicalType.NL) {
				first = env.getInput().get();
				continue;
			}else {
				break;
			}
		}

		first=env.getInput().get();
		env.getInput().unget(first);
		if(!stmtListNode.isFirst(first)) {
			throw new Exception("Not stmtList.");
		}
		tAction=stmtListNode.getHandler(first, env);
		tAction.parse();

		first=env.getInput().get();
		if(LexicalType.ENDIF==first.getType()) {
			first=env.getInput().get();
			while (true) {
				if (first.getType() == LexicalType.NL) {
					first = env.getInput().get();
					continue;
				}else {
					break;
				}
			}
			return true;
		}
		if(LexicalType.ELSE==first.getType()) {
			first=env.getInput().get();
			fAction=stmtListNode.getHandler(first, env);
			fAction.parse();
			first=env.getInput().get();
			if(LexicalType.ENDIF==first.getType()) {
				first=env.getInput().get();
				while (true) {
					if (first.getType() == LexicalType.NL) {
						first = env.getInput().get();
						continue;
					}else {
						break;
					}
				}

			}
		}
		return true;
	}


}
