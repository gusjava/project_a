package a.entity.gus06.data.viewer.service;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.lang.reflect.Field;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20250626";}


	private Service formPanel;
	private Service findName;
	private Service findFeatures;
	
	private JLabel labelName;
	
	private Service data;
	

	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		
		labelName = new JLabel(" ");
		
		formPanel.v("Entity name",labelName);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Service) obj;
		if(data==null)
		{
			labelName.setText(" ");
		}
		else
		{
			Class<?> objClass = data.getClass();
			Field[] fields = objClass.getDeclaredFields();
			
			for (Field field : fields)
			{
				try
				{
					field.setAccessible(true);
					if ("target".equals(field.getName()))
					{
						Object target = field.get(obj);
						labelName.setText(target.getClass().getName());
					}
				}
				catch (IllegalAccessException e)
				{throw new Exception("Failed to read object", e);}
			}
		}
	}
}
