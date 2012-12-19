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
 * ͨ�ýṹActivity
 * @author Administrator
 *
 */
public abstract class GeneralStructuralActivity extends Activity {

	private Handler jumpHandler;//������ת
	private Toast toast;//������ʾ����˾���ڴ����н���ͳһ
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ʼ��ͨ��handler
		initHandler();
		//activity�Զ����ʼ������
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
	 * �洢���ݷ�������onPause���Զ�����
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
	 * �Զ�����˾
	 * @param msg
	 */
	public void ToastShow(String msg){
		if(toast==null){
			toast=Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
		}else{
			//toast.cancel();android4.0��֧�֡�
			toast.setText(msg);
		}
		toast.show();
	}
	
	/**
	 * �Զ�������dialog
	 * @param c
	 */
	public void shouProgressDialog(Context c){
		
		progressDialog = new ProgressDialog(c);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage("������,���Ժ�...");
		progressDialog.setButton("ȡ��",new DialogInterface.OnClickListener() {					
			public void onClick(DialogInterface dialog, int which) {
				progressDialog.cancel();
			}
		});			
		progressDialog.show();
	}
	
	
	
	
}
