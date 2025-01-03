package com.example.service

import com.example.dao.StoreDao
import com.example.data.Store

class StoreService (private val storeDao: StoreDao) {

    fun getAllStores(): List<Store> = storeDao.getAllStore()

    fun getStoreById(id: Long): Store? = storeDao.getStoreById(id)

}
