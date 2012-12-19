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
 * @author Administrator
 *
 */
public abstract class GeneralStructuralActivity extends Activity {

	private Handler jumpHandler;//负责跳转
	private Toast toast;//负责显示的吐司，在此类中进行统一
	ProgressDialog progressDialog;
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
	


	abstract void initIntent();

	abstract void initControl();

	abstract void initVariable();
	
	abstract void initObserver();
	
	abstract void jump(int to);
	
	
	
	private void initHandler() {
		
		jumpHandler=new JumpHandler();
		
	}

	/**
	 * 存储数据方法，在onPause中自动调用
	 */
	private void saveData(){
		
	}
	
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
	
	
	
	
}
