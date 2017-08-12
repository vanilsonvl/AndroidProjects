package br.com.vanilson.coursedevmedia;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle args = getIntent().getBundleExtra("sign");
        if (args != null) {
            Sign signReceive = (Sign) args.getSerializable("result");
            int imageResource = getResources()
                    .getIdentifier(signReceive.getImage(), null, getPackageName());
            Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            ImageView imageSign = (ImageView) findViewById(R.id.imgSign);
            imageSign.setImageDrawable(res);
            TextView result = (TextView) findViewById(R.id.textSign);
            TextView dates = (TextView) findViewById(R.id.textDate);
            result.setText(signReceive.getName());
            dates.setText("de " + signReceive.getIniDate() + "/" + signReceive.getIniMonth()
             + " até " + signReceive.getEndDate() + "/" + signReceive.getEndMonth());
            Button back = (Button) findViewById(R.id.buttonBack);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
}
