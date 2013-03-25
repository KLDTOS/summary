package com.tool.xmltool;

/**
 * �ڴ����д���ElementBeanʵ��.
 * �ַ�������������λ�ñ���ȫ����ʼ��Ϊ����
 * @author Administrator
 *
 */
public abstract class ElementBeanFactory {

	/**
	 * ����һ���ض�ʵ��.
	 * @return �ض�ʵ��
	 */
	public final ElementBean makeElementBean() {
		ElementBean eB = new ElementBean(getEBchildNum(), getEBchildEBHave(),
				this);
		eB.setElementBeanName(getElementBeanName());
		return initSpecialElementBean(eB);
	}
	
	/**
	 * ʵ�ִ˷��� ��ʼ��eB�еĽڵ�����.
	 * @param eB ��Ҫ��ʼ��ElementBean
	 * @return ��ʼ����ɵ�ElementBean
	 */
	public abstract  ElementBean initSpecialElementBean(ElementBean eB);
		
	/**
	 * ʵ�ִ˷��� ����һ����������ʵ���ӽڵ�ʵ��Ĺ���.
	 * @return �ӽڵ�ʵ�幤��
	 */
	public abstract ElementBeanChildElementBeanFactory getChildFactory();
	
	
	
	/**
	 * ʵ�ִ˷���������ʵ����һ�����ݽڵ����.
	 * @return һ�����ݽڵ����
	 */
	public abstract int getEBchildNum();
	
	/**
	 * ʵ�ִ˷���������ʵ������û��ʵ��.
	 * @return true or false
	 */
	public abstract boolean getEBchildEBHave();

	/**
	 * 
	 * @param eb ���ʼ�����ݵ�ʵ��
	 * @return ��ʼ����ɵ�ʵ��
	 */
	public abstract ElementBean initElementInfo(ElementBean eb);
	
	/**
	 * 
	 * @return ����beanName 
	 */
	public abstract String getElementBeanName();
	
	
}
