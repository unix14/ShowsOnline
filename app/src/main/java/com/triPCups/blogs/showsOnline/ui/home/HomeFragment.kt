package com.triPCups.blogs.showsOnline.ui.home

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.adapters.BlogFeedAdapter
import com.triPCups.blogs.base.adapters.BlogFeedAdapter.Companion.FEED_POST_ITEM_TYPE
import com.triPCups.blogs.base.adapters.BlogFeedAdapter.Companion.FILTER_FEED_ITEM_TYPE
import com.triPCups.blogs.base.common.BaseFragment
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostTag
import com.triPCups.blogs.showsOnline.BuildConfig
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.databinding.FragmentHomeBinding
import com.triPCups.blogs.showsOnline.ui.MainActivity
import com.triPCups.blogs.showsOnline.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(), BlogFeedAdapter.FeedAdapterListener {

    private lateinit var searchView: SearchView

    //    private var clickedTag: PostTag? = null
    override lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()
    private val mainViewModel by sharedViewModel<MainViewModel>()
    private lateinit var adapter: BlogFeedAdapter
    val args: HomeFragmentArgs by navArgs()

    private var isLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("wow", "onViewCreated: args.categoryName is ${args.categoryName}")
        if (!::binding.isInitialized) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
        }

        val shouldImportNewStubFeed = false
        if(BuildConfig.DEBUG && shouldImportNewStubFeed) {
            viewModel.importIntoFeed(args.categoryName)
        }
        if(args.categoryName.isNotEmpty()) {
            viewModel.fetchFeed(args.categoryName)
        } else {
            viewModel.fetchFeed()
        }

        return binding.root
    }

    override fun initObservers() = with(viewModel) {
//        blogViewModel.currentNavigationData.observe(viewLifecycleOwner) {
//            Log.d("wow", "initObservers: currentNavigationData event is $it")
//            Log.d("wow", "initObservers: currentNavigationData args.categoryName is ${args.categoryName}")
//            if(it is NavigationEvents.ShowBlogCategory) {
//                viewModel.fetchFeed(it.categoryName)
//            } else if(it is NavigationEvents.ShowHomeScreen) {
////                viewModel.fetchFeed()
////                val shouldImportNewStubFeed = false
////                if(BuildConfig.DEBUG && shouldImportNewStubFeed) {
////                    viewModel.importIntoFeed(args.categoryName)
////                }
////                if(args.categoryName.isNotEmpty()) {
////                    viewModel.fetchFeed(args.categoryName)
////                } else {
////                    viewModel.fetchFeed()
////                }
//            }
//        }
        feedLiveData.observe(viewLifecycleOwner) {
            Log.d("wow", "initObservers: feedLiveData args.categoryName is ${args.categoryName} isLoaded is $isLoaded")
            Log.d("wow", "initObservers: feedLiveData posts.size is ${it.posts.size}")
            if (isLoaded) return@observe
            binding.feedRecycler.visibility = View.VISIBLE
            binding.feedProgressBar.visibility = View.GONE
            adapter.submitList(it)
            isLoaded = true

            mainViewModel.currentSearchData.value.let { query ->
                if(!query.isNullOrEmpty()) {
                    adapter.searchResults(query)
//
////                    searchView.setOnQueryTextListener(object : OnQueryTextListener,
////                        SearchView.OnQueryTextListener {
////                        override fun onQueryTextSubmit(query: String?): Boolean {
////                            return true
////                        }
////
////                        override fun onQueryTextChange(newText: String?): Boolean {
////                            adapter.searchResults(query)
////
////                            return true
////                        }
////                    })
//
//
                }
            }
        }
//        mainViewModel.currentSearchData.observe(viewLifecycleOwner) {
//            if(isLoaded) {
//                adapter.searchResults(it)
//            }
//        }
    }

    override fun initUi() {
        initAdapter()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if(getIsAdmin()){
            inflater.inflate(R.menu.feed_admin, menu)
        } else {
            inflater.inflate(R.menu.feed, menu)

//            requireContext().apply { // todo implement Searchable activity
//                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//                searchView = menu.findItem(R.id.search).actionView as SearchView
//                val component = ComponentName(this, MainActivity::class.java)
//                val searchableInfo = searchManager.getSearchableInfo(component)
//                searchView.setSearchableInfo(searchableInfo)
//
//
//                searchView.setOnQueryTextListener(object : OnQueryTextListener,
//                    SearchView.OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        Log.d("wow", "onQueryTextSubmit: $query")
//                        return false
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        adapter.searchResults(newText)
//                        Log.d("wow", "onQueryTextChange: $newText")
//                        return false
//                    }
//                })
//
//            }
        }
    }

    private fun getIsAdmin(): Boolean = mainViewModel.isAdmin

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                blogViewModel.showEditPostScreen(BlogPost().apply {
                    categoryName = viewModel.currentCategoryNameData.value?: Constants.databaseEntryName
                })
                true
            }
//            R.id.action_admin -> {
//
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTagClicked(tag: PostTag?) {
//        clickedTag = tag // todo use analytics
    }

//    override fun onPostClick(post: BlogPost, extras: FragmentNavigator.Extras?) {
//        super.onPostClick(post, extras)
//        lifecycleScope.launch {
//            binding.feedProgressBar.visibility = View.VISIBLE
//            delay(Random.nextLong(420,700))
//            binding.feedProgressBar.visibility = View.GONE
//        }
//    }

    private fun initAdapter() = with(binding) {
        if(::adapter.isInitialized) return@with
        val layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL,false)
        adapter = BlogFeedAdapter(listener= this@HomeFragment)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    FILTER_FEED_ITEM_TYPE -> 2
                    FEED_POST_ITEM_TYPE -> 1
                    else -> -1
                }
            }
        }
        feedRecycler.layoutManager = layoutManager
        feedRecycler.adapter = adapter
    }
}