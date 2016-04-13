package bistu.tfidf;

import java.io.IOException;

public class TFIDF_Main {

	public static void main(String[] args) throws IOException{

		/*
		 * 给定需计算tfidf的文件所在文件夹路径和输出的文件夹路径。
		 * 输出文件内的格式为：词，tfidf，当前专利的词数，tf(词在当前专利的次数)，idf(出现该词的专利数)
		 */
		Write_TF_IDF tfidf = new Write_TF_IDF();
		tfidf.write("sourcefile/StopWords_Content", "sourcefile/TF_IDF(term_tfidf_num_tf_idf)");
		
		/*
		 * 将之前输出的包含tfidf所有信息的文件进行分割，分别存储tf，idf，tfidf
		 * 输出数据格式为：词，tf/idf/tfidf.
		 */
		Split_TF_IDF split = new Split_TF_IDF();
		split.write("sourcefile/TF_IDF(term_tfidf_num_tf_idf)", "sourcefile/term_tf", "sourcefile/term_idf", "sourcefile/term_tfidf");
		
		/*
		 * 过滤tfidf值过低的词，排序并写入文件
		 */
		TFIDF_filter tff = new TFIDF_filter();
		tff.filter("sourcefile/term_tfidf", 2.0,"sourcefile/filter_tfidf");

	}

}
