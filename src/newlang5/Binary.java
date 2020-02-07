package newlang5;

public class Binary extends Node{
	Node opr1, opr2;
	LexicalType op;
	
	public Binary(LexicalType op) {
		this.op=op;
	}
	
	@Override
	public Value getValue() throws Exception{
		Value v1=opr1.getValue();
		Value v2=opr2.getValue();
		switch(op) {
		case ADD:
		case SUB:
		case MUL:
		case DIV:
		
	}
	return null;

}
