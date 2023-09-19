import jakarta.mail.Authenticator
import jakarta.mail.Message
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

fun main(args: Array<String>) {

    val usuarioInfo = Files.readAllLines(
        Path("C:\\Users\\vitor\\Documents\\mailtest.txt")
    )[0].split(";")

    val props = Properties()
    props["mail.smtp.host"] = "smtp.gmail.com"
    props["mail.smtp.socketFactory.port"] = "465"
    props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.port"] = "465"

    val session = Session.getDefaultInstance(props, SMTPAuth(usuarioInfo[0], usuarioInfo[1]))
    val destinatario = InternetAddress.parse("ninaka2990@tenjb.com")
    val mimeMessage = MimeMessage(session)
    mimeMessage.setFrom(usuarioInfo[0])
    mimeMessage.setRecipients(Message.RecipientType.TO,destinatario)
    mimeMessage.subject = "Email que NÃO poderia ser uma reunião"
    mimeMessage.setText("sou uma boa pessoa, vou me inscrever no canal e não criar " +
            "reuniões que poderiam ser um e-mail =) =)")

    Transport.send(mimeMessage)

}

private class SMTPAuth(
    var username: String,
    var pwd: String
) : Authenticator() {

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(username, pwd)
    }
}