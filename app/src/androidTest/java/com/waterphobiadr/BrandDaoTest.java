package com.waterphobiadr;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.waterphobiadr.data.local.database.BrandDatabase;
import com.waterphobiadr.data.local.database.table.Brand;
import com.waterphobiadr.data.local.database.dao.BrandDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4.class)
public class BrandDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private BrandDao mBrandDao;
    private BrandDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, BrandDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mBrandDao = mDb.brandDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetBrand() throws Exception {
        Brand brand = new Brand("brand");
        mBrandDao.insert(brand);
        List<Brand> allBrands = LiveDataTestUtil.getValue(mBrandDao.getAlphabetizedBrands());
        assertEquals(allBrands.get(0).getBrand(), brand.getBrand());
    }

    @Test
    public void getAllBrands() throws Exception {
        Brand brand = new Brand("aaa");
        mBrandDao.insert(brand);
        Brand brand2 = new Brand("bbb");
        mBrandDao.insert(brand2);
        List<Brand> allBrands = LiveDataTestUtil.getValue(mBrandDao.getAlphabetizedBrands());
        assertEquals(allBrands.get(0).getBrand(), brand.getBrand());
        assertEquals(allBrands.get(1).getBrand(), brand2.getBrand());
    }

    @Test
    public void deleteAll() throws Exception {
        Brand brand = new Brand("brand");
        mBrandDao.insert(brand);
        Brand brand2 = new Brand("brand2");
        mBrandDao.insert(brand2);
        mBrandDao.deleteAll();
        List<Brand> allBrands = LiveDataTestUtil.getValue(mBrandDao.getAlphabetizedBrands());
        assertTrue(allBrands.isEmpty());
    }
}
