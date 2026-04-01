package a.entity.gus06.app.info.framework.copyright;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140920";}
	
	public static final String START = "2014";
	
	private String c;

	
	public Object g() throws Exception
	{
		if(c==null) c = copyright();
		return c;
	}
	
	private String copyright()
	{
		String y = new SimpleDateFormat("yyyy").format(new Date());
		String yearInfo = y.equals(START)?START:START+" "+y;
		return "Framework gus06, Copyright \u00a9 "+yearInfo+" Augustin Delale";
	}
}
