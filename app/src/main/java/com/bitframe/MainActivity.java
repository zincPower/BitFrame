package com.bitframe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bitframe.bit.activity.FragmentMainActivity;
import com.bitframe.bit.activity.ToolbarDemoActivity;
import com.bitframe.web.WebActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_fragment_demo).setOnClickListener(this);
        findViewById(R.id.tv_toolbar).setOnClickListener(this);
        findViewById(R.id.tv_web_view).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_fragment_demo:
                intent = new Intent(this, FragmentMainActivity.class);
                break;
            case R.id.tv_toolbar:
                intent = new Intent(this, ToolbarDemoActivity.class);
                break;
            case R.id.tv_web_view:
                intent = new Intent(this, WebActivity.class);
                break;
        }
        startActivity(intent);
    }

}
