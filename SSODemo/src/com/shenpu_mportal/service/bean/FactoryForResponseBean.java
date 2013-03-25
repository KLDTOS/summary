package com.shenpu_mportal.service.bean;

import com.tool.xmltool.ElementBean;
import com.tool.xmltool.ElementBeanChildElementBeanFactory;
import com.tool.xmltool.ElementBeanFactory;

public class FactoryForResponseBean extends ElementBeanFactory{

	@Override
	public ElementBean initSpecialElementBean(ElementBean eB) {
		eB.setName(1, "isUser");
		eB.setElementBeanName("ResponseBean");
		return eB;
	}

	@Override
	public ElementBeanChildElementBeanFactory getChildFactory() {
		
		return null;
	}

	@Override
	public int getEBchildNum() {
		
		return 1;
	}

	@Override
	public boolean getEBchildEBHave() {
		
		return false;
	}

	@Override
	public ElementBean initElementInfo(ElementBean eb) {
		eb.setValue(1, "0");
		return eb;
	}

	@Override
	public String getElementBeanName() {
		
		return "ResponseBean";
	}

}
