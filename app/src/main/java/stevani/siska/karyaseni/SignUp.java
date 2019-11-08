package stevani.siska.karyaseni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText email,pass;
    Button regist;

    private FirebaseAuth mAuth;
    String TAG = "register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.editEmailUp);
        pass = findViewById(R.id.editPassUp);
        regist = findViewById(R.id.buttonUp);

        mAuth = FirebaseAuth.getInstance();

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inputemail = email.getText().toString();
                final String inputpass = pass.getText().toString();

                //INI FIREBASE
                mAuth.createUserWithEmailAndPassword(inputemail, inputpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignUp.this, "Authentication Succes.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(SignUp.this,SigninActivity.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
                //ini adalah akhir firebase

            }
        });
    }
}
