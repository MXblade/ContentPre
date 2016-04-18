package bistu.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dependency.nnparser.parser_dll;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import bistu.functions.ClearTxt_Path;

/**
 * 对专利数据进行分词
 * TxtPath = 输入文档路径
 * OutPath = 输出文档路径;
 * @author Joen
 *
 */
public class PtParser {
	private static String TxtPath = null;
	private static String OutPath = null;
	
	public void Parser() throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(OutPath);
		parser_write(TxtPath, OutPath);
	}
	
	/**
	 * 对inpath的数据进行分词，并将分词结果写入outpath
	 * @param inpath
	 * @param outpath
	 * @throws IOException
	 */
	public static void parser_write(String inpath, String outpath) throws IOException{
		//
		InputStreamReader readin = new InputStreamReader(new FileInputStream(inpath), "GBK");
        BufferedReader in=new BufferedReader(readin);
		BufferedWriter out = new BufferedWriter(new FileWriter(outpath));
        String temp = null;
		
		while ((temp = in.readLine()) != null) {
			temp = temp.replaceAll(" ", "");
			String[] singlestence = temp.split("，|。|,|；");
        	for(int j = 0;j<singlestence.length;j++)
        	{
        		//对每一句进行分词
        		List<Term> segparser = HanLP.segment(singlestence[j]);
        		for(int k = 0; k<segparser.size(); k++){
        			//分词后的自带词性，由于不需要词性，则将词性删除
        			String st = segparser.get(k).toString();
        			String[] stsplit = st.split("/");
        			if(stsplit[0] != "、"){
            			out.write(stsplit[0]);
            			out.newLine();
         			}
        			//System.out.println(stsplit[0]);
        		}
        	}
		}
		in.close();
		out.close();
	}
	
	
	public String getTxtPath() {
		return TxtPath;
	}

	public void setTxtPath(String txtPath) {
		TxtPath = txtPath;
	}

	public String getOutPath() {
		return OutPath;
	}

	public void setOutPath(String outPath) {
		OutPath = outPath;
	}

	
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//PtParser pp = new Parser("CN1036174.txt");
		PtParser pp = new PtParser();
		pp.setTxtPath("sourcefile/After_Parser/CN104137670.txt");
		pp.setOutPath("sourcefile/abctest.txt");
		pp.Parser();
	}
*/
}
