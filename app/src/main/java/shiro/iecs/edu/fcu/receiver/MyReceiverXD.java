package shiro.iecs.edu.fcu.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class MyReceiverXD extends BroadcastReceiver {

    static int id = 70000;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Context理解為「上下文」或者「場景」: 只需在哪個環境中運行?!
        //收到監聽式 執行的程式碼

        //因為是用包裹送來的 那就用包裹收 ,從貨車(intent)上收下來
        String msg =  intent.getStringExtra("TEXT_KEY");
        Toast.makeText(context, "這是MyReceiverXD 所發出的", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        Intent newintent = new Intent();
        newintent.setClass(context, ActivityNotification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newintent ,0);

        Notification notify = null;
        if( Build.VERSION.SDK_INT>=11 ){
            notify = newNotification(context, pendingIntent,
                    "(New) Broadcast is received.", msg);
        }else {
            notify = oldNotification(context, pendingIntent,
                    "(Old) Broadcast is received.", msg);
        }

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id++, notify);
    }

    @SuppressWarnings("deprecation")
    private Notification oldNotification(
            Context context, PendingIntent pi,
            String title, String msg){

        Notification notify = new Notification(
                R.mipmap.ic_launcher, title,
                System.currentTimeMillis());
        //notify.setLatestEventInfo(context, title, msg, pi);
        return notify;
    }

    @SuppressLint("NewApi")
    private Notification newNotification(
            Context context, PendingIntent pi,
            String title, String msg){

        Notification.Builder builder =
                new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setTicker(msg);
        builder.setAutoCancel(true); //點通知是否清除
        builder.setWhen(System.currentTimeMillis());
        Notification notify = builder.build();
        return notify;
    }


}
