package com.example.data.repository

import com.example.data.database.AppDatabase
import com.example.data.model.database.UserDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserLocalDataStoreImpl constructor(
    private val database: AppDatabase
) : UserLocalDataStore {

    override suspend fun saveUsers(users: List<UserDatabaseModel>): Flow<List<Long>> {
        return flowOf(database.userDao().insertUsers(users))
    }

    override suspend fun getUsers(): Flow<List<UserDatabaseModel>> {
        return database.userDao().loadUsers()
    }
}