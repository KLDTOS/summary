package com.tool.xmltool;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * 实体输出为xml的工具.
 * @author Administrator
 *
 */
public class ElementToXml {

	/**
	 * 
	 * @param outp 输出流
	 * @param elements 需要转换的实体
	 * @param rootName 根节点名字
	 */
	public final void toXml(final OutputStream outp,
			final List<ElementBean> elements, final String rootName) {

		PrintWriter pw = new PrintWriter(outp);
		toXml(pw, elements, rootName);
		
		
	}

	/**
	 * 输出子实体的方法.
	 * @param pw 输出
	 * @param childElement 子实体 
	 */
	private void childElementToXml(final PrintWriter pw,
			final ElementBean childElement) {
		
		//此处需要写入eb的属性
		pw.println("<" + childElement.getElementBeanName() + " "
				+ childElement.getAttributesValue() + ">");
		//循环输出所有子节点
		for (int j = childElement.getChildsNum(); j > 0; j--) {
			pw.print("<" + childElement.getName(j - 1) + ">");
				pw.print(childElement.getValue(j - 1));
			pw.println("</" + childElement.getName(j - 1) + ">");
		}
		//循环输出所有子实体 ，此处稍微递归了一下，虽然android递归运算有上限(四位数~。~|||)应该不会出现达到上限的情况
		for (int n = childElement.getElementBeanChildsNum(); n > 0; n--) {
			childElementToXml(pw, childElement.getChildElement(n));
		}	

		pw.println("</" + childElement.getElementBeanName() + " "
				+ ">");
	}	
	
	/**
	 * 
	 * @param pw PrintWriter
	 * @param elements 实体
	 * @param rootName 根节点名字
	 */
	public final void toXml(final PrintWriter pw,
			final List<ElementBean> elements, final String rootName) {
		
		// 拼XML格式数据
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");	
		// 根节点
		if (rootName != null && !"".equals(rootName)) {
			pw.println("<" + rootName + ">");
		} else {
			pw.println("<root >");
		}
		//实体列表
		int eleSize = elements.size();
		for (int i = 0; i < eleSize; i++) {
			ElementBean eb = elements.get(i);
			childElementToXml(pw, eb);
		}
		
		// 根节点
		if (rootName != null && !"".equals(rootName)) {
			pw.println("</" + rootName + ">");
		} else {
			pw.println("</root >");
		}
		pw.flush();
		pw.close();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
