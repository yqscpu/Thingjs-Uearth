package com.uinv.gis.tileProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * GIS相关工具类
 * @author HY
 *
 */
public class GISUtil {
	/**
	 * 根据瓦片目录获,行列号,级别取瓦片图
	 * @param bundlesDir
	 * @param level
	 * @param row
	 * @param col
	 * @return
	 * @throws IOException
	 */
	public static byte[] getTiles(String bundlesDir,int level, int row, int col) throws IOException
	   {
	       int size = 128;
	       byte[] result = null;
	       InputStream isBundle = null;
	       InputStream isBundlx = null;
	       try
	       {
	    	   String l = "0" +level;
	           int lLength = l.length();
	           if (lLength > 2)
	           {
	               l = l.substring(lLength -2);
	           }
	           l = "L" + l;
	           int rGroup=size*(row/size);
	           String r = Integer.toHexString(rGroup);
	           //行号不足4位补齐4位，如果超过4位不作处理
	           if(r.length() < 4){
	        	   String new_r = "000" +r;
		           if (new_r.length() > 4)
		           {
		        	   new_r = new_r.substring(new_r.length() -4);
		           }
		           r = "R" + new_r;
	           }
	           else{
	        	   r = "R" + r;
	           }
	           //列号不足4位补齐4位，如果超过4位不作处理
	           int cGroup = size*(col/size);
	           String c = Integer.toHexString(cGroup);
	           if(c.length() < 4){
	        	   String new_c = "000" +c;
		           if (new_c.length() > 4)
		           {
		               new_c = new_c.substring(new_c.length() -4);
		           }
		           c = "C" + new_c;
	           }
	           else{
	        	   c = "C" + c;
	           }

	           String bundleBase = bundlesDir+ "/" + l + "/" + r + c;
	           String bundlxFileName =bundleBase + ".bundlx";
	           String bundleFileName =bundleBase + ".bundle";

	           int index = size * (col -cGroup) + (row - rGroup);
	           //行列号是整个范围内的，在某个文件中需要先减去前面文件所占有的行列号（都是128的整数）这样就得到在文件中的真是行列号
//	           System.out.println(bundlxFileName);
//	           System.out.println(bundleFileName);
//	           Resource bundlxFileResource = ResourceResolver.getResource(bundlxFileName);
//	           Resource bundleFileResource= ResourceResolver.getResource(bundleFileName);
	           isBundlx = new FileInputStream(new File(bundlxFileName));
	          // isBundlx..Seek(16 + 5 * index,SeekOrigin.Begin);
	           isBundlx.skip(16 + 5 * index);
	           byte[] buffer = new byte[5];
	           isBundlx.read(buffer);
	           long offset = (long)(buffer[0]& 0xff) + (long)(buffer[1] & 0xff)
	                   * 256 + (long)(buffer[2]& 0xff) * 65536
	                  + (long)(buffer[3] &0xff) * 16777216
	                   + (long)(buffer[4]& 0xff) * 4294967296L;
	          
	           isBundle = new FileInputStream(new File(bundleFileName));
//	           isBundle = new FileInputStream(bundleFileName);
	           //isBundle.Seek(offset,SeekOrigin.Begin);
	           isBundle.skip(offset);
	           byte[] lengthBytes = new byte[4];
	           isBundle.read(lengthBytes, 0,4);
	           int length =(int)(lengthBytes[0] & 0xff)
	                   + (int)(lengthBytes[1] & 0xff) * 256
	                   + (int)(lengthBytes[2]& 0xff) * 65536
	                   + (int)(lengthBytes[3]& 0xff) * 16777216;
	           result = new byte[length];
	           isBundle.read(result);
	       }
	       catch (Exception ex)
	       {
	           return null;
	       }
	       finally
	       {
	           if (isBundle != null)
	           {
	               isBundle.close();
	               isBundlx.close();
	           }
	       }
	       //System.out.println("level="+level+"row="+row+"col="+col);
	       return result;
	   }
}
