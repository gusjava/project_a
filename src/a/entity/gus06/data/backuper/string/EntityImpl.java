package a.entity.gus06.data.backuper.string;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, G, R, P, V {

	public String creationDate() {return "20150426";}
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}


	private Service toString;
	private File storeDir;



	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
		storeDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String s = (String) toString.t(obj);
		
		File dir = new File(storeDir,key);
		dir.mkdirs();
		
		File file = new File(dir,now()+".txt");
		PrintStream p = new PrintStream(file);
		p.print(s);
		p.close();
	}
	
	
	
	
	public Object r(String key) throws Exception
	{return new File(storeDir,key);}
	
	
	public void p(Object obj) throws Exception
	{v("default",obj);}
	
	
	public Object g() throws Exception
	{return r("default");}
}
