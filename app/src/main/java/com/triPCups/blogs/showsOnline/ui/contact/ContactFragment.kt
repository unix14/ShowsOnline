package com.triPCups.blogs.showsOnline.ui.contact

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.triPCups.blogs.base.common.openMail
import com.triPCups.blogs.base.common.openUrl
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.Constants.SIGAL_WEBSITE
import com.triPCups.blogs.showsOnline.common.shareApp
import com.triPCups.blogs.showsOnline.databinding.FragmentContactBinding
import com.triPCups.blogs.showsOnline.databinding.FragmentSlideshowBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        binding.root

        binding.emailButton.setOnClickListener {
            sendEmail()
        }
        binding.whatsappButton.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://chat.whatsapp.com/DyCN11ZaoZtKNAZuC7C3ic")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "אנא וודאו כי מותקן אצלכם וואטסאפ", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun sendEmail() = with(requireContext()){
        openMail(
            "support@3p-cups.com",
            "New Test Submission",
            "I have a new test suggestion for this app.\n\n\nThis email was sent from ${getString(R.string.app_name)}",
            "Send email"
        )

        Toast.makeText(
            this,
            "Submit your suggestions to our email\nSupport@3P-Cups.com",
            Toast.LENGTH_LONG
        ).show()
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