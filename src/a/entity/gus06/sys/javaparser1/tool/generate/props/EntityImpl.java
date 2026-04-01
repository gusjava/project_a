package a.entity.gus06.sys.javaparser1.tool.generate.props;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220930";}
	


	private Service extractGetterP;
	private Service extractSetterP;

	public EntityImpl() throws Exception
	{
		extractGetterP = Outside.service(this,"gus06.java.srccode.extract.prop.getter");
		extractSetterP = Outside.service(this,"gus06.java.srccode.extract.prop.setter");
	}

	
	public Object t(Object obj) throws Exception
	{
		List methods = (List) obj;
		if(methods==null) return new HashMap();
		
		Map props = new HashMap();
		for(int i=0;i<methods.size();i++)
		{
			Map m = (Map) methods.get(i);
			List params = (List) get(m,"params");
			String name = (String) get(m,"name");
			String declaration = (String) get(m,"declaration");
			String returnType = (String) get(m,"return");
			
			String propG = (String) extractGetterP.t(name);
			String propS = (String) extractSetterP.t(name);
			
			if(propG!=null && returnType!=null && params.isEmpty())
			{
				String propName = propG;
				String propType = returnType;
				handleProp(props, propName, propType, declaration, null);
			}
			else if(propS!=null && returnType==null && params.size()==1)
			{
				Map param = (Map) params.get(0);
				String propName = propS;
				String propType = (String) get(param,"type");
				handleProp(props, propName, propType, null, declaration);	
			}
		}
		return props;
	}
	
	
	private void handleProp(Map props, String propName, String propType, String getter, String setter) throws Exception
	{
		Map m1 = (Map) get(props,propName);
		if(m1==null)
		{
			m1 = new HashMap();
			m1.put("name",propName);
			m1.put("type",propType);
			m1.put("readable",getter!=null);
			m1.put("writable",setter!=null);
			if(getter!=null) m1.put("getter",getter);
			if(setter!=null) m1.put("setter",setter);
			props.put(propName,m1);
		}
		else
		{
			String name = (String) get(m1,"name");
			String type = (String) get(m1,"type");
			
			if(!Objects.equals(name,propName)) throw new Exception("Invalid prop name: "+name);
			if(!Objects.equals(type,propType)) throw new Exception("Invalid prop type: "+type);
			
			if(getter!=null)
			{
				m1.put("readable",true);
				m1.put("getter",getter);
			}
			if(setter!=null)
			{
				m1.put("writable",true);
				m1.put("setter",setter);
			}
		}
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}