package bistu.cluster;

import java.io.IOException;
/**
 * 构建words_map和 专利的向量组，并写入文件，
 * 执行一次。
 * 若存在这两个文件，该操作可不执行。
 * @author Joen
 *
 */

public class Create_WP_PV {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		/*
		 * 将构建的words_map写入文件
		 */
		Create_Words_Map cwm = new Create_Words_Map("sourcefile/filter_tfidf");
		cwm.write_words_map("sourcefile/cluster/Words_Map.txt");

		/*
		 * 将构建的文件向量写入文件
		 */
		Create_PatentsVectors wpv = new Create_PatentsVectors();
		wpv.PatentsVectors("sourcefile/cluster/Words_Map.txt", "sourcefile/filter_tfidf", "sourcefile/cluster/Patents_Vectors.txt");

		/*
		 * 
		 */
	}

}
