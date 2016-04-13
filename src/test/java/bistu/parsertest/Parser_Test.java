package bistu.parsertest;

import java.io.IOException;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;

import bistu.parser.PtParser;

public class Parser_Test {

	public static void main (String[] args)throws IOException{
//		/PtParser parser = new PtParser();
		String ss = "本发明公开了一种用于涡轮发动机上喷射装置的隔板的控制机构，其中使用了一个流线形环形件使得每个空气一燃料喷射装置的隔板同步运动。环形件与每个隔板连接，一个单独的控制件控制环形件的转动。这样将绕过控制系统的空气流的扰动降至最小。";
		Segment sg = new CRFSegment();
		List list = HanLP.segment(ss);
		System.out.println(list);
		System.out.println(HanLP.parseDependency("把市场经济奉行的等价交换原则引入党的生活和国家机关政务活动中"));
		//parser.parser_write("sourcefile/CN1034039.txt", "sourcefile/test.txt");
	}
}
