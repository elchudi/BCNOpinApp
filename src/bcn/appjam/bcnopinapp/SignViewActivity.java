package bcn.appjam.bcnopinapp;

import bcn.appjam.bcnopinapp.beans.PetitionsStatic;
import bcn.appjam.bcnopinapp.beans.SignPetition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignViewActivity extends Activity {

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	 
	        setContentView(R.layout.activity_sign_view);
	        Intent intent = getIntent();
	        String message = intent.getStringExtra(SignActivity.SIGN_NAME);
	        TextView name ;
    		TextView desc ;
    		TextView amount_required  ;
    		TextView amount_collected;
    		
	        for(int i = 0; i< PetitionsStatic.petitions.size();i++ ){
	        	SignPetition curr = PetitionsStatic.petitions.get(i);
	        	Log.i("Names", curr.name.trim());
	        	Log.i("Current", String.valueOf(i));
	        	if(curr.name.trim().equalsIgnoreCase((message.substring(10).trim()))){
	        		Log.i("EQULA!!!", String.valueOf(i));
		        	
	        		name = (TextView) findViewById(R.id.name1);
	        		desc = (TextView) findViewById(R.id.desc1);
	        		amount_required  = (TextView) findViewById(R.id.amount_required1);
	        		amount_collected= (TextView) findViewById(R.id.amount_collected1);
	        		name.setText(curr.name);
	        		desc.setText(curr.desc);
	        		amount_required.setText(String.valueOf(curr.signature_target_amout));
	        		amount_collected.setText(String.valueOf(curr.signature_current_amount));
	        	}
	        	
	        }
	        
	        
    		
    		Log.i("Message", message);
    		Log.i("Message", message.substring(10).trim());
    		 
	        
	        
	 
	}
	
	 public void voteUp(View view){
	    	Intent intent = new Intent(this, SignActivity.class);
	    	
	    	TextView t = (TextView)findViewById(R.id.name1);
	    	String message = (String)t.getText();
	    	   for(int i = 0; i< PetitionsStatic.petitions.size();i++ ){
		        	SignPetition curr = PetitionsStatic.petitions.get(i);
		        	Log.i("Names", curr.name.trim());
		        	Log.i("Current", String.valueOf(i));
		        	if(curr.name.trim().equalsIgnoreCase((message.trim()))){
		        		Log.i("EQULA!!!", String.valueOf(i));
			        	curr.signature_current_amount += 1;
		        	}
		        	
		        }
	     	
	     	startActivity(intent);
	    	 
	     }
}