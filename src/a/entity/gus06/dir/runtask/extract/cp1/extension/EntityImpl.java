package a.entity.gus06.dir.runtask.extract.cp1.extension;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150630";}


	private Service listing;
	private Service getInput;
	private Service copyInsideDir;
	private Service isZip;
	private Service handleZip;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		getInput = Outside.service(this,"gus06.input.text.dialog");
		copyInsideDir = Outside.service(this,"gus06.dir.perform.copyfile");
		isZip = Outside.service(this,"gus06.file.filter.ext.istype.archive.zip");
		handleZip = Outside.service(this,"gus06.file.zip.extracttodir.extension");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		String ext = (String) getInput.t("Enter target extension:");
		if(ext==null || ext.equals("")) return;
		
		File dir1 = new File(dir.getAbsolutePath()+"_extracted");
		dir1.mkdirs();
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			handle(f,dir1,ext);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	
	private void handle(File f, File dir, String ext)
	{
		try
		{
			if(isType(f,ext)) copyInsideDir.p(new File[]{f,dir});
			else if(isZip.f(f)) handleZip.p(new Object[]{f,dir,ext});
		}
		catch(Exception e)
		{Outside.err(this,"handle(File,File,String)",e);}
	}
	
	
	
	
	private boolean isType(File f, String ext)
	{return f.getName().toLowerCase().endsWith("."+ext);}
}
