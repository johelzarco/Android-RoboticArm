// Robotic Arm
// Android app for communicate with an Arduino Due and control a robotic arm of six degrees of freedom.
// The communication between Android and Arduino will be through USB protocol; In the android side
// it will use the Android Open Accessory Protocol and the Arduino part will utilize the Usb Host library
// developed by Oleg Mazurov (circuitsathome.com)
//
// This project can be found with more detail in bio-robotics.org
package org.appuniversity.roboticarm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AbstractAdkActivity {
	 // Each seekBar will control the spin of its  servo motors.
	 TextView textIn;
	 //Radio button to open or close the arm's gripper
	 RadioButton ledOn, ledOff;
	 SeekBar seekBarB;
	 TextView progressBrazo; // Spin main element (Two servo motor)
	 // Two servos for the "forearm" element
	 SeekBar seekBarA;
	 TextView progressAnte;
	 // First servo for the hand
	 SeekBar seekBarM;
	 TextView progressMano;
	 // Wrist servo
	 SeekBar seekBarMun;
	 TextView progressMun;
	 // Two button to control the DC motor that spins the whole arm in Z axis
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
	   
	  // seekBar for servo control
	  // Every seekBar send its state(spin) and an identifier to the arduino in one single string
	  // For example writeAdk("50j") will tell the arduino to spin the main arm to 40 degree position.
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
	  
	  // seekBar forearm
	  seekBarA.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			int p =0;
			
				p = progress + 20;
				progressAnte.setText("El giro es: " + p);
				WriteAdk("a"+ p);
		}
	});
	  
	  //seekBar hand
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
	  
	  // seekBar wrist
	  
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
	  
	  // Whole arm spin in Z axis
	  
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

	  // Radio buttons for gripper
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
