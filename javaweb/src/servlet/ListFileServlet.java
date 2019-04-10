package servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {  
	 
		  
	    public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");  
	        File root = new File(storeDirectory);  
	          
	        //��Map����ݹ���ļ�����key:UUID�ļ���   value:���ļ���  
	        Map<String, String> map = new HashMap<String, String>();  
	        treeWalk(root,map);  
	          
	        request.setAttribute("map", map);  
	        request.getRequestDispatcher("/listfile.jsp").forward(request, response);  
	    }  
	    //�ݹ飬���ļ����ŵ�Map��  
	    private void treeWalk(File root, Map<String, String> map) {  
	        if(root.isFile()){  
	            String fileName = root.getName();//�ļ���       
	            String oldFileName = fileName.substring(fileName.indexOf("_")+1);  
	            map.put(fileName, oldFileName);  
	        }else{  
	            File fs[] = root.listFiles();  
	            for(File file:fs){  
	                treeWalk(file, map);  
	            }  
	        }  
	          
	    }  
	  
	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        doGet(request, response);  
	    }  
	  
	}