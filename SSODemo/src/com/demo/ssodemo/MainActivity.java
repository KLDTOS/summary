package com.demo.ssodemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends LoginActivity {

	@Override
	public void jumpAppHomeActivity() {
		
		Toast.makeText(getApplicationContext(), "jumpapphomeactivity", Toast.LENGTH_LONG).show();
		
	}

    

   

}
