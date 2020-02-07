package newlang4;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	   LexicalAnalyzer input;
	   Map<String, Function> library;
	   Map<String, VariableNode> var_table;

	    public Environment(LexicalAnalyzer my_input) {
	        input = my_input;
	        library = new HashMap<String, Function>();
	        library.put("PRINT", new PrintFunction() );//printfunctionは自分で作る
	        var_table = new HashMap<String, VariableNode>();
	    }

	    public LexicalAnalyzer getInput() {
 	        return input;
	    }
	    public Function getFunction(String fname) {
	        return (Function) library.get(fname);
	    }

	    public VariableNode getVariable(String vname) {
	        VariableNode v;
	        v = (VariableNode) var_table.get(vname);
	        if (v == null) {
	            v = new VariableNode(vname);
	            var_table.put(vname, v);
	        }
	        return v;
	    }
}
