package fromjavatokotlin.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.NotBlank

class RobotDto(
    @JsonProperty("name")
    val code: String? = null,
    val displayName: String? = null,
    val mass: Int? = null,
    val availability: Boolean? = null,
    val abilities: List<AbilityDto> = ArrayList()
) : IdentifiableDto() {

    override fun toString(): String {
        return "RobotDto(super=" + super.toString() + ", code=" + code + ", displayName=" + displayName + ", mass=" + mass + ", availability=" + availability + ", abilities=" + abilities + ")"
    }

    data class AbilityDto(@get:NotBlank val code: String? = null)
}