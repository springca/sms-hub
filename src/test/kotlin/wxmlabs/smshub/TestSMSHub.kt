package wxmlabs.smshub

import org.junit.Test

class TestSMSHub {
    @Test
    fun testSendMessage() {
        SMSHub send "hello, world" to "13513282102"
    }
}
