package fromjavatokotlin.controller

//import fromjavatokotlin.controller.dto.RobotDto
//import fromjavatokotlin.model.Robot
//
//fun Robot.toDto(robotNameTemplate: String) = RobotDto(
//        displayName = robotNameTemplate.format(code.replace('_', ' ')),
//        code = code,
//        mass = mass,
//        availability = availability,
//        abilities = abilities.map { it.toDto() }
//).apply {
//    id = this@toDto.id
//}
//
//fun Robot.Ability.toDto() = RobotDto.AbilityDto(code = code)
//
//fun RobotDto.AbilityDto.toModel() = Robot.Ability(code)