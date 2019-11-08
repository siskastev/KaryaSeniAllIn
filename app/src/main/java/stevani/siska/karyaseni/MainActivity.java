package stevani.siska.karyaseni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    Button tambah, logout;
    ImageView inputgambr;

    private static final int CAPTURE_CAMERA = 1;



    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambah= findViewById(R.id.tambahGambar);
        logout = findViewById(R.id.btnSignout);
        inputgambr = findViewById(R.id.inputGambar);

        mAuth = FirebaseAuth.getInstance();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if ( g.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(g,CAPTURE_CAMERA);
                }

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You have Logout",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent go = new Intent(MainActivity.this,SigninActivity.class);
                startActivity(go);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK &&  requestCode == CAPTURE_CAMERA) {
            Bundle extras = data.getExtras();
            Bitmap b = (Bitmap) data.getExtras().get("data");
            Intent i = new Intent(getApplicationContext(), ResultActivity.class);
            i.putExtra(ResultActivity.GET_GAMBAR,b);
            startActivity(i);
        }
    }


}
