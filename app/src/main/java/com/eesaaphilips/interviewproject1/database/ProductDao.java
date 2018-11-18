package com.eesaaphilips.interviewproject1.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.eesaaphilips.interviewproject1.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * from product_table ORDER BY id ASC")
    LiveData<List<Product>> getAllPorducts();

    @Query("DELETE FROM product_table")
    int deleteAll();

    @Query("DELETE FROM product_table WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM product_table WHERE id LIKE :Product_id")
    Product getById(int Product_id);

    @Query("SELECT COUNT(*) FROM product_table")
    int getCount();
}
