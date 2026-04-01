package a.entity.gus06.sys.filesdico2.buildline;

import a.framework.*;
import java.io.File;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150825";}
	

	private Service getMd5;
	private Service getMimeType;
	private Service getCreated;
	private Service getModified;
	private Service getCharset;
	private Service getPageNumber;
	
	public EntityImpl() throws Exception
	{
		getMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");
		getMimeType = Outside.service(this,"gus06.file.mime.tika.detect.asstring.s");
		getCreated = Outside.service(this,"gus06.file.creationtime.timestamp.s");
		getModified = Outside.service(this,"gus06.file.lastmodifiedtime.timestamp.s");
		getCharset = Outside.service(this,"gus06.file.info.string.charset.asstring.s");
		getPageNumber = Outside.service(this,"gus06.file.info.pagenumber.asstring.s");
	}

	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(!f.exists()) return null;
		
		String md5 = (String) getMd5.t(f);
		String mimeType = (String) getMimeType.t(f);
		String created = (String) getCreated.t(f);
		String modified = (String) getModified.t(f);
		String charset = (String) getCharset.t(f);
		String pageNumber = (String) getPageNumber.t(f);
		
		String size = ""+f.length();
		String name = f.getName();
		String location = f.getParentFile().getAbsolutePath();
		String pageNumber_ = pageNumber!=null?pageNumber:"";
		
		StringBuffer b = new StringBuffer();
		b.append(md5+"\t");
		b.append(modified+"\t");
		b.append(created+"\t");
		b.append(size+"\t");
		b.append(mimeType+"\t");
		b.append(charset+"\t");
		b.append(pageNumber_+"\t");
		b.append(location+"\t");
		b.append(name);
		
		return b.toString();
	}
}
