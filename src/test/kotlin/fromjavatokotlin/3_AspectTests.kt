package fromjavatokotlin

import fromjavatokotlin.config.AspectConfig
import fromjavatokotlin.service.RobotService
import org.aspectj.lang.JoinPoint
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class `3_AspectTests` {

    @Test
    fun `logs aspect is not failed on empty params`() {
        val mock = mock(JoinPoint::class.java, RETURNS_DEEP_STUBS)
        `when`(mock.target.javaClass).thenReturn(RobotService::class.java as Class<Any>)
        `when`(mock.signature.name).thenReturn("testMethod")

        `when`(mock.args).thenReturn(emptyArray<Any>())

        AspectConfig().log(mock)
    }

    @Test
    fun `logs aspect is not failed on null params`() {
        val mock = mock(JoinPoint::class.java, RETURNS_DEEP_STUBS)
        `when`(mock.target.javaClass).thenReturn(RobotService::class.java as Class<Any>)
        `when`(mock.signature.name).thenReturn("testMethod")

        `when`(mock.args).thenReturn(arrayOf("abc", null))

        AspectConfig().log(mock)
    }

    @Test
    fun `logs aspect is not failed on different type params`() {
        val mock = mock(JoinPoint::class.java, RETURNS_DEEP_STUBS)
        `when`(mock.target.javaClass).thenReturn(RobotService::class.java as Class<Any>)
        `when`(mock.signature.name).thenReturn("testMethod")

        `when`(mock.args).thenReturn(arrayOf("abc", 42, 3.14))

        AspectConfig().log(mock)
    }
}