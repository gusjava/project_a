package a.entity.gus06.file.read.string.autodetect.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160916";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Charset charset = (Charset) o[1];
		
		return read(file,charset);
	}
	
	
	private String read(File file, Charset charset) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,charset);
		return isrToString_v2(isr,(int) file.length());
	}
	
	
	private String isrToString_v1(InputStreamReader isr, int len) throws Exception
	{
		char[] a= new char[len];
		try {isr.read(a,0,len);}
		finally {isr.close();}
		return new String(a);
	}
	
	
	// texte distordu...
	private String isrToString_v2(InputStreamReader isr, int len) throws Exception
	{
		BufferedReader br = new BufferedReader(isr);
		StringBuffer b = new StringBuffer();
		
		String line;
		while((line = br.readLine())!=null) b.append(line+"\n");
		br.close();
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
