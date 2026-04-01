package a.entity.gus06.sys.point3d.engine1;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160421";}

	

	public void v(String key, Object obj) throws Exception
	{
		List list = (List) obj;
		if(!key.contains("\n")) handleRule(list,key);
		else
		{
			String[] lines = key.split("\n");
			for(int i=0;i<lines.length;i++)
			handleRule(list,lines[i]);
		}
	}
	
	
	private void handleRule(List list, String rule) throws Exception
	{
		String[] n = rule.split(" ");
		
		if(n[0].equals("rotate")) {rotate(list,n[1],n[2]);return;}
		if(n[0].equals("rotate_OX")) {rotate_OX(list,n[1]);return;}
		if(n[0].equals("rotate_OY")) {rotate_OY(list,n[1]);return;}
		if(n[0].equals("rotate_OZ")) {rotate_OZ(list,n[1]);return;}
		if(n[0].equals("translate")) {translate(list,n[1]);return;}
		if(n[0].equals("affine")) {affine(list,n[1]);return;}
		if(n[0].equals("proj_XY")) {proj_XY(list);return;}
		if(n[0].equals("proj_XZ")) {proj_XZ(list);return;}
		if(n[0].equals("proj_YZ")) {proj_YZ(list);return;}
		if(n[0].equals("proj_OX")) {proj_OX(list);return;}
		if(n[0].equals("proj_OY")) {proj_OY(list);return;}
		if(n[0].equals("proj_OZ")) {proj_OZ(list);return;}
		
		throw new Exception("Invalid rule: "+rule);
	}
	
	
	
	private void rotate(List list, String axe, String angle_) throws Exception
	{
		double[] a = buildPoint(axe);
		double angle = d(angle_);
		for(int i=0;i<list.size();i++)
		rotate((double[])list.get(i),a,angle);
	}

	private void rotate_OX(List list, String angle_) throws Exception
	{
		double angle = d(angle_);
		for(int i=0;i<list.size();i++)
		rotate_OX((double[])list.get(i),angle);
	}
	
	private void rotate_OY(List list, String angle_) throws Exception
	{
		double angle = d(angle_);
		for(int i=0;i<list.size();i++)
		rotate_OY((double[])list.get(i),angle);
	}
	
	private void rotate_OZ(List list, String angle_) throws Exception
	{
		double angle = d(angle_);
		for(int i=0;i<list.size();i++)
		rotate_OZ((double[])list.get(i),angle);
	}
	
	private void translate(List list, String depl) throws Exception
	{
		double[] d = buildPoint(depl);
		for(int i=0;i<list.size();i++)
		translate((double[])list.get(i),d);
	}
	
	private void affine(List list, String factor_)
	{
		double factor = d(factor_);
		for(int i=0;i<list.size();i++)
		affine((double[])list.get(i),factor);
	}
	
	private void proj_XY(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_XY((double[])list.get(i));
	}
	
	private void proj_XZ(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_XZ((double[])list.get(i));
	}
	
	private void proj_YZ(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_YZ((double[])list.get(i));
	}
	
	private void proj_OX(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_OX((double[])list.get(i));
	}
	
	private void proj_OY(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_OY((double[])list.get(i));
	}
	
	private void proj_OZ(List list)
	{
		for(int i=0;i<list.size();i++)
		proj_OZ((double[])list.get(i));
	}
	
	
	
	
	

	
	private void rotate(double[] p, double[] a, double angle)
	{
		double ad = Math.sqrt(a[0]*a[0]+a[1]*a[1]);
		double teta1 = a[1]!=0?atan(a[0]/a[1]):Math.PI/2;
		double teta2 = a[2]!=0?atan(ad/a[2]):Math.PI/2;

		rotate_OZ(p,teta1);
		rotate_OX(p,teta2);
		rotate_OZ(p,angle);
		rotate_OX(p,-teta2);
		rotate_OZ(p,-teta1);
	}
	
	private void rotate_OX(double[] p, double angle)
	{
		double y = p[1];
		double z = p[2];
		p[1] = y*cos(angle)-z*sin(angle);
		p[2] = y*sin(angle)+z*cos(angle);
	}
	
	private void rotate_OY(double[] p, double angle)
	{
		double x = p[0];
		double z = p[2];
		p[0] = z*sin(angle)+x*cos(angle);
		p[2] = z*cos(angle)-x*sin(angle);
	}

	private void rotate_OZ(double[] p, double angle)
	{
		double x = p[0];
		double y = p[1];
		p[0] = x*cos(angle) - y*sin(angle);
		p[1] = x*sin(angle) + y*cos(angle);
	}
	
	private void translate(double[] p, double[] d)
	{
		p[0]+=d[0];
		p[1]+=d[1];
		p[2]+=d[2];
	}

	private void affine(double[] p, double factor)
	{
		p[0]*=factor;
		p[1]*=factor;
		p[2]*=factor;
	}

	private void proj_XY(double[] p){p[2]=0;}
	private void proj_XZ(double[] p){p[1]=0;}
	private void proj_YZ(double[] p){p[0]=0;}

	private void proj_OX(double[] p){p[1]=0;p[2]=0;}
	private void proj_OY(double[] p){p[0]=0;p[2]=0;}
	private void proj_OZ(double[] p){p[0]=0;p[1]=0;}
	
	
	
	
	private double cos(double angle)	{return Math.cos(angle);}
	private double sin(double angle)	{return Math.sin(angle);}
	private double atan(double angle)	{return Math.atan(angle);}
	
	
	private double[] buildPoint(String value) throws Exception
	{
		String[] n = value.split(";");
		if(n.length!=3) throw new Exception("Invalid 3D point value: "+value);
		return new double[]{d(n[0]),d(n[1]),d(n[2])};
	}
	
	private double d(String value)
	{return Double.parseDouble(value);}
}
