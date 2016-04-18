package bistu.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import bistu.functions.ClearTxt_Path;

/**
 * 对分词后的数据进行过滤停用词操作
 * 
 * @author Joen
 *
 */
public class Filter_StopWords {

	public void filte(String StopWordspath, String inpath, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		HashSet<String> stopwords = new HashSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(StopWordspath));
		String temp = null;
		while((temp = br.readLine()) != null)
		{
			stopwords.add(temp);
		}
		br.close();
		
		File folder = new File(inpath);
		File[] files = folder.listFiles();
		clear.clearpath(outpath);
		for(int i = 0; i < files.length; i++){
			String filepath = files[i].getPath();
			String filename = files[i].getName();
			String fileoutpath = outpath + "/" + filename;
			clear.cleartxt(fileoutpath);
			BufferedReader bfile = new BufferedReader(new FileReader(filepath));
			BufferedWriter out = new BufferedWriter(new FileWriter(fileoutpath));

			String filetemp = null;
			bfile.readLine();
			bfile.readLine();
			while((filetemp = bfile.readLine()) != null){
				if(!stopwords.contains(filetemp)){
					out.write(filetemp);
					out.newLine();
				}
			}
			out.close();
			bfile.close();
			System.out.println("dealing: " + i);
		}

		
	
		
	}
	
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Filter_StopWords filter = new Filter_StopWords();
		filter.filte("sourcefile/stopwordslist.txt", "sourcefile/After_Parser", "sourcefile/StopWords_Content");
	}

}
