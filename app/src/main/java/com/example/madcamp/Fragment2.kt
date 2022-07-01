package com.example.madcamp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
    private var imageRecycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var allPictures: ArrayList<Image>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        imageRecycler = view.findViewById(R.id.image_recycler)
        progressBar = view.findViewById(R.id.recycler_progress)


        // Storage permission
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it, Manifest.permission.READ_CALENDAR
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    101
                )
            }
            allPictures = ArrayList()

            if(allPictures!!.isEmpty()){
                progressBar?.visibility = View.VISIBLE
                // Get all images from Storage
                allPictures = getAllImages()
                // Set Adapter to recycler
                imageRecycler?.adapter = ImageAdapter(requireActivity().applicationContext, allPictures!!)
                progressBar?.visibility = View.GONE
            }
            // Inflate the layout for this fragment

        }
        return view
    }

    private fun getAllImages(): ArrayList<Image>? {
        val images = ArrayList<Image>()

        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)

        var cursor = activity?.contentResolver?.query(allImageUri, projection, null, null, null)

        try{
            cursor!!.moveToFirst()
            do{
                val image = Image()
                image.imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.imageName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while(cursor.moveToNext())
            cursor.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return images
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    private fun newInstant() : Fragment2
    {
        val args = Bundle()
        val frag = Fragment2()
        frag.arguments = args
        return frag
    }
}