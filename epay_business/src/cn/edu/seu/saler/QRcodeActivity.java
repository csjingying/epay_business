package cn.edu.seu.saler;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import cn.edu.seu.main.MainActivity;
import cn.edu.seu.main.R;

public class QRcodeActivity extends Activity {

	private ImageView img;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode);
		img = (ImageView)findViewById(R.id.imageView1);
		String info = MainActivity.person.getUsername() + ":" + MainActivity.person.getBluetoothmac() + ":individual"; 
		Bitmap bm;
		try {
			bm = EncodingHandler.createQRCode(info , 400);
			img.setImageBitmap(bm);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
