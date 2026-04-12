package a.entity.gus.y.docview1.pathtotext;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240107";}

	private Service pathToInputStream;
	private Service inputStreamToText;

	public EntityImpl() throws Exception {
		pathToInputStream = Outside.service(this, "readinputstream");
		inputStreamToText = Outside.service(this, "gus.x.io.build.string");
	}
	
	public Object t(Object obj) throws Exception {
		String path = (String) obj;
		if(path==null) return null;
		
		InputStream is = (InputStream) pathToInputStream.t(path);
		if (is == null) throw new Exception("InputStream not found for path: "+path);
		
		return inputStreamToText.t(is);
	}
}
