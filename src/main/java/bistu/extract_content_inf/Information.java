package bistu.extract_content_inf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 提取专利信息并写入文件
 * @author Joen
 *
 */

public class Information {
	
	public static void main(String[] args) throws IOException {
		//inputPath为原始专利文件夹，RewriteContent为覆盖操作
		File inputPath = new File("courcefile/UnDtd_Content");
		//提取后的数据存储位置
		File outputPath = new File("courcefile/ContentAllInformation");
		//获取inpath下所有文件
		File[] fs = inputPath.listFiles();
		//创建清空DTD规则对象
		RewriteContent re = new RewriteContent();
		//创建提取专利信息对象
		ExtraPart extra = new ExtraPart();
		
		for(int i = 0; i < fs.length; i++){
			System.out.println(i);
			//清除DTD规则
			re.ClearDTD(fs[i].getAbsolutePath());
			//提取专利信息
			extra.exinf(fs[i].getAbsolutePath());
			//构建每个文件对应的输出位置
			String HeadPa = outputPath + "\\" + extra.ContentID + ".txt";
			OutputStreamWriter pw = null;//定义一个输出流
			try {
				pw = new OutputStreamWriter(new FileOutputStream(HeadPa),"GBK");
				pw.write(extra.ContentID + "\n");//将要写入文件的内容，可以多次write
				pw.write(extra.ContentTitle);
				pw.write(extra.ContentAbs);
				pw.write(extra.ContentClaim);
				pw.close();//关闭流
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}

}
