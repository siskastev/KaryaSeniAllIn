package stevani.siska.karyaseni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button submit;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.editPassForget);
        submit = findViewById(R.id.buttonForget);

        auth=FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailin = email.getText().toString();

                auth.sendPasswordResetEmail(emailin).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            Intent ri = new Intent(ForgotPassword.this,SigninActivity.class);
                            startActivity(ri);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
