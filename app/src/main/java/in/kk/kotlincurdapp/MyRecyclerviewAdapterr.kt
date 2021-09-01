package `in`.kk.kotlincurdapp

import `in`.kk.kotlincurdapp.databinding.ListItemBinding
import `in`.kk.kotlincurdapp.db.Subscriber
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerviewAdapterr(
    private val subscribersList: List<Subscriber>,
    private val clickListener: (Subscriber) -> Unit
): RecyclerView.Adapter<MyViewHoler>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoler {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding =  DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHoler(binding)
    }

    override fun onBindViewHolder(holder: MyViewHoler, position: Int) {
       holder.bind(subscribersList[position], clickListener)
    }

    override fun getItemCount(): Int {
       return subscribersList.size
    }
}

class MyViewHoler(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun  bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit){
        binding.tvName.text = subscriber.name
        binding.tvEmail.text = subscriber.email
        binding.itemCard.setOnClickListener {
            clickListener(subscriber)
        }
    }
}