package bistu.extract_content_inf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**    
 * RewriteContent 
 * @对专利数据进行去除dtd格式操作，方便之后提取专利数据的ID，Title，Abstraction，Claim 
 * @Path = 专利文件路径
 */
public class RewriteContent {

	/*
	 * 清除xml中的dtd规则。
	 */
	public void ClearDTD(String path){
		try {
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			String temp = "";
			while((temp = raf.readLine())!= null){
				if(temp.contains("<?xml version=")){
					//System.out.println(temp);
					break;
				}
			}
			//覆盖写入多个空格符，用来覆盖DTD规则。
			raf.write("                                                                               ".getBytes());
			raf.close();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
