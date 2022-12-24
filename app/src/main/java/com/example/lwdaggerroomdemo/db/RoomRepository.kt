package com.example.lwdaggerroomdemo.db

import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: AppDao) {

    fun getRecords(): List<UserEntity> {
        return appDao.getRecords()
    }

    fun insertRecord(userEntity: UserEntity) {
        appDao.insertRecord(userEntity)
    }
}