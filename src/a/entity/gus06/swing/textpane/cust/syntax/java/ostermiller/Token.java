package a.entity.gus06.swing.textpane.cust.syntax.java.ostermiller;

public abstract class Token {

    public static final int UNDEFINED_STATE = -1;
    public static final int INITIAL_STATE = 0;
    
    public abstract int getID();
    public abstract String getDescription();
    public abstract String getContents();
    public abstract boolean isComment();
    public abstract boolean isWhiteSpace();
    public abstract boolean isError();
    public abstract int getLineNumber();
    public abstract int getCharBegin();
    public abstract int getCharEnd();
    public abstract String errorString();
    public abstract int getState();
}


