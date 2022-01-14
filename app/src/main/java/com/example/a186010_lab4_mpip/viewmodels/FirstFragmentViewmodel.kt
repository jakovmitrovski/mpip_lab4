package com.example.a186010_lab4_mpip.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a186010_lab4_mpip.models.DisplayStudent
import com.example.a186010_lab4_mpip.models.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.reflect.typeOf

class FirstFragmentViewModel(application: Application): AndroidViewModel(application) {

    val database = FirebaseDatabase.getInstance("https://lab4-mpip-cc07d-default-rtdb.firebaseio.com")
    val studentReference = database.getReference("students")
    private var studentListMutableLiveData: MutableLiveData<List<DisplayStudent>> = MutableLiveData()

    fun getStudents() {
        val students = mutableListOf<DisplayStudent>()
        val studentListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

                for (ds in dataSnapshot.children) {
                    val obj = ds.value as HashMap<*, *>
                    val stud = DisplayStudent(ds.key!!,
                        obj["userId"] as String,
                        obj["index"] as String,
                        obj["name"] as String,
                        obj["surname"] as String,
                        obj["telephoneNumber"] as String,
                        obj["address"] as String)
                    students.add(stud)
                }

                studentListMutableLiveData.postValue(students)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("NOW", "loadPost:onCancelled", databaseError.toException())
            }
        }
        studentReference.addValueEventListener(studentListener)
    }

    fun deleteStudent(id: String) {
        studentReference.child(id).removeValue()
        this.getStudents()
    }

    fun updateStudent(student: DisplayStudent) {
        val id = student.id!!
//        studentReference.child(id).child("userId").setValue(student.userId!!)
        studentReference.child(id).child("index").setValue(student.index!!)
        studentReference.child(id).child("name").setValue(student.name!!)
        studentReference.child(id).child("surname").setValue(student.surname!!)
        studentReference.child(id).child("telephoneNumber").setValue(student.telephoneNumber!!)
        studentReference.child(id).child("address").setValue(student.address!!)
        studentReference.push()
    }


    fun getStudentListMutableLiveData(): MutableLiveData<List<DisplayStudent>> {
        return studentListMutableLiveData
    }
}