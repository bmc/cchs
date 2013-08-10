import java.io.{FileInputStream, FileOutputStream,
                InputStreamReader, OutputStreamWriter, Reader, Writer}
import java.nio.charset.{Charset, UnsupportedCharsetException}
import scala.util.{Failure, Success, Try}
import scala.annotation.tailrec

object ConvertCharset {
  private val USAGE = "Usage: ConvertCharset in-path in-encoding out-path out-encoding"

  private class UsageException extends Exception(USAGE)

  def main(args: Array[String]) = {
    val res = for { ok            <- checkUsage(args)
                    inputCharset  <- charsetForString(args(1))
                    in            <- openInput(args(0), inputCharset)
                    outputCharset <- charsetForString(args(3))
                    out           <- openOutput(args(2), outputCharset)
                    copied        <- copy(in, out) }
               yield s"Copied $copied character(s)"

    // Try.recover() returns (a) the contents of the Success, or, (b) if 
    // Failure, the result of a function mapped over the Failure's exception.
    val msg = res.recover {
      case e: UnsupportedCharsetException => s"Bad character set: ${e.getMessage}"
      case e: UsageException              => s"${e.getMessage}"
      case e: Throwable                   => s"${e.getClass.getName}: ${e.getMessage}"
    }

    println(msg.get)
  }

  private def checkUsage(args: Array[String]): Try[Boolean] = {
    if (args.length != 4)
      Failure(new UsageException)
    else
      Success(true)
  }

  private def charsetForString(name: String): Try[Charset] = {
    Try(Charset.forName(name))
  }

  private def openInput(path: String, charset: Charset): Try[Reader] = {
    Try(new InputStreamReader(new FileInputStream(path), charset))
  }

  private def openOutput(path: String, charset: Charset): Try[Writer] = {
    Try(new OutputStreamWriter(new FileOutputStream(path), charset))
  }

  private def copy(in: Reader, out: Writer): Try[Int] = {

    @tailrec def copyNext(count: Integer): Int = {
      val c = in.read
      if (c >= 0) {
        out.write(c)
        copyNext(count + 1)
      }
      else {
	in.close()
	out.close()
        count
      }
    }

    Try(copyNext(0))
  }
}
