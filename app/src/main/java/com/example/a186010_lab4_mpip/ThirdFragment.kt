package com.example.a186010_lab4_mpip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a186010_lab4_mpip.models.DisplayStudent
import com.example.a186010_lab4_mpip.models.Student
import com.example.a186010_lab4_mpip.viewmodels.FirstFragmentViewModel

class ThirdFragment : Fragment() {

    lateinit var student: DisplayStudent
    lateinit var viewModel: FirstFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_third, container, false)

        student = ThirdFragmentArgs.fromBundle(requireArguments()).student

        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        val index: EditText = view.findViewById(R.id.etIndexEdit)
        val name: EditText = view.findViewById(R.id.etNameEdit)
        val surname: EditText = view.findViewById(R.id.etSurnameEdit)
        val telephone: EditText = view.findViewById(R.id.etPhoneNumberEdit)
        val address: EditText = view.findViewById(R.id.etAddressEdit)

        index.setText(student.index)
        name.setText(student.name)
        surname.setText(student.surname)
        telephone.setText(student.telephoneNumber)
        address.setText(student.address)

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.btnSaveEdit)

        button.setOnClickListener {

            val index = view.findViewById<EditText>(R.id.etIndexEdit).text.toString()
            val name = view.findViewById<EditText>(R.id.etNameEdit).text.toString()
            val surname = view.findViewById<EditText>(R.id.etSurnameEdit).text.toString()
            val telephoneNumber = view.findViewById<EditText>(R.id.etPhoneNumberEdit).text.toString()
            val address = view.findViewById<EditText>(R.id.etAddressEdit).text.toString()

            if (index.isEmpty() || name.isEmpty() || surname.isEmpty() || telephoneNumber.isEmpty() || address.isEmpty()) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }

            val newStudent = DisplayStudent(student.id, student.userId, index, name, surname, telephoneNumber, address)
            viewModel.updateStudent(newStudent)
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
        }
    }
}