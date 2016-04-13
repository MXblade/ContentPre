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

public class Cluster {

	Double eps = 0.5;
	ArrayList<ArrayList<String>> clust = new ArrayList<ArrayList<String>>();

	public ArrayList<String> getNeighbors(String termid, Set<String> hdterm, HashMap<String,Integer[]> hm){
		ArrayList<String> neighbor = new ArrayList<String>();
		Distance_P2P dis = new Distance_P2P();
		Set<String> words = hm.keySet();
		Iterator<String> iter2 = words.iterator();
		while(iter2.hasNext()){
			String term2 = iter2.next();
			if(hdterm.contains(term2)){
				continue;
			}
			else{
				Double distance = dis.Distance(hm.get(termid), hm.get(term2));
				if(distance > eps){
					neighbor.add(term2);
					hdterm.add(term2);
				}
			}
		}
		ArrayList<ArrayList<String>> sns = new ArrayList<ArrayList<String>>();

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
	
	public void dbscan(HashMap<String, Integer[]> hm)throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt("sourcefile/Cluster_result.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("sourcefile/Cluster_result.txt"));

		Set<String> terms = hm.keySet();
		Set<String> hdterm = new HashSet<String>();
		Iterator<String> iter = terms.iterator();
		int num = 0;
		int cl_num = 0;
		while(iter.hasNext() && (num < 300)){
			System.out.println(num++);
			String term = iter.next();
			if(hdterm.contains(term)){
				continue;
			}
			else{
				cl_num++;
				ArrayList<String> temp = getNeighbors(term,hdterm, hm);
				if(temp.size() > 1){
					clust.add(temp);
					bw.write(cl_num + "class");
					for(String s: temp){
						bw.write("   " +  s);
					}
					
				}
			}
		}
		bw.close();

	}
	
	public boolean containterm(String term){
		for(int i = 0; i < clust.size(); i++){
			ArrayList<String> clu = clust.get(i);
			if(clu.contains(term)){
				return true;
			}
		}
		return false;
	}
	
	
	public void writer(String path) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		for(int i = 0; i < clust.size(); i++){
			ArrayList<String> al = clust.get(i);
			bw.write(i + "class");
			for(int j = 0; j < al.size(); j++){
				bw.write( "   " + al.get(j));
			}
			bw.newLine();
		}
		bw.close();
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Read_Vectors rv = new Read_Vectors();
		rv.read("sourcefile/cluster/Patents_Vectors.txt");
		Cluster cluster = new Cluster();
		cluster.dbscan(rv.hm);
		cluster.writer("sourcefile/cluster/result/Cluster_Result.txt");
		System.out.println(new Date());
	}

}
