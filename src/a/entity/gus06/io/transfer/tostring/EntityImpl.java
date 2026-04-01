package a.entity.gus06.io.transfer.tostring;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140725";}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		StringBuilder sb = new StringBuilder();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try
		{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine())!=null) sb.append(line+"\n");
		}
		finally
		{
			if(br!=null) br.close();
			if(isr!=null) isr.close();
			is.close();
		}
		return sb.toString();
	}
}