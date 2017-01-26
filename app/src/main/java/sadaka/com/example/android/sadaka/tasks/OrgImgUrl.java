package sadaka.com.example.android.sadaka.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by aisha on 8/14/2016.
 */
public class OrgImgUrl extends AsyncTask<String, Void, Bitmap>
{
    ImageView img;
    String url;
    public OrgImgUrl(ImageView img,String url){
        this.url=url;
        this.img=img;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap=null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        img.setImageBitmap(result);
    }
}
