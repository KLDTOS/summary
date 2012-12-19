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
 * ����������̣���ǿ��app��activity�������̷�װ
 * @author Administrator
 *
 */
public abstract class GeneralStructuralActivity extends Activity {

	protected Handler jumpHandler;//������ת
	private Toast toast;//������ʾ����˾���ڴ����н���ͳһ
	private ProgressDialog progressDialog;
	protected BaseDataPackage baseDataPackage;//��װ���ݵĶ���ֻҪ����һ������ʲô��������������ݲ��������ģ�һд���Ǽ�ʮ�е�save���룬load����~��~�������������װ����ľ��~
	
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
	

	/**
	 * �ڴ˷����д����ϸ�ҳ�洫�������
	 */
	abstract void initIntent();
	
	/**
	 * ��ʼ���ؼ���������������ʹ��finfviewbyid(id).setOCL(myOCL)�ķ����� 
	 */
	abstract void initControl();

	/**
	 * ��ʼ�������ؼ���ʾ����
	 * �������������ͬ���ʷ���initIntent()֮������initIntent()�л�õ����ݳ�ʼ����ʾ����
	 */
	abstract void initVariable();
	
	/**
	 * ��ʼ�����ֶ�̬�仯�ؼ��ĸ��ֹ۲��ߡ�
	 * ״̬����ı�Ĳ���Ҫ����������initControl()�ǽ���setOCL();
	 */
	abstract void initObserver();
	
	/**
	 * ͨ��jumpHandler���ã�jump(msg.what)
	 * ʵ��jump()��ֱ��jumpHandler.sendMe......
	 * ���������п�����ע��jumpHandler������ͨ������������õ�jumpHandler
	 * @param to
	 */
	abstract void jump(int to);
	
	
	
	private void initHandler() {
		
		jumpHandler=new JumpHandler();
		
	}

	/**
	 * �洢���ݷ�������onPause���Զ�����
	 */
	private void saveData(){
		
	}
	
	/**
	 * Ԥ�����ݻָ�������û��ʵ�֣�û�е��ã���Ҫʱ����ʵ�ֵ���
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
	
	/**
	 * �ڴ����ж��������������
	 * ��save�д洢����ʵ����
	 * �������ѡ��洢��ʽ���˴����Դ洢���з�װ��
	 * ע����װ�洢��ʽ�����ڴ漰cpu���ķ�������ֻ�����Ƽ����̣������з�װ
	 */
	class BaseDataPackage{
		
	}
	
	
}
