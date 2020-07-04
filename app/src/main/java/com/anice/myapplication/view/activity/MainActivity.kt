package com.anice.myapplication.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.anice.myapplication.*
import com.anice.myapplication.databinding.ActivityMainBinding
import com.anice.myapplication.model.Goods
import com.anice.myapplication.view.fragment.CategoryFragment
import com.anice.myapplication.view.activity.base.BaseActivity
import com.anice.myapplication.view.adapter.GoodsAdapter
import com.anice.myapplication.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), OnItemClickListener,
    CoroutineScope {
    override val layoutRes = R.layout.activity_main
    override val bindingVariable = BR.viewModel
    override val viewModelClass = MainViewModel::class.java
    private lateinit var layoutManager: GridLayoutManager
    lateinit var goodsList: List<Goods>
    var goodID = ""
    var goodPosition = 0
    lateinit var adapter: GoodsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setTitle(R.string.app_name)
        layoutManager = GridLayoutManager(this, 2)
        rvStoreItems.layoutManager = layoutManager
        launch {
            val result = viewModel.fetchGoods()
            onResult(result)
        }

    }


    override fun onItemClick(goodID: String, position: Int) {
        var fragment = CategoryFragment.newInstance()
        this.goodID = goodID
        this.goodPosition = position
        supportFragmentManager.beginTransaction()
            .add(R.id.root_container, fragment)
            .addToBackStack("ass")
            .commit()
    }

    override fun onCategoryClick(color: String) {
        supportFragmentManager.popBackStackImmediate()
        goodsList.get(goodPosition).categoryColor = "#9C27B0"
        adapter.notifyItemChanged(goodPosition)
        launch {
            val result = viewModel.changeCategory(goodID, color, goodPosition)
            onResultChange(result)
        }


    }

    private fun onResultChange(result: List<Goods>) {
        goodsList = result
        adapter.notifyDataSetChanged()
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun onResult(result: List<Goods>) {
        goodsList = result
        adapter =
            GoodsAdapter(goodsList, this)
        rvStoreItems.setAdapter(adapter);
    }

}
