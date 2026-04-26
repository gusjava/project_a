package a.entity.gus06.dir.runtask.corpus.properties.display.incompletekeys;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151019";}
	
	
	public static final String TITLE = "Incomplete keys";


	private Service listing;
	private Service read;
	private Service showMessage;
	private Service toClipboard;
	private Service setToString;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		read = Outside.service(this,"gus06.file.read.properties.keyset");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
		setToString = Outside.service(this,"gus06.tostring.set");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Set complete = null;
		Set incomplete = null;
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			Set keys = (Set) read.t(f);
			if(complete==null)
			{
				complete = new HashSet(keys);
				incomplete = new HashSet();
			}
			else
			{
				Iterator it = keys.iterator();
				while(it.hasNext())
				{
					String key = (String) it.next();
					if(!complete.contains(key)) incomplete.add(key);
				}
				
				it = complete.iterator();
				while(it.hasNext())
				{
					String key = (String) it.next();
					if(!keys.contains(key))
					{
						incomplete.add(key);
						it.remove();
					}
				}
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String keys_ = setToString(incomplete);
		toClipboard.p(keys_);
		
		String message = "Field number: "+incomplete.size()+"\n\n"+keys_;
		showMessage.p(new String[]{message,TITLE});
	}
	
	
	
	
	private String setToString(Set keys) throws Exception
	{return (String) setToString.t(keys);}
}
