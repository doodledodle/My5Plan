package com.doodle.my5plan

import android.content.Context
import android.content.pm.PackageInfo
import java.time.LocalDateTime


object CommonLib {
    fun getAPIVersion(sContext: Context) : Int {
        val info: PackageInfo = sContext.packageManager.getPackageInfo(sContext.packageName, 0)
        val version = info.versionName

        return version.toInt()
    }


}


