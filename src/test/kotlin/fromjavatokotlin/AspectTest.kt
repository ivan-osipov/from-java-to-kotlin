package fromjavatokotlin

import fromjavatokotlin.config.AspectConfig
import fromjavatokotlin.service.RobotService
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.aspectj.lang.JoinPoint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith

@Suppress("UNCHECKED_CAST")
@ExtendWith(MockKExtension::class)
class AspectTest {

    private lateinit var jointPoint: JoinPoint

    @BeforeEach
    fun setUp(@RelaxedMockK jointPoint: JoinPoint) {
        every {
            jointPoint.target.javaClass
        } returns RobotService::class.java as Class<Any>
        every {
            jointPoint.signature.name
        } returns "testMethod"

        this.jointPoint = jointPoint
    }

    @Test
    fun `logs aspect is not failed on empty params`() {
        assertDoesNotThrow {
            every {
                jointPoint.args
            } returns emptyArray<Any>()

            AspectConfig().log(jointPoint)
        }
    }

    @Test
    fun `logs aspect is not failed on null params`() {
        assertDoesNotThrow {
            every {
                jointPoint.args
            } returns arrayOf("abc", null)

            AspectConfig().log(jointPoint)
        }
    }

    @Test
    fun `logs aspect is not failed on different type params`() {
        assertDoesNotThrow {
            every {
                jointPoint.args
            } returns arrayOf("abc", arrayOf("abc", 42, 3.14))

            AspectConfig().log(jointPoint)
        }
    }
}