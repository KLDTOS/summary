package com.tool.xmltool;

import java.util.LinkedList;
import java.util.List;

/**
 * 实体中用于封装子实体工厂及实体的工厂.
 * @author Administrator
 *
 */
public abstract class ElementBeanChildElementBeanFactory extends
		ElementBeanFactory {

	/**
	 * 实体的子实体列表.
	 * @return 实体列表
	 */
	 List<ElementBean> makeChildEBs() {

		List<ElementBean> eBs = new LinkedList<ElementBean>();

		return eBs;
	}

	/**
	 * 必须实现此方法，用以返回创建子实体的工厂.
	 * @param i 实体中子实体名字的位置
	 * @return 创建子实体的工厂
	 */
	abstract ElementBeanFactory getChildBeanFactory(int i);
	
	
	
}
