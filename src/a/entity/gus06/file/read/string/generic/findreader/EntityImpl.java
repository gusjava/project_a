package a.entity.gus06.file.read.string.generic.findreader;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}


	private Service readString_txt;
	private Service readString_pdf;
	private Service readString_epub;
	private Service readString_prop;
	private Service readString_docx;
	private Service readString_odt;
	private Service readString_image;
	private Service readString_wav;
	private Service readString_mp3;
	private Service isOfTypeTextPlain;
	

	
	public EntityImpl() throws Exception
	{
		readString_txt = Outside.service(this,"gus06.file.read.string.from.txt");
		readString_pdf = Outside.service(this,"gus06.file.read.string.from.pdf");
		readString_epub = Outside.service(this,"gus06.file.read.string.from.epub");
		readString_prop = Outside.service(this,"gus06.file.read.string.from.properties");
		readString_docx = Outside.service(this,"gus06.file.read.string.from.docx");
		readString_odt = Outside.service(this,"gus06.file.read.string.from.odt");
		readString_image = Outside.service(this,"gus06.file.read.string.from.image");
		readString_wav = Outside.service(this,"gus06.file.read.string.from.wav");
		readString_mp3 = Outside.service(this,"gus06.file.read.string.from.mp3");
		isOfTypeTextPlain = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return findReader(file);
	}
	
	
	private Service findReader(File file) throws Exception
	{
		String s = file.getName().toLowerCase();
		
		if(isImage(s)) return readString_image;
		if(en(s,"properties")) return readString_prop;
		if(en(s,"pdf")) return readString_pdf;
		if(en(s,"epub")) return readString_epub;
		if(en(s,"docx")) return readString_docx;
		if(en(s,"odt")) return readString_odt;
		if(en(s,"wav")) return readString_wav;
		if(en(s,"mp3")) return readString_mp3;
		
		if(isOfTypeTextPlain.f(file)) return readString_txt;
		if(file.length()==0) return readString_txt;
		
		throw new Exception("File type not supported yet: "+file);
	}
	
	
	private boolean en(String s, String ext)
	{return s.endsWith("."+ext);}
	
	private boolean isImage(String s)
	{return en(s,"gif") || en(s,"png") || en(s,"jpg") || en(s,"jpeg") || en(s,"bmp");}
	
	
}