package a.entity.gus06.data.perform.bjoin;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String glue1 = (String) o[1];
		String glue2 = (String) o[2];
		
		if(input instanceof Object[][])		return join((Object[][]) input,glue1,glue2);
		if(input instanceof int[][])		return join((int[][]) input,glue1,glue2);
		if(input instanceof long[][])		return join((long[][]) input,glue1,glue2);
		if(input instanceof boolean[][])	return join((boolean[][]) input,glue1,glue2);
		if(input instanceof double[][])		return join((double[][]) input,glue1,glue2);
		if(input instanceof float[][])		return join((float[][]) input,glue1,glue2);
		if(input instanceof char[][])		return join((char[][]) input,glue1,glue2);
		if(input instanceof short[][])		return join((char[][]) input,glue1,glue2);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private String join(Object[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append("" + m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(int[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(long[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(boolean[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(double[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(float[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(char[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
	
	
	private String join(short[][] m, String glue1, String glue2)
	{
		StringBuffer b = new StringBuffer();
		
		int nb1 = m.length;
		int nb2 = nb1>0 ? m[0].length : 0;
		
		for(int i=0;i<nb1;i++)
		{
			for(int j=0;j<nb2;j++)
			{
				b.append(m[i][j]);
				if(j<nb2-1) b.append(glue1);
			}
			if(i<nb1-1) b.append(glue2);
		}
		
		return b.toString();
	}
}
