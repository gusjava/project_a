package a.entity.gus06.dir.runtask.classify.byname.part0.delim;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220327";}
	
	public static final String MESSAGE = "Please, enter delim:";


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
		
		String delimQ = Pattern.quote(input);
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String name = f.getName();
			String part0 = name.split(delimQ)[0];
			
			File d = new File(dir,part0);
			File f1 = new File(d,name);
			
			moveFile.p(new File[]{f,f1});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
