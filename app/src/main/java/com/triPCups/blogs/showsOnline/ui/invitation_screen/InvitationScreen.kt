package com.triPCups.blogs.showsOnline.ui.invitation_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.triPCups.blogs.showsOnline.databinding.ScreenInvitationsBinding
import com.triPCups.blogs.showsOnline.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class InvitationScreen : AppCompatActivity() {

    private val viewModel by viewModel<InvitationScreenViewModel>()
    private lateinit var binding: ScreenInvitationsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ScreenInvitationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() = with(viewModel) {
        viewModel.canAccessData.observe(this@InvitationScreen) {
            when (it) {
                true -> {
                    binding.invitationCodeText.visibility = View.GONE
                    binding.appLogo.visibility = View.VISIBLE
                    binding.helloText.visibility = View.VISIBLE

                    Handler(mainLooper).postDelayed({
                        startActivity(Intent(this@InvitationScreen, MainActivity::class.java))
                        finish()
                    }, 420)
                }
                false -> {
                    Handler(mainLooper).postDelayed({
                        binding.invitationCodeText.visibility = View.VISIBLE
                        binding.appLogo.visibility = View.GONE
                        binding.helloText.visibility = View.GONE
                    }, 420)
                }
                else -> {
                    //still loading
                    binding.appLogo.visibility = View.VISIBLE
                    binding.invitationCodeText.visibility = View.GONE
                    binding.helloText.visibility = View.GONE
                }
            }
        }
        viewModel.currentUserData.observe(this@InvitationScreen) {
            if(it != null) {
                binding.helloText.text = "Hello ${it.name}"
            }
        }
        viewModel.uniqueInvitationId.observe(this@InvitationScreen) { code ->
            binding.invitationCodeText.text = "Your invitation code is:\n\n$code"
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkAccess()
    }

}