package com.tool.xmltool;

import java.util.LinkedList;
import java.util.List;

/**
 * ʵ�������ڷ�װ��ʵ�幤����ʵ��Ĺ���.
 * @author Administrator
 *
 */
public abstract class ElementBeanChildElementBeanFactory extends
		ElementBeanFactory {

	/**
	 * ʵ�����ʵ���б�.
	 * @return ʵ���б�
	 */
	 List<ElementBean> makeChildEBs() {

		List<ElementBean> eBs = new LinkedList<ElementBean>();

		return eBs;
	}

	/**
	 * ����ʵ�ִ˷��������Է��ش�����ʵ��Ĺ���.
	 * @param i ʵ������ʵ�����ֵ�λ��
	 * @return ������ʵ��Ĺ���
	 */
	abstract ElementBeanFactory getChildBeanFactory(int i);
	
	
	
}
