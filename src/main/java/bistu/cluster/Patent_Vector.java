package bistu.cluster;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import bistu.functions.ClearTxt_Path;

/**
 * 构建每篇专利的向量组
 * @create_vector(HashMap<String, Integer> hm,String path)
 * hm为words_map,计算每个词在words_map中的位置
 * path为专利文件路径
 * @author Joen
 *
 */
public class Patent_Vector {

	ArrayList<Integer> patent_vector = null;
	
	public void create_vector(HashMap<String, Integer> hm,String path) throws IOException{
		patent_vector = new ArrayList<Integer>();
		File file = new File(path);
		String filename = file.getName();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split("   ");
			int location = hm.get(templist[0]);
			patent_vector.add(location);
		}
		Collections.sort(patent_vector);
		
	}

}
