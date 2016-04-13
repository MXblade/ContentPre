package bistu.cluster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bistu.functions.ClearTxt_Path;

/**
 * 将构建的每一篇专利的向量组写入文件，方便之后读取。
 * @PatentsVectors(String words_map_path,String path, String outpath)
 * words_map_path words_map文件路径
 * path 专利文件夹的路径
 * outpath 向量组写入文件的路径
 * @author Joen
 *
 */
public class Create_PatentsVectors {

	public void PatentsVectors(String words_map_path,String path, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		Read_Words_Map wmap = new Read_Words_Map(words_map_path);
		Patent_Vector pvector = new Patent_Vector();
		File folder = new File(path);
		File[] files = folder.listFiles();
		clear.cleartxt(outpath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		int num = 0;
		for(File f: files){
			String filepath = f.getPath();
			String filename = f.getName();
			//将专利号中除数字以外的字符清除
			filename = filename.replaceAll("[^0-9]", "");
			//构建每片专利的向量组
			pvector.create_vector(wmap.Words_Map, filepath);
			//对应写入文件名，向量组
			bw.write(filename);
			for(int i = 0; i < pvector.patent_vector.size(); i++){
				bw.write("   " + pvector.patent_vector.get(i));
			}
			bw.newLine();
			System.out.println(num++);
		}
		bw.close();
	}
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Create_PatentsVectors wpv = new Create_PatentsVectors();
		wpv.PatentsVectors("sourcefile/Words_Map.txt", "sourcefile/filter_tfidf", "sourcefile/Patents_Vectors.txt");
	}
*/
}
