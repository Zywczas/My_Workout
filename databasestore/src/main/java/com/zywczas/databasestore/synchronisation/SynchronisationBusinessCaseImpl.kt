package com.zywczas.databasestore.synchronisation

import com.zywczas.databasestore.synchronisation.dao.LastUpdateDao
import com.zywczas.databasestore.synchronisation.entities.LastUpdateLocal
import javax.inject.Inject

class SynchronisationBusinessCaseImpl @Inject constructor(
    private val lastUpdateDao: LastUpdateDao
) : SynchronisationBusinessCase {

    override suspend fun updateDatabaseTimeStamp() = lastUpdateDao.insert(LastUpdateLocal())

}