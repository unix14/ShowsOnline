package com.triPCups.blogs.showsOnline.ui

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.triPCups.blogs.base.logic.BlogViewModel
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.showsOnline.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.triPCups.blogs.base.common.openUrl
import com.triPCups.blogs.base.ui.AuthorDetailsDialog
import com.triPCups.blogs.showsOnline.BuildConfig
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.Constants.SIGAL_WEBSITE
import com.triPCups.blogs.showsOnline.common.Constants.TRIPPY_CUPS_WEBSITE
import com.triPCups.blogs.showsOnline.common.MAKE_ADMIN_COMMAND


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val blogViewModel by viewModel<BlogViewModel>()
    private val mainViewModel by viewModel<MainViewModel>()

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_content_main)
    }

    private val drawerLayout: DrawerLayout by lazy { binding.drawerLayout }
    private val navView: NavigationView by lazy { binding.navView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAds()
        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            if(mainViewModel.isAdmin) {
//                //todo implement Control panel button for admin
//            } else {
//
//            }
//            sendEmail()
//        }

        initAdminAccessWhenNeeded()
//        handleIntent(intent)

        //todo fix problems with navigation view
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val destinations = if(mainViewModel.isAdmin) {
            setOf(
                R.id.nav_home,
//                R.id.nav_old_shows,
                R.id.nav_contact,
                R.id.nav_more_apps,
                R.id.nav_about
            )
        } else {
            setOf(
                R.id.nav_home,
//                R.id.nav_old_shows,
                R.id.nav_more_apps,
                R.id.nav_contact,
                R.id.nav_about,
                R.id.nav_qa
            )
        }
        appBarConfiguration = AppBarConfiguration(destinations, drawerLayout) {
            finish()
            true
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initNavControllerLinks()

//        navView.setNavigationItemSelectedListener { menuItem ->
////            setSelectedMenuItem(menuItem.order)
//
//            when (menuItem.itemId) {
//                R.id.nav_home -> {
//                    blogViewModel.showCategory(Constants.databaseEntryName)
//                    binding.appBarMain.toolbar.title = "Home" // todo remove if not needed
//                }
//                R.id.nav_summaries -> {
//                    blogViewModel.showCategory(Constants.databaseSummariesEntryName)
//                    binding.appBarMain.toolbar.title = "Summaries"
//                }
//                R.id.nav_interview_questions -> {
//                    blogViewModel.showCategory(Constants.databaseInterviewQuestionsEntryName)
//                    binding.appBarMain.toolbar.title = "Interview Questions"
//                }
//                R.id.nav_assignments -> {
//                    blogViewModel.showCategory(Constants.databaseAssignmentsEntryName)
//                    binding.appBarMain.toolbar.title = "Assignments"
//                }
//                R.id.nav_resources -> {
//                    blogViewModel.showCategory(Constants.databaseResourcesEntryName)
//                    binding.appBarMain.toolbar.title = "Resources"
//                }
//                R.id.nav_about -> {
//                    blogViewModel.showAbout()
////                    binding.appBarMain.toolbar.title = "About"
//                }
//                else -> { }
//            }
//            return@setNavigationItemSelectedListener true
//        }
        ///fix for onclick items not called
        navView.bringToFront()


        initObservers()
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        handleIntent(intent)
//    }

//    private fun handleIntent(intent: Intent?) { //todo implement searchable activity
//        if (Intent.ACTION_SEARCH == intent?.action) {
//            val query = intent.getStringExtra(SearchManager.QUERY)
//            Log.d("SEARCH", "Search query was: $query")
//            mainViewModel.doSearch(query)
//            binding.appBarMain.toolbar.title = "חיפוש: $query"
////            binding.appBarMain.toolbar.navigationIcon = null
////            binding.appBarMain.toolbar.isBackInvokedCallbackEnabled = false
//
////            navView.visibility = View
//        }
//    }

    private fun initNavControllerLinks() = with(binding) {
        trippyCups.setOnClickListener {
            TRIPPY_CUPS_WEBSITE.openUrl(this@MainActivity)
        }
        sigal.setOnClickListener {
            SIGAL_WEBSITE.openUrl(this@MainActivity)
        }
    }

    private fun initAdminAccessWhenNeeded() {
//        if (intent?.action == MAKE_ADMIN_COMMAND || BuildConfig.DEBUG) {
//            mainViewModel.grantDeveloperAccess()
//        } else {
            navView.menu.removeGroup(R.id.admin_group)
//        }
    }

    private fun initAds() = with(binding) {
        root.findViewById<FrameLayout>(R.id.adContainer)?.apply {
            MobileAds.initialize(this@MainActivity) {
                val adView = AdView(this@MainActivity)
                addView(adView)
                adView.apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = if (BuildConfig.DEBUG)
                        "ca-app-pub-3940256099942544/6300978111" //test
                    else "ca-app-pub-7481638286003806/7670867227" //prod

                    val adRequest = AdRequest.Builder().build()
                    loadAd(adRequest)
                }
            }
        }
    }


    private fun setSelectedMenuItem(position: Int) {
        navView.setCheckedItem(position)
//        //disable all
//        for(i in 0..navView.menu.size()) {
//            navView.menu.getItem(position).isChecked = false
//        }
//        //check desired
//        navView.menu.getItem(position).isChecked = true
    }

//    private fun sendEmail() {
//        openMail(
//            "support@3p-cups.com",
//            "New Test Submission",
//            "I have a new test suggestion for this app.\n\n\nThis email was sent from ${getString(R.string.app_name)}",
//            "Send email"
//        )
//
//        Toast.makeText(
//            this,
//            "Submit your suggestions to our email\nSupport@3P-Cups.com",
//            Toast.LENGTH_LONG
//        ).show()
//    }

//    //Todo implement main activity top buttons if needed
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        if(mainViewModel.isAdmin){
////            menuInflater.inflate(R.menu.feed_admin, menu)
//            binding.appBarMain.toolbar.inflateMenu(R.menu.feed_admin)
//        } else {
////            menuInflater.inflate(R.menu.main, menu)
//            binding.appBarMain.toolbar.inflateMenu(R.menu.main)
//        }
//        return true //super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_add -> {
//                blogViewModel.showEditPostScreen(BlogPost())
//                true
//            }
//            R.id.action_admin -> {
//
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        binding.appBarMain.toolbar.title = getString(R.string.app_name)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        binding.appBarMain.toolbar.title = getString(R.string.app_name)
    }

    private fun initObservers() = with(blogViewModel) {
        currentNavigationData.observe(this@MainActivity) {
            when (it) {
                is NavigationEvents.ShowAuthorDetailsScreen -> {
                    AuthorDetailsDialog.newInstance(it.author).show(supportFragmentManager, "AuthorDetailsDialog")
                }
                is NavigationEvents.ShowErrorMessage -> {
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                is NavigationEvents.ShowHomeScreen -> {
                    binding.appBarMain.toolbar.title = getString(R.string.app_name)
                    navController.popBackStack(R.id.nav_home, inclusive = false)
                }
                is NavigationEvents.ShowReadPostScreen -> {
                    binding.appBarMain.toolbar.title = it.post.title
                    navController.navigate(
                        R.id.nav_read_post, null,
                        null, it.extras
                    )
                }
                is NavigationEvents.ShowEditPostScreen -> {
                    navController.navigate(R.id.nav_edit_post)
                }
                is NavigationEvents.ShowBlogCategory -> {
                    binding.appBarMain.toolbar.title = it.categoryName

                    when (navController.currentBackStackEntry?.destination?.id) {
//                        R.id.nav_about -> {
//                            navController.navigate(R.id.nav_home)
//                        }
                        else -> {
                            navController.navigate(R.id.nav_home)
                        }
                    }
                }
                is NavigationEvents.ShowAboutScreen -> {
                    navController.navigate(R.id.nav_about)
                }
                else -> {}
            }
            drawerLayout.closeDrawers()
        }
    }

    override fun onResume() {
        super.onResume()
//        navController.setOnBackPressedDispatcher(object : OnBackPressedDispatcher() {
//
//        })
//        binding.appBarMain.toolbar.title = getString(R.string.app_name) //todo bring category from VM
    }

    //
}