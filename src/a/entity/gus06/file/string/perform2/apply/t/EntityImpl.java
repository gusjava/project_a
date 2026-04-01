package a.entity.gus06.file.string.perform2.apply.t;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20151014";}


	private Service readFile;
	private Service writeFile;
	private Service copyFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		writeFile = Outside.service(this,"gus06.file.write.string.charsetfrom");
		copyFile = Outside.service(this,"gus06.file.op.copy.replace");
	}


	

	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File input = (File) o[0];
		File output = (File) o[1];
		T t = (T) o[2];
		
		String text = (String) readFile.t(input);
		String text1 = (String) t.t(text);
		if(text.equals(text1)) return copyFile(input,output);
		
		writeFile.p(new Object[]{output,input,text1});
		return true;
	}
	
	
	private boolean copyFile(File input, File output) throws Exception
	{
		if(input.equals(output)) return false;
		copyFile.p(new File[]{input,output});
		return true;
	}
}
