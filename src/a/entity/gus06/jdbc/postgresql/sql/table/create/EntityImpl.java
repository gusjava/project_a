package a.entity.gus06.jdbc.postgresql.sql.table.create;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260107";}
	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.postgresql.format.sql.name");}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=6) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String[] col = (String[]) o[1];
		String[] type = (String[]) o[2];
		String[] primary = (String[]) o[3];
		String[] uniques = (String[]) o[4];
		String options = (String) o[5];
		
		if(col.length!=type.length) throw new Exception("Different col and type length: "+col.length+" <> "+type.length);
		
		String colPart = colPart(col,type);
		String primaryPart = primaryPart(primary);
		String uniquesPart = uniquesPart(uniques);
		
		StringBuffer b = new StringBuffer("CREATE TABLE "+format(path)+" ("+colPart);
		if(primaryPart!=null) b.append(","+primaryPart);
		if(uniquesPart!=null) b.append(","+uniquesPart);
		b.append(")");
		
		if(options!=null) b.append(" "+options);
		
		return b.toString();
	}
	
	private String colPart(String[] col, String[] type) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<col.length;i++)
			b.append(format(col[i])+" "+type[i]+",");
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String primaryPart(String[] primary) throws Exception
	{
		if(primary==null || primary.length==0) return null;
		
		StringBuffer b = new StringBuffer("PRIMARY KEY (");
		for(int i=0;i<primary.length;i++)
			b.append(format(primary[i])+",");
		b.deleteCharAt(b.length()-1);
		b.append(")");
		return b.toString();
	}
	
	private String uniquesPart(String[] uniques) throws Exception
	{
		if(uniques==null || uniques.length==0) return null;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<uniques.length;i++)
			b.append("UNIQUE KEY ("+format(uniques[i])+"),");
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
