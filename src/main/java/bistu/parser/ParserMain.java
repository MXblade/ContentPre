package bistu.parser;

import java.io.IOException;


public class ParserMain {

	public ParserMain(String inpath, String outpath) throws IOException{
		GetFilePath gfp = new GetFilePath(inpath, outpath);
		PtParser ps = new PtParser();
		for(int i = 0; i < gfp.in_path_list.length; i++){
			ps.setTxtPath(gfp.in_path_list[i]);
			ps.setOutPath(gfp.out_path_list[i]);
			ps.Parser();
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ParserMain pm = new ParserMain("sourcefile/All_Information_Content", "sourcefile/After_Parser");
	}

}
