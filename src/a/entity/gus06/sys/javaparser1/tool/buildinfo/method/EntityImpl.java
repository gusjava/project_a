package a.entity.gus06.sys.javaparser1.tool.buildinfo.method;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180224";}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		MethodDeclaration md = (MethodDeclaration) o[0];
		Map map = (Map) o[1];
		handleMD(md,map);
	}
	
	public Object t(Object obj) throws Exception
	{
		MethodDeclaration md = (MethodDeclaration) obj;
		Map map = new HashMap();
		handleMD(md,map);
		return map;
	}
	
	
	
	
	private void handleMD(MethodDeclaration md, Map map)
	{
		String name = buildName(md);
		String declaration = buildDeclaration(md);
		List params = buildParams(md);
		String sign = buildSign(name,params);
		String return_ = buildReturn(md);
		String body = buildBody(md);
		List modifiers = buildModifiers(md);
		List annotations = buildAnnotations(md);
		
		put(map,"name",			name);
		put(map,"declaration",		declaration);
		put(map,"sign",			sign);
		put(map,"params",		params);
		put(map,"return",		return_);
		put(map,"body",			body);
		put(map,"modifiers",		modifiers);
		put(map,"annotations",		annotations);
	}
	
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
	
	
	private String buildName(MethodDeclaration md)
	{
		return md.getNameAsString();
	}
	
	private String buildDeclaration(MethodDeclaration md)
	{
		return md.getDeclarationAsString();
	}
	
	private List buildParams(MethodDeclaration md)
	{
		List params = new ArrayList();
		Iterator<Parameter> it = md.getParameters().iterator();
		while(it.hasNext())
		{
			Parameter param = it.next();
			params.add(buildParam(param));
		}
		return params;
	}
	
	private Map buildParam(Parameter param)
	{
		Map map = new HashMap();
		map.put("name",param.getName().asString());
		map.put("type",param.getType().asString());
		return map;
	}
	
	private String buildReturn(MethodDeclaration md)
	{
		String s = md.getType().asString();
		return s.equals("void") ? null : s;
	}
	
	private String buildBody(MethodDeclaration md)
	{
		BlockStmt c = md.getBody().orElse(null);
		return c!=null ? c.toString() : null;
	}
	
	private List buildModifiers(MethodDeclaration md)
	{
		List modifiers = new ArrayList();
		Iterator<Modifier> it = md.getModifiers().iterator();
		while(it.hasNext()) modifiers.add(it.next().getKeyword().name().toLowerCase());
		return modifiers;
	}
	
	private List buildAnnotations(MethodDeclaration md)
	{
		List annotations = new ArrayList();
		int size = md.getAnnotations().size();
		for(int i=0;i<size;i++) annotations.add(md.getAnnotations().get(i).toString());
		return annotations;
	}
	
	private String buildSign(String name, List params)
	{
		StringBuffer b = new StringBuffer();
		b.append(name);
		b.append("(");
		int nb = params.size();
		for(int i=0;i<nb;i++)
		{
			Map param = (Map) params.get(i);
			b.append(param.get("type"));
			if(i<nb-1) b.append(",");
		}
		b.append(")");
		return b.toString();
	}
}