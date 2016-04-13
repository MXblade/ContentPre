package bistu.cluster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bistu.functions.ClearTxt_Path;

/**
 * 构建专利中所有词和词的位置的hashmap，并写入文件，避免重复读取文件，方便之后构建每个专利的向量组
 * @Create_Words_Map(String path)
 * 给定文件夹路径，构建hashmap
 * @write_words_map(String path)
 * 将构建好的hashmap写入path的文件
 * @author Joen
 *
 */
public class Create_Words_Map {
	
	static HashMap<String, Integer> words_map = new HashMap<String, Integer>();
	
	/*
	 * 构建Hashmap，path为专利文件夹路径
	 */
	public Create_Words_Map(String path) throws IOException{
		File folder = new File(path);
		File[] files = folder.listFiles();
		int words_loc = 0;
		for(int i = 0; i < files.length; i++){
			String filepath = files[i].getPath();
			//String filename = files[i].getName();
			BufferedReader readin = new BufferedReader(new FileReader(filepath));
			String temp = null;
			while((temp = readin.readLine()) != null)
			{
				String[] templist = temp.split("   ");
				String term = templist[0];
				if(!words_map.containsKey(term)){
					words_map.put(term, words_loc);
					words_loc++;
				}
			}
			System.out.println(i);

		}
	}
	/*
	 * 将已经构建好的words_loc的hashmap写入文件，避免每次读大量的文件
	 */
	public void write_words_map(String path) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(path);
	    BufferedWriter bfw = new BufferedWriter(new FileWriter(path));
		Iterator it = words_map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry map = (Map.Entry) it.next();
			Object key = map.getKey();
			Object value = map.getValue();
			bfw.write(key.toString() + "   " + value.toString());
			bfw.newLine();
		}
		bfw.close();
	}
	

/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Create_Words_Map cwm = new Create_Words_Map("sourcefile/filter_tfidf");
		cwm.write_words_map("sourcefile/Words_Map.txt");
		
	}
*/
}
