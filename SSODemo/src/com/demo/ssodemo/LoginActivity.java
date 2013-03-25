package com.demo.ssodemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 *  sso�����¼����ģ��.��Ҫsso���ܵ�Ӧ���׽���̳д�activity.
 * @author Administrator
 *
 */
public abstract class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	        
		if (isLoginActivity()) {
			initLogin();
		} else {
			
			//�ж�logining
			if (isLogining()) {
				//��¼�У���תӦ�ý���
				turnAppHomeActivity();
			} else {
				//��¼
				initLogin();
			}
			
		}
			
		
		
		
		
	}

	/**
	 * ��ʼ���˽�����е�¼.
	 */
	private void initLogin() {
		
		setContentView(R.layout.activity_login);
		this.findViewById(R.id.button_login).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						new Thread() {
							public void run() {
								System.out.println("onClick");
								login(((EditText) findViewById(R.id.editText_name))
										.getText().toString(),
										((EditText) findViewById(R.id.editText_pwd))
												.getText().toString());
							}
						}.start();
					}
				});
		
		this.findViewById(R.id.button_loginout).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						new Thread() {
							public void run() {
								loginOut();
							}
						}.start();

					}
				});
		}

	
	/**
	 * �ǳ�.
	 */
	protected void loginOut() {
		SharedPreferences preferenceRead = getSharedPreferences("datecatch",
				MODE_WORLD_READABLE);

		SharedPreferences preference = getSharedPreferences("datecatch",
				MODE_WORLD_WRITEABLE);
		Editor editor = preference.edit();
		editor.putBoolean("mPortalLogin", false);
		//mPortalAppNum ΪȨֵ 
		//ÿ����Ӧ�ñ�killʱȨֵ-1   ��Ӧ������ʱȨֵ+1   ������Ӧ�ñ�killʱȨֵΪ0 ��ʱ���õ�¼״̬Ϊfalse
		//��ȨֵΪ0 ���۵�¼״ֵ̬Ϊ��ֵ ȫ����Ϊfalse
		editor.putInt("mPortalAppNum",
				preferenceRead.getInt("mPortalAppNum", 0) - 1);
		editor.commit();
		
	}

	/**
	 * ��¼����.
	 * @param name �û���
	 * @param pwd ����
	 */
	protected final void login(final String name, final String pwd) {
		System.out.println("login");
		//��¼�жϷ�������д��if��
		if (checkUser(name, pwd)) {
			System.out.println("��¼");
			allLogin();
			//��תapphome
			turnAppHomeActivity();
		}
		
	}

	/**
	 * ��¼״̬��Ϊ��¼.
	 */
	@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
	private void allLogin() {
		SharedPreferences preferenceRead = getSharedPreferences("datecatch",
				MODE_WORLD_READABLE);

		SharedPreferences preference = getSharedPreferences("datecatch",
				MODE_WORLD_WRITEABLE);
		Editor editor = preference.edit();
		editor.putBoolean("mPortalLogin", true);
		//mPortalAppNum ΪȨֵ 
		//ÿ����Ӧ�ñ�killʱȨֵ-1   ��Ӧ������ʱȨֵ+1   ������Ӧ�ñ�killʱȨֵΪ0 ��ʱ���õ�¼״̬Ϊfalse
		//��ȨֵΪ0 ���۵�¼״ֵ̬Ϊ��ֵ ȫ����Ϊfalse
		editor.putInt("mPortalAppNum",
				preferenceRead.getInt("mPortalAppNum", 0) + 1);
		editor.commit();
	}

	/**
	 * ��֤�û���������.
	 * @param name �û���
	 * @param pwd ����
	 * @return �Ƿ���ȷ
	 */
	private boolean checkUser(final String name, final String pwd) {
		
		/***********test********************/
//		if ("shenpu".equals(name) && "123456".equals(pwd)) {
//			return true;
//		}
		System.out.println("checkUser");	
		
		/***********test********************/
		
		return new LoginHttpsTool().doLogin(name, pwd, this);
	}

	/**
	 * ��תappHomeҳ�淽��.
	 */
	private void turnAppHomeActivity() {
		
		jumpAppHomeActivity();
		
	}

	/**
	 * �ж��Ƿ�Ϊ��¼״̬.
	 * @return  ture or false 
	 */
	private boolean isLogining() {
		SharedPreferences preferenceRead = getSharedPreferences("datecatch",
				MODE_WORLD_READABLE);
		
		if (preferenceRead.getInt("mPortalAppNum", 0) > 0) {
			return false;
		}
		
		return preferenceRead.getBoolean("mPortalLogin", false);
	}
	
	/**
	 * ��ת����������ʵ��.
	 */
	public abstract void jumpAppHomeActivity();
	
	
	/**
	 * �����Ƿ�ΪloginActivity.
	 * @return true or false
	 */
	public boolean isLoginActivity() {
		
		return false;
	}
	
	
}
