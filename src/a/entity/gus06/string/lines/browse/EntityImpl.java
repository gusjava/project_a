package a.entity.gus06.string.lines.browse;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210512";}


	private Service openFile;
	private Service openURL;
	private Service listToArray;
	
	public EntityImpl() throws Exception
	{
		openFile = Outside.service(this,"gus06.string.lines.browse.openfile");
		openURL = Outside.service(this,"gus06.string.lines.browse.openurl");
		listToArray = Outside.service(this,"gus06.convert.listtostringarray");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String[] lines = toLines(obj);
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i].trim();
			if(!line.equals("")) browse(line);
		}
	}
	
	
	private String[] toLines(Object obj) throws Exception
	{
		if(obj instanceof List) return (String[]) listToArray.t(obj);
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split("\n");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void browse(String line) throws Exception
	{
		if(openFile(line)) return;
		if(openURL(line)) return;
	}
	
	private boolean openFile(String line) throws Exception
	{return openFile.f(line);}
	
	private boolean openURL(String line) throws Exception
	{return openURL.f(line);}
}
