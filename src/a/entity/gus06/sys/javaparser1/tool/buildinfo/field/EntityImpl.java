package a.entity.gus06.sys.javaparser1.tool.buildinfo.field;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20220928";}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		FieldDeclaration fd = (FieldDeclaration) o[0];
		Map map = (Map) o[1];
		handleFD(fd,map);
	}
	
	public Object t(Object obj) throws Exception
	{
		FieldDeclaration fd = (FieldDeclaration) obj;
		Map map = new HashMap();
		handleFD(fd,map);
		return map;
	}
	
	
	
	
	private void handleFD(FieldDeclaration fd, Map map)
	{
		put(map,"name",			buildName(fd));
		put(map,"type",			buildType(fd));
		put(map,"modifiers",		buildModifiers(fd));
		put(map,"annotations",		buildAnnotations(fd));
	}
	
	
	public void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
	
	
	
	private String buildName(FieldDeclaration fd)
	{
		VariableDeclarator vd = fd.getVariables().get(0);
		return vd.getNameAsString();
	}
	
	private String buildType(FieldDeclaration fd)
	{
		VariableDeclarator vd = fd.getVariables().get(0);
		return vd.getType().asString();
	}
	
	private List buildModifiers(FieldDeclaration fd)
	{
		List modifiers = new ArrayList();
		Iterator<Modifier> it = fd.getModifiers().iterator();
		while(it.hasNext()) modifiers.add(it.next().getKeyword().name().toLowerCase());
		return modifiers;
	}
	
	private List buildAnnotations(FieldDeclaration fd)
	{
		List annotations = new ArrayList();
		int size = fd.getAnnotations().size();
		for(int i=0;i<size;i++) annotations.add(fd.getAnnotations().get(i).toString());
		return annotations;
	}
}