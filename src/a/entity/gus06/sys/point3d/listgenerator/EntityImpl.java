package a.entity.gus06.sys.point3d.listgenerator;

import java.util.ArrayList;
import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}



	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		String[] n = rule.split(" ");
		
		if(n[0].equals("sphere")) return sphere(n[1],n[2]);
		if(n[0].equals("cylinder")) return cylinder(n[1],n[2],n[3]);
		if(n[0].equals("cube")) return cube(n[1],n[2]);
		
		throw new Exception("Invalid generation rule: "+rule);
	}


	
	private List sphere(String nb_, String r_)
	{
		int nb = i(nb_);
		double r = d(r_);
		
		List list = new ArrayList(nb);
		for(int i=0;i<nb;i++) list.add(sphere_buildPoint(r));	
		return list;
	}
	
	private double[] sphere_buildPoint(double r)
	{
		double a = randomAngle();
		double b = randomAngle();

		double x = r*cos(a)*cos(b);	   
		double y = r*cos(a)*sin(b);	 
		double z = r*sin(a);
		return new double[]{x,y,z};
	}
	
	
	
	
	private List cylinder(String nb_, String r_, String h_)
	{
		int nb = i(nb_);
		double r = d(r_);
		double h = d(h_);
		
		List list = new ArrayList(nb);
		for(int i=0;i<nb;i++) list.add(cylinder_buildPoint(r,h));	
		return list;
	}
	
	private double[] cylinder_buildPoint(double r, double h)
	{
		double a1=random()*2*Math.PI;
		double h1=(random()-0.5)*h;

		double x = r*Math.cos(a1);	   
		double y = r*Math.sin(a1);
		return new double[]{x,y,h1};
	}
	
	
	
	
	private List cube(String nb_, String r_)
	{
		int nb = i(nb_);
		double r = d(r_);
		
		List list = new ArrayList(nb);
		for(int i=0;i<nb;i++) list.add(cube_buildPoint(r));	
		return list;
	}
	
	private double[] cube_buildPoint(double r)
	{
		double a=(random()*2-1)*r;
		double b=(random()*2-1)*r;
		int face = (int)(random()*6)+1; 
		
		switch(face)
		{
			case 1:return new double[]{r,a,b};
			case 2:return new double[]{-r,a,b};
			case 3:return new double[]{a,r,b};
			case 4:return new double[]{a,-r,b};
			case 5:return new double[]{a,b,r};
			case 6:return new double[]{a,b,-r};
		}
		return null;
	}
	
	
	
	private double cos(double angle)	{return Math.cos(angle);}
	private double sin(double angle)	{return Math.sin(angle);}
	private double random()			{return Math.random();}
	private double randomAngle()		{return random()*2*Math.PI;}
	
	private double d(String value)
	{return Double.parseDouble(value);}
	
	private int i(String value)
	{return Integer.parseInt(value);}
}