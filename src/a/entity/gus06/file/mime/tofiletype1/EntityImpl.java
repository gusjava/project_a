package a.entity.gus06.file.mime.tofiletype1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201103";}
	
	public static final String TYPE_AUDIO = "audio";
	public static final String TYPE_IMAGE = "image";
	public static final String TYPE_VIDEO = "video";
	public static final String TYPE_TEXT = "text";
	public static final String TYPE_EBOOK = "ebook";
	public static final String TYPE_PDF = "pdf";
	public static final String TYPE_ARCHIVE = "archive";
	public static final String TYPE_OTHER = "other";

	
	
	public Object t(Object obj) throws Exception
	{
		String mime = (String) obj;
		if(mime==null) return null;
		
		if(mime.startsWith("audio/"))				return TYPE_AUDIO;
		if(mime.startsWith("image/")) 				return TYPE_IMAGE;
		if(mime.startsWith("video/")) 				return TYPE_VIDEO;
		if(mime.startsWith("text/")) 				return TYPE_TEXT;
		
		if(mime.equals("application/epub+zip")) 			return TYPE_EBOOK;
		if(mime.equals("application/x-mobipocket-ebook")) 	return TYPE_EBOOK;
		if(mime.equals("application/pdf")) 			return TYPE_PDF;
		if(mime.equals("application/x-matroska")) 		return TYPE_VIDEO;
		if(mime.equals("application/xml")) 			return TYPE_TEXT;
		if(mime.equals("application/json")) 			return TYPE_TEXT;
		if(mime.equals("application/javascript")) 		return TYPE_TEXT;
		if(mime.equals("application/zip")) 			return TYPE_ARCHIVE;
		
		return TYPE_OTHER;
	}
}