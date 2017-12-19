package wxmlabs.smshub

import org.junit.After
import org.junit.Before
import org.junit.Test

class TestSMSHub {
    @Before
    fun setUp() {
        SMSHub.addSender(object : SMSender("mock", 1.0, "") {
            override infix fun send(message: ShortMessage): Transfer {
                println(message.toString())
                return Transfer("1", message.mobile, this.getName())
            }
        })
    }

    @After
    fun tearDown() {
        SMSHub.removeSender("mock")
    }

    @Test
    fun testSendMessage() {
        val mobile = "13513282102"
        val message = "hello, world"
        val transfer = SMSHub send message to mobile
        SMSHub receipt transfer
    }
}
