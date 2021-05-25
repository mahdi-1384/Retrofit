package ir.avesta.retrofit.util

import android.app.Activity
import android.widget.Toast

fun Activity.mToast(str: String) = Toast.makeText(applicationContext, "$str", Toast.LENGTH_SHORT).show()