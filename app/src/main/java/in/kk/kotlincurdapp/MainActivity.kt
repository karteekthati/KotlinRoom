package `in`.kk.kotlincurdapp

import `in`.kk.kotlincurdapp.databinding.ActivityMainBinding
import `in`.kk.kotlincurdapp.db.Subscriber
import `in`.kk.kotlincurdapp.db.SubscriberDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

//pushing to new branch
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository =  SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel =  subscriberViewModel
        binding.lifecycleOwner =  this
        initRecyclerView()

    }

    private fun initRecyclerView(){
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            binding.rvSubscribers.adapter =  MyRecyclerviewAdapterr(it) { selectedItem: Subscriber ->  listItemCLicked( selectedItem )  }
        })
    }

    private fun listItemCLicked(subsciber: Subscriber){
        Toast.makeText(this, "Selected name is ${subsciber.name}", Toast.LENGTH_SHORT).show()
    }
}