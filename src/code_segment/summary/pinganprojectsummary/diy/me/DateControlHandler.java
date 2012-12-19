package code_segment.summary.pinganprojectsummary.diy.me;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class DateControlHandler extends Handler {

	public int year;
	public int month;
	public int day;
	public int viewId;

	
	/**
	 * 重写此方�?
	 * 变量�?year,month,day,viewid(均为int)
	 */
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		
		Bundle bundle=msg.getData();
		year=bundle.getInt("year");
		month=bundle.getInt("month");
		day=bundle.getInt("day");
		viewId=bundle.getInt("view");
		
		
		
		
	}


	
}
