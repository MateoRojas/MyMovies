package uio.androidbootcamp.moviesapp.application

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class CustomApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}