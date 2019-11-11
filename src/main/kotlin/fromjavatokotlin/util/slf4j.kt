package fromjavatokotlin.util

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import kotlin.properties.ReadOnlyProperty
//import kotlin.reflect.KProperty
//
//val slf4j: ReadOnlyProperty<Any, Logger>
//    get() = LoggerDelegate()
//
//class LoggerDelegate : ReadOnlyProperty<Any, Logger> {
//    lateinit var logger: Logger
//
//    override fun getValue(thisRef: Any, property: KProperty<*>): Logger {
//        if (!::logger.isInitialized) {
//            val javaClass = thisRef.javaClass
//            logger = LoggerFactory.getLogger(
//                    if (javaClass.kotlin.isCompanion) javaClass.enclosingClass else javaClass
//            )
//        }
//        return logger
//    }
//}
