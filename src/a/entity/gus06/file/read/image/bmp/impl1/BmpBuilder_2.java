package a.entity.gus06.file.read.image.bmp.impl1;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class BmpBuilder_2 {

	
	public static final int BITMAP_FILE_HEADER = 14;
	public static final int BITMAP_INFO_HEADER = 40;
	public static final String MAGIC_NUMBER = "BM";
	
	
	private BufferedInputStream fs;
	private StringBuffer info;
	
	public String toString()
	{return info.toString();}
	
	
	
	private byte[] bf;
	private byte[] bi;
	
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
	
	
	public int nNumColors;
	public int[] npalette;
	public int[] imageData;
	public Image image;
	
	
	
	
	
	public BmpBuilder_2(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		fs= new BufferedInputStream(fis);
		
		info = new StringBuffer();
		
		bf=new byte[BITMAP_FILE_HEADER];
		fs.read(bf,0,BITMAP_FILE_HEADER);
		
		bi=new byte[BITMAP_INFO_HEADER];
		fs.read(bi,0,BITMAP_INFO_HEADER);

		fileHeader();
		bitmapHeader();
		
		switch(nbitcount)
		{
		case 1:image = build_bmp1();break;
		case 4:image = build_bmp4();break;
		case 8:image = build_bmp8();break;
		//BMP files with a BitsPerPixel value of 16 or greater will not have a color palette.
		case 16:image = build_bmp16();break;
		case 24:image = build_bmp24();break;
		case 32:image = build_bmp32();break;
		default:throw new Exception("Invalid nbitcount: "+nbitcount);
		}
		fs.close();
	}






	
	
	/*
	 * typedef struct _WinBMPFileHeader
	 * {
	 * WORD   FileType;	 File type, always 4D42h ("BM")
	 * DWORD  FileSize;	 Size of the file in bytes
	 * WORD   Reserved1;	Always 0
	 * WORD   Reserved2;	Always 0
	 * DWORD  BitmapOffset; Starting position of image data in bytes
	 * } WINBMPFILEHEADER;
	 */
	private void fileHeader() throws Exception
	{
		//File type (2-byte magic value), always 4D42h ("BM")
		String type = ""+(char)bf[0]+(char)bf[1];
		if(!type.equals(MAGIC_NUMBER))
			throw new Exception("wrong magic number for BMP: "+type);
		
		nsize =		 findInt_4(bf,2);
		
		info.append(" size="+nsize);
	}

	
	
	
	
	private void bitmapHeader() throws Exception
	{   
		// bitmap header
		nbisize =	   findInt_4(bi,0);	 /* Size of this header in bytes */
		nwidth =		findInt_4(bi,4);	 /* Image width in pixels */
		nheight =	   findInt_4(bi,8);	 /* Image height in pixels */
		nplanes =	   findInt_2(bi,12);	/* Number of color planes */
		nbitcount =	 findInt_2(bi,14);	/* Number of bits per pixel */
		ncompression =  findInt_4(bi,16);	/* Compression methods used */
		nsizeimage =	findInt_4(bi,20);	/* Size of bitmap in bytes */
		nxpm =		  findInt_4(bi,24);	/* Horizontal resolution in pixels per meter */
		nypm =		  findInt_4(bi,28);	/* Vertical resolution in pixels per meter */
		nclrused =	  findInt_4(bi,32);	/* Number of colors in the image */
		nclrimp =	   findInt_4(bi,36);	/* Minimum number of important colors */
		
		info.append(" - w="+nwidth);
		info.append(" - h="+nheight);
		info.append(" - BitCount="+nbitcount);
		info.append(" - Comp="+ncompression);
		info.append(" - SizeImage="+nsizeimage);
		

		/*
		 * Compression indicates the type of encoding method used to compress the bitmap data.
		 * - 0 indicates that the data is uncompressed;
		 * - 1 indicates that the 8-bit RLE algorithm was used;
		 * - 2 indicates that the 4-bit RLE algorithm was used;
		 * - 3 indicates that bitfields encoding was used.
		 * 
		 * If the bitmap contains 16 or 32 bits per pixel, then only a Compression value of 3 is supported
		 * and the RedMask, GreenMask, and BlueMask fields will be present following the header in place of a color palette.
		 * If Compression is a value other than 3, then the file is identical to a Windows 3.x BMP file. 
		 */
		
		/*
		 * SizeOfBitmap is the size of the stored bitmap in bytes.
		 * This value is typically zero when the bitmap data is uncompressed;
		 * in this case, the decoder computes the size from the image dimensions.
		 */
		
		/*
		 * ColorsUsed is the number of colors present in the palette.
		 * If this value is zero, and the value of BitsPerPixel is less than 16,
		 * then the number of entries is equal to the maximum size possible for the colormap.
		 * BMP files with a BitsPerPixel value of 16 or greater will not have a color palette.
		 * This value is calculated by using the value of the BitsPerPixel field: 
		 */
		
		/*
		 * ColorsImportant is the number of significant colors in the palette,
		 * determined by their frequency of appearance in the bitmap data;
		 * the more frequent the occurrence of a color, the more important it is.
		 * This field is used to provide as accurate a display as possible
		 * when using graphics hardware supporting fewer colors than are defined in the image.
		 * ColorsImportant is 0 if all of the colors in the palette are to be considered important.
		 */
	}
	
	
	
	
	
	
	
	
	private Image buildImage_fromPalette()
	{
		int[] colorData = new int[imageData.length];
		for(int i=0;i<imageData.length;i++)
		{
			int value = imageData[i];
			colorData[i] = npalette[value];
		}
		
		return createImage(colorData);
	}
	
	
	
	
	
	private Image createImage(int[] pix)
	{
		MemoryImageSource mis = new MemoryImageSource(nwidth,nheight,pix,0,nwidth);
		return Toolkit.getDefaultToolkit().createImage(mis);
	}
	
	
	
	
	
	
	
	
	private int findInt_4(byte[] b, int p)
	{
		return (((int)b[p+3]&0xff)<<24)
		| (((int)b[p+2]&0xff)<<16)
		| (((int)b[p+1]&0xff)<<8)
		| (int)b[p]&0xff;
	}
	
	
	
	private int findInt_2(byte[] b, int p)
	{
		return (((int)b[p+1]&0xff)<<8)
		| (int)b[p]&0xff;
	}
	
	
	
	private int findInt_color_3(byte[] b, int p)
	{
		return (255&0xff)<<24
		| (((int)b[p+2]&0xff)<<16)
		| (((int)b[p+1]&0xff)<<8)
		| (int)b[p]&0xff;
	}
	
	
	/*
	 * refaire findInt_color_2
	 * qui n'utilise que p et p+1
	 */
	private int findInt_color_2(byte[] b, int p)
	{
		return (255&0xff)<<24
		| (((int)b[p+2]&0xff)<<16)
		| (((int)b[p+1]&0xff)<<8)
		| (int)b[p]&0xff;
	}
	

	
	
	
	private void readPalette() throws IOException
	{
		nNumColors = 0;
		if(nclrused>0) nNumColors = nclrused;
		else nNumColors = (1&0xff)<<nbitcount;
		info.append(" - colors="+nNumColors);
		
		npalette = new int[nNumColors];
		byte[] bpalette = new byte[nNumColors*4];
		fs.read(bpalette,0,nNumColors*4);
		
		int bitPosition = 0;
		for(int n= 0;n<nNumColors;n++)
		{
			npalette[n] = findInt_color_3(bpalette,bitPosition);
			bitPosition += 4;
		}
	}
	
	
	
	
	
	
	private int pixelValue_1bit(byte[] bdata, int pixelPosition)
	{
		int bytePosition = (int) pixelPosition/8;
		int bitPosition = pixelPosition%8;
		
		int byte_ = ((int)bdata[bytePosition]&0xff);
		int r = (byte_>>(7-bitPosition)) & 1;
		return r;
	}
	
	
	
	
	private int pixelValue_4bit(byte[] bdata, int pixelPosition)
	{
		int bytePosition = (int) pixelPosition/2;
		int bitPosition = pixelPosition%2;
		
		int byte_ = ((int)bdata[bytePosition]&0xff);
		if(bitPosition==0) byte_ >>= 4;
		int r = byte_&0xf;
		return r;
	}
	
	
	
	
	private int pixelValue_8bit(byte[] bdata, int pixelPosition)
	{
		return ((int)bdata[pixelPosition]&0xff);
	}
	
	
	
	
	
	
	
	
	
	
	
	private Image build_bmp1() throws IOException
	{
		readPalette();
		imageData = new int[nwidth*nheight];
		
		if(nsizeimage==0)
		{
		 // v�rifier ??
			nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
			nsizeimage *= nheight;
		}
		
		int npad = (nsizeimage*8/nheight)-nwidth;
		int bitLength = (nwidth+npad)*nheight;
		
		int byteLength = (int)bitLength/8;
		if(bitLength%8>0) byteLength++;
		
		byte[] bdata = new byte[byteLength];
		fs.read(bdata,0,byteLength);
		
		int pixelPosition = 0;
		for(int i=0;i<nheight;i++)
		{
			for(int j=0;j<nwidth;j++)
			{
				int index = nwidth*(nheight-i-1)+j; 
				imageData[index] = pixelValue_1bit(bdata,pixelPosition);
				pixelPosition++;
			}
			pixelPosition += npad;
		}
		
		return buildImage_fromPalette();
	}
	
	
	
	
	
	
	
	private Image build_bmp4() throws IOException
	{
		readPalette();
		imageData = new int[nwidth*nheight];
		
		if(nsizeimage==0)
		{
		 // v�rifier ??
			nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
			nsizeimage *= nheight;
		}
		
		int npad = (nsizeimage*2/nheight)-nwidth;
		int bit4Length = (nwidth+npad)*nheight;
		
		int byteLength = (int)bit4Length/2;
		if(bit4Length%2>0) byteLength++;

		byte[] bdata = new byte[byteLength];
		fs.read(bdata,0,byteLength);
		
		int pixelPosition = 0;
		for(int i=0;i<nheight;i++)
		{
			for(int j=0;j<nwidth;j++)
			{
				int index = nwidth*(nheight-i-1)+j; 
				imageData[index] = pixelValue_4bit(bdata,pixelPosition);
				pixelPosition++;
			}
			pixelPosition += npad;
		}
		
		return buildImage_fromPalette();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Image build_bmp8() throws IOException
	{
		readPalette();
		
		if(nsizeimage==0)
		{
			nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
			nsizeimage *= nheight;
		}

		
		imageData = new int[nwidth*nheight];
		
		int npad = (nsizeimage/nheight)-nwidth;
		int byteLength = (nwidth+npad)*nheight;
		byte[] bdata = new byte[byteLength];
		fs.read(bdata,0,byteLength);
		
		int pixelPosition = 0;
		for(int i=0;i<nheight;i++)
		{
			for(int j=0;j<nwidth;j++)
			{
				int index = nwidth*(nheight-i-1)+j;
				imageData[index] = pixelValue_8bit(bdata,pixelPosition);
				pixelPosition++;
			}
			pixelPosition += npad;
		}
		
		return buildImage_fromPalette();
	}
	
	
	
	
	
	
	
	
	
	private Image build_bmp16() throws IOException
	{
		info.append(" - bmp16 not supported yet...");
		return null;
	}
	
	
	
	
	
	
	
	
	
	private Image build_bmp24() throws IOException
	{
		int npad = (nsizeimage/nheight)-nwidth*3;
		int byteLength = (nwidth+npad)*nheight*3;

		int ndata[] = new int[nheight*nwidth];
		byte brgb[] = new byte[byteLength];
		fs.read (brgb,0,byteLength);
		int nindex = 0;
		
		for(int i=0;i<nheight;i++)
		{
			for(int j=0;j<nwidth;j++)
			{
				ndata[nwidth*(nheight-i-1)+j] = findInt_color_3(brgb,nindex);
				nindex += 3;
			}
			nindex += npad;
		}
		return createImage(ndata);
	}
	
	
	
	
	
	
	
	
	
	private Image build_bmp32() throws IOException
	{
		info.append(" - bmp32 not supported yet...");
		return null;
	}
	
}
