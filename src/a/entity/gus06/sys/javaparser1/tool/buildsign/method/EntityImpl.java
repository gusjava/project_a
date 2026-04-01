package a.entity.gus06.sys.javaparser1.tool.buildsign.method;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}


	
	public Object t(Object obj) throws Exception
	{
		MethodDeclaration md = (MethodDeclaration) obj;
		
		StringBuffer b = new StringBuffer();
		buildName(b, md);
		b.append("(");
		buildParams(b, md);
		b.append(")");
		return b.toString();
	}
	
	private void buildName(StringBuffer b, MethodDeclaration md)
	{
		b.append(md.getNameAsString());
	}
	
	private void buildParams(StringBuffer b, MethodDeclaration md)
	{
		Iterator<Parameter> it = md.getParameters().iterator();
		boolean found = false;
		while(it.hasNext())
		{
			if(found) b.append(",");
			found = true;
			buildParam(b, it.next());
		}
	}
	
	private void buildParam(StringBuffer b, Parameter param)
	{
		b.append(param.getType().asString());
	}
}