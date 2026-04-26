package a.entity.gus06.dir.runtask.md5matched.open;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220714";}


	private Service listing;
	private Service stringToList;
	private Service fromClipboard;
	private Service buildMd5;
	private Service openFile;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		stringToList = Outside.service(this,"gus06.string.split.delim.n.list");
		fromClipboard = Outside.service(this,"gus.x.clipboard.string");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		openFile = Outside.service(this,"gus06.awt.desktop.open");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List md5List = buildFromClipboard();
		if(md5List==null || md5List.isEmpty())
		{
			showMessage.p("No MD5 data detected inside clipboard");
			return;
		}
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		Set done = new HashSet();
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String md5 = (String) buildMd5.t(f);
			
			if(md5List.contains(md5) && !done.contains(md5))
			{
				openFile.p(f);
				done.add(md5);
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		showMessage.p("Opened files: "+done.size());
	}
	
	
	
	private List buildFromClipboard() throws Exception
	{
		String s = (String) fromClipboard.g();
		if(s==null) return null;
		return (List) stringToList.t(s);
	}
}