package com.triPCups.blogs.showsOnline.ui.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.triPCups.blogs.base.common.openUrl
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.Constants.SIGAL_WEBSITE
import com.triPCups.blogs.showsOnline.common.shareApp
import com.triPCups.blogs.showsOnline.common.shareShow
import com.triPCups.blogs.showsOnline.databinding.FragmentSlideshowBinding

class AboutFragment : Fragment() {

    private lateinit var slideshowViewModel: AboutViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        slideshowViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textSlideshow.apply {
            movementMethod = ScrollingMovementMethod()
            slideshowViewModel.text.observe(viewLifecycleOwner) {
                text = it
            }
        }
        binding.sigal.setOnClickListener {
            SIGAL_WEBSITE.openUrl(requireContext())
        }
        binding.store.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context?.packageName}")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${context?.packageName}")))
            }
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share_post -> {
                requireContext().shareApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}