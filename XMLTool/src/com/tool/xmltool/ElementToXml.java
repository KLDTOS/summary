package com.tool.xmltool;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * ʵ�����Ϊxml�Ĺ���.
 * @author Administrator
 *
 */
public class ElementToXml {

	/**
	 * 
	 * @param outp �����
	 * @param elements ��Ҫת����ʵ��
	 * @param rootName ���ڵ�����
	 */
	public final void toXml(final OutputStream outp,
			final List<ElementBean> elements, final String rootName) {

		PrintWriter pw = new PrintWriter(outp);
		toXml(pw, elements, rootName);
		
		
	}

	/**
	 * �����ʵ��ķ���.
	 * @param pw ���
	 * @param childElement ��ʵ�� 
	 */
	private void childElementToXml(final PrintWriter pw,
			final ElementBean childElement) {
		
		//�˴���Ҫд��eb������
		pw.println("<" + childElement.getElementBeanName() + " "
				+ childElement.getAttributesValue() + ">");
		//ѭ����������ӽڵ�
		for (int j = childElement.getChildsNum(); j > 0; j--) {
			pw.print("<" + childElement.getName(j - 1) + ">");
				pw.print(childElement.getValue(j - 1));
			pw.println("</" + childElement.getName(j - 1) + ">");
		}
		//ѭ�����������ʵ�� ���˴���΢�ݹ���һ�£���Ȼandroid�ݹ�����������(��λ��~��~|||)Ӧ�ò�����ִﵽ���޵����
		for (int n = childElement.getElementBeanChildsNum(); n > 0; n--) {
			childElementToXml(pw, childElement.getChildElement(n));
		}	

		pw.println("</" + childElement.getElementBeanName() + " "
				+ ">");
	}	
	
	/**
	 * 
	 * @param pw PrintWriter
	 * @param elements ʵ��
	 * @param rootName ���ڵ�����
	 */
	public final void toXml(final PrintWriter pw,
			final List<ElementBean> elements, final String rootName) {
		
		// ƴXML��ʽ����
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");	
		// ���ڵ�
		if (rootName != null && !"".equals(rootName)) {
			pw.println("<" + rootName + ">");
		} else {
			pw.println("<root >");
		}
		//ʵ���б�
		int eleSize = elements.size();
		for (int i = 0; i < eleSize; i++) {
			ElementBean eb = elements.get(i);
			childElementToXml(pw, eb);
		}
		
		// ���ڵ�
		if (rootName != null && !"".equals(rootName)) {
			pw.println("</" + rootName + ">");
		} else {
			pw.println("</root >");
		}
		pw.flush();
		pw.close();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
