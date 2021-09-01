package `in`.kk.kotlincurdapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberrDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subsriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subsriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subsriber: Subscriber)

    @Query(value = "DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>


}