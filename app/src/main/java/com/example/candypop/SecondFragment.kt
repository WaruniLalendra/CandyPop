package com.example.candypop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.candypop.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener{
            val name = binding.txtName.text.toString().trim()
            val orderNo = Integer.parseInt(binding.txtOrderNo.text.toString().trim())
            val satisfaction = binding.stSatisfaction.isChecked

            val editor = preferences.edit()
            editor.putString("NAME", name)
            editor.putInt("ORDER_NO", orderNo)
            editor.putBoolean("SATISFACTION", satisfaction)

            editor.apply()

            if(satisfaction){
                val dialogViewTrue = LayoutInflater.from(requireContext()).inflate(R.layout.true_dialog,null)

                val builder = AlertDialog.Builder(requireActivity())
                    .setView(dialogViewTrue).show()
            }else{
                val dialogViewFalse = LayoutInflater.from(requireContext()).inflate(R.layout.false_dialog,null)

                val builder = AlertDialog.Builder(requireActivity())
                    .setView(dialogViewFalse).show()
            }
        }



        binding.buttonSecond.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            val name = preferences.getString("NAME", "")
            val orderNo = preferences.getInt("ORDER_NO", 0)
            val satisfaction = preferences.getBoolean("SATISFACTION", false)

            binding.txtShow.text = "Name : $name \nOrder No: $orderNo \nDelivered: $satisfaction"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}