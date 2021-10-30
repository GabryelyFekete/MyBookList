package com.example.mybooklistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaLogout extends AppCompatActivity {
    //Iniciando variavél
    DrawerLayout drawerLayout;

    private TextView nomeUsuario,emailUsuario;
    private Button bt_logout;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_logout);

        //Atribuir variavél
        drawerLayout = findViewById(R.id.drawer_layout);


        getSupportActionBar().hide();
        IniciarComponentes();

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaLogout.this,firstpageapp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ClickMenu(View view){
    //Abrir drawer
        Tela_de_Navegacao.openDrawer(drawerLayout);
    }

    public void Clicklogo(View view){
        //Fechar drawer
        Tela_de_Navegacao.closeDrawer(drawerLayout);
    }

    public void ClickInicio(View view){
        //Redirecionar activity para inicio
        Tela_de_Navegacao.redirectActivity(this,Tela_de_Navegacao.class);
    }

    public void ClickCadastro(View view){
        //Redirecionar activity para cadastro
        Tela_de_Navegacao.redirectActivity(this, Main.class);
    }

    public void ClickLogout(View view){
        //Recriar activity
        recreate();
    }

    public void ClickSobre(View view){
        //Fechar app
        Tela_de_Navegacao.redirectActivity(this,Sobre.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Fechar drawer
        Tela_de_Navegacao.closeDrawer(drawerLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = bd.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUsuario.setText(email);
                }

            }
        });
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.textusername);
        emailUsuario = findViewById(R.id.textuseremail);
        bt_logout = findViewById(R.id.bt_logout);
    }

}
