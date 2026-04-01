package a.entity.gus06.data.editor.renderedimage.editor1.labeldesc;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.Icon;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200106";}

	private Service buildDesc;
	private Service is16x16;
	private Service findIcon;
	
	private JLabel label;
	private Object image;

	public EntityImpl() throws Exception
	{
		buildDesc = Outside.service(this,"gus06.image.description");
		is16x16 = Outside.service(this,"gus06.image.filter.is16x16");
		findIcon = Outside.service(this,"gus06.find.icon");
		
		label = new JLabel(" ");
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		
		label.setText(getDesc());
		label.setIcon(getIcon());
	}
	
	private String getDesc() throws Exception
	{return image!=null ? (String) buildDesc.t(image) : " ";}
	
	private Icon getIcon() throws Exception
	{return image!=null && is16x16.f(image) ? (Icon) findIcon.t(image) : null;}
}