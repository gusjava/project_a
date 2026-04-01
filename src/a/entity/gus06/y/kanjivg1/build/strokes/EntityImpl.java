package a.entity.gus06.y.kanjivg1.build.strokes;

import a.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.net.URL;
import java.io.InputStream;
import java.net.URI;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250802";}
	
	
	public Object t(Object obj) throws Exception
	{
		Document document = buildDocument(obj);
		return buildStrokes(document);
	}
	
	private Document buildDocument(Object obj)  throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return fileToDocument((File) obj);
		if(obj instanceof URL) return urlToDocument((URL) obj);
		if(obj instanceof URI) return uriToDocument((URI) obj);
		if(obj instanceof InputStream) return inputStreamToDocument((InputStream) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Document fileToDocument(File file)  throws Exception
	{
		if(!file.isFile() || file.length()==0) return null;
		return uriToDocument(file.toURI());
	}
	
	private Document urlToDocument(URL url) throws Exception
	{
		return uriToDocument(url.toURI());
	}
	
	private Document uriToDocument(URI uri) throws Exception
	{
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
		return factory.createDocument(uri.toString());
	}
	
	private Document inputStreamToDocument(InputStream is)  throws Exception
	{
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
		return factory.createDocument(null, is);
	}
	
	
	private List buildStrokes(Document document) throws Exception
	{
		List strokes = new ArrayList();
		if(document==null) return strokes;
		
		NodeList pathElements = document.getElementsByTagName("path");
		for (int i = 0; i < pathElements.getLength(); i++)
		{
			Element pathElement = (Element) pathElements.item(i);
			String d = pathElement.getAttribute("d");
			String id = pathElement.getAttribute("id");
			
			// Heuristique : garder seulement les traits de dessin (eviter les numeros, decorations, etc.)
			if (id != null && !id.isEmpty() && d != null && !d.isEmpty())
			{
				if (!id.matches(".*[Nn]um.*"))
				{
					Shape stroke = parseSvgPathToShape(d);
					strokes.add(stroke);
				}
			}
		}
		return strokes;
	}
	
	private Shape parseSvgPathToShape(String svgPathD)
	{
		PathParser parser = new PathParser();
		AWTPathProducer producer = new AWTPathProducer();
		producer.setWindingRule(Path2D.WIND_NON_ZERO);
		parser.setPathHandler(producer);
		parser.parse(svgPathD);
		return producer.getShape();
	}
}
