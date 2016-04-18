package bistu.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bistu.functions.ClearTxt_Path;

/**
 * 过滤空格操作，现用不着
 * @author Joen
 *
 */
public class FilterSpace {

	public void filter(String path, String outpath)throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		File folder = new File(path);
		File[] files = folder.listFiles();
		int num = 0;
		for(File f:files){
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp = null;
			String filename = f.getName();
			String filepath = outpath + "/" + filename;
			clear.cleartxt(filepath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
			while((temp = br.readLine()) != null){
				if(!temp.equals("   ")){
					bw.write(temp);
					bw.newLine();
				}
			}
			bw.close();
			br.close();
			System.out.println(num++);

		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		FilterSpace fs = new FilterSpace();
		fs.filter("sourcefile/term_tfidf", "sourcefile/filter_space");
	}

}
