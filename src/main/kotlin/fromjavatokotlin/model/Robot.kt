package fromjavatokotlin.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("robots")
class Robot(
    val code: String,
    val mass: Int? = null,
    @Field(AVAILABILITY_FIELD)
    val availability: Boolean? = null,
    @Field(ABILITIES_FIELD)
    val abilities: List<Ability> = ArrayList()
) : Identifiable() {

    data class Ability(var code: String? = null)

    companion object {
        const val AVAILABILITY_FIELD = "availability"
        const val ABILITIES_FIELD = "abilities"
    }
}