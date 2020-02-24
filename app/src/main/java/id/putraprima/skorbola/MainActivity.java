package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @NotEmpty
    private EditText homeTeamInput;
    @NotEmpty
    private EditText awayTeamInput;

    public static final String HOME_KEY = "home";
    public static final String AWAY_KEY = "away";

    private static final String TAG = MatchActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    private ImageView avatar1, avatar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeamInput = (EditText) findViewById(R.id.home_team);
        awayTeamInput = (EditText) findViewById(R.id.away_team);

        avatar1 = (ImageView) findViewById(R.id.home_logo);
        avatar2 = (ImageView) findViewById(R.id.away_logo);
        //TODO
        //Fitur Main Activity

        //2. Validasi Input Away Team
    }

    //1. Validasi Input Home Team

    //3. Ganti Logo Home Team
    //4. Ganti Logo Away Team

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatar1.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {
        String home = homeTeamInput.getText().toString();
        String away = awayTeamInput.getText().toString();

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra(HOME_KEY, home);
        intent.putExtra(AWAY_KEY, away);
        startActivity(intent);
    }

    public void handleAva1(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void handleAva2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
}
