package sp.com;

import android.content.Context;
import android.widget.Toast;

public class Message {
   public static void message(Context context, String messages){
       Toast.makeText(context,messages,Toast.LENGTH_LONG).show();
   }
}
