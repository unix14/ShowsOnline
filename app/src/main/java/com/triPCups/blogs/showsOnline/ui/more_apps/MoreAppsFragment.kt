package com.triPCups.blogs.showsOnline.ui.more_apps

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
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.Constants.DEV_LINK_IN_GPS
import com.triPCups.blogs.showsOnline.common.Constants.TRIPPY_CUPS_WEBSITE
import com.triPCups.blogs.showsOnline.common.shareApp
import com.triPCups.blogs.showsOnline.databinding.FragmentMoreAppsBinding
import com.triPCups.blogs.showsOnline.databinding.FragmentSlideshowBinding

class MoreAppsFragment : Fragment() {

    private var _binding: FragmentMoreAppsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoreAppsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.store.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(DEV_LINK_IN_GPS)))
            } catch (e: ActivityNotFoundException) {
                DEV_LINK_IN_GPS.openUrl(requireContext())
            }
        }
        binding.trippyCups.setOnClickListener { //todo think about this logo maybe change it to lower resolution
            TRIPPY_CUPS_WEBSITE.openUrl(requireContext())
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