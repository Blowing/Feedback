package com.wujie.feedback.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wujie.feedback.R;
import com.wujie.feedback.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_layout)
    LinearLayout mainLayout;


    private List<View> layoutList = new ArrayList<>();
    private List<View> deleteList = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            addPictureLayout(msg.arg1, 0);
        }
    };

    private int mainLyoutWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        Log.e("layout", "width"+width +"height:"+ height);

        mainLayout.post(new Runnable() {
            @Override
            public void run() {
                Log.e("layout1", "width"+mainLayout.getWidth() +"height:"+ mainLayout.getHeight());
                mainLyoutWidth = Utils.px2dip(getApplicationContext(), mainLayout.getWidth());
                Message message = new Message();
                message.arg1 = mainLyoutWidth;
                handler.sendMessage(message);
            }
        });


    }


    private void addPictureLayout(int width, int id) {

        int mWidth = 16;
        int lWidth = width / 4 - mWidth;

        int addWidth = lWidth - 17/2;
        RelativeLayout relativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams reLayoutParams = new LinearLayout.LayoutParams(Utils.dip2px(this,lWidth),
                Utils.dip2px(this,lWidth));

       // reLayoutParams.setMargins(0, 0, id == 3 ? 0 :Utils.dip2px(this, mWidth), 0);

        reLayoutParams.setMargins(0, 0, Utils.dip2px(this, mWidth), 0);
        //reLayoutParams.rightMargin = Utils.dip2px(this,mWidth);

        relativeLayout.setLayoutParams(reLayoutParams);



        ImageView pictureView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Utils.dip2px(this,addWidth), Utils.dip2px(this,addWidth));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pictureView.setLayoutParams(layoutParams);
        pictureView.setImageResource(R.mipmap.feedback_icon_add_picture);
        pictureView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        relativeLayout.addView(pictureView);

        final ImageView deleteView = new ImageView(this);
        RelativeLayout.LayoutParams dlayoutParams = new RelativeLayout.LayoutParams(Utils.dip2px(this,17), Utils.dip2px(this,17));
        dlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        dlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        dlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        deleteView.setLayoutParams(dlayoutParams);
        deleteView.setImageResource(R.mipmap.feedback_icon_delete);
        deleteView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        relativeLayout.addView(deleteView);
        deleteView.setTag(id);
        deleteList.add(deleteView);
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                mainLayout.removeView(layoutList.get(id));
                layoutList.remove(id);
                deleteList.remove(id);
                for (int i = 0; i < deleteList.size(); i++) {
                    deleteList.get(i).setTag(i);
                }
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutList.size() < 4)  {
                    addPictureLayout(mainLyoutWidth, layoutList.size());
                }
            }
        });

        layoutList.add(relativeLayout);

        mainLayout.addView(relativeLayout);

    }


}
