package a.entity.gus.y.docview1.gui1a.first;

import java.io.InputStream;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20231231";}
	
	private Service pathToInputStream;
	private Service inputStreamToText;
	private Service gui1;

	public EntityImpl() throws Exception {
		pathToInputStream = Outside.service(this, "m011_read_inputstream");
		inputStreamToText = Outside.service(this, "gus.x.io.build.string");
		gui1 = Outside.service(this, "*gus.y.docview1.gui1.first");
	}
	
	public void p(Object obj) throws Exception {
		String path = (String) obj;
		
		InputStream is = (InputStream) pathToInputStream.t(path);
		if (is == null) throw new Exception("InputStream not found for path: "+path);
		
		String text = (String) inputStreamToText.t(is);
		gui1.p(text);
	}
	
	public Object i() throws Exception {
		return gui1.i();
	}
}
