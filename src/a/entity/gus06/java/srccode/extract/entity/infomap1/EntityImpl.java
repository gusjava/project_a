package a.entity.gus06.java.srccode.extract.entity.infomap1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}
	
	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_SIGNATURE = "signature";
	public static final String KEY_IMPORTS = "imports";
	public static final String KEY_CREATIONDATE = "creationdate";
	public static final String KEY_DEPENDENCIES = "dependencies";
	public static final String KEY_SERVICECALLS = "servicecalls";
	public static final String KEY_RESOURCECALLS = "resourcecalls";



	private Service toArray;
	
	private Service extractName;
	private Service extractSignature;
	private Service extractImports;
	private Service extractDate;
	private Service extractDependencies;
	private Service extractServiceCalls;
	private Service extractResourceCalls;





	public EntityImpl() throws Exception
	{
		toArray = Outside.service(this,"gus06.java.srccode.toarray");
		
		extractName = Outside.service(this,"gus06.java.srccode.extract.entity.name");
		extractSignature = Outside.service(this,"gus06.java.srccode.extract.entity.signature1");
		extractImports = Outside.service(this,"gus06.java.srccode.extract.entity.imports1");
		extractDate = Outside.service(this,"gus06.java.srccode.extract.entity.creationdate");
		extractDependencies = Outside.service(this,"gus06.java.srccode.extract.entity.dependencies1");
		extractServiceCalls = Outside.service(this,"gus06.java.srccode.extract.entity.calls.service");
		extractResourceCalls = Outside.service(this,"gus06.java.srccode.extract.entity.calls.resource");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		
		Object entityName = extractName.t(lines);
		Object signature = extractSignature.t(lines);
		Object imports1 = extractImports.t(lines);
		Object creationDate = extractDate.t(lines);
		Object dependencies = extractDependencies.t(lines);
		Object serviceCalls = extractServiceCalls.t(lines);
		Object resourceCalls = extractResourceCalls.t(lines);

		if(creationDate==null) throw new Exception("Creation date not found for entity: "+entityName);

		Map map = new HashMap();
		
		if(entityName!=null) map.put(KEY_ENTITYNAME,entityName);
		if(signature!=null) map.put(KEY_SIGNATURE,signature);
		if(imports1!=null) map.put(KEY_IMPORTS,imports1);
		if(creationDate!=null) map.put(KEY_CREATIONDATE,creationDate);
		if(dependencies!=null) map.put(KEY_DEPENDENCIES,dependencies);
		if(serviceCalls!=null) map.put(KEY_SERVICECALLS,serviceCalls);
		if(resourceCalls!=null) map.put(KEY_RESOURCECALLS,resourceCalls);
		
		return map;
	}
}
