package code_segment.summary.pinganprojectsummary;

/**
 * µ¥ÀýÄ£Ê½»º´æ
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
