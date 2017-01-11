@Grapes(
	@Grab(group='joda-time', module='joda-time', version='2.3')
)
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

def file = new File('../data/fells_loop.gpx')

def slurper = new XmlSlurper()
def gpx = slurper.parse(file)
println gpx.name
println ''
println gpx.desc
println ''
println gpx.@version
println gpx.@creator

gpx.rte.rtept.each {
	println it.@lat
	println it.@lon
	
	def printableTime = new DateTime(it.time.toString())
	def format = DateTimeFormat.forPattern('MM/dd/yyyy - hh:mm aa')
	println printableTime.toString(format)
}
