package a.entity.gus06.file.read.image.bmp.impl1;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class BmpBuilder_1 {

	
	public static final int BITMAP_FILE_HEADER = 14;
	public static final int BITMAP_INFO_HEADER = 40;
	
	
	private BufferedInputStream fs;
	private StringBuffer info;
	
	public String toString()
	{return info.toString();}
	
	
	private byte[] bf;
	private byte[] bi;
	
	public String type;
	public int nsize;
	public int nbisize;
	public int nwidth;
	public int nheight;
	public int nplanes;
	public int nbitcount;
	public int ncompression;
	public int nsizeimage;
	public int nxpm;
	public int nypm;
	public int nclrused;
	public int nclrimp;
	
	public Image image;
	
	
	
	
	
	public BmpBuilder_1(File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		fs= new BufferedInputStream(fis);
		
		info = new StringBuffer();
		
		bf=new byte[BITMAP_FILE_HEADER];
		bi=new byte[BITMAP_INFO_HEADER];
		
		fs.read(bf,0,BITMAP_FILE_HEADER);
		fs.read(bi,0,BITMAP_INFO_HEADER);

		extractInfo();
		
		switch(nbitcount)
		{
			case 1:image = build_bmp1();break;
			case 4:image = build_bmp4();break;
			case 8:image = build_bmp8();break;
			case 24:image = build_bmp24();break;
			default:info.append(" aborting...");
		}
		fs.close();
	}






	
	

	private void extractInfo()
	{
		// type du fichier BMP
		type = ""+(char)bf[0]+(char)bf[1];
		info.append(" type="+type);
		
		// taille du fichier BMP
		nsize = (((int)bf[5]&0xff)<<24) 
		| (((int)bf[4]&0xff)<<16)
		| (((int)bf[3]&0xff)<<8)
		| (int)bf[2]&0xff;
		info.append(" size="+nsize);
		
		// taille du info header
		nbisize = (((int)bi[3]&0xff)<<24)
		| (((int)bi[2]&0xff)<<16)
		| (((int)bi[1]&0xff)<<8)
		| (int)bi[0]&0xff;
		//System.out.println("Size of bitmapinfoheader is :"+nbisize);
		
		// largeur de l'image
		nwidth = (((int)bi[7]&0xff)<<24)
		| (((int)bi[6]&0xff)<<16)
		| (((int)bi[5]&0xff)<<8)
		| (int)bi[4]&0xff;
		info.append(" width="+nwidth);
		
		// hauteur de l'image
		nheight = (((int)bi[11]&0xff)<<24)
		| (((int)bi[10]&0xff)<<16)
		| (((int)bi[9]&0xff)<<8)
		| (int)bi[8]&0xff;
		info.append(" height="+nheight);
		
		nplanes = (((int)bi[13]&0xff)<<8) | (int)bi[12]&0xff;
		//System.out.println("Planes is :"+nplanes);
		
		nbitcount = (((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
		//System.out.println("BitCount is :"+nbitcount);
		info.append(" BitCount="+nbitcount);
		
		// Look for non-zero values to indicate compression
		ncompression = (((int)bi[19])<<24)
		| (((int)bi[18])<<16)
		| (((int)bi[17])<<8)
		| (int)bi[16];
		info.append(" Compression="+ncompression);
		
		nsizeimage = (((int)bi[23]&0xff)<<24)
		| (((int)bi[22]&0xff)<<16)
		| (((int)bi[21]&0xff)<<8)
		| (int)bi[20]&0xff;
		info.append(" SizeImage="+nsizeimage);
		
		nxpm = (((int)bi[27]&0xff)<<24)
		| (((int)bi[26]&0xff)<<16)
		| (((int)bi[25]&0xff)<<8)
		| (int)bi[24]&0xff;
		//System.out.println("X-Pixels per meter is :"+nxpm);
		
		nypm = (((int)bi[31]&0xff)<<24)
		| (((int)bi[30]&0xff)<<16)
		| (((int)bi[29]&0xff)<<8)
		| (int)bi[28]&0xff;
		//System.out.println("Y-Pixels per meter is :"+nypm);
		
		nclrused = (((int)bi[35]&0xff)<<24)
		| (((int)bi[34]&0xff)<<16)
		| (((int)bi[33]&0xff)<<8)
		| (int)bi[32]&0xff;
		//System.out.println("Colors used are :"+nclrused);
		
		nclrimp = (((int)bi[39]&0xff)<<24)
		| (((int)bi[38]&0xff)<<16)
		| (((int)bi[37]&0xff)<<8)
		| (int)bi[36]&0xff;
		//System.out.println("Colors important are :"+nclrimp);
	}
	
	
	
	
	
	
	
	
	
	private Image build_bmp24() throws IOException
	{
		//	 No Palatte data for 24-bit format but scan lines are
		//	 padded out to even 4-byte boundaries.

		int npad = (nsizeimage/nheight)-nwidth*3;
		int byteLength = (nwidth+npad)*nheight*3;

		int ndata[] = new int[nheight * nwidth];
		byte brgb[] = new byte[byteLength];
		fs.read (brgb, 0,byteLength);
		int nindex = 0;
		
		for(int j=0;j<nheight;j++)
		{
			for(int i=0;i<nwidth;i++)
			{
				ndata[nwidth*(nheight-j-1)+i] =
					(255&0xff)<<24
					| (((int)brgb[nindex+2]&0xff)<<16)
					| (((int)brgb[nindex+1]&0xff)<<8)
					| (int)brgb[nindex]&0xff;
//			  System.out.println("Encoded Color at ("
//	  +i+","+j+")is:"+nrgb+" (R,G,B)= ("
//			  +((int)(brgb[2]) & 0xff)+","
//			  +((int)brgb[1]&0xff)+","
//			  +((int)brgb[0]&0xff)+")");
				nindex += 3;
			}
			nindex += npad;
		}
		MemoryImageSource mis = new MemoryImageSource(nwidth,nheight,ndata,0,nwidth);
		return create(mis);
	}
	
	
	
	
	
	
	
	
	
	private Image build_bmp8() throws IOException
	{
		//	 Have to determine the number of colors, the clrsused
		//	 parameter is dominant if it is greater than zero.  If
		//	 zero, calculate colors based on bitsperpixel.
		int nNumColors = 0;
		
		if(nclrused>0) nNumColors = nclrused;
		else nNumColors = (1&0xff)<<nbitcount;
		
		//System.out.println("The number of Colors is"+nNumColors);
		
		//	 Some bitmaps do not have the sizeimage field calculated
		//	 Ferret out these cases and fix 'em.
		if(nsizeimage==0)
		{
			nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
			nsizeimage *= nheight;
			//System.out.println("nsizeimage (backup) is"+nsizeimage);
		}
		
		//	 Read the palatte colors.
		int  npalette[] = new int[nNumColors];
		byte bpalette[] = new byte[nNumColors*4];
		fs.read (bpalette, 0, nNumColors*4);
		int nindex8 = 0;
		
		for(int n= 0;n<nNumColors;n++)
		{
			npalette[n] = (255&0xff)<<24
			| (((int)bpalette[nindex8+2]&0xff)<<16)
			| (((int)bpalette[nindex8+1]&0xff)<<8)
			| (int)bpalette[nindex8]&0xff;
//			System.out.println ("Palette Color "+n
//					+" is:"+npalette[n]+" (res,R,G,B)= ("
//					+((int)(bpalette[nindex8+3]) & 0xff)+","
//					+((int)(bpalette[nindex8+2]) & 0xff)+","
//					+((int)bpalette[nindex8+1]&0xff)+","
//					+((int)bpalette[nindex8]&0xff)+")");
			nindex8 += 4;
		}
		
		//	 Read the image data (actually indices into the palette)
		//	 Scan lines are still padded out to even 4-byte
		//	 boundaries.
		int npad8 = (nsizeimage / nheight) - nwidth;
		//System.out.println("nPad is:"+npad8);
		
		int  ndata8[] = new int [nwidth*nheight];
		byte bdata[] = new byte [(nwidth+npad8)*nheight];
		fs.read (bdata, 0, (nwidth+npad8)*nheight);
		nindex8 = 0;
		for (int j8 = 0; j8 < nheight; j8++)
		{
			for (int i8 = 0; i8 < nwidth; i8++)
			{
				ndata8 [nwidth*(nheight-j8-1)+i8] =
					npalette [((int)bdata[nindex8]&0xff)];
				nindex8++;
			}
			nindex8 += npad8;
		}
		MemoryImageSource mis = new MemoryImageSource(nwidth,nheight,ndata8,0,nwidth);
		return create(mis);
	}
	
	
	
	private Image build_bmp4() throws IOException
	{
		return null;
	}
	
	
	private Image build_bmp1() throws IOException
	{
		return null;
	}
	
	
	
	
	private Image create(ImageProducer producer)
	{return Toolkit.getDefaultToolkit().createImage(producer);}
}
