package a.entity.gus06.file.extract.infomap1;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150811";}
	
	public static final String KEY_RWX = "rwx";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_SHA1 = "sha1";
	public static final String KEY_NAME = "name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MIMETYPE = "mimetype";
	public static final String KEY_MIMEHIER = "mimehier";
	public static final String KEY_CHARSET = "charset";
	public static final String KEY_ENDOFLINE = "endofline";
	public static final String KEY_PAGENUMBER = "pagenumber";
	public static final String KEY_DURATION = "duration";
	
	public static final String KEY_CREATIONTIME = "creationtime";
	public static final String KEY_LASTMODIFIEDTIME = "lastmodifiedtime";
	public static final String KEY_LASTACCESSTIME = "lastaccesstime";



	private Service getMimeType;
	private Service getMimeHier;
	private Service getCharset;
	private Service getEndOfLine;
	private Service getPageNumber;
	private Service getDuration;
	private Service getRwx;
	
	private Service getMD5;
	private Service getSHA1;
	
	private Service getCreationTime;
	private Service getLastModifiedTime;
	private Service getLastAccessTime;


	public EntityImpl() throws Exception
	{
		getMimeType = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
		getMimeHier = Outside.service(this,"gus06.file.mime.tika.hierarchy.description");
		getCharset = Outside.service(this,"gus06.file.info.string.charset.asstring.s");
		getEndOfLine = Outside.service(this,"gus06.file.info.string.endofline.asstring.s");
		getPageNumber = Outside.service(this,"gus06.file.info.pagenumber.asstring.s");
		getDuration = Outside.service(this,"gus06.file.info.duration.asstring.s");
		getRwx = Outside.service(this,"gus06.file.rwx.tostring");
		
		getMD5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");
		getSHA1 = Outside.service(this,"gus06.crypto.hash.sha1.hexa.s");
		
		getCreationTime = Outside.service(this,"gus06.file.creationtime.timestamp");
		getLastModifiedTime = Outside.service(this,"gus06.file.lastmodifiedtime.timestamp");
		getLastAccessTime = Outside.service(this,"gus06.file.lastaccesstime.timestamp");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		
		String name = file.getName();
		String size = ""+file.length();
		String location = file.getParent();
		
		String rwx = (String) getRwx.t(file);
		String md5 = (String) getMD5.t(file);
		String sha1 = (String) getSHA1.t(file);
		String mimeType = (String) getMimeType.t(file);
		String mimeHier = (String) getMimeHier.t(file);
		String charset = (String) getCharset.t(file);
		String endOfLine = (String) getEndOfLine.t(file);
		String pageNumber = (String) getPageNumber.t(file);
		String duration = (String) getDuration.t(file);
		
		String creationTime = (String) getCreationTime.t(file);
		String lastModifiedTime = (String) getLastModifiedTime.t(file);
		String lastAccessTime = (String) getLastAccessTime.t(file);
		
		
		
		put(map,KEY_RWX,rwx);
		put(map,KEY_MD5,md5);
		put(map,KEY_SHA1,sha1);
		put(map,KEY_NAME,name);
		put(map,KEY_LOCATION,location);
		put(map,KEY_SIZE,size);
		put(map,KEY_MIMETYPE,mimeType);
		put(map,KEY_MIMEHIER,mimeHier);
		put(map,KEY_CHARSET,charset);
		put(map,KEY_ENDOFLINE,endOfLine);
		put(map,KEY_PAGENUMBER,pageNumber);
		put(map,KEY_DURATION,duration);
		
		put(map,KEY_CREATIONTIME,creationTime);
		put(map,KEY_LASTMODIFIEDTIME,lastModifiedTime);
		put(map,KEY_LASTACCESSTIME,lastAccessTime);
		
		return map;
	}
	
	
	
	private void put(Map map, String key, String value)
	{map.put(key,display(value));}
	
	private String display(String s)
	{return s!=null?s:"";}
}