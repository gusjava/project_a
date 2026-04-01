package a.entity.gus06.sys.parser3.resolver1.op.seq.product.motley;

import a.framework.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	
	
	private Service listByScalar;
	private Service performSum;
	private Service multImage;
	private Service multRectangle;

	public EntityImpl() throws Exception
	{
		listByScalar = Outside.service(this,"gus06.sys.opposite1.list.byscalar");
		performSum = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.perform");
		multImage = Outside.service(this,"gus06.awt.bufferedimage.resize1.multiply");
		multRectangle = Outside.service(this,"gus06.awt.rectangle.multiply");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Object[] objByScalar = (Object[]) findByObjScalar(oo);
		if(objByScalar==null) throw new Exception("Invalid product operation");
		
		
		Double scalar = (Double) objByScalar[0];
		Object element = objByScalar[1];
		
		if(element==null) return null;
		
		if(element instanceof String)
		return multString(scalar,(String) element);
		
		if(element instanceof Color)
		return multColor(scalar,(Color) element);
		
		if(element instanceof List)
		return multList(scalar,(List) element);
		
		if(element instanceof E)
		return multE(scalar,(E) element);
		
		if(element instanceof G)
		return multG(scalar,(G) element);
		
		if(element instanceof F)
		return multF(scalar,(F) element);
		
		if(element instanceof P)
		return multP(scalar,(P) element);
		
		if(element instanceof H)
		return multH(scalar,(H) element);
		
		if(element instanceof BufferedImage)
		return multImage(scalar,(BufferedImage) element);
		
		if(element instanceof Rectangle)
		return multRectangle(scalar,(Rectangle) element);
		
		if(element instanceof int[])
		return multIntArray(scalar,(int[]) element);
		
		if(element instanceof double[])
		return multDoubleArray(scalar,(double[]) element);
		
		if(element instanceof int[][])
		return multIntMatrix(scalar,(int[][]) element);
		
		if(element instanceof double[][])
		return multDoubleMatrix(scalar,(double[][]) element);
		
		throw new Exception("Invalid product operation for element: "+element.getClass().getName());
		
	}
	
	
	
	
	private Object[] findByObjScalar(Object[] oo) throws Exception
	{
		double factor = 1;
		Object element = null;
		
		for(Object o:oo)
		{
			if(o instanceof Number) factor *= ((Number)o).doubleValue();
			else if(element==null) element = o;
			else throw new Exception("Non scalar element found many times: "+element+" and "+o);
		}
		return new Object[]{Double.valueOf(factor),element};
	}
	
	
	
	
	private String multString(Double k, String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<k.intValue();i++) b.append(s);
		return b.toString();
	}
	
	private Color multColor(Double k, Color c)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		int r1 = (int) (r*k.doubleValue());
		int g1 = (int) (g*k.doubleValue());
		int b1 = (int) (b*k.doubleValue());
		
		if(r1<0) r1=0; else if(r1>255) r1=255;
		if(g1<0) g1=0; else if(g1>255) g1=255;
		if(b1<0) b1=0; else if(b1>255) b1=255;
		
		return new Color(r1,g1,b1);
	}
	
	private List multList(Double k, List l) throws Exception
	{
		return (List) listByScalar.t(new Object[]{l,Integer.valueOf(k.intValue())});
	}
	
	
	private E multE(Double k, E e)
	{return new E0(k.intValue(),e);}
	
	private G multG(Double k, G g)
	{return new G0(k.intValue(),g);}
	
	private F multF(Double k, F f)
	{return new F0(k.intValue(),f);}
	
	private P multP(Double k, P p)
	{return new P0(k.intValue(),p);}
	
	private H multH(Double k, H h)
	{return new H0(k.doubleValue(),h);}
	
	private BufferedImage multImage(Double k, BufferedImage image) throws Exception
	{return (BufferedImage) multImage.t(new Object[]{image,k});}
	
	private Rectangle multRectangle(Double k, Rectangle rect) throws Exception
	{return (Rectangle) multRectangle.t(new Object[]{rect,k});}
	
	
	
	private double[] multIntArray(Double k, int[] v)
	{
		double[] kv = new double[v.length];
		for(int i=0;i<v.length;i++) kv[i] = k.doubleValue() * v[i];
		return kv;
	}
	
	private double[] multDoubleArray(Double k, double[] v)
	{
		double[] kv = new double[v.length];
		for(int i=0;i<v.length;i++) kv[i] = k.doubleValue() * v[i];
		return kv;
	}
	
	private double[][] multIntMatrix(Double k, int[][] v)
	{
		int n1 = v.length;
		int n2 = n1>0 ? v[0].length : 0;
		
		double[][] kv = new double[n1][n2];
		
		for(int i=0;i<n1;i++)
		for(int j=0;j<n2;j++)
		kv[i][j] = k.doubleValue() * v[i][j];
		
		return kv;
	}
	
	private double[][] multDoubleMatrix(Double k, double[][] v)
	{
		int n1 = v.length;
		int n2 = n1>0 ? v[0].length : 0;
		
		double[][] kv = new double[n1][n2];
		
		for(int i=0;i<n1;i++)
		for(int j=0;j<n2;j++)
		kv[i][j] = k.doubleValue() * v[i][j];
		
		return kv;
	}
	
	
	
	private class E0 implements E
	{
		private int k;
		private E e;
		
		public E0(int k, E e)
		{
			this.k = k;
			this.e = e;
		}
		
		public void e() throws Exception
		{for(int i=0;i<k;i++) e.e();}
	}
	
	
	
	private class G0 implements G
	{
		private int k;
		private G g;
		
		public G0(int k, G g)
		{
			this.k = k;
			this.g = g;
		}
		
		public Object g() throws Exception
		{
			Object[] oo = new Object[k];
			for(int i=0;i<k;i++) oo[i] = g.g();
			return performSum.t(oo);
		}
	}
	
	
	
	private class F0 implements F
	{
		private int k;
		private F f;
		
		public F0(int k, F f)
		{
			this.k = k;
			this.f = f;
		}
		
		public boolean f(Object obj) throws Exception
		{
			for(int i=0;i<k;i++) if(f.f(obj)) return true;
			return false;
		}
	}
	
	
	
	private class P0 implements P
	{
		private int k;
		private P p;
		
		public P0(int k, P p)
		{
			this.k = k;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{for(int i=0;i<k;i++) p.p(obj);}
	}
	
	
	
	private class H0 implements H
	{
		private double k;
		private H h;
		
		public H0(double k, H h)
		{
			this.k = k;
			this.h = h;
		}
		
		public double h(double v) throws Exception
		{return h.h(v) * k;}
	}
}