package code_segment.summary.pinganprojectsummary;

/**
 * ����ģʽ����
 * @author Administrator
 *
 */
public class SingletonCache {

	static private SingletonCache singletonCache;
	static private boolean isNew = false;//ʹ�÷���������~ ���������~���͵�javabean��ʽ����
	
	private SingletonCache(){
		
	}
	
	public static SingletonCache getSingletonCache(){
		if(singletonCache==null){
			singletonCache=new SingletonCache();
			isNew = true;
		}
			
		return singletonCache;		
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	
	
}
