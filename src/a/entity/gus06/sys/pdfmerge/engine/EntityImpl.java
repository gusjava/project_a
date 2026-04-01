package a.entity.gus06.sys.pdfmerge.engine;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import com.lowagie.text.pdf.PdfReader;
import a.framework.*;

public class EntityImpl implements Entity, V, E {

	public String creationDate() {return "20161005";}
	
	private Document1 document;
	private File pdfFile;
	private File[] f;
	
	private Comparator c;
	
	public EntityImpl()
	{
		c = new Comparator(){
			public int compare(Object o1, Object o2)
			{
				String n1 = ((File)o1).getName();
				String n2 = ((File)o2).getName();
				return n1.compareTo(n2);
			}
		};
	}

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("pdfFile")) {pdfFile = (File) obj;return;}
		if(key.equals("files")) {f = toFiles(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public void e() throws Exception
	{
		if(pdfFile==null) throw new Exception("Pdf file not initialized yet");
		if(f==null) throw new Exception("Files not initialized yet");
		
		process();
	}
	
	private void process() throws Exception
	{
		try
		{
			document = new Document1(pdfFile);
			for(int i=0;i<f.length;i++)
			{
				if(isPDF(f[i])) handlePDF(f[i]);
				else if(isImage(f[i])) handleImage(f[i]);
			}
			document.close();
		}
		catch(Exception e)
		{
			if(document!=null) document.close();
			throw new Exception("Build failed for pdf file: "+pdfFile,e);
		}
	}
	
	private void handlePDF(File file) throws Exception
	{
		FileInputStream is = new FileInputStream(file);
		PdfReader pdfReader = new PdfReader(is);
		int number = pdfReader.getNumberOfPages();
		
		for(int i=1;i<=number;i++)
		document.importPage(pdfReader,i);
		
		is.close();
	}
	
	private void handleImage(File file) throws Exception
	{
		document.addImagePage(file);
	}
	
	private boolean isPDF(File f)
	{
		if(!f.isFile()) return false;
		String n = f.getName().toLowerCase();
		return n.endsWith(".pdf");
	}
	
	private boolean isImage(File f)
	{
		if(!f.isFile()) return false;
		String n = f.getName().toLowerCase();
		return n.endsWith(".jpg") || n.endsWith(".jpeg") || n.endsWith(".png") || n.endsWith(".gif");
	}
	
	private File[] toFiles(Object obj) throws Exception
	{
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof File) return sortArray(toFiles2((File)obj));
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File[] toFiles2(File path) throws Exception
	{
		if(path.isFile()) return new File[]{path};
		return path.listFiles();
	}
	
	private File[] sortArray(File[] f)
	{
		Arrays.sort(f,c);
		return f;
	}
}
