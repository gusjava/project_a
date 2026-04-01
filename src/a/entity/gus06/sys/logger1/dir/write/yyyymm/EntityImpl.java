package a.entity.gus06.sys.logger1.dir.write.yyyymm;

import a.framework.*;
import java.io.File;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190412";}

	private Service timeStampBuilder1;
	private Service timeStampBuilder2;

	public EntityImpl() throws Exception
	{
		timeStampBuilder1 = Outside.service(this,"gus06.time.date.yyyymmdd_hhmmss");
		timeStampBuilder2 = Outside.service(this,"gus06.time.date.yyyymm");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Date date = (Date) o[1];
		String line = (String) o[2];
		
		String timeStamp1 = (String) timeStampBuilder1.t(date);
		String timeStamp2 = (String) timeStampBuilder2.t(date);
		
		File file = new File(dir,timeStamp2+".txt");
		FileOutputStream fos = new FileOutputStream(file,true);
		PrintStream p = new PrintStream(fos,true,"UTF8");
		
		p.println(timeStamp1+"\t"+line);
		
		p.close();
	}
}
