package a.entity.gus06.file.string.reader.handlelines.autodetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;
import java.util.Set;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150922";}


	private Service getLineNumber;
	private Service findCharset;
	
	public EntityImpl() throws Exception
	{
		getLineNumber = Outside.service(this,"gus06.file.string.info.linenumber");
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
	}
	
	
	


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		P handler = (P) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		if(progress!=null)
		{
			String nb = (String) getLineNumber.t(file);
			((V)progress).v("size",nb);
		}
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,charset(file));
		BufferedReader br = new BufferedReader(isr);
		
		try
		{
			String line = null;
			while((line = br.readLine())!=null)
			{
				handler.p(line);
				if(progress!=null) ((E) progress).e();
				if(interrupt!=null && !interrupt.isEmpty()) break;
			}
		}
		finally {fis.close();}
	}
	
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}
