package org.appuniversity.roboticarm;

import android.app.Activity;
//import android.app.ActionBar;
//import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.os.Build;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AbstractAdkActivity {
	  
	 TextView textIn;
	 RadioButton ledOn, ledOff;
	 SeekBar seekBarB;
	 TextView progressBrazo; // giro del brazo principal
	 // antebrazo
	 SeekBar seekBarA;
	 TextView progressAnte;
	 // mano
	 SeekBar seekBarM;
	 TextView progressMano;
	 // muneca
	 SeekBar seekBarMun;
	 TextView progressMun;
	 // Giro del brazo completo
	 Button leftButton;
	 Button rightButton;

	 
	 
	 @Override
	 protected void doOnCreate(Bundle savedInstanceState) {
	  setContentView(R.layout.activity_main);
	  textIn = (TextView)findViewById(R.id.textin);
	  ledOn = (RadioButton)findViewById(R.id.LedOn);
	  ledOff = (RadioButton)findViewById(R.id.LedOff);
	  
	  seekBarB = (SeekBar) findViewById(R.id.seekBarB);
	  progressBrazo = (TextView) findViewById(R.id.Brazo_progress);
	  
	  seekBarA = (SeekBar) findViewById(R.id.seekBarA);
	  progressAnte = (TextView)findViewById(R.id.antebrazo_progress);
	  
	  seekBarM = (SeekBar) findViewById(R.id.seekBarM);
	  progressMano = (TextView) findViewById(R.id.mano_progress);
	  
	  seekBarMun = (SeekBar) findViewById(R.id.seekBarMun);
	  progressMun = (TextView) findViewById(R.id.mun_progress);
	  
	  leftButton = (Button) findViewById(R.id.button_left);
	  rightButton = (Button) findViewById(R.id.button_right);
	  

	  
	  // seekBar del brazo principal
	  seekBarB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {		
				
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			int j = 0;
			j = progress + 40;
			progressBrazo.setText("El giro es: "+ j);
			WriteAdk("b"+ j);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			
			
		}
	});
	  
	  // seekbar del antebrazo
	  seekBarA.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			int p =0;
			
				p = progress + 20;
				progressAnte.setText("El giro es: " + p);
				WriteAdk("a"+ p);
		}
	});
	  
	  //seekBar de la mano
	  seekBarM.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			progressMano.setText("El giro es:" + progress);
			WriteAdk("m" + progress);
		}
	});
	  
	  // seekBar de la muneca
	  
	  seekBarMun.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {	}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			progressMun.setText("El giro es" +  progress);
			WriteAdk("n" + progress);
		}
	});
	  
	  // Botones para girar todo el brazo
	  
	  leftButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			WriteAdk("left");
		}
	});
	  
	  rightButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			WriteAdk("right");
			
		}
	});

	  
	  // Radio buttons para abrir y cerrar la pinza
	  ledOn.setOnCheckedChangeListener(new OnCheckedChangeListener(){
	 
	   @Override
	   public void onCheckedChanged(CompoundButton buttonView,
	     boolean isChecked) {
	    if(isChecked){
	     WriteAdk("open");
	     Toast.makeText(getApplicationContext(), "Abrir", Toast.LENGTH_LONG).show();
	    }
	   }});
	   
	  ledOff.setOnCheckedChangeListener(new OnCheckedChangeListener(){
	 
	   @Override
	   public void onCheckedChanged(CompoundButton buttonView,
	     boolean isChecked) {
	    if(isChecked){
	     WriteAdk("close");
	     Toast.makeText(getApplicationContext(), "cerrar", Toast.LENGTH_SHORT).show();
	    }
	   }});
	 
	 }
	 
	 @Override
	 protected void doAdkRead(String stringIn) {
	  textIn.setText(stringIn);
	 }
	 
	
	 
	}//fin Main
