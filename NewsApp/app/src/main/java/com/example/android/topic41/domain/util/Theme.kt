package com.example.android.topic41.domain.util

import java.util.*
import kotlin.collections.ArrayList

class Theme {
    var requestParameterValues = HashMap<String, String>()
    val requestParameterKeys: MutableList<String> = ArrayList()


    companion object {
        private const val DEFAULT_KEY = "Software"
        private const val DEFAULT_VALUE = "software"
        private const val EXTRA_KEY_0 = "General"
        private const val EXTRA_VALUE_0 = "general"
        private const val EXTRA_KEY_1 = "Health"
        private const val EXTRA_VALUE_1 = "health"
        private const val EXTRA_KEY_2 = "Science"
        private const val EXTRA_VALUE_2 = "science"
        private const val EXTRA_KEY_3 = "Technology"
        private const val EXTRA_VALUE_3 = "technology"
    }

    init {
        requestParameterValues[DEFAULT_KEY] = DEFAULT_VALUE
        requestParameterValues[EXTRA_KEY_0] = EXTRA_VALUE_0
        requestParameterValues[EXTRA_KEY_1] = EXTRA_VALUE_1
        requestParameterValues[EXTRA_KEY_2] = EXTRA_VALUE_2
        requestParameterValues[EXTRA_KEY_3] = EXTRA_VALUE_3

        for ((key) in requestParameterValues) {
            requestParameterKeys.add(key)
            requestParameterKeys.reverse()
        }
    }
}