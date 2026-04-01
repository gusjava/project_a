package a.entity.gus06.string.lines.browse.openurl.withloginpwd;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240620";}


	private Service browseUrl;
	private Service perform;

	public EntityImpl() throws Exception
	{
		browseUrl = Outside.service(this,"gus06.awt.desktop.browse");
		perform = Outside.service(this,"gus06.sys.clipboard1.queueforpaste");
	}
	
	public void p(Object obj) throws Exception
	{
		String[] lines = toLines(obj);
		if(lines.length!=3) throw new Exception("Wrong line number: "+lines.length);
		
		String url = lines[0];
		String login = lines[1];
		String pwd = lines[2];
		
		perform.p(List.of(login, pwd));
		browseUrl.p(url);
	}
	
	private String[] toLines(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split("\n");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}