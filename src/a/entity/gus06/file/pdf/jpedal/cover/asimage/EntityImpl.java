package a.entity.gus06.file.pdf.jpedal.cover.asimage;

import java.awt.Image;
import java.io.File;
import org.jpedal.Display;
import org.jpedal.PdfDecoder;
import a.framework.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}
	
	public EntityImpl() throws Exception
	{
		System.setProperty("org.jpedal.jai","true");
	}
	
	public Object t(Object obj) throws Exception
	{
		return firstPageAsImage((File) obj);
	}
	
	private Image firstPageAsImage(File file) throws Exception
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Image> future = executor.submit(() -> firstPageAsImage_(file));
		
		try
		{
		    return future.get(120, TimeUnit.SECONDS);
		}
		catch (TimeoutException e)
		{
		    future.cancel(true);
		    throw new Exception("Timeout sur la génération d'image", e);
		} finally {
		    executor.shutdownNow();
		}
	}
	
	private Image firstPageAsImage_(File file) throws Exception
	{
		PdfDecoder decoder = null;
		
		try
		{
			decoder = new PdfDecoder(false);
			decoder.setDisplayView(Display.SINGLE_PAGE,Display.DISPLAY_CENTERED);
			decoder.openPdfFile(file.getAbsolutePath());
			decoder.decodePage(1);
			
			return decoder.getPageAsImage(1);
		}
		catch(Exception e)
		{throw e;}
		finally
		{if(decoder!=null) decoder.closePdfFile();}
	}
}