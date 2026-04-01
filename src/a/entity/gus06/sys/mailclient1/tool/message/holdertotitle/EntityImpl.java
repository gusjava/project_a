package a.entity.gus06.sys.mailclient1.tool.message.holdertotitle;

import a.framework.*;
import javax.mail.Address;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201117";}

	private Service formatDate;
	private Service formatAddress;

	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.fr.format2");
		formatAddress = Outside.service(this,"gus06.sys.mailclient1.tool.addressarray.format");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		R holder = (R) obj;
		
		boolean isOut = ((F)obj).f("out");
		String subject = (String) holder.r("subject");
		Date date = (Date) holder.r(isOut ? "sentDate" : "receivedDate");
		String addr = (String) ((R) holder).r(isOut ? "recipientsTO" : "from");
		
		String dateStr = (String) formatDate.t(date);
		
		StringBuffer b = new StringBuffer();
		b.append(dateStr);
		
		if(isOut) b.append(" sent to "+addr);
		else b.append(" from "+addr);
		 
		return b.toString();
	}
}