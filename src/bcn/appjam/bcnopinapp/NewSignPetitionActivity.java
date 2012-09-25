package bcn.appjam.bcnopinapp;

import bcn.appjam.bcnopinapp.beans.PetitionsStatic;
import bcn.appjam.bcnopinapp.beans.SignPetition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewSignPetitionActivity extends Activity {

//	public final static String NAME_MESSAGE = "com.example.myfirstapp.MESSAGE";
//	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
//	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_petition);
    }
    
    public void createSignPetitionMessage(View view) {
        // Do something in response to button
    	
    	
    	Intent intent = new Intent(this, SignActivity.class);
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        
        EditText editText2 = (EditText) findViewById(R.id.desc);
        String desc = editText2.getText().toString();
        
        EditText editText3 = (EditText) findViewById(R.id.amount);
        String amount = editText3.getText().toString();
        
        
        SignPetition  petition = new SignPetition();
        petition.name = name;
        petition.desc = desc;
        petition.signature_target_amout = Integer.parseInt(amount);
        PetitionsStatic.petitions.add(petition);
        
        
        startActivity(intent);
    	
    }



}
