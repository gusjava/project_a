package a.entity.gus06.mail.message.emlfile.tomessage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151015";}


	private Session session;

	public EntityImpl() throws Exception
	{
		session = Session.getInstance(new Properties());
	}


	public Object t(Object obj) throws Exception
	{
		return fileToMimeMessage((File) obj);
	}

	
	private MimeMessage fileToMimeMessage(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		MimeMessage message = new MimeMessage(session,fis);
		fis.close();
		return message;
	}
}
