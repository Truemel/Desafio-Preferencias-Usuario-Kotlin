package cl.desafiolatam.desafiounobase

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class HomeActivity : AppCompatActivity() {
    private lateinit var userName: String
    private lateinit var homeTitle: TextView
    private lateinit var nicknameTitle: TextView
    private lateinit var spanishCheckBox: CheckBox
    private lateinit var englishCheckBox: CheckBox
    private lateinit var germanCheckBox: CheckBox
    private lateinit var otherCheckBox: CheckBox
    private lateinit var otherTextInput: TextInputEditText
    private lateinit var nickNameInput: TextInputEditText
    private lateinit var ageInput: TextInputEditText
    private lateinit var save: Button
    private lateinit var container: ConstraintLayout
// para almacenar los datos en shared preferences utilice claves que contengan el nombre del usuario y el nombre de el campo guardado.
    //esta recomendación no aplica para todos los valores, pero ayuda con varios
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViews()
        loadData()
        save.setOnClickListener {
            //Agregar los pasos necesario para guardar los datos
            saveNickNameAndAge()
            saveLanguages()
            Snackbar.make(container, "Datos guardados", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setUpViews() {
        homeTitle = findViewById(R.id.home_title)
        nicknameTitle = findViewById(R.id.nickname_title)
        spanishCheckBox = findViewById(R.id.language_one)
        englishCheckBox = findViewById(R.id.language_two)
        germanCheckBox = findViewById(R.id.language_three)
        otherCheckBox = findViewById(R.id.language_other)
        otherTextInput = findViewById(R.id.other_language_input)
        nickNameInput = findViewById(R.id.nickname_input)
        ageInput = findViewById(R.id.age_input)
        save = findViewById(R.id.save_button)
        container = findViewById(R.id.container)
    }
    private fun loadData() {
        userName = this.intent.getStringExtra("userName")
        val title = "Bienvenido Esta es la pantalla inicial, aquí $userName podrá ver todas sus preferencias"
        homeTitle.text = title
        handleLanguages()
        loadProfile()
    }

    private fun loadProfile() {
        //crear las claves para buscar y almacenar los datos, recuerde asociar las mismas al usuario de alguna manera
        val nicknameKey = ""
        val ageKey = ""
        val nickNameString = SharedPreferenceController.getNickName(userName, this)
        nickNameInput.setText(nickNameString)
        nicknameTitle.text = nickNameString
        val ageString = SharedPreferenceController.getAge(userName, this)
        ageInput.setText(ageString.toString())
    }

    private fun handleLanguages() {
        val languagesKey = ""
        //val languages = mutableSetOf()
        //ocupar resolveLanguage para cargar los datos iniciales en los checkboxs
        resolveLanguage(SharedPreferenceController.getLanguage("Spanish", userName,this))
        resolveLanguage(SharedPreferenceController.getLanguage("English", userName,this))
        resolveLanguage(SharedPreferenceController.getLanguage("German", userName,this))
        resolveLanguage(SharedPreferenceController.getLanguage("Other", userName,this))
    }

    private fun resolveLanguage(language: String) {
        if (language.isNotEmpty() && language == "Spanish") {
            spanishCheckBox.isChecked = true
        }
        if (language.isNotEmpty() && language == "English") {
            englishCheckBox.isChecked = true
        }
        if (language.isNotEmpty() && language == "German") {
            germanCheckBox.isChecked = true
        }
        if (language.isNotEmpty() && language.contains("Other")) {
            otherCheckBox.isChecked = true
            otherTextInput.setText(language.split("*")[1]) // ocupar el mismo delimitador para almacenar el valor de este campo
        }
    }

    private fun saveLanguages() {
        //crear lo necesario para guardar los idiomas seleccionados por el usuario
        //en sharedpreferences
        val languagesKey = ""
        if(spanishCheckBox.isChecked)
            SharedPreferenceController.setLanguage("Spanish", userName, this)
        else
            SharedPreferenceController.removeLanguage("Spanish", userName, this)

        if(englishCheckBox.isChecked)
            SharedPreferenceController.setLanguage("English", userName, this)
        else
            SharedPreferenceController.removeLanguage("English", userName, this)

        if(germanCheckBox.isChecked)
            SharedPreferenceController.setLanguage("German", userName, this)
        else
            SharedPreferenceController.removeLanguage("German", userName, this)

        if(otherCheckBox.isChecked)
            SharedPreferenceController.setLanguage("Other*${otherTextInput.text.toString()}", userName, this)
        else
            SharedPreferenceController.removeLanguage("Other", userName, this)
    }

    private fun saveNickNameAndAge() {
        val nicknameKey = ""
        val ageKey = ""
        if (nickNameInput.text!!.isNotEmpty()) {
           //almacenar
            SharedPreferenceController.setNickName(nickNameInput.text.toString(), userName, this)
        }
        if (ageInput.text!!.isNotEmpty()) {
            val ageInt = ageInput.text.toString().toInt()
            //almacenar
            SharedPreferenceController.setAge(ageInt, userName, this)
        }
    }
}
