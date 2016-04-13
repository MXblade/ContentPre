package bistu.cluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * 将存储的每片专利的向量读入，构成HashMap<String, Integer[]>，key为专利号，value为专利对应的向量
 * @author Joen
 *
 */
public class Read_Vectors {
	HashMap<String, Integer[]> hm = new HashMap<String, Integer[]>();
	public void read(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split("   ");
			String id = templist[0];
			Integer[] vector = new Integer[templist.length-1];
			for(int i = 1; i < templist.length; i++){
				vector[i-1] = Integer.parseInt(templist[i]);
			}
			hm.put(id, vector);
			
		}
	}
}
