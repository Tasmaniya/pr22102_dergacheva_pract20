package com.example.pr22102_dergacheva_pract20

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pr22102_dergacheva_pract20.ui.theme.Pr22102_dergacheva_pract20Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class User(val name: String, val sName: String, val email: String)
private lateinit var myDataBase: DatabaseReference
private val userList = mutableStateListOf<User>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr22102_dergacheva_pract20Theme {
                myDataBase.push().setValue(userList)
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    var field1 by remember { mutableStateOf("") }
    var field2 by remember { mutableStateOf("") }
    var field3 by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = field1, onValueChange = { field1 = it }, label = { Text("Field 1") })
        TextField(value = field2, onValueChange = { field2 = it }, label = { Text("Field 2") })
        TextField(value = field3, onValueChange = { field3 = it }, label = { Text("Field 3") })


    }
}
fun addDataToFirebase(field1: String, field2: String) {
    val database = FirebaseDatabase.getInstance()
    val data = mapOf(
        "field1" to field1,
        "field2" to field2
    )
    database.reference.child("data").push().setValue(data)
        .addOnSuccessListener {
            // Данные успешно отправлены
        }
        .addOnFailureListener { error ->
            // Обработка ошибок
            Log.e("FirebaseError", "Ошибка при отправке данных: ${error.message}")
        }
}
