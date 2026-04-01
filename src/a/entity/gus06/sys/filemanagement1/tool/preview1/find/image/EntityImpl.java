package a.entity.gus06.sys.filemanagement1.tool.preview1.find.image;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}

	public static final String KEY_ALLOCINE_CODE = "allocine.code";
	public static final String KEY_MIME = "mime";



	private Service findPosterImage;
	private Service allocineIcon;
	private Service pdfIcon;
	private Service ebookIcon;
	private Service toFileType1;


	public EntityImpl() throws Exception
	{
		findPosterImage = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.find.image");
		allocineIcon = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.image.print.icon");
		pdfIcon = Outside.service(this,"gus06.sys.filemanagement1.tool.pdf.image.print.icon");
		ebookIcon = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.image.print.icon");
		toFileType1 = Outside.service(this,"gus06.file.mime.tofiletype1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		
		Map prop = (Map) ((R)engine).r("prop:"+md5);
		
		String code = getProp(prop,KEY_ALLOCINE_CODE);
		if(code!=null) return handleAllocine(engine,code,md5);
		
		Object preview = ((R)engine).r("preview:"+md5);
		String mime = getProp(prop,KEY_MIME);
		String fileType = (String) toFileType1.t(mime);
		
		if(fileType==null) return preview;
		
		if(fileType.equals("pdf")) return pdfIcon(preview);
		if(fileType.equals("ebook")) return ebookIcon(preview);
		
		return preview;
	}
	
	
	
	
	
	private Object handleAllocine(Object engine, String code, String md5) throws Exception
	{
		Object preview = findPosterImage.t(new Object[]{engine,code});
		if(preview==null) preview = ((R)engine).r("preview:"+md5);
		return allocineIcon(preview);
	}
	
	
	
	private Object allocineIcon(Object image) throws Exception
	{return allocineIcon.t(image);}
	
	private Object pdfIcon(Object image) throws Exception
	{return pdfIcon.t(image);}
	
	private Object ebookIcon(Object image) throws Exception
	{return ebookIcon.t(image);}
	
	
	private String getProp(Map map, String key)
	{
		if(map==null || !map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}