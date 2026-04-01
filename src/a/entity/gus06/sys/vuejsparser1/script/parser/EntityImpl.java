package a.entity.gus06.sys.vuejsparser1.script.parser;

import a.framework.*;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.nio.file.Path;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260104";}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		CharStream cs = buildCharStream(obj);
		JavaScriptLexer lexer = new JavaScriptLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaScriptParser parser = new JavaScriptParser(tokens);
		ParseTree tree = parser.program();
		
		CustomListener listener = new CustomListener();
		ParseTreeWalker.DEFAULT.walk(listener, tree);
		
		return listener.getRoot();
	}
	
	private CharStream buildCharStream(Object obj)  throws Exception
	{
		if(obj instanceof String) return CharStreams.fromString((String) obj);
		if(obj instanceof Path) return CharStreams.fromPath((Path) obj);
		if(obj instanceof File) return CharStreams.fromPath(((File) obj).toPath());
		if (obj instanceof InputStream) return CharStreams.fromStream((InputStream) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
