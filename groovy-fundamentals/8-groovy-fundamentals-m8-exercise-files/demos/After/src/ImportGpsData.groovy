@GrabConfig(systemClassLoader=true)
@Grapes([
	@Grab(
		group='org.codehaus.groovy.modules.http-builder', 
		module='http-builder', 
		version='0.6' ),
	 @Grab('mysql:mysql-connector-java:5.1.6'),
	 @Grab(group='joda-time', module='joda-time', version='2.3')
	])
import groovyx.net.http.RESTClient
import groovy.sql.Sql
import org.joda.time.DateTime

def file = new File('../data/fells_loop.gpx')

def slurper = new XmlSlurper()
def gpx = slurper.parse(file)

gpx.with {
	println name
	println ''

	println desc
	println ''	

	println attributes()['version']
	println attributes()['creator']
}

def forecastApi = new RESTClient('https://api.forecast.io/')
def credentialsFile = new File('credentials.groovy')
def configSlurper = new ConfigSlurper()
def credentials = configSlurper.parse(credentialsFile.toURL())
def sql = Sql.newInstance("jdbc:mysql://localhost:3306/gps", "root", "root", 
	"com.mysql.jdbc.Driver")

gpx.rte.rtept.each {
	println it.@lat
	println it.@lon

	def parser = new DateParser()
	println parser.parse(it.time.toString())

	def queryString = "forecast/${credentials.apiKey}/${it.@lat},${it.@lon},${it.time}"
	def response = forecastApi.get(path: queryString)

	println "${response.data.currently.summary}"
	println "${response.data.currently.temperature} degrees"

	def routepoints = sql.dataSet("routepoints")
	routepoints.add(latitude: it.@lat.toDouble(), longitude: it.@lon.toDouble(), 
		timestep: new DateTime(it.time.toString()).toDate(), 
		temperature: response.data.currently.temperature)
}

sql.close()
