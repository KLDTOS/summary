package com.tool.xmltool;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װ���ݵ�ʵ����.
 * @author Administrator
 *
 */
public class ElementBean {

	/**ʵ��name.*/
	private String elementBeanName = "element";
	/**�ӽڵ�����.*/
	private int childsNum;
	/**��ʵ������.*/
	private int elementBeanChildsNum = 0;
	/**�Ƿ�����ʵ��.*/
	private boolean eBChildHave;
	/**��������������.*/
	private String[] childValues;
	/**ʵ�����и������ݶ�Ӧ������.*/
	private String[] childNames;
	/**Ƕ��ʵ������.*/
	private List<String> elementBeanNames;
	/**Ƕ��ʵ���б�.*/
	private List<ElementBean> elementBeanChildValues;
	/**��ʵ�彨�칤��.*/
	private ElementBeanFactory eBFactory;
	/**������list.*/
	private ArrayList<String> attributesName = null;
	/**����ֵlist.*/
	private ArrayList<String> attributesValue = null;
	/**�жϴ�ʵ��������Ϣ.*/
	private boolean haveInfo = false;


	/**
	 * ��������ʵ����. 
	 * ʹ��protected�ǿ��ǵ����ش����new����������̳й���
	 * @param childNum �ӽڵ�������ʵ�������������
	 * @param eBCHave �Ƿ�����ʵ��
	 * @param eBF ��ʵ�幤��
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
			//�������ʵ�壬makeʵ��list
			elementBeanChildValues = eBFactory.getChildFactory().makeChildEBs();
		}
		
		
	}
	
	/**����ĳ���ӽڵ�����.
	 *  
	 * @param childNameNum �ӽڵ�λ�� 
	 * @param name �ӽڵ�����
	 * @return �����Ƿ�ɹ��ķ���ֵ
	 */
	public final boolean setName(final int childNameNum, final String name) {
		//�����Ƿ�ɹ��ķ���ֵ
		boolean succeed = true;
		//�������λ�ô������������ز���ʧ��
		if (!checkNum(childNameNum)) {
			succeed = false;
			return succeed;
		}
		//��ֵ�ڷ�Χ�ڣ�ִ�в��������÷���ֵΪtrue
		childNames[childNameNum - 1] = name;
		succeed = true;
		
		return succeed;
	}
	
	/**�������е��ӽڵ�����.
	 * @param names �ӽڵ������б�
	 * @return �������
	 */
	public final boolean setNames(final String[] names) {
		//��������ֵ
		boolean succeed = true;
		//���Ȳ�ͬ�����ز���ʧ��
		if (names.length != childsNum) {
			succeed = false;
			return succeed;
		}
		//������ͬ���������践��ֵΪtrue
		childNames = names;
		succeed = true;
		
		return succeed;
	}
	
	/**
	 * 
	 * @param childNum �ӽڵ�����
	 * @return �ӽڵ�����
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
	
	
	/**�õ���Ӧ�ӽڵ�ֵ.
	 * @param childNum �ӽڵ�λ��
	 * @return �ӽڵ�ֵ
	 */
	public final  String getValue(final int childNum) {
		
		String value = "";
		//���λ�ò��ڷ�Χ�ڷ��ؿ�
		if (!checkNum(childNum)) {
			value = "";
			return value;
		}
		
		value = childValues[childNum ];
		
		return value;
	}
	
	/** ͨ���˷����õ��ӽڵ�ֵ.
	 * @param childName �ӽڵ�����
	 * @return ��Ӧ�ӽڵ��ֵ�����û�з��ؿ��ַ���
	 */
	public final String getValue(final String childName) {
		  
		String value = "";
		//��childNames���ҵ��±꣬��childValues��ȡֵ
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
	 *  @param childNum �ӽڵ�λ��
	 * @param value �ӽڵ�ֵ
	 * @return �����Ƿ�ɹ�
	 */
	public final boolean setValue(final int childNum, final String value) {
		
		boolean succeed = true;
		//���������ڷ�Χ�ڷ���false
		if (!checkNum(childNum)) {
			succeed = false;
			return succeed;
		}
		//�������ڷ�Χ��
		childValues[childNum - 1] = value;
		succeed = true;
		
		return succeed;
	}
	
	/**
	 * 
	 * @param childName �ӽڵ�����
	 * @param value �ӽڵ�ֵ
	 * @return �����Ƿ�ɹ�
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
	 * У��ڵ����Ƿ��ڷ�Χ��.
	 * @param num �ڵ���
	 * @return  true or false
	 */
	private boolean checkNum(final int num) {
		if (num < 0 || num > childsNum) {
			return false;
		}
		return true;
	}

	/**
	 * У�鴫���ʵ��������������Ӧ����.
	 * @param childBeanName ��ʵ������
	 * @return ��Ӧ��ʵ�幤��
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
	 * ��list�����һ����ʵ��.
	 * @param eb ��ʵ��
	 */
	public final void putChildEB(final ElementBean eb) {	
		elementBeanChildValues.add(eb);
		elementBeanChildsNum++;
	}
	
	/**
	 * 
	 * @return �ӽڵ�(��������)����
	 */
	public final int getChildsNum() {
		return childsNum;
	}
	
	/**
	 * ��Ҫʹ�ô˷���������factory��initʱ����setElementBeanName().
	 * @return ʵ������ �����ж�ʵ�����
	 */
	public final String getElementBeanName() {
		return elementBeanName;
	}

	
	/**����ʵ�����֣����Էֱ�ʵ�����.
	 * @param eBName ʵ�������(���ظ�������ͬʵ�岻�����ظ�)
	 */
	public final void setElementBeanName(final String eBName) {
		elementBeanName = eBName;
	}

	/**
	 * 
	 * @return ��ʵ������
	 */
	public final int getElementBeanChildsNum() {
		return elementBeanChildsNum;
	}
	
	/**
	 * ��ʵ��������Եķ���.
	 * @param attributeName ������
	 * @param attributeValue ����ֵ
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
	 * �õ�ʵ���Ӧ����ֵ �������п���Ϊ����ֵ��գ��ʴ˳�Ҫ������Ĭ�Ϸ���ֵ�����޴�����ʱ����.
	 * @param attributeName ������
	 * @param errorNH �޴�����ʱ�ķ����ַ���
	 * @return ����ֵ���Զ����������ʱ�ַ���
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
	 * @return ������������
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
	 * @return �Ƿ��нڵ�����
	 */
	private boolean checkAttributesHave() {
		if (attributesName == null) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param childElementNum ��ʵ�����
	 * @return ��Ӧ�����ʵ��
	 */
	public final ElementBean getChildElement(final int childElementNum) {
		
		if (elementBeanChildsNum < childElementNum) {
			return null;
		}
		
		return elementBeanChildValues.get(childElementNum);
	}

	/**
	 * 
	 * @return ��������.
	 */
	public final boolean isHaveInfo() {
		return haveInfo;
	}

	/**
	 * 
	 * @param haveIf ��������.
	 */
	public final void setHaveInfo(final boolean haveIf) {
		this.haveInfo = haveIf;
	}

	/**
	 * 
	 * @return ��ʵ�彨����ʵ��
	 */
	public final ElementBeanFactory geteBFactory() {
		return eBFactory;
	}
	
	
	
	
	
}
