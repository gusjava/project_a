package a.entity.gus06.appli.gusclient1.execute.pseudo.register.step2;

import a.framework.*;
import java.io.File;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141014";}


	private Service init;
	private Service sendPost;
	
	public EntityImpl() throws Exception
	{
		init = Outside.service(this,"gus06.sys.crypto.pseudo.init");
		sendPost = Outside.service(this,"*gus06.web.httprequest.post.send");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String url = (String) obj;
		Map m = (Map) init.g();
		
		Map post = new HashMap();
		post.put("body",m);
		post.put("url",url);
		
		String res = (String) sendPost.t(post);
		
		JOptionPane.showMessageDialog(null,message(res));
	}
	
	
	
	private String message(String res)
	{
		if(res.equals("done")) return "Pseudo successfully registered.";
		return "Registration has failed.";
	}
}
