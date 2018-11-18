package com.eesaaphilips.interviewproject1.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.eesaaphilips.interviewproject1.model.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProductRepository {
    public ProductDao mDao;
    private LiveData<List<Product>> mAllProducts;
    private Executor executor = Executors.newSingleThreadExecutor();

    ProductRepository(Application application) {
        ProductDatabase mDb = ProductDatabase.getInstance(application);
        mDao = mDb.productDao();
        mAllProducts = mDao.getAllPorducts();
    }


    public void insert(final Product product) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insert(product);
            }
        });
    }

    public void delete(final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.delete(id);
            }
        });
    }

    public void deleteAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteAll();
            }
        });
    }

    public LiveData<List<Product>> getmAllProducts() {
        return mAllProducts;
    }

    public Product getById(final int id) {
        return mDao.getById(id);
    }
}
