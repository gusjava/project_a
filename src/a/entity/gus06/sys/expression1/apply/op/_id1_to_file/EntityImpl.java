package a.entity.gus06.sys.expression1.apply.op._id1_to_file;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160922";}
	
	public static final String C_FILE_MAPPER = "file_mapper";


	private Service getContext;
	private Service contextScriptFile;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		contextScriptFile = Outside.service(this,"gus06.sys.script1.access.context.script.file");
	}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		if(data==null) return null;
		if(data instanceof String) return findFile((String) data, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private File findFile(String id, Map opMap) throws Exception
	{
		Map context = (Map) getContext.t(opMap);
		
		File scriptFile = (File) contextScriptFile.t(context);
		if(scriptFile==null) throw new Exception("Script file not found inside context map: "+scriptFile);
		
		T t = (T) get(context,C_FILE_MAPPER);
		if(t==null) throw new Exception("File mapper not found inside context map");
		
		return (File) t.t(new Object[]{scriptFile,id});
	}
	
	
	private Object get(Map m, String k)
	{return m.containsKey(k)?m.get(k):null;}
}