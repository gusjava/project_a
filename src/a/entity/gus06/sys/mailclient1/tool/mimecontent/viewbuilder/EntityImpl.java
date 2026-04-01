package a.entity.gus06.sys.mailclient1.tool.mimecontent.viewbuilder;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.SharedByteArrayInputStream;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.QPDecoderStream;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.util.Objects;
import javax.swing.JTabbedPane;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240318";}

	public static final String MULTIPART_ALTERNATIVE = "multipart/alternative";
	public static final String MULTIPART_MIXED = "multipart/mixed";
	public static final String MULTIPART_RELATED = "multipart/related";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String IMAGE_PNG = "image/png";
	public static final String IMAGE_JPEG = "image/jpeg";


	private Service streamToImage;
	private Service imageToComp;
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		streamToImage = Outside.service(this,"gus06.mail.base64decoderstream.image");
		imageToComp = Outside.service(this,"gus06.convert.imagetojpanel");
		panel  = new JPanel();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object content = o[0];
		String type = (String) o[1];
		
		return buildComp(content, type);
	}
	
	
	private JComponent buildComp(Object content, String type) throws Exception
	{
		String[] infos = type.split(";");
		String info0 = infos[0].trim().toLowerCase();
		
		if(info0.equals(MULTIPART_ALTERNATIVE))
			return new ViewMultipartAlternative((MimeMultipart) content, type);
		if(info0.equals(MULTIPART_MIXED))
			return new ViewMultipartMixed((MimeMultipart) content, type);
		if(info0.equals(MULTIPART_RELATED))
			return new ViewMultipartRelated((MimeMultipart) content, type);
		if(info0.equals(TEXT_HTML))
			return new ViewTextHtml((String) content, type);
		if(info0.equals(TEXT_PLAIN))
			return new ViewTextPlain((String) content, type);
		if(info0.equals(IMAGE_PNG))
			return new ViewImagePng((BASE64DecoderStream) content, type);
		if(info0.equals(IMAGE_JPEG))
			return new ViewImageJpeg((BASE64DecoderStream) content, type);
		
		return new ViewUnsupported(content, type);
	}
	
	private class JPanel1 extends JPanel
	{
		public JPanel1(String type)
		{
			super(new BorderLayout());
			add(new JLabel(type), BorderLayout.NORTH);
		}
		protected void addMainComp(JComponent comp)
		{add(comp, BorderLayout.CENTER);}
	}
	
	private class ViewMultipartAlternative extends JPanel1
	{
		public ViewMultipartAlternative(MimeMultipart content, String type) throws Exception
		{
			super(type);
			JTabbedPane comp = new JTabbedPane();
			for(int i=0;i<content.getCount();i++)
			{
				BodyPart bodyPart = content.getBodyPart(i);
				comp.addTab(""+(i+1), bodyPartToComp(bodyPart));
			}
			addMainComp(comp);
		}
	}
	
	private class ViewMultipartMixed extends JPanel1
	{
		public ViewMultipartMixed(MimeMultipart content, String type) throws Exception
		{
			super(type);
			JTabbedPane comp = new JTabbedPane();
			for(int i=0;i<content.getCount();i++)
			{
				BodyPart bodyPart = content.getBodyPart(i);
				comp.addTab(""+(i+1), bodyPartToComp(bodyPart));
			}
			addMainComp(comp);
		}
	}
	
	private class ViewMultipartRelated extends JPanel1
	{
		public ViewMultipartRelated(MimeMultipart content, String type) throws Exception
		{
			super(type);
			JTabbedPane comp = new JTabbedPane();
			for(int i=0;i<content.getCount();i++)
			{
				BodyPart bodyPart = content.getBodyPart(i);
				comp.addTab(""+(i+1), bodyPartToComp(bodyPart));
			}
			addMainComp(comp);
		}
	}
	
	private class ViewTextHtml extends JPanel1
	{
		public ViewTextHtml(String content, String type)
		{
			super(type);
			JTextArea area = textArea(new Color(153,204,255), content);
			addMainComp(new JScrollPane(area));
		}
	}
	
	private class ViewTextPlain extends JPanel1
	{
		public ViewTextPlain(String content, String type)
		{
			super(type);
			JTextArea area = textArea(new Color(204,255,153), content);
			addMainComp(new JScrollPane(area));
		}
	}
	
	private class ViewImagePng extends JPanel1
	{
		public ViewImagePng(BASE64DecoderStream content, String type) throws Exception
		{
			super(type);
			Object image = streamToImage.t(new Object[]{content, "png"});
			JComponent comp = (JComponent) imageToComp.t(image);
			addMainComp(comp);
		}
	}
	
	private class ViewImageJpeg extends JPanel1
	{
		public ViewImageJpeg(BASE64DecoderStream content, String type) throws Exception
		{
			super(type);
			Object image = streamToImage.t(new Object[]{content, "jpeg"});
			JComponent comp = (JComponent) imageToComp.t(image);
			addMainComp(comp);
		}
	}
	
	private class ViewUnsupported extends JPanel1
	{
		public ViewUnsupported(Object content, String type)
		{
			super(type);
			String text = "Unsupported yet\n"+content.getClass().getName();
			JTextArea area = textArea(Color.LIGHT_GRAY, text);
			addMainComp(new JScrollPane(area));
		}
	}
	
	private JComponent bodyPartToComp(BodyPart part) throws Exception
	{
		String disposition = part.getDisposition();
		Object content = part.getContent();
		String type = part.getContentType();
				
		if(Objects.equals(disposition, "attachment"))
		type += " (attached: "+part.getFileName()+")";
		return buildComp(content, type);
	}
	
	private JTextArea textArea(Color color, String text)
	{
		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(2,2,2,2));
		area.setBackground(color);
		area.setText(text);
		return area;
	}
}