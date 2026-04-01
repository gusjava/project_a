package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.rename;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161119";}


	private Service findDepMap;
	private Service confirm;
	private Service askInput;
	private Service clear;
	private Service listing;
	private Service move;
	private Service readFile;
	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		findDepMap = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.find.dependency.map");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		askInput = Outside.service(this,"gus06.input.text.dialog");
		clear = Outside.service(this,"gus06.dir.perform.removefiles0.clear");
		listing = Outside.service(this,"gus06.dir.listing0.files");
		move = Outside.service(this,"gus06.dir.perform.movefile");
		readFile = Outside.service(this,"gus06.file.read.string");
		writeFile = Outside.service(this,"gus06.file.write.string");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String scriptName = (String) o[1];
		String path1 = (String) o[2];
		
		String path2 = (String) askInput.t(new String[]{"Please, enter script new name:",path1});
		if(path2==null || path2.equals(path1)) return null;
		
		File dir1 = new File(root, path1.replace(".",File.separator));
		File dir2 = new File(root, path2.replace(".",File.separator));
		
		if(!dir1.isDirectory())
		{
			showErr(path1+" not found. Rename aborted");
			return null;
		}
		
		File[] ff1 = (File[]) listing.t(dir1);
		File[] ff2 = (File[]) listing.t(dir2);
		
		if(ff1==null || ff1.length==0) 
		{
			showErr(path1+" not found. Rename aborted");
			return null;
		}
		if(ff2!=null && ff2.length>0)
		{
			showErr(path2+" is not available. Rename aborted");
			return null;
		}
		
		Map depMap = (Map) findDepMap.t(new Object[]{root, scriptName, path1});
		if(!depMap.isEmpty())
		{
			boolean shouldRefactor = confirm.f(refactorConfirmMessage(depMap));
			if(shouldRefactor) refactor(depMap, path1, path2);
		}
		
		dir2.mkdirs();
		for(File f1:ff1) move.p(new File[]{f1,dir2});
		clear.p(dir1);
		return path2;
	}
	
	
	
	private String refactorConfirmMessage(Map depMap)
	{
		List list = new ArrayList(depMap.keySet());
		int nb = list.size();
		if(nb==1) return "One dependency has been found:\n- "+list.get(0)+"\nRefactor it ?";
		
		Collections.sort(list);
		StringBuffer b = new StringBuffer();
		b.append(nb+" dependencies have been found:\n");
		for(int i=0;i<nb;i++)
		b.append("- "+list.get(i)+"\n");
		b.append("Refactor them ?\n");
		return b.toString();
	}
	
	
	private void refactor(Map depMap, String path1, String path2) throws Exception
	{
		Iterator it = depMap.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f = (File) depMap.get(path);
			String s = (String) readFile.t(f);
			
			s = s.replace("\""+path1+"\"", "\""+path2+"\"");
			s = s.replace("'"+path1+"'", "'"+path2+"'");
			writeFile.p(new Object[]{f,s});
		}
	}
	
	
	private void showErr(String msg)
	{JOptionPane.showMessageDialog(null, msg ,"Error", JOptionPane.ERROR_MESSAGE);}
}