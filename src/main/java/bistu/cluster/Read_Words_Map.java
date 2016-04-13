package bistu.cluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import bistu.functions.ClearTxt_Path;

/**
 * 读取已经写入words_map的文件，重新构建words_map
 * @author Joen
 *
 */
public class Read_Words_Map {
	
	HashMap<String, Integer> Words_Map = new HashMap<String, Integer>();
	
	public Read_Words_Map(String path) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split("   ");
			Words_Map.put(templist[0], Integer.parseInt(templist[1]));
		}
		br.close();
	}

}
