package sp.com;
//.java means class. the rest is activity
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_form extends AppCompatActivity {

    private EditText iname;
    private EditText iadminnum;
    private EditText iemail;
    Fchelpermodel helper;
    private Cursor model = null;
    private TextView name;
    private Menu menu=null;
    boolean isItemAdd =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);

        iname = findViewById(R.id.iName);
        iemail=findViewById(R.id.iemail);
        iadminnum=findViewById(R.id.iadminnum);
        name=findViewById(R.id.name);

        helper= new Fchelpermodel(Detail_form.this);
        model= helper.getAll();



    }



    public void saveData(View v ){
        String namestr= iname.getText().toString().trim();
        String emailstr=iemail.getText().toString().trim();
        String adminstr = iadminnum.getText().toString().trim();

//        if(namestr=="" || emailstr!="" || adminstr!= ""){
//            helper.insert(namestr,adminstr,emailstr);
//            Message.message(this,"Added successfully");
//        }
//        else if(namestr == ""){
//            Message.message(this,"Empty!");
//        }
        helper.insert(namestr,adminstr,emailstr);
        Message.message(this,"Added successfully");

//        menu.findItem(R.id.register).setVisible(false);
//        menu.findItem(R.id.unregister).setVisible(true);
        //set already

        //why cannot set menu

        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }




}
