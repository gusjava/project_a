package a.entity.gus06.data.perform.empty;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import javax.swing.text.JTextComponent;
import java.awt.Container;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141026";}


	private Service emptyDirFile;


	public EntityImpl() throws Exception
	{
		emptyDirFile = Outside.service(this,"gus06.dirfile.op.empty");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof StringBuffer)
		{empty((StringBuffer) obj);return;}
		
		if(obj instanceof JTextComponent)
		{empty((JTextComponent) obj);return;}
		
		if(obj instanceof Container)
		{empty((Container) obj);return;}
		
		if(obj instanceof Collection)
		{empty((Collection) obj);return;}
		
		if(obj instanceof Map) 
		{empty((Map) obj);return;}
		
		if(obj instanceof File)
		{empty((File) obj);return;}
		
		
		
		if(obj instanceof StringBuffer[])
		{empty((StringBuffer[]) obj);return;}
		
		if(obj instanceof JTextComponent[])
		{empty((JTextComponent[]) obj);return;}
		
		if(obj instanceof Container[])
		{empty((Container[]) obj);return;}
		
		if(obj instanceof Collection[])
		{empty((Collection[]) obj);return;}
		
		if(obj instanceof Map[]) 
		{empty((Map[]) obj);return;}
		
		if(obj instanceof File[])
		{empty((File[]) obj);return;}
		
		
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void empty(StringBuffer b)
	{b.setLength(0);}
	
	private void empty(JTextComponent comp)
	{comp.setText("");}
	
	private void empty(Container contrainer)
	{contrainer.removeAll();}
	
	private void empty(Collection c)
	{c.clear();}
	
	private void empty(Map m)
	{m.clear();}
	
	private void empty(File f) throws Exception
	{emptyDirFile.p(f);}
	
	
	
	
	
	private void empty(StringBuffer[] bb)
	{
		for(StringBuffer b:bb)
		b.setLength(0);
	}
	
	private void empty(JTextComponent[] cc)
	{
		for(JTextComponent c:cc)
		c.setText("");
	}
	
	private void empty(Container[] cc)
	{
		for(Container c:cc)
		c.removeAll();
	}
	
	private void empty(Collection[] cc)
	{
		for(Collection c:cc)
		c.clear();
	}
	
	private void empty(Map[] mm)
	{
		for(Map m:mm)
		m.clear();
	}
	
	private void empty(File[] ff) throws Exception
	{
		for(File f:ff)
		emptyDirFile.p(f);
	}
}
