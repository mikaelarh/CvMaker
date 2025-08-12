package com.yamidev.easycvmaker.ui.cv

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.yamidev.easycvmaker.R
import com.google.android.material.button.MaterialButton

class Cv_Activity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    private var imageUri: Uri? = null

    private lateinit var btnPersonalInfo: MaterialButton
    private lateinit var btnProfile: MaterialButton
    private lateinit var btnExperience: MaterialButton
    private lateinit var btnEducation: MaterialButton
    private lateinit var btnSkills: MaterialButton
    private lateinit var btnLanguages: MaterialButton
    private lateinit var btnCertifications: MaterialButton
    private lateinit var btnHobbies: MaterialButton
    private lateinit var btnSoftSkills: MaterialButton
    private lateinit var btnAdditionalInfo: MaterialButton
    private lateinit var btnSocial: MaterialButton
    private lateinit var btnVisualizePDF: MaterialButton
    private lateinit var btnExportPDF: MaterialButton

    private val pickImageLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            imageUri = result.data!!.data
            imgProfile.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_cv_activity)
        supportActionBar?.hide()
        imgProfile = findViewById(R.id.imgProfile)
        imgProfile.setOnClickListener { showImagePickerDialog() }

        btnPersonalInfo = findViewById(R.id.button_personal_info)
        btnProfile = findViewById(R.id.button_profile)
        btnExperience = findViewById(R.id.button_work_experience)
        btnEducation = findViewById(R.id.button_school_info)
        btnSkills = findViewById(R.id.button_skills)
        btnLanguages = findViewById(R.id.button_language)
        btnCertifications = findViewById(R.id.button_certifications)
        btnHobbies = findViewById(R.id.button_hobby)
        btnSoftSkills = findViewById(R.id.button_soft_skills)
        btnAdditionalInfo = findViewById(R.id.button_summary)
        btnSocial = findViewById(R.id.button_social_network)
        btnVisualizePDF = findViewById(R.id.button_visualize)
        btnExportPDF = findViewById(R.id.buton_export)

        setListeners()
    }

    private fun setListeners() {
        btnPersonalInfo.setOnClickListener { handleSection("Información Personal") }
        btnProfile.setOnClickListener { handleSection("Perfil Profesional") }
        btnExperience.setOnClickListener { handleSection("Experiencia Laboral") }
        btnEducation.setOnClickListener { handleSection("Educación") }
        btnSkills.setOnClickListener { handleSection("Habilidades Técnicas") }
        btnLanguages.setOnClickListener { handleSection("Idiomas") }
        btnCertifications.setOnClickListener { handleSection("Cursos y Certificados") }
        btnHobbies.setOnClickListener { handleSection("Aficiones / Intereses") }
        btnSoftSkills.setOnClickListener { handleSection("Habilidades Blandas") }
        btnAdditionalInfo.setOnClickListener { handleSection("Información Adicional") }
        btnSocial.setOnClickListener { handleSection("Redes Sociales") }

        btnVisualizePDF.setOnClickListener { handleAction("Visualizar PDF") }
        btnExportPDF.setOnClickListener { handleAction("Exportar PDF") }
    }

    private fun handleSection(sectionName: String) {
        Toast.makeText(this, "Sección: $sectionName", Toast.LENGTH_SHORT).show()
    }

    private fun handleAction(actionName: String) {
        Toast.makeText(this, actionName, Toast.LENGTH_SHORT).show()
    }

    private fun showImagePickerDialog() {
        AlertDialog.Builder(this)
            .setTitle("Seleccionar Foto")
            .setItems(arrayOf("Elegir desde galería")) { _: DialogInterface?, _: Int -> openGallery() }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }
}
