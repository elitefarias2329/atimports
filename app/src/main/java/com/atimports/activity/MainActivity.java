package com.atimports.activity;


import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.model.Lote;
import com.atimports.recycler.LoteAdapter;
import com.atimports.repository.LoteRepository;
import com.atimports.utils.PhotoUtils;
import com.atimports.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLeiloes;
    private RecyclerView.Adapter rvAdapter;
    private List<Lote> listaLeiloes;

    private FloatingActionButton fab;

    private LoteRepository loteRepository;
    private CompositeDisposable compositeDisposable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLeiloes = findViewById(R.id.rv_lotes);
        fab = findViewById(R.id.fab_add);

        compositeDisposable = new CompositeDisposable();
        listaLeiloes = new ArrayList<>();

        popularListViewLeiloes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvLeiloes.setLayoutManager(layoutManager);

        //FLOAT BUTTON
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        concederPermissoes();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextChange(String newText) {

                //Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();

                if(newText.length()>=3){
                    filtrarListViewLeiloes(newText);
                }
                else if(newText.length()==0){
                    popularListViewLeiloes();
                }



                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                return true;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void concederPermissoes(){

        int PERMISSION_ALL = 1;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED                ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                                                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                                                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_ALL);
        }
    }



    private void filtrarListViewLeiloes(String produto){

        //DATABASE
        loteRepository = LoteRepository.getInstance(MainActivity.this);

        Disposable disposable = loteRepository.getLoteByProduto(produto)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<List<Lote>>() {
                            @Override
                            public void accept(List<Lote> leiloes) throws Exception {

                                listaLeiloes.clear();
                                listaLeiloes.addAll(leiloes);
                                rvAdapter = new LoteAdapter(MainActivity.this,listaLeiloes);
                                rvLeiloes.setAdapter(rvAdapter);
                                rvAdapter.notifyDataSetChanged();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

        compositeDisposable.add(disposable);

    }







    private void popularListViewLeiloes(){

        //DATABASE
        loteRepository = LoteRepository.getInstance(MainActivity.this);

        Disposable disposable = loteRepository.getAllLote()
                                              .observeOn(AndroidSchedulers.mainThread())
                                              .subscribeOn(Schedulers.io())
                                              .subscribe(
                                                      new Consumer<List<Lote>>() {
                                                            @Override
                                                            public void accept(List<Lote> leiloes) throws Exception {

                                                                listaLeiloes.clear();
                                                                listaLeiloes.addAll(leiloes);
                                                                rvAdapter = new LoteAdapter(MainActivity.this,listaLeiloes);
                                                                rvLeiloes.setAdapter(rvAdapter);
                                                                rvAdapter.notifyDataSetChanged();
                                                            }
                                                        },
                                                        new Consumer<Throwable>() {
                                                            @Override
                                                            public void accept(Throwable throwable) throws Exception {
                                                               Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                              );

        compositeDisposable.add(disposable);

    }





    public void detalharLote(final Lote lote){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(Constantes.DETALHE_LOTE, lote);
        intent.putExtras(b);
        startActivity(intent);
    }



    public void deleteLote(final Lote lote){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Excluir Item");
        builder.setMessage("Tem certeza que deseja excluir este item?");
        builder.setCancelable(false);


        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Observable.create(

                        new ObservableOnSubscribe<Object>() {

                            @Override
                            public void subscribe(ObservableEmitter<Object> emitter){

                                try {

                                    //DATABASE
                                    loteRepository = LoteRepository.getInstance(MainActivity.this);

                                    if(!Utils.isBlank(lote.getPathFotoProduto())){
                                        PhotoUtils.deleteImage(lote.getPathFotoProduto(), MainActivity.this);
                                    }

                                    loteRepository.deleteLote(lote);
                                    emitter.onComplete();
                                }
                                catch (Exception e) {
                                    emitter.onError(e);
                                }
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe();

                Toast.makeText(getApplicationContext(), "Item excluído com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();

    }



}

