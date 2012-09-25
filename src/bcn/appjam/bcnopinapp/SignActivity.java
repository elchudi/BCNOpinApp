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
import android.widget.TextView;
import bcn.appjam.bcnopinapp.beans.SignPetition;

public class SignActivity extends ListActivity {

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
              "Please wait...", "Retrieving data ...", true);
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
                        if (tt != null) {
                              tt.setText("Name: "+o.name);                            }
                        if(bt != null){
                              bt.setText("Status: "+ o.desc);
                        }
                }
                return v;
        }
    }
    
    private void getSignPetitions(){
        try{
            m_orders = new ArrayList<SignPetition>();
            SignPetition o1 = new SignPetition();
            o1.name = "SF services";
            o1.desc = "Lorem ipso";
            SignPetition o2 = new SignPetition();
            o2.name = "SF services";
            o2.desc = "Lorem ipso";
            m_orders.add(o1);
            m_orders.add(o2);
               Thread.sleep(2000);
            Log.i("ARRAY", ""+ m_orders.size());
          } catch (Exception e) {
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
    
}
