package com.store.dao;

import com.store.BaseTest;
import com.store.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(2, shopCategoryList.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParentId(parentCategory.getShopCategoryId());
        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }
}
