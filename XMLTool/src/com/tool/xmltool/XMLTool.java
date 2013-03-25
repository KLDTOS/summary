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
 * XMLͨ�ý�������.
 * @author Administrator
 *
 */
public class XMLTool {

	/**��Ҫ�������ӽڵ�ʵ�������������.*/
//	private int elementChildNum;
	/**ʵ���б�.*/
	private List<ElementBean> ebs;
	/**ʵ���б��ڵ�����.*/
	private String elementListFatherNode;
	/**һ�����Ƶ�ʵ�幤��.*/
	private ArrayList<ElementBeanFactory> particularElementBeanFactory;
	/**�����������õ�.*/
	private ElementBean eB;
	
	/**
	 * ����һ���ض���xml������.
	 * @param eCN elementChildNum��Ҫ�������ӽڵ�ʵ�������������
	 * @param eLFN elementListFatherNodeʵ���б��ڵ�����
	 * @param pEBF particularElementBeanFactory �Զ����ʵ�幤���б�
	 */
	public XMLTool(final int eCN, final String eLFN,
			final ArrayList<ElementBeanFactory> pEBF) {
		
		ebs = new LinkedList<ElementBean>();
		//elementChildNum = eCN;
		elementListFatherNode = eLFN;
		particularElementBeanFactory = pEBF;
		
	}
	
	
	/**
	 * ����ִ�з���(dom).
	 * @param input xml������
	 * @return xml�����ɵ�ʵ���б�
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
			
			//�õ�xml�е�ʵ���б�
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
						
					
						
						
						
						// ͨ���ض�����make�ض�ʵ��
						eB = particularElementBeanFactory.get(s - 1)
								.makeElementBean();
						
						// ���xml��ʵ���ӽڵ�list
						NodeList domElementChilds = domElement.getChildNodes();
						
						// ����xml�ӽڵ�
						int childsNum = domElementChilds.getLength();
						for (int j = 0; j < childsNum; j++) {
							if (domElementChilds.item(j).getNodeType() 
									== Node.ELEMENT_NODE) {
								
								// ʵ���ӽڵ��ʵ�� ~~��~~|||||�ðɣ�ȷʵ����
								Element childElement = (Element) domElementChilds
										.item(j);
								
								// ƥ��ʵ�����Ƿ�����ʵ�弰�Ƿ�Ϊ��ʵ��
								ElementBeanFactory childBeanFactory = eB
										.checkChildBeanName(childElement
												.getNodeName());

								// ���Ϊ�ղ�������
								if (childBeanFactory != null) {
									// make��ʵ�岢��ֵ
									ElementBean childEB = resolutionChildEB(
											childElement,
											childBeanFactory.makeElementBean());
									eB.putChildEB(childEB);
								}
								
								// ���ӽڵ㸳ֵ
								eB.setValue(childElement.getNodeName(), childElement
										.getFirstChild().getNodeValue());
								
								// ����ʵ������
								NamedNodeMap nNMap = childElement.getAttributes();
								for (int h = 0; h < nNMap.getLength(); h++) {
									eB.addAttribute(nNMap.item(h).getNodeName(),
											nNMap.item(h).getNodeValue());
								}	
							}
						} //if �ж��Ƿ�Ϊ��֪�ڵ�.
						
					} //for ���������б�
		
				} // for ����xml�ӽڵ�
				if (eB != null) {
					ebs.add(eB);
				}
				
			}
				
		

		return ebs;
	}
	
	/**
	 *  �����͸�ʵ����ȫһ����Ϊ���Ժ��޸ķ���ѵݹ鲿�ֶ���������.
	 * @param childElement dom����ʵ��
	 * @param elementBean ��Ҫ�����ɵ�ʵ��
	 * @return ��ֵ��ɵ�elementBean
	 */
	private ElementBean resolutionChildEB(final Element childElement,
			final ElementBean elementBean) {
		
		//���xml��ʵ���ӽڵ�list
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
