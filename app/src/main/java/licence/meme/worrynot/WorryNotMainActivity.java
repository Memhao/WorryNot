package licence.meme.worrynot;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import licence.meme.worrynot.main.MainActivity;


public class WorryNotMainActivity extends Activity  {


    private ProgressBar progressBar;
    private boolean bActive;
    private static final int TIMER_RUNTIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worry_not);


        progressBar = (ProgressBar)findViewById(R.id.progress_main_pb);
        final Thread timerThread = new Thread() {
            @Override
            public void run() {
                bActive = true;
                try {
                    int waited = 0;
                    while(bActive && (waited < TIMER_RUNTIME)) {
                        sleep(200);
                        if(bActive) {
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                } catch(InterruptedException e) {
                } finally {
                    onContinue();
                }
            }
        };
        timerThread.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void updateProgress(final int timePassed) {
        if(null != progressBar) {
            final int progress = progressBar.getMax() * timePassed / TIMER_RUNTIME;
            progressBar.setProgress(progress);
        }
    }

    public void onContinue() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}

