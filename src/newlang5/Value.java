package newlang5;

import newlang4.ValueType;

public abstract class Value {
// �������ׂ��R���X�g���N�^
    public Value(String s) {};
    public Value(int i) {};
    public Value(double d) {};
    public Value(boolean b) {};
    public Value(String s, ValueType t) {};
    public abstract String get_sValue();
	public abstract String getSValue();
	// �X�g�����O�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract int getIValue();
    	// �����^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract double getDValue();
    	// �����_�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract boolean getBValue();
    	// �_���^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract ValueType getType();
}
