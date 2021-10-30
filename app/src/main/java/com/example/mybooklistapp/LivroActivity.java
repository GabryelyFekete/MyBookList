package com.example.mybooklistapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LivroActivity extends Activity {
    private EditText titulo,autor,editora,colecao,numpaginas,nota,recomendacao,recomendaria,genero,filme,personagemfavorito,capitulo;
    String[] mensagem = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    private Button btsalvar, btCancelar;
    String usuarioID;
    FirebaseFirestore bd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadlivro);

        //Objects getSupportActionBar().hide();
        InicarComponentes();

        btCancelar.setOnClickListener(view -> {

            Intent i = new Intent(this, Main.class);
            startActivity(i);
            finish();

        });
        btsalvar.setOnClickListener(view -> {

            String T = titulo.getText().toString();
            String A = autor.getText().toString();
            String ED = editora.getText().toString();
            String COL = colecao.getText().toString();
            String NPAG = numpaginas.getText().toString();
            String NT = nota.getText().toString();
            String RCAO = recomendacao.getText().toString();
            String RRIA = recomendaria.getText().toString();
            String GEN = genero.getText().toString();
            String FLM = filme.getText().toString();
            String PFAV = personagemfavorito.getText().toString();
            String CAP = capitulo.getText().toString();


            if(T.isEmpty() || A.isEmpty() || ED.isEmpty() || COL.isEmpty() || NPAG.isEmpty() || NT.isEmpty() || RCAO.isEmpty() || RRIA.isEmpty()
                    || GEN.isEmpty() || FLM.isEmpty() || PFAV.isEmpty() || CAP.isEmpty()){
                Snackbar snackbar = Snackbar.make(view, mensagem[0],Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }else{
                SalvarLivro();

                Snackbar snackbar = Snackbar.make(view, mensagem[1],Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();

                titulo.setText("");
                autor.setText("");
                editora.setText("");
                colecao.setText("");
                numpaginas.setText("");
                nota.setText("");
                recomendacao.setText("");
                recomendaria.setText("");
                genero.setText("");
                filme.setText("");
                personagemfavorito.setText("");
                capitulo.setText("");
            }
        });
    }
    private void SalvarLivro() {

        String T = titulo.getText().toString();
        String A = autor.getText().toString();
        String ED = editora.getText().toString();
        String COL = colecao.getText().toString();
        String NPAG = numpaginas.getText().toString();
        String NT = nota.getText().toString();
        String RCAO = recomendacao.getText().toString();
        String RRIA = recomendaria.getText().toString();
        String GEN = genero.getText().toString();
        String FLM = filme.getText().toString();
        String PFAV = personagemfavorito.getText().toString();
        String CAP = capitulo.getText().toString();

        bd = FirebaseFirestore.getInstance();

        Map<String,Object> livros = new HashMap<>();
        livros.put("T", T);
        livros.put("A", A);
        livros.put("ED", ED);
        livros.put("COL", COL);
        livros.put("NPAG", NPAG);
        livros.put("NT", NT);
        livros.put("RCAO", RCAO);
        livros.put("RRIA", RRIA);
        livros.put("GEN", GEN);
        livros.put("FLM", FLM);
        livros.put("PFAV", PFAV);
        livros.put("CAP", CAP);


        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CollectionReference collectionReference = bd.collection("Livros por Leitor").document(usuarioID).collection("Livros lidos");
        collectionReference.add(livros).addOnSuccessListener(unused -> Log.d("bd","Sucesso ao salvar os dados"))
                .addOnFailureListener(e -> Log.d("bd_erro","Erro ao salvar os dados" + e.toString()));
    }

    public void onClickSalvar (View view){
        Livro liv = new Livro();

        liv.setTitulo(titulo.getText().toString());
        liv.setAutor(autor.getText().toString());
        liv.setEditora(editora.getText().toString());
        liv.setColecao(colecao.getText().toString());
        liv.setNumpaginas(Integer.valueOf(numpaginas.getText().toString()));
        liv.setNota(Integer.valueOf(nota.getText().toString()));
        liv.setRecomendacao(recomendacao.getText().toString());
        liv.setRecomendaria(recomendaria.getText().toString());
        liv.setGenero(genero.getText().toString());
        liv.setFilme(filme.getText().toString());
        liv.setPersonagemfavorito(personagemfavorito.getText().toString());
        liv.setCapitulo(Integer.valueOf(capitulo.getText().toString()));

    }private void InicarComponentes(){
        titulo = findViewById(R.id.edTitulo);
        autor = findViewById(R.id.edAutor);
        editora = findViewById(R.id.edEditora);
        colecao = findViewById(R.id.edColecao);
        numpaginas = findViewById(R.id.edNumpaginas);
        nota = findViewById(R.id.edNota);
        recomendacao = findViewById(R.id.edRecomendacao);
        recomendaria = findViewById(R.id.edRecomendaria);
        genero = findViewById(R.id.edGenero);
        filme = findViewById(R.id.edFilme);
        personagemfavorito = findViewById(R.id.edPersonagemFavorito);
        capitulo = findViewById(R.id.edCapitulos);}
}
