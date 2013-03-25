package com.tool.xmltool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML通用解析工具.
 * @author Administrator
 *
 */
public class XMLTool {

	/**需要解析的子节点实体基本类型数量.*/
//	private int elementChildNum;
	/**实体列表.*/
	private List<ElementBean> ebs;
	/**实体列表父节点名字.*/
	private String elementListFatherNode;
	/**一个定制的实体工厂.*/
	private ArrayList<ElementBeanFactory> particularElementBeanFactory;
	/**解析过程中用的.*/
	private ElementBean eB;
	
	/**
	 * 创建一个特定的xml解析器.
	 * @param eCN elementChildNum需要解析的子节点实体基本类型数量
	 * @param eLFN elementListFatherNode实体列表父节点名字
	 * @param pEBF particularElementBeanFactory 自定义的实体工厂列表
	 */
	public XMLTool(final int eCN, final String eLFN,
			final ArrayList<ElementBeanFactory> pEBF) {
		
		ebs = new LinkedList<ElementBean>();
		//elementChildNum = eCN;
		elementListFatherNode = eLFN;
		particularElementBeanFactory = pEBF;
		
	}
	
	
	/**
	 * 解析执行方法(dom).
	 * @param input xml输入流
	 * @return xml解析成的实体列表
	 */
	public final List<ElementBean> resolution(final InputStream input) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		if (input == null) {
			return null;
		}
			
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document document = null;
			try {
				document = builder.parse(input);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element element = document.getDocumentElement();
			
			//得到xml中的实体列表
			NodeList nodeList = element
					.getElementsByTagName(elementListFatherNode);
			
			int i;
			for (i = 0; i < nodeList.getLength(); i++) {
				Element domElement = (Element) nodeList.item(i);
				
				for (int s = particularElementBeanFactory.size(); s > 0; s--) {

					System.out.println("NodeName==" + domElement.getNodeName());
						
					if (domElement.getNodeName().equals(
							particularElementBeanFactory.get(s - 1)
									.getElementBeanName())) {
						
					
						
						
						
						// 通过特定工厂make特定实体
						eB = particularElementBeanFactory.get(s - 1)
								.makeElementBean();
						
						// 获得xml中实体子节点list
						NodeList domElementChilds = domElement.getChildNodes();
						
						// 遍历xml子节点
						int childsNum = domElementChilds.getLength();
						for (int j = 0; j < childsNum; j++) {
							if (domElementChilds.item(j).getNodeType() 
									== Node.ELEMENT_NODE) {
								
								// 实体子节点的实体 ~~。~~|||||好吧，确实好绕
								Element childElement = (Element) domElementChilds
										.item(j);
								
								// 匹配实体中是否有子实体及是否为子实体
								ElementBeanFactory childBeanFactory = eB
										.checkChildBeanName(childElement
												.getNodeName());

								// 如果为空不做处理
								if (childBeanFactory != null) {
									// make子实体并赋值
									ElementBean childEB = resolutionChildEB(
											childElement,
											childBeanFactory.makeElementBean());
									eB.putChildEB(childEB);
								}
								
								// 给子节点赋值
								eB.setValue(childElement.getNodeName(), childElement
										.getFirstChild().getNodeValue());
								
								// 处理实体属性
								NamedNodeMap nNMap = childElement.getAttributes();
								for (int h = 0; h < nNMap.getLength(); h++) {
									eB.addAttribute(nNMap.item(h).getNodeName(),
											nNMap.item(h).getNodeValue());
								}	
							}
						} //if 判断是否为已知节点.
						
					} //for 遍历工厂列表
		
				} // for 遍历xml子节点
				if (eB != null) {
					ebs.add(eB);
				}
				
			}
				
		

		return ebs;
	}
	
	/**
	 *  方法和父实体完全一样，为了以后修改方便把递归部分独立出来了.
	 * @param childElement dom解析实体
	 * @param elementBean 需要解析成的实体
	 * @return 赋值完成的elementBean
	 */
	private ElementBean resolutionChildEB(final Element childElement,
			final ElementBean elementBean) {
		
		//获得xml中实体子节点list
		NodeList childElementChilds = childElement.getChildNodes();
		for (int u = 0; u < childElementChilds.getLength(); u++) {
			if (childElementChilds.item(u).getNodeType() == Node.ELEMENT_NODE) {
			
				Element cEChild = (Element) childElementChilds.item(u);
				ElementBeanFactory cBeanFactory = elementBean
						.checkChildBeanName(cEChild.getNodeName());
				if (cBeanFactory != null) {
					ElementBean childEB = resolutionChildEB(cEChild,
							cBeanFactory.makeElementBean());
					elementBean.putChildEB(childEB);
				}
				elementBean.setValue(cEChild.getNodeName(), cEChild
						.getFirstChild().getNodeValue());
			}			
		}
		
		NamedNodeMap  nNMap = childElement.getAttributes();						
		for (int h = 0; h < nNMap.getLength(); h++) {
			elementBean.addAttribute(nNMap.item(h).getNodeName(), nNMap.item(h)
					.getNodeValue());
		}
		
		return elementBean;
	}
	
	
}
