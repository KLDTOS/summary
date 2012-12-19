package code_segment.summary.pinganprojectsummary.diy.me;







import code_segment.summary.pinganprojectsummary.R;
import code_segment.summary.pinganprojectsummary.diy.openproject.ArrayWheelAdapter;
import code_segment.summary.pinganprojectsummary.diy.openproject.OnWheelChangedListener;
import code_segment.summary.pinganprojectsummary.diy.openproject.WheelView;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class CityDialog extends Dialog {

	Context context;
	View cityView;
	int citySelect=0;
	String[] cityId={"0101","0103","0104","0105","0106","0107","0108","0109","0110","0112","0113","0114","0115","0116","0117","0118","0119","0120","0230"
};
	Handler handler;
	WheelView country;
	WheelView city;
	int firstSelect=0;
	
	public CityDialog(Context context, int theme,Handler handler,int firstSelect) {
		super(context, theme);
		this.context=context;
		this.handler=handler;
		this.firstSelect=firstSelect;
		initView();
		this.setContentView(cityView);
	}

	private void initView() {
		cityView=LayoutInflater.from(context).inflate(R.layout.cities_layout, null);
		country = (WheelView)cityView.findViewById(R.id.cityDialog_country);
		String countries[] = new String[] {" ä¸?æµ?","","","",""};
        country.setVisibleItems(5);
        country.setAdapter(new ArrayWheelAdapter<String>(countries));
		
        final String cities[][] = new String[][] {
    		new String[] {" é»?æµ?åŒ?", " å?æ¹?åŒ?", " å¾?æ±?åŒ?", " é•?å®?åŒ?", " é?å®?åŒ?"," æ™?é™?åŒ?", " é—?åŒ?åŒ?", " è™?å?åŒ?", " æ?æµ?åŒ?",
    				" é—?è¡?åŒ?", " å®?å±?åŒ?", " å˜?å®?åŒ?", " æµ?ä¸?æ–?åŒ?"," é‡?å±?åŒ?", " æ?æ±?åŒ?", " é?æµ?åŒ?", " å?æ±?åŒ?"," å¥?è´?åŒ?", " å´?æ˜?å?"},
    		new String[] {""},
    		new String[] {""},
    		new String[] {""},
    		new String[] {""}
        };
		
        city = (WheelView)cityView.findViewById(R.id.cityDialog_city);
        city.setVisibleItems(5);

        country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				city.setAdapter(new ArrayWheelAdapter<String>(cities[newValue]));
				city.setCurrentItem(cities[newValue].length / 2);
			}
		});
		
        country.setCurrentItem(0);
    	city.setCurrentItem(citySelect);
    	city.setAdapter(new ArrayWheelAdapter<String>(cities[0]));
		
    	city.addChangingListener(new OnWheelChangedListener() {			
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				citySelect=newValue;
				
			}
		});
    	city.setCurrentItem(firstSelect);
    	
    	Button sure=(Button)cityView.findViewById(R.id.city_surebutton);
    	sure.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				
				Bundle bundle=new Bundle();
				bundle.putInt("cityIdSelect", citySelect);
				Message msg=new Message();
				msg.setData(bundle);
				handler.sendMessage(msg);
				
				dismiss();
				
			}
		});
    	
	}

	

}
