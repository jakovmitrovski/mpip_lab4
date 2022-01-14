package com.example.a186010_lab4_mpip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a186010_lab4_mpip.databinding.FragmentFirstBinding
import com.example.a186010_lab4_mpip.models.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase




/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    var database = FirebaseDatabase.getInstance("https://lab4-mpip-cc07d-default-rtdb.firebaseio.com")
    var studentReference = database.getReference("students")
    val mAuth = FirebaseAuth.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btnSave.setOnClickListener {
            val index = binding.etIndex.text.toString()
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val address = binding.etAddress.text.toString()

            if (index.isEmpty() or name.isEmpty() or surname.isEmpty() or phoneNumber.isEmpty() or address.isEmpty()){
                return@setOnClickListener
            }

            uploadData(index, name, surname, phoneNumber, address)
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun uploadData(
        index: String,
        name: String,
        surname: String,
        phoneNumber: String,
        address: String
    ) {
        val currentStudent = Student(mAuth.uid!!, index, name, surname, phoneNumber, address)

        studentReference.push().setValue(currentStudent)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                    binding.etIndex.setText("")
                    binding.etName.setText("")
                    binding.etSurname.setText("")
                    binding.etPhoneNumber.setText("")
                    binding.etAddress.setText("")
                } else {
                    Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                }

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}