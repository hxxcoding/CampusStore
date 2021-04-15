package com.store.service.impl;

import com.store.dao.ShopDao;
import com.store.dto.ShopExecution;
import com.store.entity.Shop;
import com.store.enums.ShopStateEnum;
import com.store.service.ShopService;
import com.store.util.ImageUtil;
import com.store.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * 抛出RuntimeException异常 事务回滚
     * @param shop
     * @param shopImgInputStream
     * @return
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        // 判断shop是否包含必须的值
        if(shop == null)
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        try {
            // 给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);  // 添加店铺信息
            if(effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {
                if(shopImgInputStream != null) {
                    // 存储图片
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new RuntimeException("addShopImg error:" + e.getMessage() + "店铺创建失败");
                    }
                    // 更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0) {
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}
