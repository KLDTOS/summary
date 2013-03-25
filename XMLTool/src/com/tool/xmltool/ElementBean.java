package com.tool.xmltool;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装数据的实体类.
 * @author Administrator
 *
 */
public class ElementBean {

	/**实体name.*/
	private String elementBeanName = "element";
	/**子节点数量.*/
	private int childsNum;
	/**子实体数量.*/
	private int elementBeanChildsNum = 0;
	/**是否有子实体.*/
	private boolean eBChildHave;
	/**解析出来的数据.*/
	private String[] childValues;
	/**实体类中各个数据对应的名字.*/
	private String[] childNames;
	/**嵌套实体名字.*/
	private List<String> elementBeanNames;
	/**嵌套实体列表.*/
	private List<ElementBean> elementBeanChildValues;
	/**此实体建造工厂.*/
	private ElementBeanFactory eBFactory;
	/**属性名list.*/
	private ArrayList<String> attributesName = null;
	/**属性值list.*/
	private ArrayList<String> attributesValue = null;
	/**判断此实体有无信息.*/
	private boolean haveInfo = false;


	/**
	 * 创建数据实体类. 
	 * 使用protected是考虑到隐藏此类的new方法。必须继承工厂
	 * @param childNum 子节点数量或实体基本数据数量
	 * @param eBCHave 是否有子实体
	 * @param eBF 子实体工程
	 */
	ElementBean(final int childNum, final Boolean eBCHave,
			final ElementBeanFactory eBF) {
		
		eBFactory = eBF;
		childsNum = childNum;
		eBChildHave = eBCHave;
		childValues = new String[childsNum];
		childNames = new String[childsNum];
		elementBeanNames = new ArrayList<String>();
		if (eBChildHave) {
			//如果有子实体，make实体list
			elementBeanChildValues = eBFactory.getChildFactory().makeChildEBs();
		}
		
		
	}
	
	/**设置某个子节点名字.
	 *  
	 * @param childNameNum 子节点位置 
	 * @param name 子节点名字
	 * @return 操作是否成功的返回值
	 */
	public final boolean setName(final int childNameNum, final String name) {
		//操作是否成功的返回值
		boolean succeed = true;
		//如果传入位置大于数量，返回操作失败
		if (!checkNum(childNameNum)) {
			succeed = false;
			return succeed;
		}
		//数值在范围内，执行操作并设置返回值为true
		childNames[childNameNum - 1] = name;
		succeed = true;
		
		return succeed;
	}
	
	/**设置所有的子节点名字.
	 * @param names 子节点名字列表
	 * @return 操作结果
	 */
	public final boolean setNames(final String[] names) {
		//操作返回值
		boolean succeed = true;
		//长度不同，返回操作失败
		if (names.length != childsNum) {
			succeed = false;
			return succeed;
		}
		//长度相同，操作并设返回值为true
		childNames = names;
		succeed = true;
		
		return succeed;
	}
	
	/**
	 * 
	 * @param childNum 子节点数字
	 * @return 子节点名称
	 */
	public final String getName(final int childNum) {
		
		String name = "";
		
		if (!checkNum(childNum)) {
			name = "";
			return name;
		}
		name = childNames[childNum];
		return name;
	}
	
	
	/**得到对应子节点值.
	 * @param childNum 子节点位置
	 * @return 子节点值
	 */
	public final  String getValue(final int childNum) {
		
		String value = "";
		//如果位置不在范围内返回空
		if (!checkNum(childNum)) {
			value = "";
			return value;
		}
		
		value = childValues[childNum ];
		
		return value;
	}
	
	/** 通过此方法得到子节点值.
	 * @param childName 子节点名字
	 * @return 对应子节点的值，如果没有返回空字符串
	 */
	public final String getValue(final String childName) {
		  
		String value = "";
		//在childNames里找到下标，在childValues里取值
		for (int i = 0; i < childsNum; i++) {
			if (childNames[i].equals(childName)) {
				value = childValues[i];
				return value;
			}
		}
		
		value = "";
		
		return value;
	}

	/**
	 *  @param childNum 子节点位置
	 * @param value 子节点值
	 * @return 操作是否成功
	 */
	public final boolean setValue(final int childNum, final String value) {
		
		boolean succeed = true;
		//操作数不在范围内返回false
		if (!checkNum(childNum)) {
			succeed = false;
			return succeed;
		}
		//操作书在范围内
		childValues[childNum - 1] = value;
		succeed = true;
		
		return succeed;
	}
	
	/**
	 * 
	 * @param childName 子节点名字
	 * @param value 子节点值
	 * @return 操作是否成功
	 */
	public final boolean setValue(final String childName, final String value) {
		
		boolean succeed = true;
		
		for (int i = 0; i < childsNum; i++) {
			if (childNames[i].equals(childName)) {
				childValues[i] = value;
				succeed = true;
				return succeed;
			}
		}
		
		succeed = false;
		
		return false;
	}
	
	/**
	 * 校验节点编号是否在范围内.
	 * @param num 节点编号
	 * @return  true or false
	 */
	private boolean checkNum(final int num) {
		if (num < 0 || num > childsNum) {
			return false;
		}
		return true;
	}

	/**
	 * 校验传入的实体名，并返回相应工厂.
	 * @param childBeanName 子实体名字
	 * @return 对应子实体工厂
	 */
	public final ElementBeanFactory checkChildBeanName(
			final String childBeanName) {
		if (!eBChildHave) {
			return null;
		}
		for (int i = 0; i < elementBeanNames.size(); i++) {
			if (childBeanName.equals(elementBeanNames.get(i))) {
				return eBFactory.getChildFactory().getChildBeanFactory(i);
			}
		}
			
		return	null;
	}
	
	/** 
	 * 向list中添加一个子实体.
	 * @param eb 子实体
	 */
	public final void putChildEB(final ElementBean eb) {	
		elementBeanChildValues.add(eb);
		elementBeanChildsNum++;
	}
	
	/**
	 * 
	 * @return 子节点(基本数据)数量
	 */
	public final int getChildsNum() {
		return childsNum;
	}
	
	/**
	 * 如要使用此方法，需在factory中init时调用setElementBeanName().
	 * @return 实体名字 用于判断实体对象
	 */
	public final String getElementBeanName() {
		return elementBeanName;
	}

	
	/**设置实体名字，用以分辨实体对象.
	 * @param eBName 实体对象名(可重复，非相同实体不建议重复)
	 */
	public final void setElementBeanName(final String eBName) {
		elementBeanName = eBName;
	}

	/**
	 * 
	 * @return 子实体数量
	 */
	public final int getElementBeanChildsNum() {
		return elementBeanChildsNum;
	}
	
	/**
	 * 给实体添加属性的方法.
	 * @param attributeName 属性名
	 * @param attributeValue 属性值
	 */
	protected final void addAttribute(final String attributeName,
			final String attributeValue) {
		if (attributesName == null) {
			attributesName = new ArrayList<String>();
			attributesValue = new ArrayList<String>();
		}
		attributesName.add(attributesName.size(), attributeName);
		attributesValue.add(attributesName.size(), attributeValue);	
	}

	/**
	 * 得到实体对应属性值 因属性有可能为各种值或空，故此出要求输入默认返回值，在无此属性时返回.
	 * @param attributeName 属性名
	 * @param errorNH 无此属性时的返回字符串
	 * @return 属性值或自定义的无属性时字符串
	 */
	public final String getAttributeValue(final String attributeName,
			final String errorNH) {

		for (int i = attributesName.size(); i > 0; i--) {
			if (attributesName.get(i).equals(attributeName)) {
				return attributesValue.get(i);
			}
		}

		return errorNH;
	}

	/**
	 * 
	 * @return 返回所有属性
	 */
	public final String getAttributesValue() {
		StringBuilder str = new StringBuilder();
		if (!checkAttributesHave()) {
			return "";
		}
		
		for (int i = attributesName.size(); i > 0; i--) {
			str.append(attributesName.get(i) + "=" + attributesValue.get(i));
			str.append(" ");
		}
		 
		
		return str.toString();
	}
	
	/**
	 * 
	 * @return 是否有节点属性
	 */
	private boolean checkAttributesHave() {
		if (attributesName == null) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param childElementNum 子实体序号
	 * @return 对应序号子实体
	 */
	public final ElementBean getChildElement(final int childElementNum) {
		
		if (elementBeanChildsNum < childElementNum) {
			return null;
		}
		
		return elementBeanChildValues.get(childElementNum);
	}

	/**
	 * 
	 * @return 有无数据.
	 */
	public final boolean isHaveInfo() {
		return haveInfo;
	}

	/**
	 * 
	 * @param haveIf 有无数据.
	 */
	public final void setHaveInfo(final boolean haveIf) {
		this.haveInfo = haveIf;
	}

	/**
	 * 
	 * @return 此实体建造类实例
	 */
	public final ElementBeanFactory geteBFactory() {
		return eBFactory;
	}
	
	
	
	
	
}
