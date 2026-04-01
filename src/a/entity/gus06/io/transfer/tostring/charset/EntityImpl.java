package a.entity.gus06.io.transfer.tostring.charset;

import a.framework.*;
import java.io.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250509";}


	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) o[0];
		Charset charset = (Charset) o[1];
		
		StringBuilder sb = new StringBuilder();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try
		{
			isr = new InputStreamReader(is, charset);
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
