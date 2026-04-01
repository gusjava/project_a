package a.entity.gus06.sys.filemanagement1.scan.store.properties;

import a.framework.*;
import java.util.Properties;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191128";}


	private Service checkMode;
	private Service getMimeType;
	private Service getMd5;
	private Service getName0Ext;
	private Service getNow;
	
	private Service handleEbook;
	private Service handleText;
	private Service handlePdf;
	private Service handleJpeg;
	private Service handleDll;
	private Service handleVideo;
	private Service handleAudio;
	private Service handleClass;
	private Service handleZip;


	public EntityImpl() throws Exception
	{
		checkMode = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.mode");
		getMimeType = Outside.service(this,"gus06.file.mime.tika.detect.asstring.s");
		getMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");
		getName0Ext = Outside.service(this,"gus06.file.getname0ext");
		getNow = Outside.service(this,"gus06.time.now");
		
		handleEbook = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.ebook");
		handleText = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.text");
		handlePdf = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.pdf");
		handleJpeg = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.jpeg");
		handleDll = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.dll");
		handleVideo = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.video");
		handleAudio = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.audio");
		handleClass = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.class1");
		handleZip = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties.handle.zip");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		File file = (File) o[1];
		String[] row = (String[]) o[2];
		
		String md5 = row!=null ? row[4] : (String) getMd5.t(file);
		
		boolean ok = checkMode.f(new Object[]{engine,md5});
		if(!ok) return false;
		
		
		String size = ""+file.length();
		String mime = row!=null ? row[5] : (String) getMimeType.t(file);
		String time = (String) getNow.g();
		String[] nameExt = (String[]) getName0Ext.t(file);
		
		Properties prop = new Properties();
		
		prop.put("size",size);
		prop.put("md5",md5);
		prop.put("mime",mime);
		prop.put("name0",nameExt[0]);
		prop.put("ext",nameExt[1]);
		prop.put("time",time);
		
		if(isAudioMime(mime))
			handleAudio.p(new Object[]{engine,prop,file});
		if(isClassMime(mime))
			handleClass.p(new Object[]{engine,prop,file});
		if(isDllMime(mime))
			handleDll.p(new Object[]{engine,prop,file});
		if(isEbookMime(mime))
			handleEbook.p(new Object[]{engine,prop,file});
		if(isJpegMime(mime))
			handleJpeg.p(new Object[]{engine,prop,file});
		if(isPdfMime(mime))
			handlePdf.p(new Object[]{engine,prop,file});
		if(isTextMime(mime))
			handleText.p(new Object[]{engine,prop,file});
		if(isVideoMime(mime))
			handleVideo.p(new Object[]{engine,prop,file});
		if(isZipMime(mime))
			handleZip.p(new Object[]{engine,prop,file});
		
		((V) engine).v("writeProp",prop);
		return true;
	}
	
	
	
	
	private boolean isTextMime(String mime)
	{return mime.startsWith("text/");}
	
	private boolean isEbookMime(String mime)
	{return mime.equals("application/epub+zip") || mime.equals("application/x-mobipocket-ebook");}
	
	private boolean isPdfMime(String mime)
	{return mime.equals("application/pdf");}
	
	private boolean isVideoMime(String mime)
	{return mime.startsWith("video/");}
	
	private boolean isAudioMime(String mime)
	{return mime.startsWith("audio/");}
	
	private boolean isJpegMime(String mime)
	{return mime.equals("image/jpeg");}
	
	private boolean isDllMime(String mime)
	{return mime.equals("application/x-msdownload");}
	
	private boolean isClassMime(String mime)
	{return mime.equals("application/java-vm");}
	
	private boolean isZipMime(String mime)
	{return mime.equals("application/zip") || mime.endsWith("+zip");}
}