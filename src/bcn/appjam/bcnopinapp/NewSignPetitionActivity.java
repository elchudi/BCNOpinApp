package bcn.appjam.bcnopinapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class NewSignPetitionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_petition);
    }
    
    public void createSignPetitionMessage(View view) {
        // Do something in response to button
    	
    	
    	Intent intent = new Intent(this, SignActivity.class);
    	startActivity(intent);
    }



}
