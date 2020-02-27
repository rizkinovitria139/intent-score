package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TODO
    //Fitur Main Activity

    private EditText homeTeamInput;
    private EditText awayTeamInput;

    public static final String HOME_KEY = "home";
    public static final String AWAY_KEY = "away";
    public static final String HOME_IMG_KEY = "homeImg";
    public static final String AWAY_IMG_KEY = "awayImg";

    private static final String TAG = MatchActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE=1&2;

    private ImageView avatar1, avatar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        homeTeamInput = (EditText) findViewById(R.id.home_team);
        awayTeamInput = (EditText) findViewById(R.id.away_team);

        avatar1 = (ImageView) findViewById(R.id.home_logo);
        avatar2 = (ImageView) findViewById(R.id.away_logo);

    }

    //3. Ganti Logo Home Team
    //4. Ganti Logo Away Team

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == 1){
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
        if(requestCode == 2){
            if(data != null){
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatar2.setImageBitmap(bitmap);
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

        //1. Validasi Input Home Team
        //2. Validasi Input Away Team

        if((home).equals("") || (away).equals("")){
            Toast.makeText(getApplicationContext(), "Isi nama Team!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, MatchActivity.class);
            avatar1.buildDrawingCache();
            avatar2.buildDrawingCache();

            Bitmap homeTeamImage = avatar1.getDrawingCache();
            Bitmap awayTeamImage = avatar2.getDrawingCache();

            Bundle extras = new Bundle();
            extras.putParcelable(HOME_IMG_KEY, homeTeamImage);
            extras.putParcelable(AWAY_IMG_KEY, awayTeamImage);

//            intent.putExtra(extras);
            intent.putExtra(HOME_KEY, home);
            intent.putExtra(AWAY_KEY, away);

            startActivity(intent);
        }
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
