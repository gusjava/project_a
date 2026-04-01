package a.entity.gus06.file.perform.rename.extracted.datefr.add0;

import a.framework.*;
import java.io.File;
import java.util.Date;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20220206";}


	private Service fileToText;
	private Service extractDate;
	private Service stringToDate;
	private Service dateToString;
	
	public EntityImpl() throws Exception
	{
		fileToText = Outside.service(this,"gus06.file.read.string.generic");
		extractDate = Outside.service(this,"gus06.string.extract.date.fr.f");
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
		dateToString = Outside.service(this,"gus06.time.date.yyyymmdd.point");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String info = extractInfo(file);
		
		String fileName = file.getName();
		if(!fileName.startsWith(info+" - ")) fileName = info+" - "+fileName;
		
		File file1 = new File(file.getParentFile(), fileName);
		rename(file, file1);
		return file1;
	}
	
	
	private String extractInfo(File file) throws Exception
	{
		String text = (String) fileToText.t(file);
		if(text==null) return "NO_TEXT";
		
		String dateStr = (String) extractDate.t(text);
		if(dateStr==null) return "NOT_FOUND";
		
		Date date = (Date) stringToDate.t(dateStr);
		if(date==null) return "NOT_PARSED";
		
		return (String) dateToString.t(date);
	}
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
}