package newlang5;

public class Expr {

	
	
	private Expr(Environment env) {
		this.env=env;
	}
	
	@Override
	publc boolean parse() {
		while(true) {
			Node op=getOperand();
			if (op!=null) return false;
				child.add(op);
				op=getOperator();
				if(op==null)break;
				child.add(op);
		}
		return true;
	}
	
	private Node getOperand() {
		return null;
	
	}
	
	private Node getOperator() {
		
	}
}
