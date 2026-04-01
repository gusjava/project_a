package a.entity.gus06.java.srccode.replacepackage;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251219";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String src = (String) o[0];
		String newPackageName = (String) o[1];
		
		if(newPackageName==null)
			return src.replaceFirst("(?m)^\\s*package\\s+[\\w\\.]+\\s*;\\s*\\n?","");
		
		Pattern p = Pattern.compile("(?m)^\\s*package\\s+[\\w\\.]+\\s*;");
		Matcher m = p.matcher(src);
		
		if(m.find()) return m.replaceFirst("package "+newPackageName+";");
		return "package "+newPackageName+";\n\n"+src;
	}
}
