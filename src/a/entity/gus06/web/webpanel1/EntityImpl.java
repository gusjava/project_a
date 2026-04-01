package a.entity.gus06.web.webpanel1;

import a.framework.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;


public class EntityImpl extends S1 implements Entity, G, I, P, HyperlinkListener {

	public String creationDate() {return "20140909";}

	private JTextPane textPane;
	private URL url;

	
	public EntityImpl() throws Exception
	{
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		textPane.addHyperlinkListener(this);
	}
	
	
	public Object i() throws Exception
	{return textPane;}
	
	
	public Object g() throws Exception
	{return url;}
	
	
	public void p(Object obj) throws Exception
	{setURL((URL) obj);}
	
	
	
	private void setURL(URL url)
	{
		this.url = url;
		textPane.setText("");
		if(url!=null) updateGui();
	}
	

	private void updateGui()
	{
		try{textPane.setPage(url);}
		catch(IOException e)
		{Outside.err(this,"updateGui()",e);}
	}


	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			JEditorPane pane = (JEditorPane) e.getSource();
			if(e instanceof HTMLFrameHyperlinkEvent)
			{
				HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent) e;
				HTMLDocument doc = (HTMLDocument) pane.getDocument();
				doc.processHTMLFrameHyperlinkEvent(evt);
			}
			else
			{
				setURL(e.getURL());
				urlChanged();
			}
		}
	}

	
	
	
	private void urlChanged()
	{send(this,"urlChanged()");}
}
