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
 *  sso单点登录集成模块.需要sso功能的应用首界面继承此activity.
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
			
			//判断logining
			if (isLogining()) {
				//登录中，跳转应用界面
				turnAppHomeActivity();
			} else {
				//登录
				initLogin();
			}
			
		}
			
		
		
		
		
	}

	/**
	 * 初始化此界面进行登录.
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
	 * 登出.
	 */
	protected void loginOut() {
		SharedPreferences preferenceRead = getSharedPreferences("datecatch",
				MODE_WORLD_READABLE);

		SharedPreferences preference = getSharedPreferences("datecatch",
				MODE_WORLD_WRITEABLE);
		Editor editor = preference.edit();
		editor.putBoolean("mPortalLogin", false);
		//mPortalAppNum 为权值 
		//每当有应用被kill时权值-1   有应用启动时权值+1   当所有应用被kill时权值为0 此时设置登录状态为false
		//若权值为0 不论登录状态值为何值 全部视为false
		editor.putInt("mPortalAppNum",
				preferenceRead.getInt("mPortalAppNum", 0) - 1);
		editor.commit();
		
	}

	/**
	 * 登录方法.
	 * @param name 用户名
	 * @param pwd 密码
	 */
	protected final void login(final String name, final String pwd) {
		System.out.println("login");
		//登录判断方法调用写在if里
		if (checkUser(name, pwd)) {
			System.out.println("登录");
			allLogin();
			//跳转apphome
			turnAppHomeActivity();
		}
		
	}

	/**
	 * 登录状态改为登录.
	 */
	@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
	private void allLogin() {
		SharedPreferences preferenceRead = getSharedPreferences("datecatch",
				MODE_WORLD_READABLE);

		SharedPreferences preference = getSharedPreferences("datecatch",
				MODE_WORLD_WRITEABLE);
		Editor editor = preference.edit();
		editor.putBoolean("mPortalLogin", true);
		//mPortalAppNum 为权值 
		//每当有应用被kill时权值-1   有应用启动时权值+1   当所有应用被kill时权值为0 此时设置登录状态为false
		//若权值为0 不论登录状态值为何值 全部视为false
		editor.putInt("mPortalAppNum",
				preferenceRead.getInt("mPortalAppNum", 0) + 1);
		editor.commit();
	}

	/**
	 * 验证用户名和密码.
	 * @param name 用户名
	 * @param pwd 密码
	 * @return 是否正确
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
	 * 跳转appHome页面方法.
	 */
	private void turnAppHomeActivity() {
		
		jumpAppHomeActivity();
		
	}

	/**
	 * 判断是否为登录状态.
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
	 * 跳转方法，必须实现.
	 */
	public abstract void jumpAppHomeActivity();
	
	
	/**
	 * 设置是否为loginActivity.
	 * @return true or false
	 */
	public boolean isLoginActivity() {
		
		return false;
	}
	
	
}
