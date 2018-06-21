package andy.ham;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by lx on 2018/6/21.
 */

public class Welcome  extends Activity {
    private ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageResource(R.drawable.welcome);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){}
            @Override
            public void onAnimationEnd(Animation animation){
                Intent intent=new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                Welcome.this.finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation){}
        });
    }
}