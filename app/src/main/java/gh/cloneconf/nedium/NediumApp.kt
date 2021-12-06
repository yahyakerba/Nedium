package gh.cloneconf.nedium

import android.app.Application
import gh.cloneconf.extractor.Extractor
import gh.cloneconf.nedium.Singleton.extractor

class NediumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        extractor = Extractor(applicationContext)
    }
}