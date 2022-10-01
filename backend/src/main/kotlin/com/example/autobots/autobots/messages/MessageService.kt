package com.example.autobots.autobots.messages

import Message
import org.springframework.stereotype.Service

@Service
class MessageService(val db: MessageRepository) {
    fun findMessages(): List<Message> = db.findMessages()
    fun post(message: Message) = db.save(message.copy(id=null))
}
