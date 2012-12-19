package code_segment.summary.pinganprojectsummary;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * 通用结构Activity
 * 针对事务（流程）性强的app的activity工作流程封装
 * @author Administrator
 *
 */
public abstract class GeneralStructuralActivity extends Activity {

	protected Handler jumpHandler;//负责跳转
	private Toast toast;//负责显示的吐司，在此类中进行统一
	private ProgressDialog progressDialog;
	protected BaseDataPackage baseDataPackage;//封装数据的对象，只要做过一次银行什么的软件，对那数据操作会疯掉的，一写就是几十行的save代码，load代码~。~。。。。必须封装啊有木有~
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化通用handler
		initHandler();
		//activity自定义初始化工作
		initIntent();
		initControl();
		initVariable();
		initObserver();
	}
	

	/**
	 * 在此方法中处理上个页面传入的数据
	 */
	abstract void initIntent();
	
	/**
	 * 初始化控件，监听方法建议使用finfviewbyid(id).setOCL(myOCL)的方法。 
	 */
	abstract void initControl();

	/**
	 * 初始化各个控件显示内容
	 * 因情况，条件不同，故放在initIntent()之后，依据initIntent()中获得的数据初始化显示内容
	 */
	abstract void initVariable();
	
	/**
	 * 初始化各种动态变化控件的各种观察者。
	 * 状态不会改变的不需要变量名，在initControl()是进行setOCL();
	 */
	abstract void initObserver();
	
	/**
	 * 通过jumpHandler调用，jump(msg.what)
	 * 实现jump()后直接jumpHandler.sendMe......
	 * 在其他类中可以先注入jumpHandler，或者通过单例缓存类得到jumpHandler
	 * @param to
	 */
	abstract void jump(int to);
	
	
	
	private void initHandler() {
		
		jumpHandler=new JumpHandler();
		
	}

	/**
	 * 存储数据方法，在onPause中自动调用
	 */
	private void saveData(){
		
	}
	
	/**
	 * 预留数据恢复方法，没有实现，没有调用，需要时自行实现调用
	 */
	private void loadData(){
		
	}
	
	class JumpHandler extends Handler{

		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
		}

		@Override
		public void handleMessage(Message msg) {	
			super.handleMessage(msg);
			jump(msg.what);
		}

		@Override
		public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
			return super.sendMessageAtTime(msg, uptimeMillis);
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		saveData();
	}
	
	/**
	 * 自定义吐司
	 * @param msg
	 */
	public void ToastShow(String msg){
		if(toast==null){
			toast=Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
		}else{
			//toast.cancel();android4.0不支持。
			toast.setText(msg);
		}
		toast.show();
	}
	
	/**
	 * 自定义阻塞dialog
	 * @param c
	 */
	public void shouProgressDialog(Context c){
		
		progressDialog = new ProgressDialog(c);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage("请求中,请稍候...");
		progressDialog.setButton("取消",new DialogInterface.OnClickListener() {					
			public void onClick(DialogInterface dialog, int which) {
				progressDialog.cancel();
			}
		});			
		progressDialog.show();
	}
	
	/**
	 * 在此类中定义基础数据类型
	 * 在save中存储此类实例，
	 * 依情况存选择存储方式，此处不对存储进行封装。
	 * 注：封装存储方式导致内存及cpu消耗翻倍，故只给出推荐流程，不进行封装
	 */
	class BaseDataPackage{
		
	}
	
	
}
