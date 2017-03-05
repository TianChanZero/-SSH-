/**
 * 
 */
package indi.jack.chapter1.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.connector.InputBuffer;

/**
 * <p>Title:UploadFile</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author jackhoo
 * @date   2017年3月5日 上午11:03:40
 */
@WebServlet(
		name="uploadfile",
		urlPatterns="/upload",
		loadOnStartup=1
		)
@MultipartConfig(
		fileSizeThreshold=5_242_880,//5mb文件阀
		maxFileSize=20971520l,//20mb
		maxRequestSize=41943040l//50mb
		)
public class UploadFile extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filepart=req.getPart("file1");
		if (filepart!=null) {
			InputStream inputStream=filepart.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "utf-8");
			String filename=filepart.getName();
			String filetype=filepart.getContentType();
			System.out.println("文件名="+filename+"文件类型="+filetype);
			FileOutputStream fileOutputStream=new FileOutputStream(new File("D:/file.txt"));
			OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, "utf-8");
			int read;
			final char[] chars=new char[1024];
			while ((read=inputStreamReader.read(chars))!=-1) {
				outputStreamWriter.write(chars, 0, read);
			}
			fileOutputStream.close();
			inputStream.close();
		}
	}

}
