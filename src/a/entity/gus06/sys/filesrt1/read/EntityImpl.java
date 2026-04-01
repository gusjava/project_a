package a.entity.gus06.sys.filesrt1.read;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230103";}
	
	public static final Pattern P_TIME = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}");


	private Service readFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String s = (String) readFile.t(file);
		String[] lines = s.split("\n");
		List data = new ArrayList();
		
		List bloc = new ArrayList();
		for(int i=0;i<lines.length;i++)
		{
			if(lines[i].equals("") && !bloc.isEmpty()) 
			{
				int index = data.size();
				Object record = blocToRecord(index, bloc);
				data.add(record);
				bloc = new ArrayList();
			}
			else bloc.add(lines[i]);
		}
		return data;
	}
	
	private Object blocToRecord(int index, List bloc) throws Exception
	{
		try
		{
			int size = bloc.size();
			if(size<3) throw new Exception("Invalid bloc size: "+size);
			
			String row0 = (String) bloc.get(0);
			String row1 = (String) bloc.get(1);
			
			int nb = toInt(row0);
			if(nb!=index+1) throw new Exception("Invalid position row: "+row0);
			
			String[] t = (row1).split(" --> ");
			if(t.length!=2) throw new Exception("Invalid timeStamp row: "+row1);
			String start = t[0];
			String end = t[1];
			
			int[] startInfos = parseTime(start);
			int[] endInfos = parseTime(end);
			if(isOrdered(endInfos, startInfos)) throw new Exception("Invalid timeStamp row: end="+end+" is before start="+start);
			
			StringBuffer b = new StringBuffer();
			for(int i=2;i<size;i++)
			{
				b.append((String) bloc.get(i));
				if(i<size-1) b.append("\n");
			}
			
			return new Object[]{startInfos, endInfos, b.toString()};
		}
		catch(Exception e)
		{throw new Exception("An error occurred for srt bloc at index "+index, e);}
	}
	
	private int[] parseTime(String time) throws Exception
	{
		if(!P_TIME.matcher(time).matches()) 
			throw new Exception("Invalid time format: "+time);
		return new int[]{
			toInt(time.substring(0,2)),
			toInt(time.substring(3,5)),
			toInt(time.substring(6,8)),
			toInt(time.substring(9,12))
		};
	}
	
	private int toInt(String s) throws Exception
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e)
		{throw new Exception("Invalid number: "+s);}
	}
	
	private boolean isOrdered(int[] a, int[] b)
	{
		for(int i=0;i<4;i++)
		{
			if(a[i]<b[i]) return true;
			if(a[i]>b[i]) return false;
		}
		return false;
	}
}