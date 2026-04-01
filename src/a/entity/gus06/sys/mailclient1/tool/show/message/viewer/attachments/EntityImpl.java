package a.entity.gus06.sys.mailclient1.tool.show.message.viewer.attachments;

import a.framework.*;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240322";}


	private JPanel panel;
	private JLabel label;
	
	private Message message;
	private String contentType;
	private List fileNames;
	private List fileWriters;
	
	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		
		panel = new JPanel(new GridLayout(1,0));
		panel.add(label);
		
		fileNames = new ArrayList();
		fileWriters = new ArrayList();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null){resetGui();return;}
		
		message = (Message) obj;
		contentType = message.getContentType();
		
		fileNames.clear();
		fileWriters.clear();
		
		if(contentType.contains("multipart"))
		{
			MimeMultipart content = (MimeMultipart) message.getContent();
			int number = content.getCount();
			for(int i=0;i<number;i++)
			{
				MimeBodyPart bodyPart = (MimeBodyPart) content.getBodyPart(i);
				Object bodyContent = bodyPart.getContent();
				String disposition = bodyPart.getDisposition();
				
				if(Objects.equals(disposition, "attachment"))
				{
					String fileName = bodyPart.getFileName();
					P fileWriter = new FileWriter(bodyPart);
					
					fileNames.add(fileName);
					fileWriters.add(fileWriter);
				}
			}
		}
	}
	
	
	private class FileWriter implements P
	{
		private MimeBodyPart part;
		public FileWriter(MimeBodyPart part)
		{this.part = part;}
		
		public void p(Object obj) throws Exception
		{
			File f = (File) obj;
			part.saveFile(f);
		}
	}
	
	private void resetGui()
	{
		
	}
}
