package com.tool.xmltool;

/**
 * 在此类中创建ElementBean实例.
 * 字符串数组中所有位置必须全部初始化为“”
 * @author Administrator
 *
 */
public abstract class ElementBeanFactory {

	/**
	 * 创造一个特定实体.
	 * @return 特定实体
	 */
	public final ElementBean makeElementBean() {
		ElementBean eB = new ElementBean(getEBchildNum(), getEBchildEBHave(),
				this);
		eB.setElementBeanName(getElementBeanName());
		return initSpecialElementBean(eB);
	}
	
	/**
	 * 实现此方法 初始化eB中的节点名称.
	 * @param eB 需要初始的ElementBean
	 * @return 初始化完成的ElementBean
	 */
	public abstract  ElementBean initSpecialElementBean(ElementBean eB);
		
	/**
	 * 实现此方法 返回一个用于制作实体子节点实体的工厂.
	 * @return 子节点实体工厂
	 */
	public abstract ElementBeanChildElementBeanFactory getChildFactory();
	
	
	
	/**
	 * 实现此方法，返回实体中一级数据节点个数.
	 * @return 一级数据节点个数
	 */
	public abstract int getEBchildNum();
	
	/**
	 * 实现此方法，返回实体中有没有实体.
	 * @return true or false
	 */
	public abstract boolean getEBchildEBHave();

	/**
	 * 
	 * @param eb 需初始化数据的实例
	 * @return 初始化完成的实例
	 */
	public abstract ElementBean initElementInfo(ElementBean eb);
	
	/**
	 * 
	 * @return 返回beanName 
	 */
	public abstract String getElementBeanName();
	
	
}
