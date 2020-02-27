package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView winTeamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winTeamText = findViewById(R.id.winText);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            winTeamText.setText(extras.getString(MatchActivity.WIN_KEY));
        }
    }
}
