package bistu.tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * 计算给定文档的TF_IDF_TFIDF;
 * @term_freq_map 存储当前专利的每一个词出现的次数
 * @doc_freq_map 存储当前专利的每一个词在总专利中出现的文件数目
 * @w_map 存储当前专利的每一个词的TFIDF值
 * @term_num 统计当前专利的总词数
 * @author Joen
 *
 */

public class TF_IDF {

	HashMap<String, Integer> term_freq_map = null;
	HashMap<String, Integer> doc_freq_map = null;
	HashMap<String, Double>  w_map = null;
	int term_num = 0;
	
	public void gettfidf(String p_path1, int doc_num, HashMap words_maps) throws IOException{

		
		//词频
		term_freq_map = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(p_path1));
		String line = "";
		term_num = 0;
		while((line = br.readLine()) != null){
			term_num++;
			if(term_freq_map.containsKey(line)){
				int value = term_freq_map.get(line) + 1;
				term_freq_map.put(line, value);
			}
			else{
				term_freq_map.put(line, 1);
			}
		}
		br.close();

		//文档频率
		doc_freq_map = new HashMap<String, Integer>();
		Set<String> pterm = term_freq_map.keySet();
		Iterator<String> set = pterm.iterator();
		String term = null;
		while(set.hasNext()){
			term = (String) set.next();
			int doc_freq = (Integer) words_maps.get(term);
			doc_freq_map.put(term, doc_freq);
		}
		
		//TFIDF计算
		w_map = new HashMap<String, Double>();
		String w_term = "";
		Set<String> set_w = term_freq_map.keySet();
		Iterator<String> iter_w = set_w.iterator();
		while(iter_w.hasNext()){
			w_term = (String) iter_w.next();
			int At = term_freq_map.get(w_term);
			int Bt = doc_freq_map.get(w_term);
			Double A = (double) At;
			Double B = (double) Bt;
			Double result = (A/term_num)*Math.log(doc_num/( B ))*100;
			w_map.put(w_term, result);
		}
	}	
	
/*	public static void test(HashMap hm){
		Iterator wset = hm.entrySet().iterator();
		while(wset.hasNext()){
			Map.Entry entry = (Map.Entry) wset.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + "++ " + value);
		}
		
	}
*/	
	
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Patents_Maps pm = new Patents_Maps("sourcefile/After_Parser");
		TFIDF tf = new TFIDF();
		gettfidf("sourcefile/test/CN104137671.txt", 120000, pm.maps);
		tf.test(tf.w_map);
	}
*/
}
