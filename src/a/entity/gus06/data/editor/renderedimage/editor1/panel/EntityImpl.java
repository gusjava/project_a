package a.entity.gus06.data.editor.renderedimage.editor1.panel;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20250226";}


	private Service shiftPanel;
	private Service panel1;
	private Service panel2;
	private Service is16x16;
	
	private Object image;
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		panel1 = Outside.service(this,"*gus06.sys.editor16x16.maingui");
		panel2 = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.panel.view");
		is16x16 = Outside.service(this,"gus06.image.filter.is16x16");
		
		panel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{imageEditedByPanel1();}
		});
		panel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{imageEditedByPanel2();}
		});
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		if(image==null){reset();return;}
		
		if(is16x16.f(image))
		{
			panel1.p(image);
			panel2.p(null);
			shiftPanel.p(panel1);
		}
		else
		{
			panel1.p(null);
			panel2.p(image);
			shiftPanel.p(panel2);
		}
	}
	
	
	private void reset() throws Exception
	{
		panel1.p(null);
		panel2.p(null);
		shiftPanel.p(null);
	}
	
	
	
	private void imageEditedByPanel1()
	{
		try
		{
			image = panel1.g();
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"imageEditedByPanel1()",e);}
	}
	
	
	private void imageEditedByPanel2()
	{
		try
		{
			image = panel2.g();
			imageEdited();
		}
		catch(Exception e)
		{Outside.err(this,"imageEditedByPanel2()",e);}
	}
	
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}