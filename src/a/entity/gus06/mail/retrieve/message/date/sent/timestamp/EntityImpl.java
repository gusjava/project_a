package a.entity.gus06.mail.retrieve.message.date.sent.timestamp;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.Message;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160609";}


	public Object t(Object obj) throws Exception
	{
		Message message = (Message) obj;
		Date date = message.getSentDate();
		return dateToString(date);
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String dateToString(Date date)
	{return date==null?"":sdf.format(date);}
}
