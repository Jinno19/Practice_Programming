package newlang5;

public class PrintFunction extends Function{

	@Override
    public Value invoke(ExprList arg) {
		String pstr="";
		for(Expr ex: arg.getValues()) {
			pstr+=ex.getValue().getSValue();
			
		}
    	System.out.println(pstr);       
    }
}
