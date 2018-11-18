package com.eesaaphilips.interviewproject1.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.eesaaphilips.interviewproject1.model.Product;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public ProductRepository mRepository;
    private LiveData<List<Product>> mAllProducts;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllProducts = mRepository.getmAllProducts();
    }

    public LiveData<List<Product>> getmAllProducts() {
        return mAllProducts;
    }

    public void insert(Product product) {
        mRepository.insert(product);
    }

    public void delete(int id) {
        mRepository.delete(id);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}