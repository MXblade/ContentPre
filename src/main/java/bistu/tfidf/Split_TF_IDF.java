package bistu.tfidf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bistu.functions.ClearTxt_Path;
/**
 * 将已经处理好的包含（词，tfidf，该篇文章总次数，tf，idf）的文件处理，分别显示tfidf，tf，idf值
 * @path 包含tfidf所有信息的文件夹路径
 * @tpath 输出tf值的文件夹路径
 * @ipath 输出idf值的文件夹路径
 * @tipath 输出tfidf值的文件夹路径
 * @author Joen
 *
 */
public class Split_TF_IDF {

	public void write(String path, String tpath, String ipath, String tipath) throws IOException{
		
		//清空输出文件夹内容，并创建文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(tpath);
		clear.clearpath(ipath);
		clear.clearpath(tipath);

		//遍历path所有文件
		File folder = new File(path);
		File[] files = folder.listFiles();
		for(int i = 0; i < files.length; i++){
			System.out.println(i);
			
			//获取文件路径和文件名
			String filepath = files[i].getPath();
			String filename = files[i].getName();
			
			//构建输出文件路径
			String tfoutpath = tpath + "/" + filename;
			String idfoutpath = ipath + "/" + filename;
			String tfidfoutpath = tipath + "/" + filename;
			
			//清空已有文件内容，并创建文件
			clear.cleartxt(tfidfoutpath);
			clear.cleartxt(tfoutpath);
			clear.cleartxt(idfoutpath);
			
			//构建输出流
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			BufferedWriter bt = new BufferedWriter(new FileWriter(tfoutpath));
			BufferedWriter bi = new BufferedWriter(new FileWriter(idfoutpath));
			BufferedWriter bti = new BufferedWriter(new FileWriter(tfidfoutpath));
			
			//分割并写入文件
			String temp = null;
			while((temp = br.readLine()) !=null){
				String[] templist = temp.split("   ");
				bti.write(templist[0] + "   " + templist[1]);
				bt.write(templist[0] + "   " + templist[3] + "   " + templist[2]);
				bi.write(templist[0] + "   " + templist[4]);
				bi.newLine();
				bt.newLine();
				bti.newLine();
			}
			br.close();
			bt.close();
			bi.close();
			bti.close();
		}
	}
	
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Split_TF_IDF rti = new Split_TF_IDF();
		rti.write("sourcefile/TF_IDF(term_tfidf_num_tf_idf)", "sourcefile/term_tf", "sourcefile/term_idf", "sourcefile/term_tfidf");
	}
*/
}
