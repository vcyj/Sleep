package com.example.pagegallery

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.looperpager_photo_view.*
import kotlinx.android.synthetic.main.pager_photo_view.*
import java.util.ArrayList


class GalleryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val galleryAdapter=GalleryAdapter()
        recyclerview.apply {
            adapter=galleryAdapter
            layoutManager=GridLayoutManager(requireContext(),2)
        }

        val galleryViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleryViewModel::class.java)
        galleryViewModel.photoListLive.observe(this, Observer {
            galleryAdapter.submitList(it)
            swiperefreshed.isRefreshing=false
        })
        galleryViewModel.photoListLive.value?:galleryViewModel.fetchData()
        swiperefreshed.setOnRefreshListener {
            galleryViewModel.fetchData()
        }

        val loopitems: MutableList<Loopitem> = ArrayList()
        loopitems.add(Loopitem(R.drawable.a))
        loopitems.add(Loopitem(R.drawable.b))
        loopitems.add(Loopitem(R.drawable.ycy2))
        LooperPager.setAdapter(LooperPagerAdapter(loopitems, LooperPager))
        LooperPager.setClickable(false)
        LooperPager.setOffscreenPageLimit(3)
        LooperPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        LooperPager.setPageTransformer(compositePageTransformer)
        LooperPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LooperPager.removeCallbacks(looperrunnable)
                LooperPager.postDelayed(looperrunnable, 3000) //滑动时间3s
            }
        })

    }
    private val looperrunnable =
//        Runnable { LooperPager!!.currentItem =}
//        Runnable { LooperPager.currentItem?.plus(1) }
    Runnable { LooperPager?.currentItem=LooperPager!!.currentItem + 1  }
}
