package code_segment.summary.pinganprojectsummary.diy.me;

import code_segment.summary.pinganprojectsummary.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DateDialog extends Dialog {

	
	View date;
	Context context;
	Button back;
	Button sure;
	DatePicker datePicker;
	int year;
	int month;
	int day;
	Handler handler;
	View onClickView;
	Time defaultDate;
	
	public DateDialog(Context context,int theme, Handler calculatorDateControlHandler,View v) {
		super(context,theme);
		this.context=context;
		initDateView();
		onClickView=v;
		handler=calculatorDateControlHandler;
		defaultDate=new Time();
		
		
		this.setContentView(date);
		
	}
	public DateDialog(Context context,int theme,DateControlHandler calculatorDateControlHandler, View v,
			Time defaultDate) {
		super(context,theme);
		this.context=context;
		this.defaultDate=defaultDate;
		initDateView();
		onClickView=v;
		handler=calculatorDateControlHandler;
		
		
		
		this.setContentView(date);
	}
	/**
	 * 初始化date控件
	 */
	private void initDateView() {
		date=LayoutInflater.from(context).inflate(R.layout.time_control, null);
		datePicker=(DatePicker)date.findViewById(R.id.datePicker_dialog);
		sure=(Button)date.findViewById(R.id.dateDialog_surebtn);
		back=(Button)date.findViewById(R.id.dateDialog_backbtn);
		
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
				
			}
		});
		
		sure.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				
				year=datePicker.getYear();
				month=datePicker.getMonth();
				day=datePicker.getDayOfMonth();
				
				Bundle bundle=new Bundle();
				bundle.putInt("year",year);
				bundle.putInt("month", month);
				bundle.putInt("day", day);
				bundle.putInt("view", onClickView.getId());
				
				Message msg=new Message();
				msg.setData(bundle);
				handler.sendMessage(msg);
				
				dismiss();
			}
		});
		datePicker.init(defaultDate.year, defaultDate.month, defaultDate.monthDay, null);
		
		
	}

}
