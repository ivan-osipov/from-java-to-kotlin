package fromjavatokotlin.model

import org.springframework.data.annotation.Id

open class Identifiable {
    @Id
    var id: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Identifiable

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Identifiable(id=$id)"
    }
}