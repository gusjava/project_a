package a.entity.gus06.data.build.viewcomp;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import java.util.Locale;
import java.text.DecimalFormatSymbols;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180505";}


	private DecimalFormat df;
	private SimpleDateFormat sdf;

	public EntityImpl() throws Exception
	{
		df = new DecimalFormat("0.0000",DecimalFormatSymbols.getInstance(Locale.US));
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new JLabel();
		
		if(obj instanceof String) return handleString((String) obj);
		if(obj instanceof Double) return handleDouble((Double) obj);
		if(obj instanceof Float) return handleFloat((Float) obj);
		if(obj instanceof Integer) return handleInteger((Integer) obj);
		if(obj instanceof Boolean) return handleBoolean((Boolean) obj);
		if(obj instanceof Color) return handleColor((Color) obj);
		if(obj instanceof Date) return handleDate((Date) obj);
		if(obj instanceof Image) return handleImage(obj);
		if(obj instanceof RenderedImage) return handleImage(obj);
		
		return new JLabel(obj.toString());
	}
	
	
	private Object handleString(String s) throws Exception
	{
		JLabel l = new JLabel();
		l.setText(s);
		return l;
	}
	
	private Object handleDouble(Double d) throws Exception
	{
		String display = df.format(d.doubleValue());
		JLabel l = new JLabel();
		l.setText(display);
		return l;
	}
	
	private Object handleFloat(Float d) throws Exception
	{
		String display = df.format(d.floatValue());
		JLabel l = new JLabel();
		l.setText(display);
		return l;
	}
	
	private Object handleInteger(Integer n) throws Exception
	{
		JLabel l = new JLabel();
		l.setText(""+n);
		return l;
	}
	
	private Object handleBoolean(Boolean b) throws Exception
	{
		JCheckBox l = new JCheckBox();
		l.setSelected(b.booleanValue());
		return l;
	}
	
	private JLabel handleColor(Color color)
	{
		JLabel l = new JLabel(" ");
		l.setBackground(color);
		l.setOpaque(true);
		return l;
	}
	
	private JLabel handleDate(Date date)
	{
		String display = sdf.format(date);
		JLabel l = new JLabel();
		l.setText(display);
		return l;
	}
	
	private Object handleImage(Object image)
	{
		return new ScreenJPanel(image);
	}
	
	
	
	
	
	public class ScreenJPanel extends JPanel
	{
		private Object image;
		public ScreenJPanel(Object image) {this.image = image;}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(image==null) return;
        
			if(image instanceof RenderedImage)
				paintRenderedImage((Graphics2D)g,(RenderedImage)image);
			else if(image instanceof Image)
				paintImage((Graphics2D)g,(Image)image);
			else if(image instanceof ImageIcon)
				paintImageIcon((Graphics2D)g,(ImageIcon)image);
		}
		
		private void paintRenderedImage(Graphics2D g2, RenderedImage image)
		{
			int imageW = image.getWidth();
			int imageH = image.getHeight();
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
        		double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;
    		
    			if(cx>=cy)
    			{
    				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
    				AffineTransform af = AffineTransform.getTranslateInstance(a+ins.left,ins.top);
    	        		af.scale(cy,cy);
    	       		 	g2.drawRenderedImage(image,af);
			}
			else
    			{
    				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);		
    				AffineTransform af = AffineTransform.getTranslateInstance(ins.left,a+ins.top);
    				af.scale(cx,cx);
				g2.drawRenderedImage(image,af);
			}
		}
		
		
		private void paintImage(Graphics2D g2, Image image)
		{
			int imageW = image.getWidth(null);
			int imageH = image.getHeight(null);
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
			double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;

			if(cx>=cy)
			{
				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
				int dx = (int)(imageW*cy);
				int dy = getHeight()-ins.bottom-ins.top;
				g2.drawImage(image,a+ins.left,ins.top,dx,dy,this);
			}
			else
			{
				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);	
				int dx = getWidth()-ins.left-ins.right;
				int dy = (int)(imageH*cx);
				g2.drawImage(image,ins.left,a+ins.top,dx,dy,this);
			}
		}
		
		private void paintImageIcon(Graphics2D g2, ImageIcon image)
		{paintImage(g2,image.getImage());}
	}
}
