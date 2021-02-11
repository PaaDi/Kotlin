package com.madera.kotlin.Controller.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.madera.kotlin.Controller.Home.HomeActivity
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var txtAccueil: String? = null
    private var txtUsername: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            txtUsername = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)

        //set the text of your text view
        /*val txtAccueil = view!!.findViewById(R.id.txtAccueil) as TextView
        txtAccueil.setText("Projets")*/

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(txtUsername)?.let {
            txtUsername = it
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(txtUsername: String) =
                MenuFragment().apply {
                    arguments = Bundle(1).apply {
                        putString(ARG_PARAM2, txtUsername)

                    }
                }
    }


}