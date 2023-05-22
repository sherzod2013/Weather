package com.yusmp.basecode.base.recyclerAdapter

abstract class Identifiable {
    abstract val id: Long

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return id == (other as Identifiable).id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}