package a.entity.gus06.swing.textpane.cust.syntax.java.ostermiller;

public interface Lexer {
    
    public Token getNextToken() throws java.io.IOException ;
    public void reset(java.io.Reader reader, int yyline, int yychar, int yycolumn) throws java.io.IOException;
}
