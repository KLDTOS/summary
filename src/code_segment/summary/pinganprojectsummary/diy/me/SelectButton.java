package code_segment.summary.pinganprojectsummary.diy.me;



import code_segment.summary.pinganprojectsummary.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectButton extends LinearLayout {

	
	LayoutInflater inflater;
	private Button left;//左按钮
	private Button right;//右按钮
	private Button middle;
	private String letfSring;
	private String rightSring;
	private String middleString;
	private int selected=0;//0为左按钮，1位右按钮
	private SelectListener selectListener;
	private boolean use=true;
	
	public SelectButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.selectbutton,this);
		
		left=(Button)findViewById(R.id.buttonleft);
		right=(Button)findViewById(R.id.buttonright);
		middle=(Button)findViewById(R.id.buttonmiddle);
		initBackGround();
		if(use){
			left.setOnClickListener(new LeftOCL());
			right.setOnClickListener(new RightOCL());
			middle.setOnClickListener(new MiddleOCL());
		}
		
	}
	/**
	 * 初始化按钮
	 * @param letfSring
	 * @param rightSring
	 */
	public void init(String letfSring ,String rightSring){
		this.letfSring=letfSring;
		this.rightSring=rightSring;
		
		middle.setVisibility(View.GONE);
		initText();
	}
	/**
	 * 初始化3个按钮
	 * @param letfSring
	 * @param middleSring
	 * @param rightSring
	 */
	public void init(String letfSring, String middleSring, String rightSring) {
		this.letfSring=letfSring;
		this.rightSring=rightSring;
		this.middleString=middleSring;
		
		middle.setVisibility(View.VISIBLE);
		initText();
	}
	/**
	 * 初始化文字
	 */
	private void initText(){
		left.setText(letfSring);
		left.setTextColor(Color.WHITE);
		right.setText(rightSring);
		right.setTextColor(Color.GRAY);
		middle.setText(middleString);
		middle.setTextColor(Color.GRAY);
	}
	/**
	 * 初始化背景图片
	 */
	private void initBackGround(){
		left.setBackgroundResource(R.drawable.selectbutton_leftbg_one);
		right.setBackgroundResource(R.drawable.selectbutton_rightbg_one);
		middle.setBackgroundResource(R.drawable.selectbutton_middlebg_one);
	}
	/**
	 * 左按钮监听
	 * @author Administrator
	 *
	 */
	class LeftOCL implements OnClickListener{
		public void onClick(View v) {
			
			if(selected==0){//閫変腑鐘舵?
				
			}else{//鏈?涓?
				selected=0;
				selectChange();
			}
			
		}
	}
	/**
	 * 右按钮监听
	 * @author Administrator
	 *
	 */
	class RightOCL implements OnClickListener{
		
		public void onClick(View v) {
			
			if(selected==1){//閫変腑鐘舵?
				
			}else{//鏈?涓?
				selected=1;
				selectChange();
			}
			
		}	
	}
	class MiddleOCL implements OnClickListener{

		public void onClick(View v) {
			
			if(selected==2){
				
			}else{
				selected=2;
				selectChange();
			}
			
		}
	}
	/**
	 * 选择改变
	 */
	public void selectChange() {
		try {
			selectListener.selectChange(selected);
		} catch (Exception e) {
			// TODO: don't have selectListener
		}
		
		backGroundChange();
		textColorChange();
	}
	/**
	 * 改变字体颜色
	 */
	private void textColorChange() {
		switch (selected) {
		case 0:
			left.setTextColor(Color.WHITE);
			middle.setTextColor(Color.GRAY);
			right.setTextColor(Color.GRAY);
			break;
		case 1:
			left.setTextColor(Color.GRAY);
			middle.setTextColor(Color.GRAY);
			right.setTextColor(Color.WHITE);
			break;
		case 2:
			left.setTextColor(Color.GRAY);
			middle.setTextColor(Color.WHITE);
			right.setTextColor(Color.GRAY);
			break;	
		default:
			break;
		}
		
	}
	/**
	 * 改变背景图片
	 * 
	 */
	public void backGroundChange(){
		//浜掓崲鑳屾櫙

		
		switch (selected) {
		case 0:
			left.setBackgroundResource(R.drawable.selectbutton_leftbg_one);
			right.setBackgroundResource(R.drawable.selectbutton_rightbg_one);
			middle.setBackgroundResource(R.drawable.selectbutton_middlebg_one);
			break;

		case 1:
			left.setBackgroundResource(R.drawable.selectbutton_leftbg_two);
			right.setBackgroundResource(R.drawable.selectbutton_rightbg_two);
			middle.setBackgroundResource(R.drawable.selectbutton_middlebg_one);
			break;
			
		case 2:
			left.setBackgroundResource(R.drawable.selectbutton_leftbg_two);
			right.setBackgroundResource(R.drawable.selectbutton_rightbg_one);
			middle.setBackgroundResource(R.drawable.selectbutton_middlebg_two);
			
			break;
		default:
			break;
		}
	}
	public int getSelected() {
		return selected;
	}
	public void setSeleted(int select){
		selected=select;
		selectChange();
	}
	public void setSelectListener(SelectListener selectListener) {
		this.selectListener=selectListener;
		
	}
	
	
	
	@Override
	public void setFocusable(boolean focusable) {
		super.setFocusable(focusable);
		use=focusable;
		if(use==false){
			left.setOnClickListener(null);
			right.setOnClickListener(null);
			middle.setOnClickListener(null);
		}else{
			left.setOnClickListener(new LeftOCL());
			right.setOnClickListener(new RightOCL());
			middle.setOnClickListener(new MiddleOCL());
		}
	}
	
	public interface SelectListener {

		abstract void selectChange(int select);
	}
	
	
}
