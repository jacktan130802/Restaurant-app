package sp.com;
//Please cher all your layout can load esp pics!!!
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
public class Infos extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView adminnum;
        private Cursor c=null;
    private Fchelpermodel helper =null;
    private Cursor model;
    private String name1;
    private String email1;
    private String adminnuml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        name=findViewById(R.id.name);


        email=findViewById(R.id.email);
        adminnum=findViewById(R.id.adminnum);
        helper = new Fchelpermodel(Infos.this);
//        model=helper.getAll();
//
//
//
//        String record=helper.getID(model); //check c vs model why i change then can
//        Cursor c= helper.getById(record);
//        c.moveToFirst();//what
//        name.setText(helper.getName(c));

        load();

        name.setText(name1);
        email.setText(email1);
        adminnum.setText(adminnuml); // please work out the reason why u declare as text.Usually to set so you need another ariable string to set.Work out flow chart


        ActionBar ab =getSupportActionBar();//Setting the action bar(The top navbar name)
        ab.setTitle("My Profile");


    }




//        model=helper.getAll();
//        restaurantID= getIntent().getStringExtra("ID");//where you come from
////        if(restaurantID!=null){
//            load();
////        }



    public void load(){
Cursor c =helper.getAll();
if(c.getCount()==0){
    Message.message(this,"No data is added");
}else{
    while(c.moveToNext()){

            name1 = c.getString(1);
            adminnuml = c.getString(2);
            email1 = c.getString(3);
        }

    }
}

    }



