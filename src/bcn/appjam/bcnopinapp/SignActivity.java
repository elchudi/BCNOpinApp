package bcn.appjam.bcnopinapp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import bcn.appjam.bcnopinapp.beans.PetitionsStatic;
import bcn.appjam.bcnopinapp.beans.SignPetition;

public class SignActivity extends ListActivity {
	
	public static String SIGN_NAME = "bcn.name";
	
	
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<SignPetition> m_orders = null;
    private SignAdapter m_adapter;
    private Runnable viewOrders;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        m_orders = new ArrayList<SignPetition>();
        this.m_adapter = new SignAdapter(this, R.layout.sign, m_orders);
        setListAdapter(this.m_adapter);
       
        viewOrders = new Runnable(){
            @Override
            public void run() {
                getSignPetitions();
            }
        };
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(SignActivity.this,    
              "Espere si us plau...", "Retrieving data ...", true);
    }
    
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                m_adapter.add(m_orders.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };
    
    public void newSignPetitionMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, NewSignPetitionActivity.class);
    	
    	
    	startActivity(intent);
    }

    private class SignAdapter extends ArrayAdapter<SignPetition> {

        private ArrayList<SignPetition> items;

        public SignAdapter(Context context, int textViewResourceId, ArrayList<SignPetition> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.sign, null);
                }
                SignPetition o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext);
                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                        TextView amount = (TextView) v.findViewById(R.id.amount);
                        if (tt != null) {
                        	  tt.setId(800);
                        	  tt.setText("Peticion: "+o.name);                            }
                        if(bt != null){
                        	  bt.setId(900);
                              bt.setText("Descripcion: "+ o.desc);
                        }
                        if(amount != null){
                        	amount.setId(1000);
                            amount.setText(" " + o.signature_current_amount + " / "+ o.signature_target_amout);
                      }
                }
                return v;
        }
    }
    
    private void getSignPetitions(){
    	if(PetitionsStatic.petitions.size()==0){
       	 SignPetition o1 = new SignPetition();
         o1.name = "Mantenimiento de la nueva versión del Ecce Homo de Borja";
         o1.desc = "El osado trabajo realizado por la espontánea artista en el Ecce Homo del Santuario de la Misericordia de Borja, supone además de un entrañable acto de amor, un inteligente reflejo de la situación política y social de nuestro tiempo. En el cual se pone de manifiesto una sutil crítica a las teorías creacionistas de la Iglesia, a la vez que se cuestiona el surgimiento de nuevos ídolos. El resultado de la intervención combina inteligentemente el expresionismo primitivo de Francisco de Goya, con figuras como Ensor, Munch, Modigliani o el grupo Die Brücke, perteneciente a la corriente artística del expresionismo alemán.";
         o1.signature_target_amout = 10000;
         o1.signature_current_amount = 2367;
         SignPetition o2 = new SignPetition();
         o2.name = "Anulen dietas de alojamiento a diputados con casa en Madrid";
         o2.desc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
         o2.signature_target_amout = 20000;
         o2.signature_current_amount = 2345;
         PetitionsStatic.petitions.add(o1);
         PetitionsStatic.petitions.add(o2);
    	} 
        try{
            m_orders = new ArrayList<SignPetition>();
            	for (int i = 0; i < PetitionsStatic.petitions.size();i++){
            		m_orders.add(PetitionsStatic.petitions.get(i));
            	
            }
               Thread.sleep(1000);
            Log.i("ARRAY", ""+ m_orders.size());
          } catch (Exception e) {
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
    
     public void viewSignPetitionMessage(View view){
    	Intent intent = new Intent(this, SignViewActivity.class);
    	
//    	LinearLayout lin = (LinearLayout) findViewById(view.getId());
    	LinearLayout lin = (LinearLayout) view;
    	
    	String message = "";
    	Log.i("child count", ""+ lin.getChildCount());
    	for (int i = 0; i < lin.getChildCount(); i++){
    		TextView textView = (TextView) lin.getChildAt(i);
    		if(textView.getId()==800 ){
    			message = textView.getText().toString();
    		}
    	}
     	
     	intent.putExtra(SIGN_NAME, message);
     	
     	startActivity(intent);
    	 
     }
    
}
