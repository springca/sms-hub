package wxmlabs.smshub

data class ShortMessage(val mobile: String, val message: String)
data class Transfer(val code: String, val mobile: String, val sender: String)

object SMSHub {
    private val scheduler = Class.forName(System.getProperty("wxmlabs.smshub.scheduler", SimpleScheduler::class.java.name)).newInstance() as SMScheduler

    @JvmStatic
    infix fun addSender(sender: SMSender) {
        scheduler.addSender(sender)
    }

    @JvmStatic
    infix fun removeSender(name: String): SMSender {
        return scheduler.removeSender(name)
    }

    @JvmStatic
    infix fun assignFor(message: ShortMessage): SMSender {
        return scheduler.assignFor(message)
    }

    @JvmStatic
    infix fun receipt(transfer: Transfer) {
        scheduler.receipt(transfer)
    }

    @JvmStatic
    infix fun send(message: String): WrappedMessage {
        return WrappedMessage(message)
    }

    class WrappedMessage(private val message: String) {
        infix fun to(mobile: String): Transfer {
            val sm = ShortMessage(mobile, message)
            return SMSHub
                .assignFor(sm)
                .send(sm)
        }
    }

    class SimpleScheduler : SMScheduler() {
        private val senders: MutableMap<String, SMSender> = HashMap()
        override fun addSender(sender: SMSender) {
            senders.put(sender.getName(), sender)
        }

        override fun getSender(name: String): SMSender {
            return senders[name] ?: throw RuntimeException("No such sender $name")
        }

        override fun assignFor(message: ShortMessage): SMSender {
            return senders.values.iterator().next()
        }

        override fun receipt(transfer: Transfer) {
            // transfer.sender 权重+1
        }

        override fun removeSender(name: String): SMSender {
            return senders.remove(name) ?: throw RuntimeException("No such sender $name")
        }
    }
}
