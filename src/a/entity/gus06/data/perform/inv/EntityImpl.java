package a.entity.gus06.data.perform.inv;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151117";}


	private Service invFilter;
	private Service invFunction;
	private Service invMap;
	private Service invJPanel;
	private Service invJTabbedPane;
	private Service invJSplitPane;
	private Service invRenderedImage;
	private Service invImage;
	private Service invColor;
	
	public EntityImpl() throws Exception
	{
		invFilter = Outside.service(this,"gus06.feature.op.filter.inv");
		invFunction = Outside.service(this,"gus06.feature.op.function.inv");
		invMap = Outside.service(this,"gus06.map.inv.map");
		invJPanel = Outside.service(this,"gus06.swing.panel.inv.grid");
		invJTabbedPane = Outside.service(this,"gus06.swing.tabbedpane.inv");
		invJSplitPane = Outside.service(this,"gus06.swing.splitpane.inv");
		invRenderedImage = Outside.service(this,"gus06.awt.renderedimage.transform.color.invert");
		invImage = Outside.service(this,"gus06.awt.bufferedimage.transform.color.invert");
		invColor = Outside.service(this,"gus06.awt.color.inv.rgb");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return inv((Boolean) obj);
		if(obj instanceof Double) return inv((Double) obj);
		if(obj instanceof Integer) return inv((Integer) obj);
		if(obj instanceof Long) return inv((Long) obj);
		if(obj instanceof Float) return inv((Float) obj);
		
		if(obj instanceof F) return invFilter.t(obj);
		if(obj instanceof H) return invFunction.t(obj);
		if(obj instanceof Map) return invMap.t(obj);
		if(obj instanceof JPanel) return invJPanel.t(obj);
		if(obj instanceof JTabbedPane) return invJTabbedPane.t(obj);
		if(obj instanceof JSplitPane) return invJSplitPane.t(obj);
		
		if(obj instanceof boolean[]) return inv((boolean[]) obj);
		if(obj instanceof double[]) return inv((double[]) obj);
		if(obj instanceof int[]) return inv((int[]) obj);
		if(obj instanceof long[]) return inv((long[]) obj);
		if(obj instanceof float[]) return inv((float[]) obj);
		
		if(obj instanceof Color) return invColor.t(obj);
		if(obj instanceof Image) return invImage.t(obj);
		if(obj instanceof RenderedImage) return invRenderedImage.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Boolean inv(Boolean b)
	{return Boolean.valueOf(!b.booleanValue());}
	
	private Double inv(Double b)
	{return Double.valueOf(1.0/b.doubleValue());}
	
	private Double inv(Integer b)
	{return Double.valueOf(1.0/b.doubleValue());}
	
	private Double inv(Long b)
	{return Double.valueOf(1.0/b.doubleValue());}
	
	private Float inv(Float b)
	{return Float.valueOf(1.0f/b.floatValue());}
	
	
	
	private boolean[] inv(boolean[] b)
	{
		boolean[] r = new boolean[b.length];
		for(int i=0;i<b.length;i++) r[i] = !b[i];
		return r;
	}
	
	private double[] inv(double[] b)
	{
		double[] r = new double[b.length];
		for(int i=0;i<b.length;i++) r[i] = 1.0/b[i];
		return r;
	}
	
	private double[] inv(int[] b)
	{
		double[] r = new double[b.length];
		for(int i=0;i<b.length;i++) r[i] = 1.0/((double) b[i]);
		return r;
	}
	
	private double[] inv(long[] b)
	{
		double[] r = new double[b.length];
		for(int i=0;i<b.length;i++) r[i] = 1.0/((double) b[i]);
		return r;
	}
	
	private double[] inv(float[] b)
	{
		double[] r = new double[b.length];
		for(int i=0;i<b.length;i++) r[i] = 1.0/((double) b[i]);
		return r;
	}
}