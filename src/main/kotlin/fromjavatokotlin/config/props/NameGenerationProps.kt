package fromjavatokotlin.config.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("app")
@ConstructorBinding
class NameGenerationProps(val robotNameTemplate: String)