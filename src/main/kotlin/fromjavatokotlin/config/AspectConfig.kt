package fromjavatokotlin.config

import fromjavatokotlin.util.slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy
@Aspect
class AspectConfig {
    @Before("execution(* @org.springframework.stereotype.Service fromjavatokotlin..*.*(..))")
    fun log(joinPoint: JoinPoint) {
        log.debug(
            "{}#{} is called {}",
            joinPoint.target.javaClass,
            joinPoint.signature.name,
            joinParams(joinPoint.args)
        )
    }

    private fun joinParams(args: Array<Any?>): String {
        return if (args.isEmpty()) {
            ""
        } else args.asSequence()
            .map { obj: Any? -> obj.toString() }
            .joinToString(",", "with params [", "]")
    }

    companion object {
        private val log by slf4j
    }
}