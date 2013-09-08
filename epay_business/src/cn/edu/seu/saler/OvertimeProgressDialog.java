package cn.edu.seu.saler;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class OvertimeProgressDialog extends ProgressDialog{

	public static final String TAG="ProgressDialog";
	private long mTimeOut=0;
	private Timer mTimer=null;
	private OnTimeOutListener mTimeOutListener=null;
	private Handler mHandler=new Handler(){
		public void handlerMessage(Message msg)
		{
			if(mTimeOutListener!=null)
				mTimeOutListener.onTimeOut(OvertimeProgressDialog.this);
			dismiss();
		}
	};
	public interface OnTimeOutListener
	{
		abstract public void onTimeOut(ProgressDialog dialog);
	}
	public OvertimeProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setTimeOut(long t,OnTimeOutListener timeOutListener)
	{
		mTimeOut=t;
		if(timeOutListener!=null)
			this.mTimeOutListener=timeOutListener;
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(mTimeOut!=0)
			mTimer=new Timer();
		TimerTask timerTast=new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=mHandler.obtainMessage();
				mHandler.sendMessage(msg);
			}
			
		};
		mTimer.schedule(timerTast, mTimeOut);
	}
	public static OvertimeProgressDialog createProgressDialog(Context context,long time,OnTimeOutListener listener)
	{
		OvertimeProgressDialog progressDialog=new OvertimeProgressDialog(context);
		if(time!=0)
		{
			progressDialog.setTimeOut(time, listener);
		}
		return progressDialog;
	}

}
