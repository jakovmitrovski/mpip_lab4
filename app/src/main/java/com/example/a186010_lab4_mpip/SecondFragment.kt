package com.example.a186010_lab4_mpip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a186010_lab4_mpip.adapters.StudentRecyclerViewAdapter
import com.example.a186010_lab4_mpip.databinding.FragmentSecondBinding
import com.example.a186010_lab4_mpip.models.DisplayStudent
import com.example.a186010_lab4_mpip.viewmodels.FirstFragmentViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var recyclerViewAdapter: StudentRecyclerViewAdapter
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var studentList: MutableList<DisplayStudent>
    private lateinit var firstFragmentViewModel: FirstFragmentViewModel

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

        firstFragmentViewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        studentRecyclerView = view.findViewById(R.id.studentRecyclerView)

        studentRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = StudentRecyclerViewAdapter(this, mutableListOf<DisplayStudent>(), firstFragmentViewModel)

        firstFragmentViewModel.getStudentListMutableLiveData().observe(viewLifecycleOwner,
            { t -> recyclerViewAdapter.updateData(t!!) })

        studentRecyclerView.adapter = recyclerViewAdapter

        firstFragmentViewModel.getStudents()

        binding.btnAddStudents.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}