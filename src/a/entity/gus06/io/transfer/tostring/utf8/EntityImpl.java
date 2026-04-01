package a.entity.gus06.io.transfer.tostring.utf8;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170410";}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		StringBuilder sb = new StringBuilder();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try
		{
			isr = new InputStreamReader(is,"UTF-8");
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