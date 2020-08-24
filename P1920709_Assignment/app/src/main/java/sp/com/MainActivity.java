package sp.com;
//thi is used when is basedd on accttivity.Getapplication context is used when is based on other app mainly outside activity.
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static sp.com.R.*;

public class MainActivity extends AppCompatActivity  {
    private ImageView fcImage;
    private TextView fcDisc;
    private FoodCourt adapter = null;//create object call adapter from class foodcourt.
    //    private FoodCourt adapter =null;
    private ListView list;
    private Cursor model=null;

    public final int ItemId=101;
    boolean isRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        String name[] = {"Jake's Restaurant", "Quan Ut Ut - Vo Van Kiet", "3T Grill Restaurant", "5ku Station"};
        String disc[] = {"Võ Văn Kiệt, Phường Cầu Ông Lãnh, Quận 1, Hồ Chí Minh, Vietnam", "Võ Văn Kiệt, Phường Cầu Ông Lãnh, Quận 1, Hồ Chí Minh, Vietnam", "48-66 Tôn Thất Thiệp, Bến Nghé, Quận 1, Hồ Chí Minh, Vietnam", "28-32 Lê Thánh Tôn, Bến Nghé, Quận 1, Hồ Chí Minh, Vietnam"};
        int img[] = {drawable.bq, drawable.bq2, drawable.bq3, drawable.bq4};
        list = findViewById(id.list);
        adapter = new FoodCourt(this, name, disc, img);//constructor
        list.setAdapter(adapter);
        Fchelpermodel helper = new Fchelpermodel(MainActivity.this);

     list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double lat;
                double lon;
                String place ;
                if (position == 0 ){

                    lat=10.773872;
                    lon=106.701445;
                    place = "Jake's Restaurant";

                    Intent intent = new Intent(MainActivity.this,Location.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("place",place);
                    startActivity(intent);
                //finish() means at the last page cannot go back.Stuck there.
                }

                if (position == 1 ){

                    lat=10.767584;
                    lon=106.699445;
                    place = "Quan Ut Ut - Vo Van Kiet";

                    Intent intent = new Intent(MainActivity.this,Location.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("place",place);
                    startActivity(intent);

                }
                if (position == 2 ){

                    lat= 10.778894;
                    lon= 106.704641;
                    place = "3T Grill Restaurant";

                    Intent intent = new Intent(MainActivity.this,Location.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("place",place);
                    startActivity(intent);

                }
                if (position == 3 ){
                    lat= 10.783745;
                    lon=106.705142;
                    place = "5ku Station";

                    Intent intent = new Intent(MainActivity.this,Location.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("place",place);
                    startActivity(intent);

                }
            }
        });


          Cursor c = helper.getAll();
          if(c.getCount()==0){
              isRegister=false;
          }else{
              isRegister=true;
          }


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);

    }

    //There are certain place Menu can be set.Menu can only be set here causethey paased the default and alr constructed menu with these methods alr.

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.register);
        //isRegister=false
        if (isRegister) {
            item.setTitle("UnRegister");



        } else {
            item.setTitle("Register");
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
if(item.getItemId()== id.register) {
    if (isRegister) {


        Message.message(MainActivity.this, "You have successfully unregistered");


        isRegister = false;


    } else {

        Intent intent = new Intent(MainActivity.this, Detail_form.class);
        startActivity(intent);
        isRegister = true;

    }

}
        if(item.getItemId()==R.id.about) {
            if (isRegister) {


                Intent intent = new Intent(MainActivity.this, Infos.class);
                startActivity(intent);
            } else {


                AlertDialog.Builder abuilder = new AlertDialog.Builder(this);
                abuilder.setMessage("You have not registered yet")
                        .setTitle("Alert")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() { //new will auto create for you.
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                AlertDialog alert = abuilder.create();
                alert.show();//every kind of this type of thing got a function or method that need to be written to show.(E.g startActivity)
            }

        }
            if (!isRegister == true) {
                Fchelpermodel helper = new Fchelpermodel(MainActivity.this);
                helper.delete();
            }


            return super.onOptionsItemSelected(item);





    }


    class FoodCourt extends ArrayAdapter<String> {
        Context c;
        String name[];

        String disc[];
        int img[];

        public FoodCourt(Context c, String name[], String disc[], int img[]) {
            super(c, R.layout.fc, R.id.fcName, name);//first 3 is to set out the background thing that you goona insert your data to.
//things that you goona take from outside world and then override to the parnt array adapterBasically things you goona add in
//there is no need to use recycleand bind view cause the number of rows is little and is fxed.Those are used cause they cant gauge the number of data
            this.name = name;
            this.c = c; //basically your trying to link the super to your prev and aft.
            this.img = img;
            this.disc = disc;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//context kena layout inflator servvice lmao
            View row = inflater.inflate(R.layout.fc, parent, false);
            //row is  relative to relativelayout only.Must do for all so they will know.
            TextView fcName = row.findViewById(R.id.fcName);
            ImageView fcImage = row.findViewById(id.fcImage);
            TextView fcDisc = row.findViewById(id.fcDisc);
            fcName.setText(name[position]);
            fcDisc.setText(disc[position]);
            fcImage.setImageResource(img[position]);

            return (row);

        }
    }






}
