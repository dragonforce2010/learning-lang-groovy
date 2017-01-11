@Grapes(
	@Grab(group='joda-time', module='joda-time', version='2.3')
)
import org.joda.time.format.DateTimeFormat

class DateTimeCategory {

	def static String createPrintableTime(dateTime) {
		def format = DateTimeFormat.forPattern('MM/dd/yyyy - hh:mm aa')
		dateTime.toString(format)
	}
}