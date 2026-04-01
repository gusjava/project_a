package a.entity.gus06.file.read.image.generic.findreader;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	private Service readImage_image;
	private Service readImage_video;
	private Service readImage_pdf;
	private Service readImage_ods;
	private Service readImage_mobi;
	private Service readImage_epub;
	private Service readImage_ico;
	private Service readImage_txt;
	private Service readImage_prop;
	private Service readImage_cbz;
	private Service readImage_cbr;
	private Service readImage_webp;
	private Service readImage_svg;
	
	private Service default0;
	

	
	public EntityImpl() throws Exception
	{
		readImage_image = Outside.service(this,"gus06.file.read.image.from.image");
		readImage_video = Outside.service(this,"gus06.file.read.image.from.video");
		readImage_pdf = Outside.service(this,"gus06.file.read.image.from.pdf");
		readImage_ods = Outside.service(this,"gus06.file.read.image.from.ods");
		readImage_mobi = Outside.service(this,"gus06.file.read.image.from.mobi");
		readImage_epub = Outside.service(this,"gus06.file.read.image.from.epub");
		readImage_ico = Outside.service(this,"gus06.file.read.image.from.ico");
		readImage_txt = Outside.service(this,"gus06.file.read.image.from.txt");
		readImage_prop = Outside.service(this,"gus06.file.read.image.from.properties");
		readImage_cbz = Outside.service(this,"gus06.file.read.image.from.cbz");
		readImage_cbr = Outside.service(this,"gus06.file.read.image.from.cbr");
		readImage_webp = Outside.service(this,"gus06.file.read.image.from.webp");
		readImage_svg = Outside.service(this,"gus06.file.read.image.from.svg");
		
		default0 = Outside.service(this,"gus06.file.read.image.generic.default0");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return findReader(file);
	}
	
	
	private Service findReader(File file) throws Exception
	{
		String s = file.getName().toLowerCase();
		
		if(isImage(s)) return readImage_image;
		if(isVideo(s)) return readImage_video;
		
		if(en(s,"pdf")) return readImage_pdf;
		if(en(s,"ods")) return readImage_ods;
		if(en(s,"mobi")) return readImage_mobi;
		if(en(s,"epub")) return readImage_epub;
		if(en(s,"ico")) return readImage_ico;
		if(en(s,"txt")) return readImage_txt;
		if(en(s,"properties")) return readImage_prop;
		if(en(s,"cbz")) return readImage_cbz;
		if(en(s,"cbr")) return readImage_cbr;
		if(en(s,"webp")) return readImage_webp;
		if(en(s,"svg")) return readImage_svg;
		
		return default0;
	}
	
	
	private boolean isImage(String s)
	{return en(s,"gif") || en(s,"png") || en(s,"jpg") || en(s,"jpeg") || en(s,"bmp");}
	
	private boolean isVideo(String s)
	{return en(s,"avi") || en(s,"mkv") || en(s,"wmv") || en(s,"flv") || en(s,"mp4") || en(s,"mov");}
	
	
	private boolean en(String s, String ext)
	{return s.endsWith("."+ext);}
}