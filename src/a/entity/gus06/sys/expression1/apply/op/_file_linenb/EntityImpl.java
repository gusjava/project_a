package a.entity.gus06.sys.expression1.apply.op._file_linenb;

import a.framework.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180508";}


	private Service buildFile;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof File)
		{
			return lineNb((File) value);
		}
		if(value instanceof String)
		{
			File file = file((String) value, opMap);
			return lineNb(file);
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
	
	
	
	public Integer lineNb(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		int number = 0;
		while((br.readLine())!=null) number++;
		fis.close();
		
		return Integer.valueOf(number);
	}
}
