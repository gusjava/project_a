package a.entity.gus06.appli.dragontale.resource.builder.tilemap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, G {


	public String creationDate() {return "20200516";}


	
	private Service loader; 
	private int[][] map;


	
	public EntityImpl() throws Exception
	{
		loader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		
		
		String s = (String) loader.r("txt-tileMap");
		String[] lines = s.split("\n");
		
		int numCols = Integer.parseInt(lines[0]);
		int numRows = Integer.parseInt(lines[1]);
		map = new int[numRows][numCols];
		
		for(int row=0; row<numRows; row++) 
		{
			String line = lines[2+row];
			String[] tokens = line.split("\\s+");
			for(int col = 0; col<numCols; col++) 
			map[row][col] = Integer.parseInt(tokens[col]);
		}
	}



	public Object g() throws Exception
	{return map;}
}
