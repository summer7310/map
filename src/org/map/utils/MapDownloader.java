/**
 * 多线程下载地图图片.
 */
package org.map.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

class Resource{
	private int from_x;
	private int to_x;
	private int from_y;
	private int to_y;
	private int zoom;
	
	public Resource(int from_x, int to_x, int from_y, int to_y, int zoom)
	{
		this.from_x = from_x;
		this.to_x = to_x;
		this.from_y = from_y;
		this.to_y = to_y;
		this.zoom = zoom;
	}
	public int getFrom_x() {
		return from_x;
	}
	public void setFrom_x(int fromX) {
		from_x = fromX;
	}
	public int getTo_x() {
		return to_x;
	}
	public void setTo_x(int toX) {
		to_x = toX;
	}
	public int getFrom_y() {
		return from_y;
	}
	public void setFrom_y(int fromY) {
		from_y = fromY;
	}
	public int getTo_y() {
		return to_y;
	}
	public void setTo_y(int toY) {
		to_y = toY;
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
}
public class MapDownloader implements Runnable {
	private Resource resource = null;
	public MapDownloader(Resource resource)
	{
		this.resource = resource;
	}
	public void run() {
		File zoomDir = new File("" + this.resource.getZoom());
		if(!zoomDir.exists())
			zoomDir.mkdir();
		
		//获得系统对应的路径标识win-->\,unix-->/
	    String separator = System.getProperty("file.separator");
	    
		for(int x = this.resource.getFrom_x(); x <= this.resource.getTo_x(); x ++)
		{
			File xDir = new File("" + this.resource.getZoom() + separator + x);
			if(!xDir.exists())
				xDir.mkdir();
			for(int y = this.resource.getFrom_y(); y <= this.resource.getTo_y(); y ++)
			{
				
				String sUrl = "http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=" + x + "&y=" + y + "&z=" + this.resource.getZoom();
				System.out.println("线程" + Thread.currentThread().getName() + "发送请求：" + sUrl);
				URL url;
				try {
					url = new URL(sUrl);
				
					URLConnection uc = url.openConnection();
					DataInputStream dis = new DataInputStream(uc.getInputStream());
	
					FileOutputStream fo = new FileOutputStream("" + this.resource.getZoom() + separator + x + separator + y + ".png");
					while (dis.available() > 0)
					{
						fo.write(dis.readByte());
						fo.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("线程" + Thread.currentThread().getName() + "完成请求：" + sUrl);
			}
		}
		System.out.println("线程" + Thread.currentThread().getName() + "全部完成缩放级别为" + this.resource.getZoom() + "地图的下载!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//http://mt2.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=3380&y=1563&z=12&s=Galileo
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=3381&y=1564&z=12&s=Gal
		Resource r1 = new Resource(3380, 3381, 1563, 1564, 12);
		
		//http://mt0.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=6760&y=3126&z=13&s=Galile
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=6763&y=3129&z=13&s=Ga
		Resource r2 = new Resource(6760, 6763, 3126, 3129, 13);
		
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=13521&y=6252&z=14&s=Galileo
		//http://mt0.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=13526&y=6259&z=14&s=Galil
		Resource r3 = new Resource(13521, 13526, 6252, 6259, 14);
		
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=27042&s=&y=12505&z=15&s=Ga
		//http://mt3.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=27053&s=&y=12519&z=15&s=Galile
		Resource r4 = new Resource(27042, 27053, 12505, 12519, 15);
		
		//http://mt3.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=54085&s=&y=25010&z=16&s=
		//http://mt3.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=54107&s=&y=25038&z=16&s=Galileo
		Resource r5 = new Resource(54085, 54107, 25010, 25038, 16);
		
		//http://mt0.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=108170&s=&y=50020&z=17&s=Gal
		//http://mt2.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=108214&s=&y=50077&z=17&s=Galile
		Resource r6 = new Resource(108170, 108214, 50020, 50077, 17);
		
		//http://mt0.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=216340&y=100042&z=18&s=Galile
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=216427&y=100154&z=18&s=Ga
		Resource r7 = new Resource(216340, 216427, 100042, 100154, 18);
		
		//http://mt1.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=432681&y=200084&z=19&s=Galileo
		//http://mt0.google.cn/vt/lyrs=m@127&hl=zh-CN&gl=cn&x=432854&y=200308&z=19&s=Galileo
		Resource r8 = new Resource(432681, 432854, 200084, 200308, 19);
		
		ArrayList<Thread> threadContainer = new ArrayList<Thread>();
		Thread t1 = new Thread(new MapDownloader(r1));
		Thread t2 = new Thread(new MapDownloader(r2));
		Thread t3 = new Thread(new MapDownloader(r3));
		Thread t4 = new Thread(new MapDownloader(r4));
		Thread t5 = new Thread(new MapDownloader(r5));
		Thread t6 = new Thread(new MapDownloader(r6));
		Thread t7 = new Thread(new MapDownloader(r7));
		Thread t8 = new Thread(new MapDownloader(r8));
		
		//加入线程容器
		threadContainer.add(t1);
		threadContainer.add(t2);
		threadContainer.add(t3);
		threadContainer.add(t4);
		threadContainer.add(t5);
		threadContainer.add(t6);
		threadContainer.add(t7);
		threadContainer.add(t8);
		
		//启动所有线程
		for(Thread thread : threadContainer)
		{
			thread.start();
		}
		
		
		//join
		for(Thread thread : threadContainer)
		{
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//所有下载完成
		System.out.println("---下载全部完成---");
		
	}

}
