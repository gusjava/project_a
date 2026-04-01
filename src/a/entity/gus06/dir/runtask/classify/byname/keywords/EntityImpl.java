package a.entity.gus06.dir.runtask.classify.byname.keywords;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}
	
	public static final String MESSAGE = "Please, enter keyword sequence:";


	private Service listing;
	private Service inputDialog;
	private Service moveFile;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
		moveFile = Outside.service(this,"gus06.file.op.move.autorename");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String input = (String) inputDialog.t(MESSAGE);
		if(input==null || input.equals("")) return;
		
		String[] keywords = input.split(";");
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String name = f.getName();
			String keyword = searchForKeyword(name,keywords);
			
			File d = new File(dir,keyword);
			File f1 = new File(d,name);
			
			moveFile.p(new File[]{f,f1});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private String searchForKeyword(String name, String[] kw)
	{
		List l = new ArrayList();
		for(String k:kw) if(name.contains(k)) l.add(k);
		
		if(l.size()==1) return (String) l.get(0);
		return l.size()==0?"NONE":"MANY";
	}
}