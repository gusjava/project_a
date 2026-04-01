package a.entity.gus06.dir.runtask.corpus.properties.display.datakeys;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151018";}
	
	
	public static final String TITLE = "Data keys";


	private Service listing;
	private Service read;
	private Service showMessage;
	private Service toClipboard;
	private Service setToString;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		read = Outside.service(this,"gus06.file.read.properties");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
		setToString = Outside.service(this,"gus06.tostring.set");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Set keys = new HashSet();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			Map prop = (Map) read.t(f);
			handleProp(prop,keys);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String keys_ = setToString(keys);
		toClipboard.p(keys_);
		
		String message = "Field number: "+keys.size()+"\n\n"+keys_;
		showMessage.p(new String[]{message,TITLE});
	}
	
	
	
	
	private void handleProp(Map prop, Set keys)
	{
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			if(!value.trim().equals("")) keys.add(key);
		}
	}
	
	
	private String setToString(Set keys) throws Exception
	{return (String) setToString.t(keys);}
}
