package a.entity.gus06.file.convert.csv.parser;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160723";}

	public static final char CHAR_DELIM1 = ';';
	public static final char CHAR_DELIM2 = ',';
	public static final char CHAR_DELIM3 = '\t';


	private Service csv1;
	private Service csv2;
	private Service csv3;


	public EntityImpl() throws Exception
	{
		csv1 = Outside.service(this,"gus06.file.convert.csv1.parser");
		csv2 = Outside.service(this,"gus06.file.convert.csv2.parser");
		csv3 = Outside.service(this,"gus06.file.convert.csv3.parser");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		int delim1Nb = 0;
		int delim2Nb = 0;
		int delim3Nb = 0;
		
		int len = s.length();
		for(int i=0;i<len;i++)
		{
			char c = s.charAt(i);
			switch(c)
			{
				case CHAR_DELIM1:delim1Nb++;break;
				case CHAR_DELIM2:delim2Nb++;break;
				case CHAR_DELIM3:delim3Nb++;break;
			}
		}
		
		if(delim1Nb > delim2Nb && delim1Nb > delim3Nb) return csv1.t(s);
		if(delim2Nb > delim3Nb) return csv2.t(s);
		
		return csv3.t(s);
	}
}
