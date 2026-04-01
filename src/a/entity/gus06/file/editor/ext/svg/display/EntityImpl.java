package a.entity.gus06.file.editor.ext.svg.display;

import a.framework.*;
import java.io.File;
import javax.swing.JScrollPane;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Dimension;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;
import org.w3c.dom.Element;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JViewport;
import java.awt.geom.Dimension2D;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

public class EntityImpl implements Entity, I, P, MouseWheelListener, KeyListener {

	public String creationDate() {return "20250429";}

	private static final double MIN_ZOOM = 1.0;
	private static final double MAX_ZOOM = 10.0;
	
	private File file;
	private JSVGCanvas canvas;
	private JScrollPane scroll;
	
	private JLabel label;
	private JPanel panel;
	
	
	public EntityImpl() throws Exception
	{
		canvas = new JSVGCanvas();
		scroll = new JScrollPane(canvas);
		
		canvas.addMouseWheelListener(this);
		canvas.addKeyListener(this);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		String uri = fileToURI(file);
		
		if(uri!=null)
		{
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
			Document doc = factory.createDocument(uri);
			
			Element root = doc.getDocumentElement();
			
			String width = root.getAttribute("width");
			String height = root.getAttribute("height");
			
			StringBuffer b = new StringBuffer();
			b.append("["+width+","+height+"]");
			label.setText(b.toString());
			
			canvas.setURI(uri);
		}
		else
		{
			label.setText(" ");
			canvas.setURI(null);
		}
	}
	
	private String fileToURI(File file) throws Exception
	{
		if(file==null || !file.isFile() || file.length()==0) return null;
		return file.toURI().toString();
	}
	
	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (e.isControlDown())
		{
			double zoomFactor = (e.getWheelRotation() < 0) ? 1.1 : 0.9;
			Point mousePoint = e.getPoint();
			zoomAtMouse(mousePoint, zoomFactor);
		}
		else
		{
			scroll.dispatchEvent(e);
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if (e.isControlDown() && code == KeyEvent.VK_NUMPAD0) resetZoom();
	}
	
	public void keyReleased(KeyEvent e){}
	
	public void keyTyped(KeyEvent e){}
	
	
	
	private void zoomAtMouse(Point mousePosition, double zoomFactor)
	{
		AffineTransform transform = canvas.getRenderingTransform();
		if (transform == null) transform = new AffineTransform();
		
		double currentScale = transform.getScaleX();
		double newScale = currentScale * zoomFactor;
		
		newScale = Math.max(MIN_ZOOM, newScale);
		newScale = Math.min(MAX_ZOOM, newScale);
		
		double factor = newScale / currentScale;
		AffineTransform newTransform = new AffineTransform(transform);
		
		if (mousePosition != null)
		{
			double x = mousePosition.getX();
			double y = mousePosition.getY();
			
			newTransform.translate(x, y);
			newTransform.scale(factor, factor);
			newTransform.translate(-x, -y);
			
			transform.preConcatenate(newTransform);
		}
		else
		{
			newTransform.scale(factor, factor);
		}
		
		canvas.setRenderingTransform(newTransform, true);
		updateCanvasPreferredSize(newTransform);
		correctScrollPosition();
	}
	
	private void resetZoom()
	{
		AffineTransform identity = new AffineTransform();
		canvas.setRenderingTransform(identity, true);
		updateCanvasPreferredSize(identity);
		
		JViewport vp = scroll.getViewport();
		if (vp != null) vp.setViewPosition(new Point(0,0));
	}
	
	private void updateCanvasPreferredSize(AffineTransform transform)
	{
		Dimension2D svgSize = canvas.getSVGDocumentSize();
		if (svgSize == null) return;
		
		double scale = transform.getScaleX();
		int w = (int) Math.round(svgSize.getWidth() * scale);
		int h = (int) Math.round(svgSize.getHeight() * scale);
		
		canvas.setPreferredSize(new Dimension(w, h));
		canvas.revalidate();
	}
	
	private void correctScrollPosition()
	{
		JViewport vp = scroll.getViewport();
		if (vp == null) return;
		
		Point viewPosition = vp.getViewPosition();
		Dimension viewSize = vp.getExtentSize();
		Dimension canvasSize = canvas.getPreferredSize();
		
		int x = viewPosition.x;
		int y = viewPosition.y;
		
		if (x + viewSize.width > canvasSize.width)
		x = Math.max(0, canvasSize.width - viewSize.width);
		if (x < 0) x = 0;
		
		if (y + viewSize.height > canvasSize.height)
		y = Math.max(0, canvasSize.height - viewSize.height);
		if (y < 0) y = 0;
		
		vp.setViewPosition(new Point(x, y));
	}
}
