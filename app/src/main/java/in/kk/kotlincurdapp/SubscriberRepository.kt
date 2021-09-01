package `in`.kk.kotlincurdapp

import `in`.kk.kotlincurdapp.db.Subscriber
import `in`.kk.kotlincurdapp.db.SubscriberrDAO

class SubscriberRepository(private val dao: SubscriberrDAO) {
    val subscibers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}