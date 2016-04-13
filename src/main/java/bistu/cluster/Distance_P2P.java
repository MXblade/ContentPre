package bistu.cluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 计算两个数组的距离，公式：(a交b)/(a并b)，返回Double结果
 * @author Joen
 *
 */
public class Distance_P2P {

	public Double Distance(Integer[] a, Integer[] b){
		List<Integer> list = new ArrayList(Arrays.asList(a));
		list.retainAll(Arrays.asList(b));
		Double intersection = (double) list.size();
		Double union = (double) a.length + b.length -list.size();
		Double distance = intersection/union;
		return distance;
	}
}
