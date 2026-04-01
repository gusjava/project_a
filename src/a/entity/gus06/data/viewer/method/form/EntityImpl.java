package a.entity.gus06.data.viewer.method.form;

import a.framework.*;
import javax.swing.JComponent;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140821";}


	private Service formPanel;
	
	private Method data;
	
	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");
	}
	
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Method) obj;
		if(data==null) resetGui();
		else updateGui();
	}
		
		
		
		
		
	private void resetGui() throws Exception
	{
		formPanel.e();
	}
    
    
	
	private void updateGui() throws Exception
	{
		formPanel.e();
		
		put("Method name",methodName());
		put("Method modifiers",methodModifiers());
		put("Method return type",methodReturnType());
		put("Method parameters",methodParameters());
	}
	
	
	
	private void put(String key, String value) throws Exception
	{formPanel.v(key,value);}
    
	
	
	private String methodName()
	{
		if(data==null) return "";
		return data.getName();
	}
	
	
	
	private String methodReturnType()
	{
		if(data==null) return "";
		return data.getReturnType().getName();
	}
	
	
	private String methodParameters()
	{
		StringBuffer b = new StringBuffer();
		Class[] params = data.getParameterTypes();
		for(Class p:params) b.append(p.getName()+";");
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	
	private String methodModifiers()
	{
		int mod = data.getModifiers();
		return modToString(mod)+"("+mod+")";
	}
	
	
	
	private String modToString(int mod)
	{
		StringBuffer b = new StringBuffer();
		
		if(Modifier.isFinal(mod)) b.append("final ");
		if(Modifier.isVolatile(mod)) b.append("volatile ");
		if(Modifier.isStatic(mod)) b.append("static ");
		if(Modifier.isPublic(mod)) b.append("public ");
		if(Modifier.isProtected(mod)) b.append("protected ");
		if(Modifier.isPrivate(mod)) b.append("private ");
		
		return b.toString();
	}
	
	
	private boolean isStatic()
	{return Modifier.isStatic(data.getModifiers());}
}
