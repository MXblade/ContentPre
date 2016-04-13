package bistu.tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * 构建所有专利数据的HashSet所组成的HashMap<String, HashMap>,String为每片文档名，HashSet为该文档的所有词。
 * @author Joen
 *
 */
public class Patents_Maps {

	HashMap<String, Integer> maps = new HashMap<String, Integer>();
	/*
	 * 对给定的文件夹路径创建HashMap<String, Integer>,String为词,Integer为出现该词的专利数目
	 */
	public Patents_Maps(String path) throws IOException{
		File folder = new File(path);
		File[] filelist = folder.listFiles();
//		HashMap_Patent hp = new HashMap_Patent();
		for(int i = 0; i < filelist.length; i++){
			String filepath = filelist[i].getPath();
			createhm(filepath);
		}
		System.out.println("finish");
		
	}
	
	/*
	 * 统计每一篇专利是否包含maps中的词
	 */
	public void createhm(String path) throws IOException{
		//HashMap<String, HashMap<String, Double>>= new has;
		HashSet<String> patent_set = new HashSet<String>();
		BufferedReader readin = new BufferedReader(new FileReader(path));
		String temp = null;
		while((temp = readin.readLine()) != null){
			if(!maps.containsKey(temp)){
				maps.put(temp, 1);
				patent_set.add(temp);
			}
			else if(!patent_set.contains(temp)){
				int doc_num = maps.get(temp);
				doc_num++;
				maps.put(temp, doc_num);
				patent_set.add(temp);
			}
		}
		readin.close();
	}

	/*
	 * Test
	 */
	public void test(){
		Iterator set = maps.entrySet().iterator();
		while(set.hasNext()){
			Map.Entry entry = (Map.Entry) set.next();
			Object key = entry.getKey();
			HashSet value = (HashSet) entry.getValue();
			System.out.println("the ID : " + key);
			Iterator setset = value.iterator();
			while(setset.hasNext()){
				String entry2 = (String) setset.next();
				System.out.println("The words : " + entry2 );
			}
		}
	}
	
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Patents_Maps pm = new Patents_Maps("sourcefile/test");
		pm.test();
	}
*/
}
