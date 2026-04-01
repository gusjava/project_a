package a.entity.gus06.entitydev2.generatesrc.p1;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251205";}
	
	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_METHOD = "method";

	private Service buildBody;
	private Service creationDate;

	public EntityImpl() throws Exception
	{
		buildBody = Outside.service(this,"gus06.entitydev2.generatesrc.p1.body");
		creationDate = Outside.service(this,"gus06.entitydev2.generatesrc.tool.creationdate");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String entityName = get(map,KEY_ENTITYNAME);
		String method = get(map,KEY_METHOD);
		
		StringBuilder sb = new StringBuilder();
		sb.append("package gus06.entity."+entityName+";\n\n");
		sb.append("import a.framework.*;\n\n");
		sb.append("public class EntityImpl implements Entity, P {\n\n");
		sb.append((String) creationDate.g());
		sb.append("\tpublic void p(Object obj) throws Exception\n");
		sb.append("\t{\n");
		sb.append(buildBody(method)+"\n");
		sb.append("\t}\n");
		sb.append("}");
		return sb.toString();
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown key inside map: "+key);
		return (String) map.get(key);
	}
	
	private String buildBody(String method) throws Exception
	{return (String) buildBody.t(method);}
}
