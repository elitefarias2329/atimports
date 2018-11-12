package com.atimports.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.model.Lote;
import com.atimports.recycler.LoteAdapter;
import com.atimports.repository.LoteRepository;

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

