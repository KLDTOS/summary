package code_segment.summary.pinganprojectsummary.diy.me;

import java.security.acl.LastOwnerException;

import code_segment.summary.pinganprojectsummary.R;
import code_segment.summary.pinganprojectsummary.diy.openproject.ArrayWheelAdapter;
import code_segment.summary.pinganprojectsummary.diy.openproject.OnWheelChangedListener;
import code_segment.summary.pinganprojectsummary.diy.openproject.WheelView;



import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

public class DateDialogBlack extends Dialog {

	View date;
	Context context;
	Button back;
	Button sure;
	int year;
	int month;
	int day;
	Handler handler;
	View onClickView;
	Time defaultDate;
	Time selectDate;
	WheelView yearView;
	WheelView monthView;
	String[] yearString;
	String[] delMonth;
	String[]monthString=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
	int yearfound;
	int monthfound;
	int yearSelect;
	int monthSelect;
	
	public DateDialogBlack(Context context, int theme,Handler calculatorDateControlHandler,View v) {
		super(context,theme);
		this.context=context;
		
		defaultDate=new Time();		
		defaultDate.setToNow();
		
		initDateStrs();
		initDateView();
		onClickView=v;
		handler=calculatorDateControlHandler;
		
		this.setContentView(date);
		
	}
	
	public DateDialogBlack(Context context,int theme,DateControlHandler calculatorDateControlHandler, View v,
			Time defaultDate,Time selectDate) {
		super(context,theme);
		this.context=context;
		this.defaultDate=defaultDate;
		this.selectDate=selectDate;
		initDefDateStrs();
		initDateView();
		onClickView=v;
		handler=calculatorDateControlHandler;
		
		
		
		this.setContentView(date);
	}

	private void initDateView() {
		date=LayoutInflater.from(context).inflate(R.layout.datedialog_black, null);
		yearView=(WheelView)date.findViewById(R.id.dateDialog_year);
		monthView=(WheelView)date.findViewById(R.id.dateDialog_mouth);
		sure=(Button)date.findViewById(R.id.dateblack_surebutton);
		back=(Button)date.findViewById(R.id.dateblack_backbutton);
		
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
				
			}
		});
		
		yearView.setVisibleItems(5);
		monthView.setVisibleItems(5);

		yearView.setAdapter(new ArrayWheelAdapter<String>(yearString));
		if(monthfound!=0&&yearSelect==0){
			monthView.setAdapter(new ArrayWheelAdapter<String>(delMonth));
		}else{
			monthView.setAdapter(new ArrayWheelAdapter<String>(monthString));
		}
		
		if(selectDate!=null){
			yearView.setCurrentItem(yearSelect);
			if(yearSelect==0)	
				monthView.setCurrentItem(monthSelect);
			else
				monthView.setCurrentItem(selectDate.month);
			year=selectDate.year;
			month=selectDate.month;
		}else{
			yearView.setCurrentItem(0);
			monthView.setCurrentItem(0);
			year=defaultDate.year;
			month=defaultDate.month;	
		}
		
		
			
		yearView.addChangingListener(new OnWheelChangedListener() {			
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				year=newValue+yearfound;
				if(newValue==0){
					monthView.setAdapter(new ArrayWheelAdapter<String>(delMonth));
					monthView.setCurrentItem(0);
					month=monthfound;
				}else{
					monthView.setAdapter(new ArrayWheelAdapter<String>(monthString));
					monthView.setCurrentItem(0);
					month=0;
				}
			}
		});
		
		monthView.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if(year!=defaultDate.year)
					month=newValue;
				else
					month=Integer.parseInt(delMonth[newValue])-1;
					
			}
		});
		
		sure.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
					
				Bundle bundle=new Bundle();
				bundle.putInt("year",year);
				bundle.putInt("month", month);
				bundle.putInt("day", 1);
				bundle.putInt("view", onClickView.getId());
				
				Message msg=new Message();
				msg.setData(bundle);
				handler.sendMessage(msg);
				
				dismiss();
			}
		});
			
	}

	
	private void initDateStrs() {
		yearString=new String[300];
		delMonth=new String[12];
		yearfound=1900;
		monthfound=0;
		for(int i=1900;i<2199;i++){
			yearString[i-1900]=""+i;
		}
		delMonth=monthString;
		
	}
	
	private void initDefDateStrs() {
		yearfound=defaultDate.year;
		yearString=new String[2199-yearfound];
		
		for(int i=defaultDate.year;i<2199;i++){
			yearString[i-yearfound]=""+i;
			if(i==selectDate.year)
				yearSelect=i-yearfound;
		}
		
		monthfound=defaultDate.month;
		delMonth=new String[12-monthfound];
		
		for(int i=defaultDate.month;i<12;i++){
			delMonth[i-monthfound]=""+(i+1);
			if(i==selectDate.month)
				monthSelect=i-monthfound;
			
		}
		
	}
	
}
