package edu.nyu.scps.JUN13;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View paperView = findViewById(R.id.paper);

        // I tried to have the horizontal and vertical seekbars draw lines in the paperView field
        // but I couldn't figure out how to get the canvas and onDraw method draw lines on the field
        // so instead of just resized the field to make sure my seekbars were working correctly

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
                paperView.setScaleX(scale);
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
                paperView.setScaleY(scale);
                Log.d("YTag", "progress = " + progress);
                Log.d("YTag", "scale = " + scale);
            }

        });

        // create clickListeners for each paint color
        int colorList[] = new int[] {
            R.id.Yellow,
            R.id.Orange,
            R.id.Green,
            R.id.Blue,
            R.id.Purple,
            R.id.Black,
        };

        for(int i = 0; i < colorList.length; ++i) {
            MyOnClickListener myOnClickListener = new MyOnClickListener();
            findViewById(colorList[i]).setOnClickListener(myOnClickListener);
        }
    }

    class MyOnClickListener implements View.OnClickListener {

        // when user clicks on a color sample, set color of paper to match

        @Override
        public void onClick(View v) {
            Drawable backgroundColor = v.getBackground();
            View paperView = findViewById(R.id.paper);
            paperView.setBackground(backgroundColor);
        }

    }


    private int intResult;

    private int getInt(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        intResult = Integer.parseInt(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad integer " + string, Toast.LENGTH_LONG);
                        toast.show();
                        intResult = 0;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return intResult;
    }

    private long longResult;

    private long getLong(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        longResult = Long.parseLong(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad long " + string, Toast.LENGTH_LONG);
                        toast.show();
                        longResult = 0L;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return longResult;
    }

    private boolean booleanResult;

    private boolean getBoolean(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        booleanResult = Boolean.parseBoolean(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad boolean " + string, Toast.LENGTH_LONG);
                        toast.show();
                        booleanResult = false;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return booleanResult;
    }

    private float floatResult;

    private float getFloat(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        floatResult = Float.parseFloat(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad float " + string, Toast.LENGTH_LONG);
                        toast.show();
                        floatResult = 0.0f;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return floatResult;
    }


    private double doubleResult;

    private double getDouble(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    String string = editable.toString();
                    try {
                        doubleResult = Double.parseDouble(string);
                    } catch (NumberFormatException numberFormatException) {
                        Toast toast = Toast.makeText(MainActivity.this, "bad double " + string, Toast.LENGTH_LONG);
                        toast.show();
                        doubleResult = 0.0;
                    }

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return doubleResult;
    }

    private String stringResult;

    private String getString(String title, String message) {

        //A builder object can create a dialog object.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        //This inflater reads the dialog.xml and creates the objects described therein.
        //Pass null as the parent view because it's going in the dialog layout.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //Must be final to be mentioned in the anonymous inner class.
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(view);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = editText.getText();
                    stringResult = editable.toString();

                    //Sending this message will break us out of the loop below.
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
                return false;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }

        alertDialog.dismiss();
        return stringResult;
    }

    //The handleMessage method of this object will be called when we call the sendMessage method of
    //this object.  It throws an exception to break us out of the infinite loops below.

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            throw new RuntimeException();
        }
    };

    private void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Sending this message will break us out of the loop below.
                Message message = handler.obtainMessage();
                handler.sendMessage(message);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Loop until the user presses the EditText's Done button.
        try {
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            // do nothing
        }
    }

    private void display(String title, int i) {
        display(title, Integer.toString(i));
    }

    private void display(String title, long l) {
        display(title, Long.toString(l));
    }

    private void display(String title, boolean b) {
        display(title, Boolean.toString(b));
    }

    private void display(String title, float f) {
        display(title, Float.toString(f));
    }

    public void display(String title, double d) {
        display(title, Double.toString(d));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
