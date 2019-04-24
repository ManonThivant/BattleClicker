package com.example.heitzmaa.battleclicker1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ClicsActivity extends AppCompatActivity {
    private double money = 0;
    private double click = 1;
    private double price = 20;
    private TextView mShowMoney;
    private TextView mShowPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clics);
        mShowMoney = (TextView) findViewById(R.id.show_count);
        mShowPrice  = (TextView) findViewById(R.id.price_upgrade);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        money += click ;
        if (mShowMoney != null)
            mShowMoney.setText(Double.toString(money));
    }

    public void upgradeClick(View view) {
        if (money >= price ){
            money = money - price;
            click = click*1.5;
            price = price*1.5;
            if (mShowMoney != null)
                mShowMoney.setText(Double.toString(money));
            if (mShowPrice != null)
                mShowPrice.setText(Double.toString(price));
        }

    }
}
