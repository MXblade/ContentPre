package bistu.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShowTheTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sourcefile/Cluster_result_0.2.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("sourcefile/Cluster_result_0.3.txt"));

		ArrayList<Integer> num1 = new ArrayList<Integer>();
		ArrayList<Integer> num2 = new ArrayList<Integer>();
		
		String temp1 = null;
		String temp2 = null;
		int max_num = 0;
		int max_num2 = 0;
		while((temp1 = br.readLine()) != null){
			String[] templist = temp1.split("   ");
			num1.add(templist.length);
			if(templist.length > max_num){
				max_num = templist.length;
			}
			
		}
		
		while((temp2 = br2.readLine()) != null){
			String[] templist2 = temp2.split("   ");
			num2.add(templist2.length);

			if(templist2.length > max_num2){
				max_num2 = templist2.length;
			}
			
		}

		System.out.println("weight 0.2 = " + max_num);
		System.out.println("weight 0.3 = " + max_num);
		System.out.println("-----------");

		Collections.sort(num1);
		Collections.sort(num2);
		for(int i = num1.size(); i > num1.size()-10; i--){
			System.out.println("weight 0.2 = " + num1.get(i-1));
		}
		System.out.println("------------");
		for(int i = num2.size(); i > num2.size()-10; i--){
			System.out.println("weight 0.3 = " + num2.get(i-1));
		}
	}

}
