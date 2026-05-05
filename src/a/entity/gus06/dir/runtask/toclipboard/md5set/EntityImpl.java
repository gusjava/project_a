package a.entity.gus06.dir.runtask.toclipboard.md5set;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220713";}


	private Service listing;
	private Service listToString;
	private Service toClipboard;
	private Service buildMd5;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		listToString = Outside.service(this,"gus06.tostring.list.join.n");
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		Set md5Set = new HashSet();
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String md5 = (String) buildMd5.t(f);
			
			md5Set.add(md5);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		List md5List = new ArrayList(md5Set);
		Collections.sort(md5List);
		
		String md5Str = (String) listToString.t(md5List);
		toClipboard.p(md5Str);
		
		int nb = md5Set.size();
		showMessage.p(nb+" distinct MD5 have been found (copied to clipboard)");
	}
}