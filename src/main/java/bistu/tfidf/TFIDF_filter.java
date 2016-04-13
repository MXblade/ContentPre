package bistu.tfidf;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import bistu.functions.ClearTxt_Path;

/**
 * 对tfidf进行过滤，滤掉小于filter_value的词，进行排序后，写入文件
 * @path 需要过滤tfidf的文件夹路径
 * @filter_value 过滤值
 * @outpath 过滤后输出的文件夹路径
 * @author Joen
 *
 */
public class TFIDF_filter {
	Map<String, Double> words_map = null;
	
	/*
	 * 对tfidf低的词进行过滤,并经过排序后写入
	 */
	public void filter(String path,Double filter_value, String outpath) throws IOException{
		
		//清空并创建输出文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);

		//遍历path 下所有文件
		File folder = new File(path);
		File[] files = folder.listFiles();
		
		int num = 0;
		for(File f: files){
			System.out.println(num++);
			words_map = new HashMap<String, Double>();
			String filepath = f.getPath();
			String filename = f.getName();
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String temp = null;
			while((temp = br.readLine()) != null){
				//获取tfidf值
				String[] templist = temp.split("   ");
				Double tfidf = Double.parseDouble(templist[1]);
				//判断tfidf值与过滤值的大小
				if(tfidf > filter_value){
					words_map.put(templist[0], tfidf);
				}
			}
			//对存储过滤后词和tfidf的words_map按照tfidf排序大小
			List<Map.Entry<String, Double>> words_list = tfidf_sort(words_map);
			//构建输出文件路径，清空并创建文件
			String outfilepath = outpath + "/" +filename;
			clear.cleartxt(outfilepath);
			//输出文件
			BufferedWriter bw = new BufferedWriter(new FileWriter(outfilepath));
			for(int i = 0; i < words_list.size(); i++){
				bw.write(words_list.get(i).getKey() + "   " + words_list.get(i).getValue());
				bw.newLine();
			}
			br.close();
			bw.close();
		}
	}
	
	/*
	 * 对hashmap进行排序，返回list
	 */
	public List<Map.Entry<String, Double>> tfidf_sort(Map<String, Double> words_map2){
		List<Map.Entry<String, Double>> infoIds =
			    new ArrayList<Map.Entry<String, Double>>(words_map2.entrySet());
		
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        if((o2.getValue()-o1.getValue()) < 0)
		        	return -1;
		        else if((o2.getValue()-o1.getValue()) > 0)
		        	return 1;
		        else 
		        	return 0;
		    	//return (int) (o2.getValue() - o1.getValue()); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		}); 

		return infoIds;
	}
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		TFIDF_filter tff = new TFIDF_filter();
		tff.filter("sourcefile/term_tfidf", 2.0,"sourcefile/filter_tfidf");
	}
*/
}
