package com.eesaaphilips.interviewproject1.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.eesaaphilips.interviewproject1.model.Product;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DescriptionViewModel extends AndroidViewModel {

    public static ProductRepository mRepository;
    public MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
    private Executor executor = Executors.newSingleThreadExecutor();

    private Product product = null;

    public DescriptionViewModel(Application application) {
        super(application);
        mRepository = new ProductRepository(getApplication());
    }

    public Product loadData(int productId) {
        return mRepository.getById(productId);
    }
}
