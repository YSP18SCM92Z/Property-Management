package com.nijhoomt.ntrental.register

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.login.LoginActivity
import com.nijhoomt.ntrental.model.RegisterCredential
import kotlinx.android.synthetic.main.fragment_common_register.*
import kotlinx.android.synthetic.main.fragment_common_register.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_common_register, container, false)

        if (arguments?.getInt(ARG_SECTION_NUMBER) == 3) {
            view.tiet_register_landlord_property_manager_email.visibility = View.VISIBLE
        }

        // You need the tabTitle or the index to programmatically hide/show
        view.btn_register_register.setOnClickListener {
            if (arguments?.getInt(ARG_SECTION_NUMBER) == 3) {
                validateTenantUserInputs(view, this.activity?.application!!)
            }
            // Any other tabs
            else {
                validateOtherTabUserInputs(view, this.activity?.application!!)
            }
        }
        return view
    }

    private fun validateOtherTabUserInputs(view: View, application: Application) {
        if (view.tiet_register_user_email.text.toString() == "" ||
            view.tiet_register_password.text.toString() == "" ||
            view.tiet_register_confirm_password.text.toString() == ""
        ) {
            if (view.tiet_register_user_email.text.toString() == "") {
                view.tiet_register_user_email.error =
                    "User Email field is required!"
            }

            if (view.tiet_register_password.text.toString() == "") {
                view.tiet_register_password.error =
                    "Password field is required!"
            }

            if (view.tiet_register_confirm_password.text.toString() == "") {
                view.tiet_register_confirm_password.error =
                    "Confirm Password field is required!"
            }
        }
        // Password not match
        else if (view.tiet_register_password.text.toString() !=
            view.tiet_register_confirm_password.text.toString()
        ) {
            view.tiet_register_confirm_password.error =
                "Password does not match!"
        } else {
            processOtherTabs(view, application)
        }
    }

    private fun validateTenantUserInputs(view: View, application: Application) {
        if (view.tiet_register_landlord_property_manager_email.text.toString() == "" ||
            view.tiet_register_user_email.text.toString() == "" ||
            view.tiet_register_password.text.toString() == "" ||
            view.tiet_register_confirm_password.text.toString() == ""
        ) {
            if (view.tiet_register_landlord_property_manager_email.text.toString() == "") {
                view.tiet_register_landlord_property_manager_email.error =
                    "Landlord/Property Manager field is required!"
            }

            if (view.tiet_register_user_email.text.toString() == "") {
                view.tiet_register_user_email.error =
                    "User Email field is required!"
            }

            if (view.tiet_register_password.text.toString() == "") {
                view.tiet_register_password.error =
                    "Password field is required!"
            }

            if (view.tiet_register_confirm_password.text.toString() == "") {
                view.tiet_register_confirm_password.error =
                    "Confirm Password field is required!"
            }
        }
        // Password not match
        else if (view.tiet_register_password.text.toString() !=
            view.tiet_register_confirm_password.text.toString()
        ) {
            view.tiet_register_confirm_password.error =
                "Password does not match!"
        } else {
            processTenantTab(view, application)
        }
    }

    private fun processOtherTabs(view: View, application: Application) {
        val email = tiet_register_user_email.text.toString()
        val password = tiet_register_password.text.toString()
        val account_for = arguments?.getString(ARG_SECTION_TITLE).toString()

        val registerCredential = RegisterCredential(
            email, "", password, account_for
        )

        val pageViewModelFactory = PageViewModelFactory(
            registerCredential = registerCredential,
            application = application
        )

        val pageViewModel =
            ViewModelProviders
                .of(this, pageViewModelFactory)
                .get(PageViewModel::class.java).apply {

                    // Since what we pass is: position + 1, then
                    // 1 >> Landlord
                    // 2 >> Property Manager
                    // 3 >> Tenant
                    // 4 >> Vendor
                    setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
                    setTabTitle(arguments?.getString(ARG_SECTION_TITLE))
                }

        pageViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this.activity, "$it", Toast.LENGTH_LONG).show()
            val intent = Intent(view.context, LoginActivity::class.java)
            startActivity(intent)
        })
    }

    private fun processTenantTab(view: View, application: Application) {
        val email = tiet_register_user_email.text.toString()
        val landlord_email = tiet_register_landlord_property_manager_email.text.toString()
        val password = tiet_register_password.text.toString()
        val account_for = arguments?.getString(ARG_SECTION_TITLE).toString()

        val registerCredential = RegisterCredential(
            email, landlord_email, password, account_for
        )

        val pageViewModelFactory = PageViewModelFactory(
            registerCredential = registerCredential,
            application = application
        )

        val pageViewModel =
            ViewModelProviders
                .of(this, pageViewModelFactory)
                .get(PageViewModel::class.java).apply {

                    // Since what we pass is: position + 1, then
                    // 1 >> Landlord
                    // 2 >> Property Manager
                    // 3 >> Tenant
                    // 4 >> Vendor
                    setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
                    setTabTitle(arguments?.getString(ARG_SECTION_TITLE))
                }

        pageViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this.activity, "$it", Toast.LENGTH_LONG).show()
            val intent = Intent(view.context, LoginActivity::class.java)
            startActivity(intent)
        })
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_SECTION_TITLE = "section_title"

        @JvmStatic
        fun newInstance(sectionNumber: Int, pageTitle: String): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_SECTION_TITLE, pageTitle)
                }
            }
        }
    }
}