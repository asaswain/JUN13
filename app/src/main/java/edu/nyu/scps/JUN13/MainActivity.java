package edu.nyu.scps.JUN13;


import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {

    SketchPadView sketchPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout sketchPadLayout = (RelativeLayout) findViewById(R.id.paper);

        // build views for each bar of the bar graph

//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, // width
//                ViewGroup.LayoutParams.MATCH_PARENT  //height
//        );

        sketchPad = new SketchPadView(MainActivity.this);
        sketchPadLayout.addView(sketchPad);

        // create a listener for the vertical seekbar
        SeekBar seekBar;
        seekBar = (SeekBar) findViewById(R.id.locationX);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = (float) (100 - progress) / 100f;
                sketchPadLayout.setScaleX(scale);
                Log.d("XTag", "progress = " + progress);
                Log.d("XTag", "scale = " + scale);
            }
        });

        // create a listener for the horizontal seekbar
        seekBar = (SeekBar) findViewById(R.id.locationY);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = (float) (100 - progress) / 100f;
                sketchPadLayout.setScaleY(scale);
                Log.d("YTag", "progress = " + progress);
                Log.d("YTag", "scale = " + scale);
            }

        });

        // programmatically create views for each color and insert into palette LinearLayout field
        int colorList[] = new int[]{
                R.color.red,
                R.color.yellow,
                R.color.orange,
                R.color.green,
                R.color.blue,
                R.color.purple,
                R.color.black,
        };

        final LinearLayout palette = (LinearLayout) findViewById(R.id.palette);
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int pixelWidth = displayMetrics.widthPixels;

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                pixelWidth / colorList.length, // width
                ViewGroup.LayoutParams.MATCH_PARENT  //height
        );

        MyOnClickListener myOnClickListener = new MyOnClickListener();

        // this is just a slightly fancier version of a for loop
        for (int i : colorList) {
            View colorView = new View(MainActivity.this);
            colorView.setBackgroundColor(resources.getColor(colorList[i]));
            colorView.setLayoutParams(layoutParams2);
            palette.addView(colorView);
            // set an onClickListener for all the views in the palette to enable us to change the paint color
            colorView.setOnClickListener(myOnClickListener);
        }
    }

    class MyOnClickListener implements View.OnClickListener {

        // when user clicks on a color sample, set color of paint to match
        @Override
        public void onClick(View v) {
            ColorDrawable backgroundColor = (ColorDrawable) v.getBackground();
            sketchPad.setPaintColor(backgroundColor.getColor());
        }

    }

}
