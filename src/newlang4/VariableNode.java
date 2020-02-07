package newlang4;

public class VariableNode extends Node {
	   String var_name;
	   Value v;
	   Environment env;

	    /** Creates a new instance of variable */
	    public VariableNode(String name) {
	        var_name = name;
	    }
	    public VariableNode(LexicalUnit u) {
	        var_name = u.getValue().getSValue();
	    }

	    public static boolean isMatch(LexicalType first) {
	        return (first == LexicalType.NAME);
	    }

	    public void setValue(Value my_v) {
	        v = my_v;
	    }

	    public Value getValue() {
	        return v;
	    }

	    public String toString() {
	    	String str = var_name;

	    	return str;
	    }

}
