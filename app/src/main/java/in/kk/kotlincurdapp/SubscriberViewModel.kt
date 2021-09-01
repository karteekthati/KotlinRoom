package `in`.kk.kotlincurdapp

import `in`.kk.kotlincurdapp.db.Subscriber
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val  repository: SubscriberRepository): ViewModel(), Observable {
    val subscribers = repository.subscibers
    private var isUpdateorDelete = false

    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail =  MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText =  MutableLiveData<String>()
    @Bindable
    val clearAllorDeletButtonText =  MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllorDeletButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(id = 0, name, email))
        inputName.value = null
        inputEmail.value = null

    }
    fun clearAllorDelete(){
        clearAll()
    }
    fun insert(subscriber:Subscriber): Job = viewModelScope.launch { repository.insert(subscriber) }
    fun delete(subscriber:Subscriber): Job = viewModelScope.launch { repository.delete(subscriber) }
    fun clearAll(): Job = viewModelScope.launch { repository.deleteAll() }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value =  subscriber.name
        inputEmail.value = subscriber.email
        isUpdateorDelete =   true
        saveOrUpdateButtonText.value = "Update"
        clearAllorDeletButtonText.value = "Delete"
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}