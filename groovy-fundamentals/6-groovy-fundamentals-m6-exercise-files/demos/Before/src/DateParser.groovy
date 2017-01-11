@Grapes(
	@Grab(group='joda-time', module='joda-time', version='2.3')
)
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateParser {
	def String parse(time) {
		if (time == null)
			throw new IllegalArgumentException()
			
		def printableTime = new DateTime(time)
		def format = DateTimeFormat.forPattern('MM/dd/yyyy - hh:mm aa')
		return printableTime.toString(format)
	}
}