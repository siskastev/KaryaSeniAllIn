package stevani.siska.karyaseni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

    ImageView gambar;
    Button back;

    public static  final String GET_GAMBAR ="getgambar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        gambar = findViewById(R.id.resGambar);
        back = findViewById(R.id.btnBack);

        if (getIntent().hasExtra(GET_GAMBAR)){
            Bitmap b = getIntent().getParcelableExtra(GET_GAMBAR);
            gambar.setImageBitmap(b);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(kembali);
            }
        });
    }
}
