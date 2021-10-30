package com.example.mybooklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {
    private EditText edit_nome, edit_email, edit_senha;
    private Button bt_entrar;
    String[] mensagens = {"Preencha todos os espaços, por favor!", "Seu cadastro foi um sucesso!"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    CadastrarUsuario(view);

                }
            }
        });
    }

    private void CadastrarUsuario(View view) {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    SalvarDadosUsuario();


                    Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "insira uma senha com no mínimo 6 dígitos";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esse já foi cadastrado!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail iválido!";
                    } catch (Exception e) {
                        erro = "Erro ao cadastar usuário!";
                    }

                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }

    private void SalvarDadosUsuario(){
        String nome = edit_nome.getText().toString();

        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = bd.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("bd","Dados salvos com sucesso!");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("bd_error","Ocorreu um erro ao salvar os dados!" + e.toString());

            }
        });
    }

    private void IniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
    }
}
