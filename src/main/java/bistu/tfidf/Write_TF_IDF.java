package bistu.tfidf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import bistu.functions.ClearTxt_Path;

/**
 * 将词，tfidf,term_num(当前文本词数),tf,idf写入文件。
 * path：需要计算tfidf的文件路径
 * path2：tfidf等信息存储的文件里路径
 * 两者均为文件夹。
 * @author Joen
 *
 */
public class Write_TF_IDF {

	public void write(String path,String path2) throws IOException{
		//遍历给定的文件夹的文件
		File folder = new File(path);
		File[] files = folder.listFiles();
		//清空输出文件夹内容，并创建输出文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(path2);
		//给定path，构建所有专利的词-文档次数的hashmap
		Patents_Maps pm = new Patents_Maps(path);
		//计算tfidf
		TF_IDF tf = new TF_IDF();

		//写入文件
		for(int i = 0; i < files.length; i++){
			System.out.println("dealing: " + i);
			String filepath = files[i].getPath();
			String filename = files[i].getName();
			tf.gettfidf(filepath, files.length, pm.maps);
			String outpath = path2 + "/" + filename;
			clear.cleartxt(outpath);
			BufferedWriter out = new BufferedWriter(new FileWriter(outpath));
			Set<String> words = tf.w_map.keySet();
			
			Iterator<String> iterwords = words.iterator();
			while (iterwords.hasNext()) {
				String word = (String) iterwords.next();
				Double tidf = tf.w_map.get(word);
				int term_freq = tf.term_freq_map.get(word);
				int doc_freq = tf.doc_freq_map.get(word);
				int term_num = tf.term_num;
				out.write(word +  "   " + tidf + "   " + term_num + "   " + term_freq + "   " + doc_freq);
				out.newLine();;
			}
			out.close();
		}
	}
	
	
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Write_TF_IDF tfidf = new Write_TF_IDF("sourcefile/StopWords_Content", "sourcefile/TF_IDF(term_tfidf_num_tf_idf)");
	}
*/
}
