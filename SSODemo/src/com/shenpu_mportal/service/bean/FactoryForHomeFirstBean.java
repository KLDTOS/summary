package com.shenpu_mportal.service.bean;

import com.tool.xmltool.ElementBean;
import com.tool.xmltool.ElementBeanChildElementBeanFactory;
import com.tool.xmltool.ElementBeanFactory;

/**
 * Home infoEB Factory.
 * @author Administrator
 *
 */
public class FactoryForHomeFirstBean extends ElementBeanFactory {

	/**子节点数量.*/
	private static final int CHILD_NUMBER = 3;
	
	@Override
	public final ElementBeanChildElementBeanFactory getChildFactory() {
		
		return null;
	}

	@Override
	public final boolean getEBchildEBHave() {
		
		return false;
	}

	@Override
	public final int getEBchildNum() {
		
		return CHILD_NUMBER;
	}

	@Override
	public final ElementBean initSpecialElementBean(final ElementBean arg0) {
		
		arg0.setNames(new String[] { "hometest1", "hometest2", "hometest3" });
		
		
		return arg0;
	}

	@Override
	public final ElementBean initElementInfo(final ElementBean eb) {
		eb.setValue("hometest1", "hometest1value");
		eb.setValue("hometest2", "hometest2value");
		eb.setValue("hometest3", "hometest3value");
		return eb;
	}

	@Override
	public String getElementBeanName() {
	
		return "Test";
	}

}
