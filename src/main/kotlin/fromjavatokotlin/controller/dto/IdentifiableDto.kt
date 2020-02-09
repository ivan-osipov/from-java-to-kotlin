package fromjavatokotlin.controller.dto

open class IdentifiableDto {
    var id: String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IdentifiableDto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "IdentifiableDto(id=$id)"
    }
}