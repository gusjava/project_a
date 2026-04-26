package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_c.copy.perform2.copyurl;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220603";}


	private Service copyText;
	private Service urlToText;

	public EntityImpl() throws Exception
	{
		copyText = Outside.service(this,"gus.x.clipboard.string");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.utf8");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String line = (String) obj;
		try
		{
			String text = (String) urlToText.t(new URL(line));
			copyText.p(text);
			return true;
		}
		catch(Exception e)
		{return false;}
	}
}