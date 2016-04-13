package bistu.extract_content_inf;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ExtraPart
 * @Description 对专利数据进行预处理，提取专利的ID,Title,Abstraction,Claim
 * @ID 专利的唯一id
 * @Title 专利名称
 * @Abstraction 专利摘要，包含专利基本信息
 * @Claim 专利权利要求书
 * @author Joen
 *
 */

public class ExtraPart {
	
	String ContentID = null;
	String ContentTitle = null;
	String ContentAbs = null;
	String ContentClaim = null;

	/**
	 * 获取专利信息(id,title,abs,claim)
	 * @param path
	 */
	public void exinf(String path){
		ContentID = null;
		ContentTitle = null;
		ContentAbs = null;
		ContentClaim = null;

		
		Element element = null;
		// 可以使用绝对路径
		File f = new File(path);

		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();
			
			// 得到一个DOM并返回给document对象
			Document dt = db.parse(f);
			// 得到一个elment根元素
			element = dt.getDocumentElement();
			
			// 获得根元素下的子节点
			NodeList childNodes = element.getChildNodes();
			
			// 遍历这些子节点
			for (int i = 0; i < childNodes.getLength(); i++) {
				// 获得每个对应位置i的结点
				Node node1 = childNodes.item(i);
    
				
				if ("cn-bibliographic-data".equals(node1.getNodeName())) {
					//获取<cn-bibliographic-data>下的节点
					NodeList cnnodeDt = node1.getChildNodes();
					for (int j = 0; j < cnnodeDt.getLength(); j++){
				    	 Node cndetail = cnnodeDt.item(j);
				    	 //获取ID
				    	 if(cndetail.getNodeName().equals("cn-publication-reference"))
				    	 {
				    		 NodeList docId = cndetail.getChildNodes();
				    		 String id = getid(docId);
				    		 ContentID = id;
				    	 }
				    	 
				    	 //获取专利名称
				    	 if("invention-title".equals(cndetail.getNodeName())){
				    		 String title = cndetail.getTextContent();
				    		 ContentTitle = title;
				    	 }
				    	 
				    	 //获取摘要
				    	 if("abstract".equals(cndetail.getNodeName())){
				    		 String abs = cndetail.getTextContent();
				    		 ContentAbs = abs;
				    		 ContentAbs = ContentAbs.replace("\t", "");
				    	 }
				     }
				}
				else if("application-body".equals(node1.getNodeName())){
					NodeList applt = node1.getChildNodes();
					ContentClaim = getclaims(applt);
					//获取的claim含有无用标签，需去除
					ContentClaim = ContentClaim.replace("\n", "");
					ContentClaim = ContentClaim.replace("\t", "");
					//System.out.println(ContentClaim);
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//获取cn-publication-reference下的ID
	public static String getid(NodeList doc){
		String conID = "";
		 for(int l = 0; l < doc.getLength(); l++){
			 Node Idetail = doc.item(l);
			 if("document-id".equals(Idetail.getNodeName())){
				 NodeList id = Idetail.getChildNodes();
				 for(int m = 0; m < id.getLength(); m++){
					 Node idt = id.item(m);
					 if("country".equals(idt.getNodeName()) || "doc-number".equals(idt.getNodeName())){
						 conID = conID +idt.getTextContent();
					 }
				 }
			 }
		 }
		 return conID;
	 }

	//获取application-body下面的claims的信息
	public static String getclaims(NodeList application){
		String claim = "";
		for(int i = 0; i < application.getLength(); i++){
			Node aplnode = application.item(i);
			//获取<claims>下的节点
			if("claims".equals(aplnode.getNodeName())){
				NodeList claimdetail = aplnode.getChildNodes();
				//遍历<claims>子节点
				for(int j = 0; j < claimdetail.getLength(); j++){
					Node clapart = claimdetail.item(j);
					//获取<claim>下的节点
					if("claim".equals(clapart.getNodeName())){
						NodeList claimList = clapart.getChildNodes();
						//遍历<claim>的子节点
						for(int l = 0; l < claimList.getLength(); l++){
							Node cldetail = claimList.item(l);
							if("claim-text".equals(cldetail.getNodeName())){
								claim = claim + cldetail.getTextContent();
								
							}
						}
					}
				}
			}
		}
		return claim;
	}
	
/*	public static void main(String[] args) {
		ExtraPart extra = new ExtraPart();
		extra.exinf("C:/JavaWorkspace/ContentPre/sourcefile/testclear/201080054626NEW.xml");
	}
*/}
