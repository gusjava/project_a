package a.entity.gus06.sys.mailclient1.tool.addressarray.format;

import a.framework.*;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201117";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Address[] dd = (Address[]) obj;
		if(dd.length==0) return "";
		if(dd.length==1) return formatSingle(dd[0]);
		return formatMulti(dd);
	}
	
	
	
	private String formatSingle(Address d) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		if(d instanceof InternetAddress)
		{
			InternetAddress d1 = (InternetAddress) d;
			String personal = d1.getPersonal();
			if(personal!=null)
			{
				b.append(personal);
				b.append(" - ");
			}
			String address = d1.getAddress();
			if(address!=null)
			{
				b.append(address);
			}
		}
		else b.append(d.toString());
		
		return b.toString();
	}
	
	
	
	private String formatMulti(Address[] dd) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		int nb = dd.length;
		for(int i=0;i<nb;i++)
		{
			Address d = dd[i];
			if(d instanceof InternetAddress)
			{
				InternetAddress d1 = (InternetAddress) d;
				String personal = d1.getPersonal();
				if(personal!=null) b.append(personal);
				else
				{
					String mail = d1.getAddress();
					if(mail!=null)
					{
						b.append(mail);
					}
				}
			}
			else b.append(d.toString());
			if(i<nb-1) b.append(", ");
		}
		return b.toString();
	}
	
}