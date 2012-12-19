package code_segment.summary.pinganprojectsummary;

/**
 * 单例模式缓存
 * @author Administrator
 *
 */
public class SingletonCache {

	static private SingletonCache singletonCache;
	static private boolean isNew = false;//使用方法在这里~ 看这个变量~典型的javabean方式就行
	
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
