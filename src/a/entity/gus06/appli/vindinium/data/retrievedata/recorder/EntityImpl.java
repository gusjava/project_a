package a.entity.gus06.appli.vindinium.data.retrievedata.recorder;

import java.io.File;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	public static final Pattern REGEXP_ID = Pattern.compile("\"id\":\"([^\"]+)\"");
	public static final Pattern REGEXP_SIZE = Pattern.compile("\"size\":([0-9]+)");

	private File storeDir;

	public EntityImpl() throws Exception
	{
		storeDir = (File) Outside.resource(this,"defaultdir");
		if(storeDir==null) throw new Exception("StoreDir is null");
	}


	public void p(Object obj) throws Exception
	{
		String text = (String) obj;
		
		Matcher m_id = REGEXP_ID.matcher(text);
		if(!m_id.find()) throw new Exception("Game id not found inside jison");
		String id = m_id.group(1);
		
		Matcher m_size = REGEXP_SIZE.matcher(text);
		if(!m_size.find()) throw new Exception("Game size not found inside jison");
		String size = m_size.group(1);
		
		File file = new File(storeDir,"["+size+"]"+id+".txt");
		if(file.exists()) return;
		
		PrintStream p = new PrintStream(file);
		p.print(text);
		p.close();
	}
}
