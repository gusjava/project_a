package a.entity.gus06.entitydev2.generatesrc.tool.paramconvertor;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251205";}
	
	public Object t(Object obj) throws Exception
	{
		List params = (List) obj;
		
		if(params.isEmpty()) throw new Exception("No param found");
		
		StringBuilder sb = new StringBuilder();
		if(params.size() == 1)
		{
			Map param = (Map) params.get(0);
			handleParam(sb, param, "obj");
		}
		else
		{
			int nb = params.size();
			sb.append("\t\tObject[] o = (Object[]) obj;\n");
			sb.append("\t\tif(o.length!=").append(nb)
			.append(") throw new Exception(\"Wrong data number: \"+o.length);\n");
			
			for(int i=0; i<nb; i++)
			{
				Map param = (Map) params.get(i);
				handleParam(sb, param, "o["+i+"]");
			}
		}
		
		return sb.toString();
	}
	
	private void handleParam(StringBuilder sb, Map param, String newVarName)
	{
		String type = (String) param.get("type");
		String name = (String) param.get("name");
		sb.append("\t\t");
		
		if(type.equals("int"))
		{
			sb.append("int ");
			sb.append(name);
			sb.append(" = ((Integer) ");
			sb.append(newVarName);
			sb.append(").intValue();\n");
		}
		else if(type.equals("long"))
		{
			sb.append("long ");
			sb.append(name);
			sb.append(" = ((Long) ");
			sb.append(newVarName);
			sb.append(").longValue();\n");
		}
		else if(type.equals("double"))
		{
			sb.append("double ");
			sb.append(name);
			sb.append(" = ((Double) ");
			sb.append(newVarName);
			sb.append(").doubleValue();\n");
		}
		else if(type.equals("float"))
		{
			sb.append("float ");
			sb.append(name);
			sb.append(" = ((Float) ");
			sb.append(newVarName);
			sb.append(").floatValue();\n");
		}
		else if(type.equals("short"))
		{
			sb.append("short ");
			sb.append(name);
			sb.append(" = ((Short) ");
			sb.append(newVarName);
			sb.append(").shortValue();\n");
		}
		else if(type.equals("boolean"))
		{
			sb.append("boolean ");
			sb.append(name);
			sb.append(" = ((Boolean) ");
			sb.append(newVarName);
			sb.append(").booleanValue();\n");
		}
		else if(type.equals("Object"))
		{
			if(!name.equals(newVarName))
			{
				sb.append("Object ");
				sb.append(name);
				sb.append(" = ");
				sb.append(newVarName);
				sb.append(";\n");
			}
		}
		else
		{
			sb.append(type);
			sb.append(" ");
			sb.append(name);
			sb.append(" = ("+type+") ");
			sb.append(newVarName);
			sb.append(";\n");
		}
	}
}
