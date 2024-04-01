package com.catapi.task.view

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catapi.task.R
import com.catapi.task.data.adapter.ImageListAdapter
import com.catapi.task.data.model.ImageListModelItem
import com.catapi.task.databinding.FragmentImageListBinding
import com.catapi.task.viewModel.ImageListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.reflect.Type


class ImageListFragment : Fragment(), OnLastItemVisibleListener {

    val mCompositeDisposable = CompositeDisposable()
    private lateinit var imageListViewModel: ImageListViewModel
    private lateinit var fragmentImageListBinding: FragmentImageListBinding
    private var imageListAdapter: ImageListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentImageListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_list, container, false
        )

        return fragmentImageListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageListViewModel = ViewModelProvider(this)[ImageListViewModel::class.java]

        initRecycerView()
        imageListAdapter = ImageListAdapter(this)
        fragmentImageListBinding.recyclerView.adapter = imageListAdapter
    }

    /*@Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageListViewModel = ViewModelProvider(this)[ImageListViewModel::class.java]

        initRecycerView()
        imageListAdapter = ImageListAdapter(this)
        fragmentImageListBinding.recyclerView.adapter = imageListAdapter
    }*/

    private fun apiCall() {
        fragmentImageListBinding.progressbar.visibility = View.VISIBLE
        mCompositeDisposable.add(
            imageListViewModel.getImageList("search", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ catImage ->
                    fragmentImageListBinding.progressbar.visibility = View.GONE

                    val jsonArray = catImage.asJsonArray

                    val gson = Gson()

                    val listType: Type = object : TypeToken<List<ImageListModelItem?>?>() {}.type

                    val catList: List<ImageListModelItem> =
                        gson.fromJson<List<ImageListModelItem>>(jsonArray, listType)

                    Log.d("LIST_ITEMS", catList.toString())
                    imageListAdapter?.setData(catList)
                }, { error ->
                    Log.d("LIST_ITEMS", error.toString())
                    fragmentImageListBinding.progressbar.visibility = View.GONE
                }
                )
        )

    }

    override fun onResume() {
        super.onResume()
        apiCall()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        mCompositeDisposable.dispose()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onLastItemVisible() {
        apiCall()
    }

    fun initRecycerView() {
       val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val desiredItemWidth = resources.getDimensionPixelSize(R.dimen.desired_item_width) // Define your desired item width in resources
        val spanCount = screenWidth / desiredItemWidth
        val itemWidth = screenWidth / spanCount

        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        fragmentImageListBinding.recyclerView.layoutManager = layoutManager
        fragmentImageListBinding.recyclerView.adapter = imageListAdapter


// Set item width and height
        fragmentImageListBinding.recyclerView.addItemDecoration(object :
            RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
                layoutParams.width = itemWidth
                layoutParams.height = itemWidth // Set height as needed
                view.layoutParams = layoutParams
            }
        })
    }
}