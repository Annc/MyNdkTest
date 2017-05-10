package com.example.dou.myndktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextFirst;
    private EditText mEditTextSecond;
    private Button mButtonAddInt;
    private Button mButtonMulDouble;
    private Button mButtonBigger;
    private Button mButtonAddString;
    private TextView mTextviewResult;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //示范调用native方法
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        mEditTextFirst = (EditText)findViewById(R.id.first_para);
        mEditTextSecond = (EditText)findViewById(R.id.second_para);
        mTextviewResult = (TextView)findViewById(R.id.display_result);

        //计算加法
        mButtonAddInt = (Button)findViewById(R.id.button);
        mButtonAddInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0;
                try { a = Integer.parseInt(String.valueOf(mEditTextFirst.getText())); }
                catch (NumberFormatException e) { }
                int b = 0;
                try { b = Integer.parseInt(String.valueOf(mEditTextSecond.getText())); }
                catch (NumberFormatException e) { }
                //int r = addInt(a, b);
                mTextviewResult.setText("C++库计算" + a + "+" + b +"的结果是：" + addInt(a, b));
            }
        });

        //计算乘法
        mButtonMulDouble = (Button)findViewById(R.id.button2);
        mButtonMulDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //按钮“mulDouble”用来计算左框里的实数和右框里的实数相乘
                //你要是不输入数字，它就当你输入了“0”
                double a = 0;
                try { a = Double.parseDouble(String.valueOf(mEditTextFirst.getText())); }
                catch (NumberFormatException e) { }
                double b = 0;
                try { b = Double.parseDouble(String.valueOf(mEditTextSecond.getText())); }
                catch (NumberFormatException e) { }
                double r = mulDouble(a, b);
                mTextviewResult.setText("C++库计算" + a + "*" + b +"的结果是：" + r);
            }
        });

        //比较大小

        mButtonBigger = (Button)findViewById(R.id.button4);
        mButtonBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a = 0;
                try {
                    a = Float.parseFloat(String.valueOf(mEditTextFirst.getText()));
                } catch (NumberFormatException e) {
                }
                float b = 0;
                try {
                    b = Float.parseFloat(String.valueOf(mEditTextSecond.getText()));
                }catch (NumberFormatException e){}
                boolean r = bigger(a,b);
                mTextviewResult.setText("C++库计算" + a + "大于" + b +"的结果是：" + r);
            }

        });

        //字符串拼接
        mButtonAddString = (Button)findViewById(R.id.button3);
        mButtonAddString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "";
                a = String.valueOf(mEditTextFirst.getText());
                String b = "";
                b = String.valueOf(mEditTextSecond.getText());
                String c;
                c = addString(a,b);
                mTextviewResult.setText("C++库计算" + a + "拼接" + b +"的结果是：" + c);

            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int addInt(int a, int b); //输入整数，输出整数
    public native double mulDouble(double a, double b); //输入实数，输出实数
    public native boolean bigger(float a, float b); //输入float型实数，输出布尔值
    public native String addString(String a, String b); //输入字符串，输出字符串
}
