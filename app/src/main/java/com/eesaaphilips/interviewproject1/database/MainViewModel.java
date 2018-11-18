package com.eesaaphilips.interviewproject1.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.eesaaphilips.interviewproject1.model.Product;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public ProductRepository mRepository;
    private LiveData<List<Product>> mAllProducts;

    /**
     * Constructor: creates new Repository and sets mAllProducts
     *
     * @param application passed application used to instantiate a new {@link ProductRepository}
     */
    public MainViewModel(Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllProducts = mRepository.getmAllProducts();
    }

    //returns all the products retrieved from the repository using LiveData
    public LiveData<List<Product>> getmAllProducts() {
        return mAllProducts;
    }

    //basic room database operation handling
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