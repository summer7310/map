package org.map.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.aviyehuda.easyimage.Image;

public class ImageUtils {
	private String oriDir = "d:\\nankai";
	private String desDir = "d:\\nankaiBW";
	
	public void convertImage(String imageFile, String s1, String s2)
	{
		Image image = new Image(this.oriDir + "\\" + s1 + "\\" + s2 + "\\" + imageFile);
		image.convertToBlackAndWhite();
		File s1File = new File(this.desDir + "\\" + s1);
		if(!s1File.exists()) //如果第一级目录不存在就创建一个
		{
			s1File.mkdir();
		}
		File s2File = new File(this.desDir + "\\" + s1 + "\\" + s2);
		if(!s2File.exists()) //如果第2级目录不存在就创建一个
		{
			s2File.mkdir();
		}
		
		image.saveAs(this.desDir + "\\" + s1 + "\\" + s2 + "\\" + imageFile);
	}
	public BufferedImage grayImage(final BufferedImage srcImg) {
        int width = srcImg.getWidth();
        int height = srcImg.getHeight();
        int[] pixels = new int[width * height];
        srcImg.getRGB(0, 0, width, height, pixels, 0, width);
        int newPixels[] = new int[width * height];
        for(int i = 0; i < width * height; i++) {
            HSLColor hslColor = new HSLColor(HSLColor.fromRGB(new Color(pixels[i])));
            newPixels[i] = hslColor.adjustSaturation(0).getRGB();
        }
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0, 0, width, height, newPixels, 0, width);
        return bi;
    }

	//把彩色图片变成灰度图片
	public void convertGrayImage(String imageFile, String s1, String s2)
	{
		File s1File = new File(this.desDir + "\\" + s1);
		if(!s1File.exists()) //如果第一级目录不存在就创建一个
		{
			s1File.mkdir();
		}
		File s2File = new File(this.desDir + "\\" + s1 + "\\" + s2);
		if(!s2File.exists()) //如果第2级目录不存在就创建一个
		{
			s2File.mkdir();
		}
		
		try {
			BufferedImage image=ImageIO.read(new File(this.oriDir + "\\" + s1 + "\\" + s2 + "\\" + imageFile));
			BufferedImage outImage = this.grayImage(image);
			ImageIO.write(outImage, "png", new File(this.desDir + "\\" + s1 + "\\" + s2 + "\\" + imageFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void convertGrayImage(String imageFile)
	{
		try {
			BufferedImage image=ImageIO.read(new File(imageFile));
			BufferedImage outImage = this.grayImage(image);
			ImageIO.write(outImage, "png", new File(imageFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void batchConvert()
	{
		File oriFile = new File(this.oriDir);
		ArrayList<String> errorFiles = new ArrayList<String>();
		for(File item1 : oriFile.listFiles())
		{
			for(File item2 : item1.listFiles())
			{
				for(File item3 : item2.listFiles())
				{
					//System.out.println(item3.getPath());
					try{
						//this.convertImage(item3.getName(), item1.getName(), item2.getName());
						this.convertGrayImage(item3.getName(), item1.getName(), item2.getName());
					}
					catch(Exception e){
						//System.out.println(item3.getPath());
						errorFiles.add(item2.getPath());
					}
					
				}
			}
		}
		for(String output : errorFiles)
		{
			System.out.println(output);
		}
	}
	
	public static void main(String[] args)
	{
		//批量转全部
		//new ImageUtils().batchConvert();
		
		//转一个
		new ImageUtils().convertGrayImage("d:\\074.png");
		
	}
}
