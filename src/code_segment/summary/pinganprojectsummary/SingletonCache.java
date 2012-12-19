package code_segment.summary.pinganprojectsummary;

/**
 * ����ģʽ����
 * @author Administrator
 *
 */
public class SingletonCache {

	static SingletonCache singletonCache;
	
	private SingletonCache(){
		
	}
	
	public static SingletonCache getSingletonCache(){
		if(singletonCache==null)
			singletonCache=new SingletonCache();
		return singletonCache;		
	}
	
	
	
}
