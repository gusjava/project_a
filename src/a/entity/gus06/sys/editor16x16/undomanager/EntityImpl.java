package a.entity.gus06.sys.editor16x16.undomanager;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, R, E {

	public String creationDate() {return "20250315";}

	public final static int NB = 16;

	private List before;
	private List after;
	private Object current;
	

	public EntityImpl() throws Exception
	{
		before = new ArrayList();
		after = new ArrayList();
	}
	
	
	public void e() throws Exception
	{
		before.clear();
		after.clear();
		current = null;
	}
	
	public void p(Object obj) throws Exception
	{
		if(current!=null) before.add(current);
		current = cloneData((String[][]) obj);
		after.clear();
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("undo")) return undo();
		if(key.equals("redo")) return redo();
		if(key.equals("undoAll")) return undoAll();
		
		if(key.equals("keys")) return new String[]{"undo","redo","undoAll"};
		throw new Exception("Unknown key: "+key);
	}
	
	private Object undo()
	{
		if(before.isEmpty()) return null;
		
		after.add(current);
		current = before.get(before.size()-1);
		before.remove(before.size()-1);
		return current;
	}
	
	private Object redo()
	{
		if(after.isEmpty()) return null;
		
		before.add(current);
		current = after.get(after.size()-1);
		after.remove(after.size()-1);
		return current;
	}
	
	private Object undoAll()
	{
		while(!before.isEmpty()) undo();
		return current;
	}
	
	
	private String[][] cloneData(String[][] data_)
	{
		String[][] data = new String[NB][NB];
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		data[i][j] = data_[i][j];
		return data;
	}
}