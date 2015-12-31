package com.sbhachu.kotlin.newsreader.presentation.common.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sbhachu.kotlin.newsreader.domain.model.Item
import java.util.*


public abstract class BaseRecyclerAdapter<T, V : View>() : RecyclerView.Adapter<BaseRecyclerView<V>>() {

    private var listener : ItemListener<T>? = null
    private var items : List<T> = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerView<V> {
        return BaseRecyclerView<V>(onCreateItemView(parent, viewType))
    }

    protected abstract fun onCreateItemView(parent : ViewGroup?, viewType: Int) : V

    public override fun getItemCount(): Int {
        return items.count()
    }

    public fun getItems() : List<T> {
        return items
    }

    public fun setItems(items : List<T>) {
        this.items = items
    }

    /**
     * Listener Interface
     */
    public interface ItemListener<T> {
        fun onItemClicked(view : View, item : T)
    }

    public fun getItemListener() : ItemListener<T>? {
        return listener
    }

    public fun setItemListener(listener: ItemListener<T>) {
        this.listener = listener
    }
}