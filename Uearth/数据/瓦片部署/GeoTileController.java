package com.uinv.gis.tileProject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 获取地图瓦片的Controller
 * @author HY
 *
 */
public class GeoTileController {
	@Autowired
    private ApplicationArguments applicationArguments;
	@RequestMapping(value="/getTiles/{level}/{row}/{col}",method = RequestMethod.GET)
	public void getTiles(HttpServletRequest request, HttpServletResponse response,@PathVariable("level") int level,@PathVariable("col") int col,@PathVariable("row") int row) throws Exception {
		OutputStream out = null;
		try {
			List<String> args = applicationArguments.getNonOptionArgs();
			out=response.getOutputStream(); //得到向客户端输出二进制数据的对象 
			if(args.size()==0){
				response.setContentType("text/plain");
				String outString= "{\"success\":0,\"message\":\""+"未找到瓦片路径的参数"+"\"}";
				out.write(outString.getBytes("utf-8"));
				return;
			}
			String tilePath = args.get(0);
			byte[] result = GISUtil.getTiles(tilePath,level, row, col);
			if(result!=null){
				response.setContentType("image/png");
				out.write(result);
				return;
			}
			else{
				response.setContentType("text/plain");
				String outString= "{\"success\":0,\"message\":\""+"未找到图片,请检查路径是否正确"+"\"}";
				out.write(outString.getBytes("utf-8"));
			}
			out.flush();
			out.close();
		} catch (Exception e) {
            throw e;
		}
	}
}
