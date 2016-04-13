package bistu.cluster;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import bistu.functions.ClearTxt_Path;

/**
 * 对专利进行聚类操作，
 * @author Joen
 *
 */
public class Cluster {
	//两个专利间的相似度
	Double eps = 0.5;
	//存储聚类后的每一簇的点。
	ArrayList<ArrayList<String>> clust = new ArrayList<ArrayList<String>>();

	/**
	 * 迭代计算给定某个点termid，其余的点离该点相似度高于eps，存入hdterm，
	 * @param termid 给定的某个点
	 * @param hdterm 存储聚类成功的点。
	 * @param hm 所有专利的向量组
	 * @return
	 */
	public ArrayList<String> getNeighbors(String termid, Set<String> hdterm, HashMap<String,Integer[]> hm){
		//创建neighbor用与存储跟termid相似度大于eps的周围点。
		ArrayList<String> neighbor = new ArrayList<String>();
		//计算两者相似度
		Distance_P2P dis = new Distance_P2P();
		Set<String> words = hm.keySet();
		Iterator<String> iter2 = words.iterator();
		//遍历所有专利
		while(iter2.hasNext()){
			String term2 = iter2.next();
			if(hdterm.contains(term2)){
				continue;
			}
			else{
				//判断相似度，若大于eps，存入neighbor和hdterm
				Double distance = dis.Distance(hm.get(termid), hm.get(term2));
				if(distance > eps){
					neighbor.add(term2);
					hdterm.add(term2);
				}
			}
		}
		ArrayList<ArrayList<String>> sns = new ArrayList<ArrayList<String>>();

		//遍历neighbor，迭代计算getNeighbors
		for(String s: neighbor){
			sns.add(getNeighbors(s, hdterm, hm));
		}
		for(ArrayList<String> al:sns){
			neighbor.addAll(al);
		}
		
/*		for(String s: neighbor){
			undterm.remove(s);
		}
*/		return neighbor;
	}
	
	/**
	 * 对专利进行聚类操作，并将结果写入文件outpath
	 * @param hm 所有专利的向量组
	 * @param outpath 结果存放位置。
	 * @throws IOException
	 */
	public void dbscan(HashMap<String, Integer[]> hm, String outpath)throws IOException{
		//清空并创建outpath
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));

		//获取所有的专利点
		Set<String> terms = hm.keySet();
		//定义set用于存储已经聚类成功的点
		Set<String> hdterm = new HashSet<String>();
		Iterator<String> iter = terms.iterator();
		//用于表示正在以第num点进行密度聚类操作
		int num = 0;
		//用于表示第几类
		int cl_num = 0;
		//遍历专利所有点，依次对该点周围所有点进行迭代判断是否为一类。
		while(iter.hasNext()){
			System.out.println(num++);
			String term = iter.next();
			
			if(hdterm.contains(term)){
				continue;
			}
			else{
				cl_num++;
				//调用getNeighbors迭代获取跟term相似度大的点并存入temp
				ArrayList<String> temp = getNeighbors(term,hdterm, hm);
				//将得到的类簇中数量大于2的簇写入文件
				if(temp.size() > 1){
					clust.add(temp);
					bw.write(cl_num + "class");
					for(String s: temp){
						bw.write("   " +  s);
					}
					
				}
			}
			bw.newLine();
		}
		bw.close();

	}
	
	/*
	 * 判断已经聚类成功的所有簇里是否包含给定的点term
	 */
	public boolean containterm(String term){
		for(int i = 0; i < clust.size(); i++){
			ArrayList<String> clu = clust.get(i);
			if(clu.contains(term)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Read_Vectors rv = new Read_Vectors();
		rv.read("sourcefile/cluster/Patents_Vectors.txt");
		Cluster cluster = new Cluster();
		cluster.dbscan(rv.hm, "sourcefile/cluster/result/Cluster_Result.txt");
		System.out.println(new Date());
	}

}
