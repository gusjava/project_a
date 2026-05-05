package a.entity.gus06.file.doubloon.check;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150409";}


	private Service buildMd5;


	public EntityImpl() throws Exception
	{buildMd5 = Outside.service(this,"gus.x.crypto.hash.md5");}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f1 = o[0];
		File f2 = o[1];
		
		long s1 = f1.length();
		long s2 = f2.length();
		
		if(s1!=s2) return false;
		
		byte[] m1 = (byte[]) buildMd5.t(f1);
		byte[] m2 = (byte[]) buildMd5.t(f2);
		
		for(int i=0;i<m1.length;i++)
		if(m1[i]!=m2[i]) return false;
		
		return true;
	}
}
