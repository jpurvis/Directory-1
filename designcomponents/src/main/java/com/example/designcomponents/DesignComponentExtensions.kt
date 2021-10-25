package com.example.designcomponents

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet


fun Context.getStyledAttributes(
    attributeSet: AttributeSet?,
    styleArray: IntArray,
    block: TypedArray.() -> Unit
) =
    this.obtainStyledAttributes(attributeSet, styleArray, 0, 0).use(block)

fun TypedArray.use(block: TypedArray.() -> Unit) {
    try {
        block()
    } finally {
        this.recycle()
    }
}
