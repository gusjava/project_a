package a.entity.gus06.dir.runtask.corpus.properties.display.primarykeys;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150923";}
	
	
	public static final String TITLE = "Primary keys";


	private Service listing;
	private Service read;
	private Service showMessage;
	private Service toClipboard;
	private Service setToString;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		read = Outside.service(this,"gus.x.file.prop.read");
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
		
		Map primary = null;
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			Map prop = (Map) read.t(f);
			if(primary==null) primary = initPrimary(prop);
			else handleProp(prop,primary);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String message = buildMessage(primary);
		showMessage.p(new String[]{message,TITLE});
	}
	
	
	
	
	private Map initPrimary(Map prop)
	{
		Map m = new HashMap();
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			
			Set set = new HashSet();
			set.add(value);
			m.put(key,set);
		}
		return m;
	}
	
	
	private void handleProp(Map prop, Map primary)
	{
		Iterator it = primary.keySet().iterator();
		while(it.hasNext())
		{
			String primaryKey = (String) it.next();
			if(!prop.containsKey(primaryKey)) it.remove();
			else
			{
				String value = (String) prop.get(primaryKey);
				Set set = (Set) primary.get(primaryKey);
				if(set.contains(value)) it.remove();
				else set.add(value);
			}
		}
	}
	
	
	private String buildMessage(Map primary) throws Exception
	{
		if(primary==null) return "no file found";
		
		Set keys = primary.keySet();
		String keys_ = (String) setToString.t(keys);
		toClipboard.p(keys_);
		
		return "Field number: "+keys.size()+"\n\n"+keys_;
	}
}
