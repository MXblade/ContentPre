package bistu.parser;

import java.io.File;
import java.io.IOException;

import bistu.functions.ClearTxt_Path;

/**
 * 获取inpath路径下所有文件路径，对outpath进行初始化，
 * in_path_list[] 存储inpath路径下所有文件的路径
 * out_path_list[] 存储应在outpath下创建的对应inpath的所有路径。
 * @author Joen
 *
 */
public class GetFilePath {
	String[] in_path_list = null;
	String[] out_path_list =null;

	public GetFilePath(String inpath, String outpath) throws IOException{
		//清空outpath中的所有文件，若无outpath，则创建新文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		//遍历inpath下所有文件，作为文件输入路径，并创建对应的文件输出路径
		File inlist_path = new File(inpath);
		File[] inlist = inlist_path.listFiles();
		in_path_list = new String[inlist.length];
		out_path_list = new String[inlist.length];
		
		String[] outname = new String[inlist.length];
		for(int i = 0; i < inlist.length; i++){
			outname[i] = inlist[i].getName();
			in_path_list[i] = inlist[i].getAbsolutePath();
			out_path_list[i] = outpath +"/" + outname[i]; 
		}
		
		
	}
	
	
	
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GetFilePath gfp = new GetFilePath("sourcefile/test", "sourcefile/result");
	}
*/
}
