package a.entity.gus06.sys.javaparser1.tool.buildinfo.annotations;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.expr.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220929";}

	
	public Object t(Object obj) throws Exception
	{
		ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) obj;
		return buildAnnotations(cid);
	}
	
	private List buildAnnotations(ClassOrInterfaceDeclaration cid)
	{
		NodeList<AnnotationExpr> nodeList = cid.getAnnotations();
		List annotations = new ArrayList();
		for(AnnotationExpr ae : nodeList)
		annotations.add(buildAnnotationMap(ae));
		return annotations;
	}
	
	private Map buildAnnotationMap(AnnotationExpr ae)
	{
		Map m = new HashMap();
		put(m,"name",ae.getNameAsString());
		
		if(ae instanceof NormalAnnotationExpr)
		{
			NodeList<MemberValuePair> nodeList = ((NormalAnnotationExpr) ae).getPairs();
			Map mvMap = new HashMap();
			for(MemberValuePair mvp : nodeList) mvMap.put(mvp.getNameAsString(),mvp.getValue().toString());
			put(m,"value",mvMap);
		}
		else if(ae instanceof SingleMemberAnnotationExpr)
		{
			Expression exp = ((SingleMemberAnnotationExpr) ae).getMemberValue();
			put(m,"value",exp.toString());
		}
		return m;
	}
	
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
}