package com.example.framework.model

/**
 * @author tuanminh.vu
 */
class ObjectMapper {
    companion object {
        inline fun <reified F, reified T : Any> mapFromParent(from: F): T {
            val toClass = T::class.java
            val fromClass = F::class.java
            val fromFields = fromClass.declaredFields
            val to = toClass.newInstance()
            for (fromField in fromFields) {
                try {
                    fromField.isAccessible = true
                    val value = fromField.get(from)
                    if (fromField.type == List::class) {
                        throw  java.lang.Exception("List type is not supported yet")
                    } else {
                        fromField.set(to, value)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return to
        }
    }
}